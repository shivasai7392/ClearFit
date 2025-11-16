package com.practice.clearfit.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddWorkOutSlotRequestDto {
    private long centreId;
    private String workoutType;
    private int startTime; // in 24-hour format, e.g., 1300 for 1:00 PM
    private int endTime;   // in 24-hour format, e.g., 1400 for 2:00 PM
    private int seatsAvailable;
}
