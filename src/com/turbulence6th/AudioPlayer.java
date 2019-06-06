package com.turbulence6th;

import javax.sound.sampled.*;

public class AudioPlayer {

    private AudioFormat format;
    private DataLine.Info sourceInfo;

    public AudioPlayer() {
        this(8_000, 16, 1, true, false);

    }

    public AudioPlayer(float sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian) {
        format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        sourceInfo = new DataLine.Info(SourceDataLine.class, format);
    }

    public void play(byte[] data) {
        try {
            SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(sourceInfo);
            sourceLine.open(format);
            sourceLine.start();

            sourceLine.write(data, 0, data.length);
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}
