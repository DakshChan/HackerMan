/*
 * Floor.java
 * Version 1.2
 * Authors: @ Jordan A.
 * @ 05/30
 * A class for floors
 */

import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import javax.imageio.*;

class Floor extends Entity{
  
  private Image sprite;
    
  Floor(int x, int y, int facing, String name){
    super(x, y, facing);
    loadSprite(name);
  }
  
  private void loadSprite(String name){
    try {
      sprite = ImageIO.read(new File(name));
    } catch(Exception e) {
      System.out.println("Error loading sprite ");
    }
    
    // scales the image up to the screen size
    sprite = sprite.getScaledInstance(Entity.Size, Entity.Size, Image.SCALE_SMOOTH);
    
  }

  @Override
  public void drawSelf(Graphics g){
    g.drawImage(sprite, x, y, null);
  }
}