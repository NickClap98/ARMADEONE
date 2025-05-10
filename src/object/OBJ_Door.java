/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;


public class OBJ_Door extends SuperObject {
    public OBJ_Door(){
     name = "Door";
    try{
        
        image = ImageIO.read(getClass().getResourceAsStream("/objects/puerta.png"));
        
        
    }catch(IOException e){
    e.printStackTrace();
    }
    collision = true;
    
    }
}
