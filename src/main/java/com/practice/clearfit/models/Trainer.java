package com.practice.clearfit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Trainer extends User{
    @OneToMany(mappedBy = "trainer")
    private List<WorkOutSlot> workOutSlots;
}
