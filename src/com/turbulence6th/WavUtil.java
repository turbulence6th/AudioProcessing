package com.turbulence6th;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WavUtil {

    public WavFile parse(File file) throws IOException {
        WavFile wavFile = new WavFile();

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[4];

            fis.read(buffer, 0, 4);
            wavFile.setChunkID(new String(buffer));

            Arrays.fill(buffer, (byte) 0x0);
            fis.read(buffer, 0, 4);
            wavFile.setChunkSize(ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getInt());

            Arrays.fill(buffer, (byte) 0x0);
            fis.read(buffer, 0, 4);
            wavFile.setFormat(new String(buffer));

            Arrays.fill(buffer, (byte) 0x0);
            fis.read(buffer, 0, 4);
            wavFile.setSubchunk1ID(new String(buffer));

            Arrays.fill(buffer, (byte) 0x0);
            fis.read(buffer, 0, 4);
            wavFile.setSubchunk1Size(ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getInt());

            Arrays.fill(buffer, (byte) 0x0);
            fis.read(buffer, 0, 2);
            wavFile.setAudioFormat(ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getInt());

            Arrays.fill(buffer, (byte) 0x0);
            fis.read(buffer, 0, 2);
            wavFile.setNumChannels(ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getInt());

            Arrays.fill(buffer, (byte) 0x0);
            fis.read(buffer, 0, 4);
            wavFile.setSampleRate(ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getInt());

            Arrays.fill(buffer, (byte) 0x0);
            fis.read(buffer, 0, 4);
            wavFile.setByteRate(ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getInt());

            Arrays.fill(buffer, (byte) 0x0);
            fis.read(buffer, 0, 2);
            wavFile.setBlockAlign(ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getInt());

            Arrays.fill(buffer, (byte) 0x0);
            fis.read(buffer, 0, 2);
            wavFile.setBitsPerSample(ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getInt());

            Arrays.fill(buffer, (byte) 0x0);
            fis.read(buffer, 0, 4);
            wavFile.setSubchunk2ID(new String(buffer));

            Arrays.fill(buffer, (byte) 0x0);
            fis.read(buffer, 0, 4);
            wavFile.setSubchunk2Size(ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getInt());

            byte[] data = new byte[wavFile.getSubchunk2Size()];
            fis.read(data);
            wavFile.setData(data);
        }

        return wavFile;
    }

    public byte[] sample(int second, int volume, double... freqs) {
        int samplePerSecond = 8_000;
        short[] samples = new short[samplePerSecond * second + 1];
        int index = 0;
        for (double t = 0; t < second; t += 1D / samplePerSecond) {
            for (double freq : freqs) {
                samples[index] += sound(t, freq, volume);
            }

            index++;
        }

        return getBytesfrom16BitData(samples);
    }

    private static short sound(double t, double freq, int volume) {
        return (short) (Math.sin(freq * 2 * Math.PI * t) * volume);
    }

    public short[] get16BitData(byte[] data) {
        short[] newData = new short[data.length / 2];
        for (int i = 0; i < data.length; i = i + 2) {
            byte[] buffer = Arrays.copyOfRange(data, i, i + 2);
            newData[i / 2] = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getShort();
        }

        return newData;
    }

    public byte[] getBytesfrom16BitData(short[] data) {
        byte[] newData = new byte[data.length * 2];
        for(int i = 0; i < data.length; i++) {
            byte[] bytes = shortToByte(data[i]);
            newData[2 * i] = bytes[0];
            newData[2 * i + 1] = bytes[1];
        }

        return newData;
    }

    private byte[] shortToByte(short s) {
        byte byte1= (byte) (s);
        byte byte2= (byte) ((s >> 8) & 0xff);

        return new byte[] {
                byte1, byte2
        };
    }

    public short[] fourierTransform(short[] input) {
        short[] output = new short[input.length];

        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < input.length; i++) {
            int finalI = i;
//            executor.execute(() -> {
            ComplexNumber out = ComplexNumber.ZERO;
            for (int j = 0; j < input.length; j++) {
                double angle = 2 * Math.PI * finalI * j / input.length;
                out = out.add(
                        new ComplexNumber(input[j] * Math.cos(angle), input[j] * Math.sin(angle))
                );
            }

            output[finalI] = (short) out.getReal();
//            });
        }

        executor.shutdown();
        return output;
    }
}
