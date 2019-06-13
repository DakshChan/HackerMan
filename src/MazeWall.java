/**
 * MazeWall.java
 * Version 1.0
 * Class for walls
 */

import java.awt.Graphics;
import java.awt.Color;

class MazeWall extends MazeEntity{
  MazeWall(int x, int y){
    this.x = x * Size;
    this.y = y * Size;
  }
  
  void drawSelf(Graphics g){
    g.setColor(Color.DARK_GRAY);
    g.fillRect(x, y, Size, Size);
  }
  
  void update(){
    
  }
}