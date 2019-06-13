/**
 * MazeRunner.java
 * Version 1.0
 * This is what the player controls to play the Maze
 */

import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import javax.imageio.*;

public class MazeRunner extends MazeEntity{
  
  private int hp;
  private boolean active;
  
  MazeRunner(int x, int y){
    active = true;
    this.x = x * Size;
    this.y = y * Size;
    hp = 100;
  }
  
  void update(){
    if (hp < 0){
      active = false;
    }
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
  
  int returnHP(){
    return this.hp;
  }
  
  void charge(){
    if (hp < 100){
      hp++;
    } else {
      hp = 100;
    }
  }
  
  void discharge(){
    if (hp >= 0){
      hp--;
    }
  }
  
  void move(int direction){
    if (direction == 1){
      this.y = y - Size;
    } else if (direction == 2){
      this.x = x + Size;
    } else if (direction == 3){
      this.y = y + Size;
    } else if (direction == 4){
      this.x = x - Size;
    }
  }
  
}