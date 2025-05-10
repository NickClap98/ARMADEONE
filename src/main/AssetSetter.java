
package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Potion;


public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
    this.gp = gp;
    }
    
    public void setObject(){
    
    gp.obj[0] = new OBJ_Key();
    gp.obj[0].worldX = 32 * gp.titleSize;
    gp.obj[0].worldY = 32 * gp.titleSize;
    
    
    gp.obj[1] = new OBJ_Key();
    gp.obj[1].worldX = 10 * gp.titleSize;
    gp.obj[1].worldY = 24 * gp.titleSize;
    
    gp.obj[2] = new OBJ_Chest();
    gp.obj[2].worldX = 25 * gp.titleSize;
    gp.obj[2].worldY = 3 * gp.titleSize;
    
    gp.obj[3] = new OBJ_Door();
    gp.obj[3].worldX = 25 * gp.titleSize;
    gp.obj[3].worldY = 6 * gp.titleSize;
    
    
    gp.obj[4] = new OBJ_Door();
    gp.obj[4].worldX = 25 * gp.titleSize;
    gp.obj[4].worldY = 13 * gp.titleSize;
   
    gp.obj[5] = new OBJ_Potion();
    gp.obj[5].worldX = 39 * gp.titleSize;
    gp.obj[5].worldY = 30 * gp.titleSize;
    
    
    
    }
}
