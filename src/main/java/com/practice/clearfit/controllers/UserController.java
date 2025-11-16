package com.practice.clearfit.controllers;

import com.practice.clearfit.dtos.*;
import com.practice.clearfit.exceptions.InvalidArgumentException;
import com.practice.clearfit.models.User;
import com.practice.clearfit.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("id") Long userId){
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody RegisterUserRequestDto requestDto) throws InvalidArgumentException {
        User user = this.userService.signUp(requestDto.getUsername(), requestDto.getEmailId(), requestDto.getPassword(), requestDto.getConfirmPassword());

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmailId(user.getEmail());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto requestDto) throws InvalidArgumentException {
        return null;
    }

    public ResponseEntity<GetWorkoutsAvailabilityResponseDto> getWorkoutsAvailability(@RequestBody GetWorkoutsAvailabilityRequestDto getWorkoutsAvailabilityRequestDto) {
        return null;
    }


}
