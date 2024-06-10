package com.example.sportbookingapplication.Strategy;

import com.example.sportbookingapplication.Models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Calculateamount {

    @Autowired
private Mumbai mumbai;
@Autowired
private  Kurnool kurnool;
    public Fare getprice(City city){

        if(city.ordinal()==City.Mumbai.ordinal())
            return mumbai;

        if(city.ordinal()==City.kurnool.ordinal())
            return kurnool;
        return null;

    }


}
