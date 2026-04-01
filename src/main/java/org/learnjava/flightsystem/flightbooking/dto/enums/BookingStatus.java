package org.learnjava.flightsystem.flightbooking.dto.enums;


public enum BookingStatus {
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    REJECTED("Rejected");


    private String status;

    BookingStatus(String status) {
        this.status = status;
    }
}
