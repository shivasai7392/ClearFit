package com.practice.clearfit.repositories;

import com.practice.clearfit.models.Centre;
import com.practice.clearfit.models.WorkOutSlot;
import com.practice.clearfit.models.WorkOutType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface WorkOutSlotRepository extends JpaRepository<WorkOutSlot, Integer> {
    @Override
    <S extends WorkOutSlot> S save(S entity);

    List<WorkOutSlot> findByWorkoutTypeAndSlotDate(WorkOutType workoutType, Date slotDate);
    List<WorkOutSlot> findByWorkoutTypeAndCentreAndSlotDate(WorkOutType workoutType, Centre centre, Date slotDate);
    List<WorkOutSlot> findByWorkoutTypeAndCentreAndSlotDateAndStartHourAndEndHour(WorkOutType workoutType, Centre centre, LocalDate slotDate, int startHour, int endHour);
}
