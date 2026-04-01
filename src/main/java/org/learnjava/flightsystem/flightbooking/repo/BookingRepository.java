package org.learnjava.flightsystem.flightbooking.repo;

import org.learnjava.flightsystem.flightbooking.dto.BookingDto;
import org.learnjava.flightsystem.flightbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {


}
