package org.learnjava.flightsystem.flightbooking.exception;

public class BookingApiException extends RuntimeException
{
    public BookingApiException(String message){
        super(message);
    }
}
