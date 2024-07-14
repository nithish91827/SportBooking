package com.example.sportbookingapplication.Bookingservice;

import com.example.sportbookingapplication.DTos.BookingDetailsDTO;
import com.example.sportbookingapplication.DTos.UserDetailsDTO;
import com.example.sportbookingapplication.DTos.UserpreferedenueDetailsDTO;
import com.example.sportbookingapplication.Payment.BestAvailablePaymentMode;
import com.example.sportbookingapplication.PaymentService.PaymentService;
import com.example.sportbookingapplication.Strategy.Calculateamount;
import com.example.sportbookingapplication.Strategy.Fare;
import com.example.sportbookingapplication.Models.*;
import com.example.sportbookingapplication.Repository.Bookingrepositorydetails;
import com.example.sportbookingapplication.Repository.BookngReposiryforsportsvenue;
import com.example.sportbookingapplication.Repository.SportsVenueRepository;
import com.example.sportbookingapplication.Repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookingService {
    @Autowired
   public BookngReposiryforsportsvenue bookngReposiryforsportsvenue;
    @Autowired
    public Bookingrepositorydetails bookingrepositorydetails;

    @Autowired
    public UserRepository userRepository;
    @Autowired
    private SportsVenueRepository sportsVenueRepository;
    @Autowired
    private Calculateamount calculateamount;
    @Autowired
    private PaymentService paymentService;

    public List<sportVenue> getsportlocationdetails(SportName name,City id) {

        List<sportVenue> venues = bookngReposiryforsportsvenue.getBySport_Sportname(name,id);
        for(sportVenue allvenues:venues)
                System.out.println(allvenues.getVenuename()+" "+allvenues.getSport().getSportname());
return venues;
    }
@Transactional
    public BookingDetailsDTO Bookvenue(UserpreferedenueDetailsDTO detailsDTO) throws ParseException, RazorpayException, JsonProcessingException {

        BookingDetails details=new BookingDetails();
       // UUID val=UUID.fromString(detailsDTO.getUuid());
       Optional<User> user=userRepository.findByUserName(detailsDTO.getEmail());
       details.setUser(user.get());
       Optional<sportVenue> venue=sportsVenueRepository.findById(UUID.fromString(detailsDTO.getPreferedVenue()));
       details.setSportVenue(venue.get());
       details.setCity(venue.get().getLocation().getCity());

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
      //  format.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        LocalDateTime starttime =LocalDateTime.parse(detailsDTO.getStarttime(), format);

        LocalDateTime endtime = LocalDateTime.parse(detailsDTO.getEndtime(), format);


        // Convert Instant to LocalDate


        List<BookingDetails> SamevenueDetails=bookingrepositorydetails.findAllBySportVenueAndCity(venue.get(),venue.get().getLocation().getCity());
//       Date Userprefferedstartdate=format.parse(detailsDTO.getStarttime());
//       Date Userprefferedenddate=format.parse(detailsDTO.getEndtime());

        for(BookingDetails booked:SamevenueDetails)
        {
           if(Checkifcolliding(starttime,endtime,booked))
              return null;
        }
details.setStarttime(starttime);
details.setEndTime(endtime);

details.setStatus(BookingStatus.Pending);
        Duration duration = Duration.between(starttime, endtime);

        // Extract the number of hours from the duration
        long hours = Math.abs(duration.toHours());
        Fare f=calculateamount.getprice(venue.get().getLocation().getCity());
        int amount=f.Farecalulation(hours,starttime.getDayOfWeek().ordinal());
        details.setAmount(amount);
        BookingDetails pendingbookingdetails=bookingrepositorydetails.save(details);
     String link = paymentService.generatepaymentlink(new UserDetailsDTO(user.get().getEmailId(), pendingbookingdetails.getID().toString(),amount));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(link);

        String shortlink=jsonNode.get("short_url").asText();
            return new BookingDetailsDTO(pendingbookingdetails,shortlink);

    }

    boolean Checkifcolliding(LocalDateTime starttime,LocalDateTime endtime,BookingDetails booking){

        if (starttime.equals(booking.getStarttime()) ||  endtime.equals(booking.getEndTime())) {
            return true;
        }
        if(starttime.isAfter(booking.getStarttime()) && starttime.isBefore(booking.getEndTime()))
                return true;
        if(endtime.isAfter(booking.getStarttime()) && endtime.isBefore(booking.getEndTime()))
            return true;
        if(starttime.isBefore(booking.getStarttime()) && endtime.isAfter(booking.getEndTime()))
             return true;

        return  false;
    }


}
//    Calendar cal = Calendar.getInstance();
//        cal.set(2022, Calendar.APRIL, 25, 14, 30); // Year, month, day, hour, minute
//                Date specificDateTime = cal.getTime();
//                details.setDateTimeField(specificDateTime);
//                cal.set(2022, Calendar.APRIL, 25, 15, 30); // Year, month, day, hour, minute
//                specificDateTime = cal.getTime();