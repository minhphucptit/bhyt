package com.nminh.bhyt.controller;

import com.nminh.bhyt.exception.BadRequestException;
import com.nminh.bhyt.ResponseDto;
import com.nminh.bhyt.model.User;
import com.nminh.bhyt.repository.UserRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
@Transactional
public class UserApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MapperFacade mapperFacade;

    @PostMapping (value = "/sign-up")
    ResponseEntity<ResponseDto<User>> createUser(@RequestBody User request){
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if(optionalUser.isPresent()){
            throw new BadRequestException("User exits!");
        }

        User user = userRepository.save(request);

        ResponseDto<User> responseDto = new ResponseDto<>("R_200","Create Successfully!",user);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping(value = "/sign-in")
    ResponseEntity<ResponseDto<User>> getUser(@RequestBody User request){
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if(!optionalUser.isPresent()) {
            throw new BadRequestException("Username not exits!");
        }

        if(!userRepository.existsByUsernameAndPassword(request.getUsername(), request.getPassword())){
            throw new BadRequestException("Wrong password!");
        }

        ResponseDto<User> responseDto = new ResponseDto<>("200","Success!",optionalUser.get());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping(value = "/delete/{id}")
    ResponseEntity<ResponseDto<User>> deleteUser(@PathVariable Integer id){
        Optional<User> optionalUser = userRepository.findById(id);

        if(!optionalUser.isPresent()) {
            throw new BadRequestException("User not exits!");
        }

        userRepository.delete(optionalUser.get());

        ResponseDto<User> responseDto = new ResponseDto<>("200","Success!");

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
