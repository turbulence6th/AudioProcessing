package com.turbulence6th;

public class WavFile {

    private String chunkID;
    private int chunkSize;
    private String format;
    private String subchunk1ID;
    private int subchunk1Size;
    private int audioFormat;
    private int numChannels;
    private int sampleRate;
    private int byteRate;
    private int blockAlign;
    private int bitsPerSample;
    private String subchunk2ID;
    private int subchunk2Size;
    private byte[] data;

    public String getChunkID() {
        return chunkID;
    }

    public void setChunkID(String chunkID) {
        this.chunkID = chunkID;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSubchunk1ID() {
        return subchunk1ID;
    }

    public void setSubchunk1ID(String subchunk1ID) {
        this.subchunk1ID = subchunk1ID;
    }

    public int getSubchunk1Size() {
        return subchunk1Size;
    }

    public void setSubchunk1Size(int subchunk1Size) {
        this.subchunk1Size = subchunk1Size;
    }

    public int getAudioFormat() {
        return audioFormat;
    }

    public void setAudioFormat(int audioFormat) {
        this.audioFormat = audioFormat;
    }

    public int getNumChannels() {
        return numChannels;
    }

    public void setNumChannels(int numChannels) {
        this.numChannels = numChannels;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public int getByteRate() {
        return byteRate;
    }

    public void setByteRate(int byteRate) {
        this.byteRate = byteRate;
    }

    public int getBlockAlign() {
        return blockAlign;
    }

    public void setBlockAlign(int blockAlign) {
        this.blockAlign = blockAlign;
    }

    public int getBitsPerSample() {
        return bitsPerSample;
    }

    public void setBitsPerSample(int bitsPerSample) {
        this.bitsPerSample = bitsPerSample;
    }

    public String getSubchunk2ID() {
        return subchunk2ID;
    }

    public void setSubchunk2ID(String subchunk2ID) {
        this.subchunk2ID = subchunk2ID;
    }

    public int getSubchunk2Size() {
        return subchunk2Size;
    }

    public void setSubchunk2Size(int subchunk2Size) {
        this.subchunk2Size = subchunk2Size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
