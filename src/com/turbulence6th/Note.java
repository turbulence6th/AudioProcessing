package com.turbulence6th;

public class Note {

    private NoteDuration duration;
    private NoteFrequency[] frequency;

    public Note(NoteDuration duration, NoteFrequency... frequency) {
        this.frequency = frequency;
        this.duration = duration;
    }

    public NoteFrequency[] getFrequency() {
        return frequency;
    }

    public NoteDuration getDuration() {
        return duration;
    }
}
