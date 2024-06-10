package com.example.sportbookingapplication.Repository;

import com.example.sportbookingapplication.Models.City;
import com.example.sportbookingapplication.Models.SportName;
import com.example.sportbookingapplication.Models.sport;
import com.example.sportbookingapplication.Models.sportVenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface BookngReposiryforsportsvenue extends JpaRepository<sportVenue,UUID> {
    @Query("SELECT sv FROM sportVenue sv " +
            "JOIN FETCH sv.sport s " +
            "JOIN FETCH sv.location l " +
            "WHERE s.sportname = :sportName AND l.city = :city")
    public List<sportVenue> getBySport_Sportname(@Param("sportName") SportName sportName,
    @Param("city") City city);


}
