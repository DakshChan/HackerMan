/**
 * Turret.java
 * Version 1.0
 * This public class is used for Turrets
 */

import java.awt.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;
import java.io.*;
import javax.imageio.*;

public class Turret extends MazeEntity{
  
  private int facing, maxX, maxY;
  private double turretX, turretY, ratio, delay;
  private boolean damaging;
  private long lastTimeCheck, deltaTime;
  private MazeRunner m;
  
  Turret(int x, int y, MazeRunner m){
    lastTimeCheck = System.currentTimeMillis();
    this.x = x * Size;
    this.y = y * Size;
    this.m = m;
    delay = Math.random()*1;
    facing = (int)Math.random()*4 + 1;
    damaging = false;
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
  
  // moves the damage hurtbox in the 4 tiles around the turret
  void attack(){
    if (facing == 1 && damaging == true){ // n
      
    } else if (facing == 2 && damaging == true){ // e
      
    } else if (facing == 3 && damaging == true){ // s
      
    } else if (facing == 4 && damaging == true){ // w
      
    } else {
      
    }
  }
  
  // cycles to face all 4 directions
  void direction(){
    if (facing == 4){
      facing = 1;
    } else if (facing >= 1){
      facing ++;
    }
  }
  
  void drawSelf(Graphics g){
    g.drawImage(sprite, this.x, this.y, null);
  }
  
  // cycles between attacking and not attacking every .5 seconds plus a delay;
  void update(){
    long currentTime = System.currentTimeMillis();
    deltaTime += currentTime - lastTimeCheck;
    lastTimeCheck = currentTime;
    if (deltaTime >= 500 + (delay*1000)){
      if (damaging == false){ // runs through damaging phase
        damaging = true;
        attack();
        deltaTime = 0;
      } else if (damaging == true){ // runs through inactive phase, changes directions
        damaging = false;
        attack();
        direction();
        deltaTime = 0;
      }
    }
  }
  
}