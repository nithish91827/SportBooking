package com.example.sportbookingapplication.UserService;

import com.example.sportbookingapplication.DTos.UserDTO;
import com.example.sportbookingapplication.Models.User;
import com.example.sportbookingapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;
    public BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
 public  UserService(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    public User AddUser(UserDTO userDTO){
      User user=new User();
      user.setNameorEmailaddress(userDTO.getNameorEmailaddress());
      user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
      user.setMobile_number(Long.valueOf(userDTO.getMobilenumber()));
        return userRepository.save(user);
    }


}
