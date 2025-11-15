package com.practice.clearfit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Centre extends BaseModel{
    private String name;
    private String address;
    private String phoneNumber;
    @OneToMany(mappedBy = "centre")
    private List<WorkOutSlot> workOutSlots;
    @OneToOne
    private User administrator;
    @OneToMany(mappedBy = "centre")
    private List<CentreTiming> centreTimings;
}
