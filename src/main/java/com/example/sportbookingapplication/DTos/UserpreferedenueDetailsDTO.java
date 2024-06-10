package com.example.sportbookingapplication.DTos;

import com.example.sportbookingapplication.Models.User;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class UserpreferedenueDetailsDTO {

   private String email;
   private String preferedVenue;
   private String starttime;
   private String endtime;
}
