package com.practice.clearfit.controllers;

import com.practice.clearfit.dtos.AddWorkOutSlotRequestDto;
import com.practice.clearfit.dtos.AddWorkOutSlotResponseDto;
import com.practice.clearfit.exceptions.InvalidArgumentException;
import com.practice.clearfit.models.WorkOutSlot;
import com.practice.clearfit.models.WorkOutType;
import com.practice.clearfit.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/add-workout-slot")
    public ResponseEntity<AddWorkOutSlotResponseDto> addWorkOutSlot(AddWorkOutSlotRequestDto addWorkOutSlotRequestDto) throws InvalidArgumentException {

        WorkOutSlot workOutSlot = this.adminService.addWorkOutSlot(
                addWorkOutSlotRequestDto.getCentreId(),
                addWorkOutSlotRequestDto.getStartTime(),
                addWorkOutSlotRequestDto.getEndTime(),
                addWorkOutSlotRequestDto.getSeatsAvailable(),
                addWorkOutSlotRequestDto.getWorkoutType()
        );

        AddWorkOutSlotResponseDto responseDto = new AddWorkOutSlotResponseDto();
        responseDto.setCentreName(workOutSlot.getCentre().getName());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
