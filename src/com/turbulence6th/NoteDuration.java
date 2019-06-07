package com.turbulence6th;

public enum NoteDuration {

    SEMI_BREVE(4),     /* Whole Note     */
    MINIM(2),          /* Semi Note      */
    CROTCHET(1),       /* Quarter Note   */
    QUAVER(0.5),       /* Eighth Note    */
    SEMI_QUAVER(0.25); /* Sixteenth Note */

    private double duration;

    NoteDuration(double duration) {
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }
}
