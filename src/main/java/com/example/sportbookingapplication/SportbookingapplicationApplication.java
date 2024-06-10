package com.example.sportbookingapplication;

import com.example.sportbookingapplication.Models.*;
import com.example.sportbookingapplication.Repository.SportRepository;
import com.example.sportbookingapplication.Repository.SportsVenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class SportbookingapplicationApplication implements CommandLineRunner {

    @Autowired
    private SportsVenueRepository sportsVenueRepository;
    @Autowired
    private SportRepository sportRepository;

    public static void main(String[] args) {
        SpringApplication.run(SportbookingapplicationApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        sport s = new sport();
//        s.setDescription("Cricket is an art and Sachin is an artist");
//        s.setSportname(SportName.Cricket);
//        s.setSportstatus(Sportstatus.AVAILABLE);
//
//        sport s1 = new sport();
//        s1.setDescription("Badminton is for fitness and Olympics");
//        s1.setSportname(SportName.Badminton);
//        s1.setSportstatus(Sportstatus.AVAILABLE);
//
//// Save sport entities
//        sport cricket = sportRepository.save(s);
//        sport badminton = sportRepository.save(s1);
//
//// Create sport venues
//        sportVenue venue1 = new sportVenue();
//        venue1.setSport(cricket);
//        venue1.setVenuename("Nithish cricket academy");
//
//        sportVenue venue2 = new sportVenue();
//        venue2.setSport(cricket);
//        venue2.setVenuename("Sachin cricket academy");
//
//        sportVenue venue3 = new sportVenue();
//        venue3.setSport(badminton);
//        venue3.setVenuename("Nithish badminton academy");
//
//        sportVenue venue4 = new sportVenue();
//        venue4.setSport(badminton);
//        venue4.setVenuename("Sachin badminton academy");
//
//// Save sport venues
//        sportVenue nithish = sportsVenueRepository.save(venue1);
//        sportVenue sachin = sportsVenueRepository.save(venue2);
//        sportVenue nithish1 = sportsVenueRepository.save(venue3);
//        sportVenue sachin1 = sportsVenueRepository.save(venue4);
//
//// Print details
//        System.out.println(nithish.getVenuename() + " " + nithish.getID());
//        System.out.println(sachin.getVenuename() + " " + sachin.getID());
//        System.out.println(nithish1.getVenuename() + " " + nithish1.getID());
//        System.out.println(sachin1.getVenuename() + " " + sachin1.getID());

    }
}
