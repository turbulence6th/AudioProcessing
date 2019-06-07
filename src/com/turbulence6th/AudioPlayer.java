package com.turbulence6th;

import javax.sound.sampled.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AudioPlayer {

    private AudioFormat format;
    private DataLine.Info sourceInfo;
    private DataLine.Info targetInfo;

    public AudioPlayer() {
        this(8_000, 16, 1, true, false);
    }

    public AudioPlayer(float sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian) {
        format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        sourceInfo = new DataLine.Info(SourceDataLine.class, format);
        targetInfo = new DataLine.Info(TargetDataLine.class, format);
    }

    public void play(byte[] data) {
        try {
            SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(sourceInfo);
            sourceLine.open(format);
            sourceLine.start();

            sourceLine.write(data, 0, data.length);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public byte[] record(int second) {
        ByteWrapper byteWrapper = new ByteWrapper();
        byteWrapper.dataList = new ArrayList<>();
        byteWrapper.sizeList = new ArrayList<>();

        try {
            TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
            targetLine.open(format);
            targetLine.start();

            int numBytesRead;

            long time = System.currentTimeMillis();

            while (System.currentTimeMillis() - time < second * 1000) {
                byte[] targetData = new byte[targetLine.getBufferSize() / 5];
                numBytesRead = targetLine.read(targetData, 0, targetData.length);

                if (numBytesRead == -1) {
                    break;
                }

                byteWrapper.dataList.add(targetData);
                byteWrapper.sizeList.add(numBytesRead);
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        return merge(byteWrapper);
    }

    private byte[] merge(ByteWrapper byteWrapper) {
        int totalSize = byteWrapper.sizeList.stream().mapToInt(i -> i).sum();
        byte[] data = new byte[totalSize];
        int index = 0;
        for (int i = 0; i< byteWrapper.dataList.size(); i++) {
            byte[] subData = byteWrapper.dataList.get(i);
            int size = byteWrapper.sizeList.get(i);
            for (int j = 0; j < size; j++) {
                data[index++] = subData[j];
            }
        }

        return data;
    }

    public void recordAndPlay() {
        try {
            SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(sourceInfo);
            sourceLine.open(format);
            sourceLine.start();

            TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
            targetLine.open(format);
            targetLine.start();

            int numBytesRead;
            byte[] targetData = new byte[targetLine.getBufferSize() / 5];

            while (true) {
                numBytesRead = targetLine.read(targetData, 0, targetData.length);
                if (numBytesRead == -1) {
                    return;
                }

                sourceLine.write(targetData, 0, numBytesRead);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void play(Composition composition) {
        WavUtil wavUtil = new WavUtil();

        ByteWrapper byteWrapper = new ByteWrapper();
        byteWrapper.dataList = new ArrayList<>();
        byteWrapper.sizeList = new ArrayList<>();

        for (Note note : composition.getNoteList()) {
            double second = 60 * note.getDuration().getDuration() / composition.getTempo();
            byte[] data = wavUtil.sample(second, composition.getVolume(), note.getFrequency());
            byteWrapper.dataList.add(data);
            byteWrapper.sizeList.add(data.length);
        }

        play(merge(byteWrapper));
    }

    private static class ByteWrapper {
        List<byte[]> dataList;
        List<Integer> sizeList;
    }
}
