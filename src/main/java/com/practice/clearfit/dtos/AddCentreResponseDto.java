package com.practice.clearfit.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCentreResponseDto {
    private String name;
    private String address;
    private long administratorId;
}
