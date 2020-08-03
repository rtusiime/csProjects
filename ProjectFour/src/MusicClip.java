import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class MusicClip {
	static String filePath;
	static int x;
	Clip clip;
	AudioInputStream audioIn;
	URL url;
	
	
	//method to play
	public void play() {
		x= Graphipult.soundNo;
		if(x ==2) {
			filePath ="collide.wav";
			
		}else if(x ==1) {
			filePath ="plane.wav";
		} 
		try {
		
			url = this.getClass().getClassLoader().getResource(filePath);
			audioIn = AudioSystem.getAudioInputStream(url);
			
			clip = AudioSystem.getClip(); //get clip resource
			clip.open(audioIn); //open audio clip and load s
			clip.stop();
			clip.start();
			
		}catch(NullPointerException e) {
			System.out.println("null pointer exception");
		}
		catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}  
	}
	
	public void stop() {
		clip.stop();
	}
}
