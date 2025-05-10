package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Key;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B, arial_40B;
    BufferedImage keyImage;

    int messageCounter = 0;
    public boolean messageOn = false;
    public boolean gameFinish = false;
    public String message = "";

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.ROMAN_BASELINE, 12);
        
        arial_40B = new Font("Arial", Font.ROMAN_BASELINE, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        OBJ_Key key = new OBJ_Key();

        keyImage = key.image;

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2) {
        if (gameFinish == true) {
            g2.setFont(arial_40B);
            g2.setColor(Color.WHITE);
            String text;
            int textLenght;
            int x;
            int y;

            text = "Encontraste el tesoro!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLenght / 2;
            y = gp.screenHeight / 2 - (gp.titleSize * 3);
            g2.drawString(text, x, y);
            
            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);
             text = "Felicidades!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLenght / 2;
            y = gp.screenHeight / 2 + (gp.titleSize * 2);
            g2.drawString(text, x, y);
            gp.gameThread = null;
        } else {

            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, 5, 6, gp);
            g2.drawString("Llaves = " + gp.player.hasKey, 20, 18);

            //message if
            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(1));
                g2.drawString(message, 380, 20);

                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }

        }

    }
}
