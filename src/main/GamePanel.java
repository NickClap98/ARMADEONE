package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import main.entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    //SCREAN SETTINGS
    final int originalTileSize = 16; //16x16
    final int scale = 3; //scalador 16x3 = 48 pixeles x 48 

    public final int titleSize = originalTileSize * scale; //48x48
    public final int maxScreenCol = 18; //horizontal
    public final int maxScreenRow = 10;//vertical
    public final int screenWidth = titleSize * maxScreenCol; //768 pixeles horizontal
    public final int screenHeight = titleSize * maxScreenRow; //576 pixeles vertical

    //WORLD SETTINGS
    //50 X 50
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = titleSize * maxWorldCol;
    public final int worldHeight = titleSize * maxWorldRow;

    // FPS
    int FPS = 60;

    //SYSTEMA
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();

    Sound music = new Sound();
    Sound se = new Sound();

    public AssetSetter aSetter = new AssetSetter(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public UI ui = new UI(this);
    
    Thread gameThread;
//ENTITY AND OBJCT

//Ranuras de objeto
    public Player player = new Player(this, keyH);

    public SuperObject obj[] = new SuperObject[10];

//Set player default position
 

    public GamePanel() {//dimensionar panel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject();
        playMusic(0);
    }

    public void starGameThread() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    /*
    @Override
    public void run() {
       double drawInterval = 1000000000/FPS;
       double nextDrawTime = System.nanoTime() + drawInterval;
    while(gameThread != null){
     long currentTime = System.nanoTime();
     
        //1 Upadte: actualizacion de pocision de personaje
        update();
        //2 draw: dibujar pocision acutalizada informacion
    repaint();
   
           try {
               double remainingTime = nextDrawTime - System.nanoTime();
    remainingTime = remainingTime/1000000;
    if(remainingTime<0){
    remainingTime = 0;
    }
               Thread.sleep((long) remainingTime);
           nextDrawTime+=drawInterval;
           
           } catch (InterruptedException ex) {
               Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    }
     */
    public void run() {
        double drawInteval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInteval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;

            }
            if (timer >= 1000000000) {
             //SALIDA A CONSOLA FPS
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
        player.update();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //TILE
        tileM.draw(g2);
//OBJETOS
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

//PLAYER
        player.draw(g2);
        
        //UI
        ui.draw(g2);
        
        g2.dispose();

    }

     public void playMusic(int i) {

        music.setFile(i);
      music.setVolume(-30.0f);
      music.play();
      music.loop();
    }
     
  

    public void stopMusic() {

        music.stop();
    }

    //PLAY SONIDOS
    
    public void playSE(int i) {

        se.setFile(i);
        
        se.play();
    }
}
