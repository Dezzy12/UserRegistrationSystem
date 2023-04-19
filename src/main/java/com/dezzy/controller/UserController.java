package com.dezzy.controller;

import com.dezzy.dto.UserDTO;
import com.dezzy.exception.ResourceNotFoundException;
import com.dezzy.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {
    public static final Logger logger =
            LoggerFactory.getLogger(UserController.class);

    @Autowired
    private final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userRepository.findAll();
        return  new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        userRepository.save(user);
        return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id){
        Optional<UserDTO> userData = userRepository.findById(id);
        if (!userData.isPresent()){
            throw new ResourceNotFoundException("User with id "+ id + " not found","/api/user");
        }
        return userData.map(user ->
                new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO user){
        Optional<UserDTO> currentUser = userRepository.findById(id);
        if(currentUser.isPresent()){
            UserDTO newUser = currentUser.get();

            newUser.setName(user.getName());
            newUser.setAddress(user.getAddress());
            newUser.setEmail(user.getEmail());

            return new ResponseEntity<>(userRepository.save(newUser),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
    }
}
