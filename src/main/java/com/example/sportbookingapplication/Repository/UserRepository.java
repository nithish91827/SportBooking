package com.example.sportbookingapplication.Repository;

import com.example.sportbookingapplication.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    public Optional<User> findByUserName(String name);
    public Optional<User> findByEmailId(String email);

    public  User save(User user);
    Optional<User> getByID(UUID id);
}
