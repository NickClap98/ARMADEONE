/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nicolas
 */
public class OBJ_Potion extends SuperObject {
   public OBJ_Potion(){
    name = "Potion";
    try{
        image = ImageIO.read(getClass().getResourceAsStream("/objects/pocionVida.png"));
        
        
    }catch(IOException e){
    e.printStackTrace();
    }
    
    
    }
}
