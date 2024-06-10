package com.example.sportbookingapplication.COntroller;

import com.example.sportbookingapplication.DTos.UserDTO;
import com.example.sportbookingapplication.Models.User;
import com.example.sportbookingapplication.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Sports")
public class UserController {

    private UserService userService;
@Autowired
   public UserController(UserService userService){
        this.userService=userService;
    }
@PostMapping("/Signup")
    public User Signup(@RequestBody UserDTO userDTO){

    return userService.AddUser(userDTO);
}



}
