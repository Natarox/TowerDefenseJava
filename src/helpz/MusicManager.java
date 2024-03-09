package helpz;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class MusicManager {
	
	public static String folderPath = System.getProperty("user.dir");
	public static String resFolder = "res";
	public static String filePath = folderPath + File.separator + resFolder + File.separator;
	
	private static File playingMusicPath = new File(filePath+"playing_theme.wav");
	private static File titleMusicPath = new File(filePath+"title_theme.wav");
	private static File gameOverMusicPath = new File(filePath+"gameover_theme.wav");
	private static File gameEndMusicPath = new File(filePath+"gameend_theme.wav");
	private static AudioInputStream audioInput;
	private static Clip clip;
	private static double volume = 0.3f;
	

	public static void playingMusicStart() {
		try {
			audioInput = AudioSystem.getAudioInputStream(playingMusicPath);
			clip = AudioSystem.getClip();
			
			if(playingMusicPath.exists()) {
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				
			}else {
				System.out.println("File don't found");
			}
			setVolume();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void titleMusicStart() {
		try {
			audioInput = AudioSystem.getAudioInputStream(titleMusicPath);
			clip = AudioSystem.getClip();
			
			if(titleMusicPath.exists()) {
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				
			}else {
				System.out.println("File don't found");
			}
			
			setVolume();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void gameOverMusicStart() {
		try {
			audioInput = AudioSystem.getAudioInputStream(gameOverMusicPath);
			clip = AudioSystem.getClip();
			
			if(gameOverMusicPath.exists()) {
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				
			}else {
				System.out.println("File don't found");
			}
			setVolume();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void gameEndMusicStart() {
		try {
			audioInput = AudioSystem.getAudioInputStream(gameEndMusicPath);
			clip = AudioSystem.getClip();
			
			if(gameEndMusicPath.exists()) {
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				
			}else {
				System.out.println("File don't found");
			}
			setVolume();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void stopMusic() {
		clip.stop();
	}
	
	public static double getVolume() {
		return volume;
	}
	
	public static void changeVolume(double newVolume) {
		volume = newVolume;
	}

	public static void setVolume() {
	    if (volume < 0 || volume > 1)
	        throw new IllegalArgumentException("Volume not valid: " + volume);
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	    gainControl.setValue(20f * (float) Math.log10(volume));
	    
	    volume = (double) Math.pow(10, gainControl.getValue() / 20);
	}
	
}
