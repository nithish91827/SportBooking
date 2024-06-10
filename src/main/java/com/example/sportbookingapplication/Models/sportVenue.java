package com.example.sportbookingapplication.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Getter
@Setter
public class sportVenue extends BaseModel{
    private String venuename;

    @ManyToOne
   // @Cascade(value = CascadeType.ALL)
    private sport sport;

    @ManyToOne
   // @Cascade(value = CascadeType.ALL)
    private Location location;

    @Enumerated(EnumType.ORDINAL)
    private SportVenueAvailabilityStatus sportVenueAvailabilityStatus;


}
