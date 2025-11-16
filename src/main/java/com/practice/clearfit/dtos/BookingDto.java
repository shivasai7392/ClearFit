package com.practice.clearfit.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDto {
    private long bookingId;
    private String centerName;
    private String workout;
    private String userName;
}
