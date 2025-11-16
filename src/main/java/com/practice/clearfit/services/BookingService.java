package com.practice.clearfit.services;

import com.practice.clearfit.dtos.BookingDto;
import com.practice.clearfit.exceptions.InvalidArgumentException;
import com.practice.clearfit.models.*;
import com.practice.clearfit.repositories.BookingRepository;
import com.practice.clearfit.repositories.CentreRepository;
import com.practice.clearfit.repositories.UserRepository;
import com.practice.clearfit.repositories.WorkOutSlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final CentreRepository centreRepository;
    private final WorkOutSlotRepository workoutSlotRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, CentreRepository centreRepository, WorkOutSlotRepository workoutSlotRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.centreRepository = centreRepository;
        this.workoutSlotRepository = workoutSlotRepository;
    }

    public BookingDto bookWorkoutSlot(long userId, long centreId, String workoutType, int startHour, int endHour) throws InvalidArgumentException {
        Optional<User> optionalUser = this.userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new InvalidArgumentException("User not found with id: " + userId);
        }
        Optional<Centre> optionalCentre = centreRepository.findById(centreId);
        if (optionalCentre.isEmpty()) {
            throw new InvalidArgumentException("Centre not found");
        }
        Centre centre = optionalCentre.get();
        WorkOutSlot workOutSlot = workoutSlotRepository.findByWorkoutTypeAndCentreAndSlotDateAndStartHourAndEndHour(
                WorkOutType.valueOf(workoutType),
                centre,
                LocalDate.now(),
                startHour,
                endHour
        ).stream().findFirst().orElseThrow(() -> new InvalidArgumentException("Workout slot not found"));

        if (workOutSlot.getSeatsAvailable() == 0) {
            throw new RuntimeException("No seats available for the selected workout slot");
        }
        workOutSlot.setSeatsAvailable(workOutSlot.getSeatsAvailable() - 1);
        workoutSlotRepository.save(workOutSlot);

        User user = optionalUser.get();

        Booking booking = new Booking();
        booking.setTrainee(user);
        booking.setWorkOutSlot(workOutSlot);
        booking.setBookingStatus(BookingStatus.BOOKED);
        Booking savedBooking = bookingRepository.save(booking);
        BookingDto bookingDto = new BookingDto();
        bookingDto.setBookingId(savedBooking.getId());
        bookingDto.setUserName(user.getName());
        bookingDto.setWorkout(workoutType);
        bookingDto.setCenterName(centre.getName());
        return bookingDto;
    }

    public boolean cancelBooking(long bookingId) throws InvalidArgumentException {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new InvalidArgumentException("Booking not found with id: " + bookingId);
        }
        Booking booking = optionalBooking.get();
        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            throw new InvalidArgumentException("Booking is already cancelled");
        }
        booking.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        WorkOutSlot workOutSlot = booking.getWorkOutSlot();
        workOutSlot.setSeatsAvailable(workOutSlot.getSeatsAvailable() + 1);
        workoutSlotRepository.save(workOutSlot);
        return true;
    }

    public BookingStatus getBookingStatus(long bookingId) throws InvalidArgumentException {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new InvalidArgumentException("Booking not found with id: " + bookingId);
        }
        return optionalBooking.get().getBookingStatus();
    }
}
