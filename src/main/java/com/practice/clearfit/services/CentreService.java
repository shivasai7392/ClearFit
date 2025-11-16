package com.practice.clearfit.services;

import com.practice.clearfit.dtos.AddCentreRequestDto;
import com.practice.clearfit.models.*;
import com.practice.clearfit.repositories.CentreRepository;
import com.practice.clearfit.repositories.CentreTimingRepository;
import com.practice.clearfit.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CentreService {
    private final CentreRepository centreRepository;
    private final CentreTimingRepository centreTimingRepository;
    private final UserRepository userRepository;

    public CentreService(CentreRepository centreRepository,
                         CentreTimingRepository centreTimingRepository,
                         UserRepository userRepository) {
        this.centreRepository = centreRepository;
        this.centreTimingRepository = centreTimingRepository;
        this.userRepository = userRepository;
    }
    public Centre addCentre(String name, String address) {
        User administrator = new Admin();
        administrator.setEmail("administrator");
        administrator.setPassword("administrator");
        administrator.setPhoneNumber("8919072521");
        administrator.setUserType(UserType.ADMIN);
        administrator.setName("name");
        User saved_administrator = userRepository.save(administrator);


        Centre centre = new Centre();
        centre.setName(name);
        centre.setAddress(address);
        centre.setAdministrator(saved_administrator); // Administrator to be set later
        return centreRepository.save(centre);
    }

    public String addCentreTimings(long centreId, int openingHour, int ClosiingHour) {
        Optional<Centre> optionalCentre = centreRepository.findById(centreId);
        if (optionalCentre.isEmpty()) {
            throw new RuntimeException("Centre not found");
        }
        Centre centre = optionalCentre.get();
        CentreTiming centreTiming = new CentreTiming();
        centreTiming.setOpeningHour(openingHour);
        centreTiming.setClosingHour(ClosiingHour);
        centreTiming.setCentre(centre);
        centre.getCentreTimings().add(centreTiming);
        centreTimingRepository.save(centreTiming);
        return centre.getName();
    }

    public String addCentreWorkOutTypes(long centreId, List<String> workOutTypes) {
        Collection<WorkOutType> workOutTypesList = new ArrayList<>();

        Optional<Centre> optionalCentre = centreRepository.findById(centreId);
        if (optionalCentre.isEmpty()) {
            throw new RuntimeException("Centre not found");
        }
        Centre centre = optionalCentre.get();
        if (centre.getWorkOutTypes() == null) {
            centre.setWorkOutTypes(new ArrayList<>());
        }
        for (String workOutTypeStr : workOutTypes) {
            WorkOutType workOutType = WorkOutType.valueOf(workOutTypeStr);
            centre.getWorkOutTypes().add(workOutType);
        }
        centreRepository.save(centre);
        return centre.getName();
    }

    public Centre getCentre(Long id) {
        Optional<Centre> optionalCentre = centreRepository.findById(id);
        if (optionalCentre.isEmpty()) {
            throw new RuntimeException("Centre not found");
        }
        return optionalCentre.get();
    }
}
