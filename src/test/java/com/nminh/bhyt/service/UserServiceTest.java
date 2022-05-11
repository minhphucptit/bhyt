package com.nminh.bhyt.service;

import com.nminh.bhyt.exception.BadRequestException;
import com.nminh.bhyt.model.User;
import com.nminh.bhyt.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void sigup_WhenHaveValidUser_ReturnSuccessfull() {

        User user = new User();
        user.setUsername("admin@gmail.com");
        user.setPassword("password");

        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        userService.signup(user);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test(expected = BadRequestException.class)
    public void sigup_WhenHaveExistEmail_ReturnThrowException() {

        User user = new User();
        user.setUsername("admin@gmail.com");
        user.setPassword("password");

        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));
        userService.signup(user);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test(expected = BadRequestException.class)
    public void sigup_WhenHavePasswordNull_ReturnBadException() {

        User user = new User();
        user.setUsername("admin@gmail.com");
        user.setPassword(null);

        userService.signup(user);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test(expected = BadRequestException.class)
    public void sigup_WhenHaveShortPassword_ReturnBadException() {

        User user = new User();
        user.setUsername("admin@gmail.com");
        user.setPassword("123");
        userService.signup(user);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void sigin_WhenHaveValidUser_ReturnSuccessfull() {

        User user = new User();
        user.setUsername("admin@gmail.com");
        user.setPassword("password");

        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));
        when(userRepository.existsByUsernameAndPassword(any(), any())).thenReturn(true);
        userService.signin(user);
    }

    @Test(expected = BadRequestException.class)
    public void sigin_WhenHaveInvalidEmail_ReturnBadException() {

        User user = new User();
        user.setUsername("admin@gmail.com");
        user.setPassword("password");

        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        userService.signin(user);
    }

    @Test(expected = BadRequestException.class)
    public void sigin_WhenHaveWrongPassword_ReturnBadException() {

        User user = new User();
        user.setUsername("admin@gmail.com");
        user.setPassword("password");

        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));
        when(userRepository.existsByUsernameAndPassword(any(), any())).thenReturn(false);
        userService.signin(user);
    }

    @Test(expected = BadRequestException.class)
    public void sigin_WhenHavePasswordNull_ReturnBadException() {

        User user = new User();
        user.setUsername("admin@gmail.com");
        user.setPassword(null);

        userService.signin(user);
    }

    @Test(expected = BadRequestException.class)
    public void sigin_WhenHaveShortPassword_ReturnBadException() {

        User user = new User();
        user.setUsername("admin@gmail.com");
        user.setPassword("123");

        userService.signin(user);
    }
}
