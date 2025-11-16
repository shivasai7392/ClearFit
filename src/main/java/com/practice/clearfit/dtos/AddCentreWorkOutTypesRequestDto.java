package com.practice.clearfit.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddCentreWorkOutTypesRequestDto {
    private long centreId;
    private List<String> workOutTypes;
}
