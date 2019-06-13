/**
 * Wall.java
 * Version 1.1
 * Author: @ Jordan A.
 * @ 05/30
 * A class for walls
 */

import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import javax.imageio.*;

class Wall extends Entity{
  
  Image sprite;
    
  Wall(int x, int y, int facing,  String name){
    super(x, y, facing);
    loadSprite(name);
  }
  
  void loadSprite(String name){
    try {
      sprite = ImageIO.read(new File(name));
    } catch(Exception e) {
      System.out.println("Error loading sprite ");
    }
    
    // scales the image up to the screen size
    sprite = sprite.getScaledInstance(Entity.Size, Entity.Size, Image.SCALE_SMOOTH);
    
  }
  
  public void drawSelf(Graphics g){
    g.drawImage(sprite, this.getX(), this.getY(), null);
  }
}