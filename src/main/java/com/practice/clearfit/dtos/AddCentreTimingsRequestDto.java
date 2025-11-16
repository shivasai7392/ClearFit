package com.practice.clearfit.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCentreTimingsRequestDto {
    private int openingHour;
    private int closingHour;
    private long centreId;
}
