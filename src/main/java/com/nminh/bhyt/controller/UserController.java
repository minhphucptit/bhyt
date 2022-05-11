package com.nminh.bhyt.controller;

import com.nminh.bhyt.exception.BadRequestException;
import com.nminh.bhyt.ResponseDto;
import com.nminh.bhyt.model.User;
import com.nminh.bhyt.repository.UserRepository;
import com.nminh.bhyt.service.UserService;
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
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping (value = "/sign-up")
    ResponseEntity<ResponseDto<User>> createUser(@RequestBody User request){
        userService.signup(request);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("R_200","Create Successfully!"));
    }

    @PostMapping(value = "/sign-in")
    ResponseEntity<ResponseDto<User>> getUser(@RequestBody User request){
        userService.signin(request);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("200","Success!"));
    }
}
