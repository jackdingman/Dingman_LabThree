package org.example;

public class IllegalAgeException extends Exception {

    private final int guestAge;

    public IllegalAgeException(int guestAge) {
        super("Invalid Age: "+ guestAge + ". Must be 0 to integer.MAX");
        this.guestAge = guestAge;
    }

    public int getNights() {
        return guestAge;
    }
}

