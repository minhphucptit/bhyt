package com.nminh.bhyt.service;

import com.nminh.bhyt.exception.BadRequestException;
import com.nminh.bhyt.model.User;
import com.nminh.bhyt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void signup(User request) {

        validatePassword(request.getPassword());
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
        if(optionalUser.isPresent()){
            throw new BadRequestException("Email đã tồn tại!");
        }
        userRepository.save(request);
    }

    public void signin(User request) {

        validatePassword(request.getPassword());
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
        if(!optionalUser.isPresent()) {
            throw new BadRequestException("Email không tồn tại!");
        }
        if(!userRepository.existsByUsernameAndPassword(request.getUsername(), request.getPassword())){
            throw new BadRequestException("Sai mật khẩu");
        }
    }

    private void validatePassword(String password) {

        if (password == null || password.trim().isEmpty()) {
            throw new BadRequestException("Mật khẩu không hợp lệ");
        }
        if (password.length() < 8) {
            throw new BadRequestException("Mật khẩu phải có ít nhất 8 ký tự");
        }
    }
}
