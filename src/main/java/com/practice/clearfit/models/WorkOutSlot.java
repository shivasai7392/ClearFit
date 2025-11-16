package com.practice.clearfit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class WorkOutSlot extends BaseModel{
    private int seatsAvailable;
    @Enumerated(EnumType.ORDINAL)
    private WorkOutType workoutType;
    private int startHour;
    private int endHour;
    @CreatedDate
    private LocalDate slotDate;
    @ManyToOne
    private Centre centre;
    @OneToMany
    private List<User> interestedUsers;
    @ManyToOne
    private User trainer;
}
