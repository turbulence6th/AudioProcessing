package com.turbulence6th;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
		playComputerGeneratedAudio();
		playSampleAudio();
    }

    private static void playComputerGeneratedAudio() {
		WavUtil wavUtil = new WavUtil();
		byte[] data = wavUtil.sample(5, 10_000, Frequency.A4.getFreq(), Frequency.B4.getFreq());

		AudioPlayer audioPlayer = new AudioPlayer();
		audioPlayer.play(data);
	}

	private static void playSampleAudio() throws IOException {
    	WavUtil wavUtil = new WavUtil();
    	WavFile wavFile = wavUtil.parse(new File("sample.wav"));

    	AudioPlayer audioPlayer = new AudioPlayer(wavFile.getSampleRate(), wavFile.getBitsPerSample(), wavFile.getNumChannels(), true, false);
    	audioPlayer.play(wavFile.getData());
	}
}
