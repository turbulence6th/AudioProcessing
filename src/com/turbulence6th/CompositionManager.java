package com.turbulence6th;

import java.util.Arrays;
import java.util.List;

public class CompositionManager {

    public Composition twinkleTwinkleLittleStar() {
        Composition composition = new Composition();
        composition.setTempo(100);
        composition.setVolume(10_000);

        List<Note> noteList = Arrays.asList(
                new Note(NoteDuration.CROTCHET, NoteFrequency.C4), new Note(NoteDuration.CROTCHET, NoteFrequency.C4),
                new Note(NoteDuration.CROTCHET, NoteFrequency.G4), new Note(NoteDuration.CROTCHET, NoteFrequency.G4),
                new Note(NoteDuration.CROTCHET, NoteFrequency.A4), new Note(NoteDuration.CROTCHET, NoteFrequency.A4),
                new Note(NoteDuration.MINIM, NoteFrequency.G4),
                new Note(NoteDuration.CROTCHET, NoteFrequency.F4), new Note(NoteDuration.CROTCHET, NoteFrequency.F4),
                new Note(NoteDuration.CROTCHET, NoteFrequency.E4), new Note(NoteDuration.CROTCHET, NoteFrequency.E4),
                new Note(NoteDuration.CROTCHET, NoteFrequency.D4), new Note(NoteDuration.CROTCHET, NoteFrequency.D4),
                new Note(NoteDuration.MINIM, NoteFrequency.C4)
        );

        composition.setNoteList(noteList);
        return composition;
    }
}
