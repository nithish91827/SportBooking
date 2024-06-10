package com.example.sportbookingapplication.Repository;

import com.example.sportbookingapplication.Models.BookingDetails;
import com.example.sportbookingapplication.Models.City;
import com.example.sportbookingapplication.Models.sportVenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface Bookingrepositorydetails extends JpaRepository<BookingDetails, UUID> {
public List<BookingDetails> findAllByCityAndSportVenue(City city, sportVenue venue);
}
