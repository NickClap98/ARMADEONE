/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

/**
 *
 * @author Nicolas
 */
public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
   public int hasKey = 0;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.titleSize / 2);
        screenY = gp.screenHeight / 2 - (gp.titleSize / 2);

        solidArea = new Rectangle(0, 0, gp.titleSize, 48);
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        setDefaultValues();
        getPlayerImage();

    }

    //SETEO DE PERSONAJE
    
    public void setDefaultValues() {
        worldX = gp.titleSize * 23;
        worldY = gp.titleSize * 38;
        speed = 4;
        direction = "down";
    }

    
    //LEER ANIMACIONES
    public void getPlayerImage() {
        try {
            up0 = ImageIO.read(getClass().getResourceAsStream("/player/up0.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));

            down0 = ImageIO.read(getClass().getResourceAsStream("/player/down0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));

            left0 = ImageIO.read(getClass().getResourceAsStream("/player/left0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));

            right0 = ImageIO.read(getClass().getResourceAsStream("/player/right0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //ACTUALIZAR SEGUN MOVIMIENTO
    
    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true || keyH.zPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";

            } else if (keyH.leftPressed == true) {
                direction = "left";

            } else if (keyH.rightPressed == true) {
                direction = "right";
            }
            else if (keyH.zPressed == true) {
             
            }
            

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            //check OBJECT COLLISION
           int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            // SI LA COLISION ES FALSA , PLAYER CAN MOVE
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;

                }

            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else{spriteNum = 1;}
    }
    
   //METODO AGARRAR OBJETO Y INTERACCION
    
public void pickUpObject (int i){

if(i != 999){

String objectName = gp.obj[i].name;

switch(objectName){

    case "Key":
        gp.playSE(1);
            hasKey++;
            gp.obj[i] = null;
            gp.ui.showMessage("Tienes una llave!");
break;
    case "Door":
        if(hasKey >0){
            gp.playSE(3);
        gp.obj[i] =null;
        hasKey--;
        gp.ui.showMessage("Abriste la puerta!");
        }
        else{
        gp.ui.showMessage("Necesitas una llave!");
        }
        break;
          case "Potion":
              
        gp.playSE(1);
            speed = 5;
            gp.obj[i] = null;
            gp.ui.showMessage("Speed Up++");
break;
          case "Chest":
              
              gp.stopMusic();
              gp.playSE(4);
              gp.ui.gameFinish = true;
              break;
}
}

}
//DIBUJAR ANIMACION

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up0;
                }
                if (spriteNum == 2) {
                    image = up1;
                }
                if (spriteNum == 3) {
                    image = up2;
                }

                break;
            case "down":
                if (spriteNum == 1) {

                    image = down0;
                }
                if (spriteNum == 2) {

                    image = down1;
                }
                if (spriteNum == 3) {

                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {

                    image = left0;
                }
                if (spriteNum == 2) {

                    image = left1;
                }
                if (spriteNum == 3) {

                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {

                    image = right0;
                }
                if (spriteNum == 2) {

                    image = right1;
                }
                if (spriteNum == 3) {

                    image = right2;
                }

                break;

        }
        g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
    }
}
