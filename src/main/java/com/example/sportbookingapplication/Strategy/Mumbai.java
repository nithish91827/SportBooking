package com.example.sportbookingapplication.Strategy;

import org.springframework.stereotype.Component;

@Component
public class Mumbai implements Fare{
    @Override
    public int Farecalulation(long hours,int dayofweek) {

        if(dayofweek==6 ||dayofweek==7)
            return (int)hours*550;
        return (int)hours*350;


    }
}
