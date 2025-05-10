
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 *
 * @author Nicolas
 */
public class KeyHandler implements KeyListener {
 public boolean upPressed,downPressed,leftPressed,rightPressed ,zPressed;
    @Override
    public void keyTyped(KeyEvent e) {
   
    
    }
//MAPEAR TECLAS
    @Override
    public void keyPressed(KeyEvent e) {
   
    int code = e.getKeyCode();
    if(code == KeyEvent.VK_W){
    upPressed = true;
    }
    if(code == KeyEvent.VK_S){
    downPressed = true;
    }
    if(code == KeyEvent.VK_A){
    leftPressed = true;
    }
    if(code == KeyEvent.VK_D){
    rightPressed = true;
    }
      if(code == KeyEvent.VK_Z){
          zPressed = true;
   
    }
    
    }

    @Override
    public void keyReleased(KeyEvent e) {
   int code = e.getKeyCode();
   if(code == KeyEvent.VK_W){
    upPressed = false;
    }
    if(code == KeyEvent.VK_S){
    downPressed = false;
    }
    if(code == KeyEvent.VK_A){
    leftPressed = false;
    }
    if(code == KeyEvent.VK_D){
    rightPressed = false;
    }
    if(code == KeyEvent.VK_Z){
          zPressed = false;
   
    }
        
    }
    
}
