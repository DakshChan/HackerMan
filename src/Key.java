/**
 * Key.java
 * Version 1.0
 * Class for the key object in the maze minigame
 */

import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import javax.imageio.*;

public class Key extends MazeEntity{
  
  Image sprite;
  MazeRunner m;
  Battery b;
  boolean onMap;
  
  Key(Battery b, int x, int y, MazeRunner m){
    this.b = b;
    this.m = m;
    this.x = x * Size;
    this.y = y * Size;
    onMap = true;
    loadSprites("key.png");
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
    g.drawImage(sprite, this.x, this.y, null);
  }
  
  void update(){
    if (onMap == false){
      b.unlock();
    }
  }
  
  void pickedUp(){
    onMap = false;
  }
}