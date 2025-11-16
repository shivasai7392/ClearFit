package com.practice.clearfit.controllers;

import com.practice.clearfit.dtos.BookWorkOutSlotRequestDto;
import com.practice.clearfit.dtos.BookingDto;
import com.practice.clearfit.exceptions.InvalidArgumentException;
import com.practice.clearfit.models.BookingStatus;
import com.practice.clearfit.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/status/{bookingId}")
    public BookingStatus getBookingStatus(@PathVariable ("bookingId") Long bookingId) throws InvalidArgumentException {
        return bookingService.getBookingStatus(bookingId);
    }

    @PostMapping("/bookSlot")
    public ResponseEntity<BookingDto> bookWorkOutSlot(@RequestBody BookWorkOutSlotRequestDto bookWorkOutSlotRequestDto) throws InvalidArgumentException {
        BookingDto bookingDto = bookingService.bookWorkoutSlot(bookWorkOutSlotRequestDto.getUserId(),
                bookWorkOutSlotRequestDto.getCenterId(),
                bookWorkOutSlotRequestDto.getWorkout(),
                bookWorkOutSlotRequestDto.getStartHour(),
                bookWorkOutSlotRequestDto.getEndHour());
        return new ResponseEntity<>(bookingDto, HttpStatus.OK);
    }

    @PostMapping("/cancel/{bookingId}")
    public ResponseEntity<Boolean> cancelBooking(@PathVariable ("bookingId") Long bookingId) throws InvalidArgumentException {
        boolean isCancelled = bookingService.cancelBooking(bookingId);
        return new ResponseEntity<>(isCancelled, HttpStatus.OK);
    }
}
