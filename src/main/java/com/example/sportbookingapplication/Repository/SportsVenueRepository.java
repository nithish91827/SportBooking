package com.example.sportbookingapplication.Repository;

import com.example.sportbookingapplication.Models.sportVenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface SportsVenueRepository extends JpaRepository<sportVenue, UUID> {


}
