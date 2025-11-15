package com.practice.clearfit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.ORDINAL)
    private UserType userType;
    @OneToMany(mappedBy = "trainee")
    private List<Booking> bookings;
}
