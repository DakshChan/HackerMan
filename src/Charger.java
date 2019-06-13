/**
 * Charger.java
 * Version 1.0
 * The spawnpoint of the player, as well as an hp/energy refiller
 */

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import javax.imageio.*;


public class Charger extends MazeEntity{
  private long lastTimeCheck, deltaTime;
  Rectangle hitbox;
  MazeRunner m;
  
  Charger(int x, int y, MazeRunner m){
    this.m = m;
    this.x = x * Size;
    this.y = y * Size;
    
  }
  
  void loadSprites(String name){
    try {
      sprite = ImageIO.read(new File(name));
    } catch(Exception e) {
      System.out.println("Error loading sprite ");
    }
    // scales the image up to the screen size
    sprite = sprite.getScaledInstance(Size, Size, Image.SCALE_SMOOTH);
  }
  
  void drawSelf(Graphics g){
    g.drawImage(sprite, x, y, null);
  }
  
  void update(){
    
  }
  
  void chargeRunner(MazeRunner m){
    long currentTime = System.currentTimeMillis();
    deltaTime += currentTime - lastTimeCheck;
    lastTimeCheck = currentTime;
    if (deltaTime >= 10){
      m.charge();
    }
  }
  
  
  
}