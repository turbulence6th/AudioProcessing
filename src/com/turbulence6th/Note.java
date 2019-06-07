package com.turbulence6th;

public enum Note {

    C4(261.63),
    D4(293.66),
    E4(329.63),
    F4(349.23),
    G4(392.00),
    A4(440),
    B4(493.88),
    C5(523.25),
    D5(587.33),
    E5(659.25),
    F5(698.46),
    G5(783.99);

    private double freq;

    Note(double freq) {
        this.freq = freq;
    }

    public double getFreq() {
        return freq;
    }
}
