package org.example;

public class NightReservationException extends Exception {

    private final int nights;

    public NightReservationException(int nights) {
        super("Invalid number of nights reserved: " + nights +
                ". Reservation must be between 1 and 14 nights.");
        this.nights = nights;
    }

    public int getNights() {
        return nights;
    }
}

