package Utils;

import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    public String path;
    public Clip clip;

    public Sound(String path) {
        this.path = path;
    }

    public double getCurrentPosition() {
        if (clip != null) {
            return clip.getMicrosecondPosition() / 1e6; // Convert microseconds to seconds
        } else {
            return 0.0;
        }
    }

    public void play() {
        if (clip != null && clip.isRunning())
            this.reset();
        try {
            File musicPath = new File(this.path);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else System.out.println("Music Not found");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void pause() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void resume() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    public void reset() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public double getDuration() {
        if (clip != null) {
            long microsecondDuration = clip.getMicrosecondLength();
            return TimeUnit.MICROSECONDS.toSeconds(microsecondDuration);
        } else {
            return 0.0;
        }
    }
    public void muteUnmute() {
        if (clip != null) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (volumeControl != null) {
                if (volumeControl.getValue() > volumeControl.getMinimum()) {
                    volumeControl.setValue(volumeControl.getMinimum());
                } else {
                    volumeControl.setValue(volumeControl.getMaximum());
                }
            }
        }
    }
}
