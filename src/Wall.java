/**
 * Wall.java
 * Version 1.0
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
    
  Wall(int x, int y, int facing, int size, String name){
    super(x, y, facing, size);
    loadSprite(name);
  }
  
  void loadSprite(String name){
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
}