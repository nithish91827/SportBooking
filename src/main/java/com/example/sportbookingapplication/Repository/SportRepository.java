package com.example.sportbookingapplication.Repository;

import com.example.sportbookingapplication.Models.sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SportRepository extends JpaRepository<sport, UUID> {
}
