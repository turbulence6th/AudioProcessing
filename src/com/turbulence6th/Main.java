package com.turbulence6th;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//		playComputerGeneratedAudio();
//		playSampleAudio();
//		recordAndPlay();
//		recordAndSave();
//		generateAndSave();
    }

    private static void playComputerGeneratedAudio() {
		WavUtil wavUtil = new WavUtil();
		byte[] data = wavUtil.sample(5, 10_000, Note.A4.getFreq(), Note.B4.getFreq());

		AudioPlayer audioPlayer = new AudioPlayer();
		audioPlayer.play(data);
	}

	private static void playSampleAudio() throws IOException {
    	WavUtil wavUtil = new WavUtil();
    	WavFile wavFile = wavUtil.parse(new File("sample.wav"));

    	AudioPlayer audioPlayer = new AudioPlayer(wavFile.getSampleRate(), wavFile.getBitsPerSample(), wavFile.getNumChannels(), true, false);
    	audioPlayer.play(wavFile.getData());
	}

	private static void recordAndPlay() {
    	AudioPlayer audioPlayer = new AudioPlayer();
    	audioPlayer.recordAndPlay();
	}

	private static void recordAndSave() throws IOException {
    	WavUtil wavUtil = new WavUtil();

		AudioPlayer audioPlayer = new AudioPlayer();
		byte[] data = audioPlayer.record(5);

		WavFile wavFile = wavUtil.generate(data);
		wavUtil.writeToFile(wavFile, new File("recorded.wav"));
	}

	private static void generateAndSave() throws IOException {
		WavUtil wavUtil = new WavUtil();

		byte[] data = wavUtil.sample(5, 10_000, Note.A4.getFreq(), Note.B4.getFreq());

		WavFile wavFile = wavUtil.generate(data);
		wavUtil.writeToFile(wavFile, new File("generated.wav"));
	}
}
