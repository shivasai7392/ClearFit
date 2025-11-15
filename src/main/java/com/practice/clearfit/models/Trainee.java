package com.practice.clearfit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Trainee extends User{
    @OneToMany(mappedBy = "trainee")
    private List<Booking> bookings;
}
