package game;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundAndMusic {
    private String file_path, sound_path;
    private int state;

    Thread mus;

    public SoundAndMusic(String file_path) {
        this.file_path = file_path;
        state = 1;
        sound_path = file_path;
    }

    public void StartPlay_BGM() {
        if (state != 1) return;
        state = 2;

        mus = new Thread(new Runnable() {
            public void run() {
                while (state == 2) {
                    try {
                        File file = new File(file_path);
                        AudioInputStream stream = AudioSystem
                                .getAudioInputStream(file);
                        AudioFormat format = stream.getFormat();
                        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                        SourceDataLine dataLine = (SourceDataLine) AudioSystem.getLine(info);
                        byte[] data = new byte[512 * 1024];
                        dataLine.open();
                        dataLine.start();
                        int nBytesRead = 0;
                        while (nBytesRead != -1) {
                            if (state == 3) break;
                            nBytesRead = stream.read(data, 0, data.length);
                            if (nBytesRead >= 0) {
                                dataLine.write(data, 0, nBytesRead);
                            }
                        }
                        dataLine.drain();
                        dataLine.close();
                    } catch (UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    }
                    if (state == 3) break;
                }
            }
        });
        mus.start();
    }

    public void StopPlay_BGM() {
        state = 3;
        mus.stop();
    }

    public void playSound(String sound_path) {
        this.sound_path = sound_path;
        new Thread(new Runnable() {
            public void run() {
                try {
                    File file = new File(sound_path);
                    AudioInputStream stream = AudioSystem
                            .getAudioInputStream(file);
                    AudioFormat format = stream.getFormat();
                    DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                    SourceDataLine dataLine = (SourceDataLine) AudioSystem.getLine(info);
                    byte[] data = new byte[512 * 1024];
                    dataLine.open();
                    dataLine.start();
                    int nBytesRead = 0;
                    while (nBytesRead != -1) {
                        if (state == 3) break;
                        nBytesRead = stream.read(data, 0, data.length);
                        if (nBytesRead >= 0) {
                            dataLine.write(data, 0, nBytesRead);
                        }
                    }
                    dataLine.drain();
                    dataLine.close();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
