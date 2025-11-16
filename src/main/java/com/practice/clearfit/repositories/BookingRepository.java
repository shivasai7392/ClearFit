package com.practice.clearfit.repositories;

import com.practice.clearfit.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Override
    <S extends Booking> S save(S entity);
}
