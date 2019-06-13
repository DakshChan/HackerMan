/**
 * MazeEntity.java
 * Version 1.0
 * The basis for all entities in the Maze Minigame
 */

import java.awt.Graphics;
import java.awt.Image;

abstract public class MazeEntity{
  
  static int Size;
  Image sprite;
  int x, y;
  
  abstract void update();
  abstract void drawSelf(Graphics g);
  
  
  
}