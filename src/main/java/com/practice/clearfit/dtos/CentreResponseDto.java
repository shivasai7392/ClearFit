package com.practice.clearfit.dtos;

import com.practice.clearfit.models.CentreTiming;
import com.practice.clearfit.models.WorkOutType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CentreResponseDto {
    private String name;
    private String address;
    private List<String> workOutTypes;
}
