
package main;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author Nicolas
 */
public class Sound {
    Clip clip;
   
    URL [] soundURL = new URL[20];
  
    
 
    public Sound(){
    
   soundURL[0] = getClass().getResource("/sound/MusicaFondo.wav");
    
    soundURL[1] = getClass().getResource("/sound/coin.wav");
    
    soundURL[2] = getClass().getResource("/sound/powerup.wav");
    
    soundURL[3] = getClass().getResource("/sound/DoorSound.wav");
    
    soundURL[4] = getClass().getResource("/sound/fanfare.wav");

        
            
    }

public void setFile(int i){
try{
    AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
clip = AudioSystem.getClip();
clip.open(ais);
    
}catch(Exception e){
e.printStackTrace();
}
}
public void setVolume(float volume) {
    if (clip != null) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume);
    }
}

public void play(){
clip.start();

}

public void loop(){
clip.loop(Clip.LOOP_CONTINUOUSLY);

}

public void stop(){
clip.stop();
}

}
