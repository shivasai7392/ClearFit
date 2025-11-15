package com.practice.clearfit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CentreTiming extends BaseModel{
    @ManyToOne
    private Centre centre;
    private int openingHour;
    private int closingHour;
}
