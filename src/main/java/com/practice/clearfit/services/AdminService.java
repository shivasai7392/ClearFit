package com.practice.clearfit.services;

import com.practice.clearfit.exceptions.InvalidArgumentException;
import com.practice.clearfit.models.Centre;
import com.practice.clearfit.models.WorkOutSlot;
import com.practice.clearfit.models.WorkOutType;
import com.practice.clearfit.repositories.CentreRepository;
import com.practice.clearfit.repositories.WorkOutSlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final CentreRepository centreRepository;
    private final WorkOutSlotRepository workOutSlotRepository;

    public AdminService(CentreRepository centreRepository, WorkOutSlotRepository workOutSlotRepository) {
        this.centreRepository = centreRepository;
        this.workOutSlotRepository = workOutSlotRepository;
    }

    public WorkOutSlot addWorkOutSlot(long centreId, int startHour, int endHour, int seatsAvailable, String workoutType) throws InvalidArgumentException {
        Optional<Centre> optionalCentre = centreRepository.findById(centreId);
        if (optionalCentre.isEmpty()) {
            throw new InvalidArgumentException("Centre not found");
        }
        Centre centre = optionalCentre.get();

        List<WorkOutSlot> workOutSlots = centre.getWorkOutSlots();
        LocalDate date = LocalDate.now(ZoneId.systemDefault());
        for (WorkOutSlot workOutSlot : workOutSlots) {
            LocalDate slotDate = workOutSlot.getSlotDate();
            if (slotDate == date && workOutSlot.getStartHour() < endHour && startHour < workOutSlot.getEndHour()) {
                throw new InvalidArgumentException("Workout slot timing overlaps with existing slot");
            }
        }

        WorkOutType workOutType = WorkOutType.valueOf(workoutType);
        WorkOutSlot workOutSlot = new WorkOutSlot();
        workOutSlot.setCentre(centre);
        workOutSlot.setStartHour(startHour);
        workOutSlot.setEndHour(endHour);
        workOutSlot.setSeatsAvailable(seatsAvailable);
        workOutSlot.setWorkoutType(workOutType);

        workOutSlotRepository.save(workOutSlot);

        // Implementation to add a workout slot
        return workOutSlot;
    }
}
