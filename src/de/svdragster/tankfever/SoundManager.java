package de.svdragster.tankfever;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sven on 20.03.2017.
 */
public class SoundManager {

	private Clip clip;
	private float volume = 0.0f;

	public SoundManager() {
		try {
			//InputStream inputStream = new FileInputStream("resources/GiganticMoments.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/QuixoticView.wav"));
			//audioStream = new AudioStream(inputStream);
			DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioInputStream);
			clip.start();
			setVolume(-20);
			//AudioSystem.getAudioInputStream(audioStream);
			//AudioPlayer.player.start(audioStream);

		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		if (clip != null) {
			clip.start();
		}
	}

	public void setVolume(float volume) {
		this.volume += volume;
		if (this.volume < -80) {
			this.volume = -80;
		}
		if (this.volume > 10) {
			this.volume = 10;
		}
		if (clip != null) {
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(this.volume);
		}
	}
}
