package com.practice.clearfit.services;

import com.practice.clearfit.dtos.UserDto;
import com.practice.clearfit.exceptions.InvalidArgumentException;
import com.practice.clearfit.models.*;
import com.practice.clearfit.repositories.CentreRepository;
import com.practice.clearfit.repositories.UserRepository;
import com.practice.clearfit.repositories.WorkOutSlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WorkOutSlotRepository workOutSlotRepository;
    private final CentreRepository centreRepository;

    public UserService(UserRepository userRepository,
                       WorkOutSlotRepository workOutSlotRepository,
                       CentreRepository centreRepository) {
        this.userRepository = userRepository;
        this.workOutSlotRepository = workOutSlotRepository;
        this.centreRepository = centreRepository;
    }

    public UserDto getUserDetails(long userid) throws InvalidArgumentException {
        Optional<User> optionalUser = this.userRepository.findById(userid);
        if(optionalUser.isEmpty()){
            throw new InvalidArgumentException("User not found with id: " + userid);
        }
        User user = optionalUser.get();

        UserDto userDto = new UserDto();
        userDto.setEmailId(user.getEmail());
        userDto.setName(user.getName());

        return userDto;
    }

    public User signUp(String name, String email, String password, String confirmPassword) throws InvalidArgumentException {
        if (!password.equals(confirmPassword)) {
            throw new InvalidArgumentException("Password and Confirm Password do not match");
        }

        User user = new Trainee();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setUserType(UserType.TRAINEE);
        return userRepository.save(user);
    }

    public List<WorkOutSlot> getAvailableWorkOutSlots(String workout, long centreId) throws InvalidArgumentException {
        Optional<Centre> optionalCentre = centreRepository.findById(centreId);
        List<WorkOutSlot> workOutSlots;
        if (optionalCentre.isEmpty()) {
            workOutSlots = workOutSlotRepository.findByWorkoutTypeAndSlotDate(WorkOutType.valueOf(workout), new java.util.Date());
        }
        else {
            Centre ce = optionalCentre.get();
            workOutSlots = workOutSlotRepository.findByWorkoutTypeAndCentreAndSlotDate(WorkOutType.valueOf(workout), ce, new java.util.Date());
        }
        return workOutSlots;
    }


}
