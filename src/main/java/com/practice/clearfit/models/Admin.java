package com.practice.clearfit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Admin extends User{
    @OneToOne
    private Centre centre;
    private String passCode;
}
