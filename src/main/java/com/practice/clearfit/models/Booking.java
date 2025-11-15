package com.practice.clearfit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    @ManyToOne
    private User trainee;
    @ManyToOne
    private WorkOutSlot workOutSlot;
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
}
