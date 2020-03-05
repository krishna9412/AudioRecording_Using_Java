package javaclass;

import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class AudioRecordingclass {

	public static void main(String[] args) throws LineUnavailableException, InterruptedException {
		AudioFormat format = new AudioFormat(16000, 8, 2, true, true);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class,format);
		
		if(! AudioSystem.isLineSupported(info))
		{
			System.out.println("Line is not supported");
		}
		
		final TargetDataLine tdl = (TargetDataLine) AudioSystem.getLine(info);
		tdl.open();
		System.out.println("Recording start");
		tdl.start();
		Thread stop = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				AudioInputStream audioInputStream = new AudioInputStream(tdl);
                                
                            
			        File wavefile = new File("F:\\Recording.wav");
                                 byte[] os =wavefile.toString().getBytes();
                            
                            
			    try {
					AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, wavefile);
			    	
				} catch (Exception e) {
				}
			}
		});
		
		stop.start();
		Thread.sleep(5000);
		tdl.stop();
		tdl.close();
		System.out.println("Recording Ended");
		

	}

}