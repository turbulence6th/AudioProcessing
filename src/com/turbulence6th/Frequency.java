package com.turbulence6th;

public enum Frequency {

    A4(440),
    B4(493.88),
    C5(523.25),
    D5(587.33),
    E5(659.25),
    F5(698.46),
    G5(783.99);

    private double freq;

    Frequency(double freq) {
        this.freq = freq;
    }

    public double getFreq() {
        return freq;
    }
}
