package com.practice.clearfit.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookWorkOutSlotRequestDto {
    private String workout;
    private long centerId;
    private int startHour;
    private int endHour;
    private long userId;
}
