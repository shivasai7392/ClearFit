package com.practice.clearfit.controllers;

import com.practice.clearfit.dtos.*;
import com.practice.clearfit.models.Centre;
import com.practice.clearfit.models.WorkOutType;
import com.practice.clearfit.services.CentreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centre")
public class CentreController {
    private final CentreService centreService;
    public CentreController(CentreService centreService) {
        this.centreService = centreService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<CentreResponseDto> getCentre(@PathVariable Long id) {
        Centre centre = this.centreService.getCentre(id);
        CentreResponseDto centreResponseDto = new CentreResponseDto();
        centreResponseDto.setName(centre.getName());
        centreResponseDto.setAddress(centre.getAddress());
        List<String> workOutTypeStrings = centre.getWorkOutTypes().stream()
                .map(Enum::name)
                .toList();
        centreResponseDto.setWorkOutTypes(workOutTypeStrings);
        return new ResponseEntity<>(centreResponseDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AddCentreResponseDto> addCentre(@RequestBody AddCentreRequestDto addCentreRequestDto) {
        Centre centre = this.centreService.addCentre(addCentreRequestDto.getName(), addCentreRequestDto.getAddress());
        AddCentreResponseDto addCentreResponseDto = new AddCentreResponseDto();
        addCentreResponseDto.setName(centre.getName());
        addCentreResponseDto.setAddress(centre.getAddress());
        addCentreResponseDto.setAdministratorId(centre.getAdministrator().getId());
        return new ResponseEntity<>(addCentreResponseDto, HttpStatus.OK);
    }
    @PostMapping("/add-timings")
    public ResponseEntity<AddCentreTimingsResponseDto> addCentreTimings(@RequestBody AddCentreTimingsRequestDto addCentreTimingsRequestDto) {
        String centreName = this.centreService.addCentreTimings(
                addCentreTimingsRequestDto.getCentreId(),
                addCentreTimingsRequestDto.getOpeningHour(),
                addCentreTimingsRequestDto.getClosingHour()
        );
        AddCentreTimingsResponseDto addCentreTimingsResponseDto = new AddCentreTimingsResponseDto();
        addCentreTimingsResponseDto.setCentreName(centreName);
        return new ResponseEntity<>(addCentreTimingsResponseDto, HttpStatus.OK);
    }

    @PostMapping("/add-workout-types")
    public ResponseEntity<AddCentreWorkOutTypesResponseDto> addCentreWorkOutTypes(@RequestBody AddCentreWorkOutTypesRequestDto addCentreWorkOutTypesRequestDto) {
        String centreName = this.centreService.addCentreWorkOutTypes(
                addCentreWorkOutTypesRequestDto.getCentreId(),
                addCentreWorkOutTypesRequestDto.getWorkOutTypes()
        );
        AddCentreWorkOutTypesResponseDto addCentreWorkOutTypesResponseDto = new AddCentreWorkOutTypesResponseDto();
        addCentreWorkOutTypesResponseDto.setCentreName(centreName);
        return new ResponseEntity<>(addCentreWorkOutTypesResponseDto, HttpStatus.OK);
    }
}
