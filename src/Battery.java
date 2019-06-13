/**
 * Battery.java
 * Version 1.0
 * This public class is for the battery objectives on the map
 */

import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import javax.imageio.*;

public class Battery extends MazeEntity{
  
  private boolean locked, charged;
  private int hp;
  private long lastTimeCheck, deltaTime;
  
  Image sprite;
  MazeRunner m;
  
  Battery(int x, int y, MazeRunner m){
    hp = 0;
    this.x = x * Size;
    this.y = y * Size;
    this.locked = false;
    this.charged = false;
    this.m = m;
    loadSprites("battery0.png");
  }
  
  Battery(int x, int y, MazeRunner m, boolean locked){
    hp = 0;
    this.x = x * Size;
    this.y = y * Size;
    this.locked = locked;
    this.charged = false;
    this.m = m;
    loadSprites("batteryLocked.png");
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
  
  void unlock(){
    this.locked = false;
  }
  
  void update(){
    if (hp >= 50){
      charged = true;
    }
    
    if (this.locked = false){
      if (charged == false){
        if (m.returnHP() > 0){
          long currentTime = System.currentTimeMillis();
          deltaTime += currentTime - lastTimeCheck;
          lastTimeCheck = currentTime;
          if (deltaTime >= 10){
            m.discharge();
            hp++;
            deltaTime = 0;
          }
        }
      }
    }
    
    adjustSprite();
    
  }
  
  void adjustSprite(){
    if (locked == true){
      loadSprites("batteryLocked.png");
    } else if (hp >= 50){
      loadSprites("battery100.png");
    } else if (hp >= 45){
      loadSprites("battery90.png");
    } else if (hp >= 40){
      loadSprites("battery80.png");
    } else if (hp >= 35){
      loadSprites("battery70.png");
    } else if (hp >= 30){
      loadSprites("battery60.png");
    } else if (hp >= 25){
      loadSprites("battery50.png");
    } else if (hp >= 20){
      loadSprites("battery40.png");
    } else if (hp >= 15){
      loadSprites("battery30.png");
    } else if (hp >= 10){
      loadSprites("battery20.png");
    } else if (hp >= 5){
      loadSprites("battery10.png");
    }
  }
  
  
}