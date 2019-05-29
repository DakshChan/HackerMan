/**
 * Terminal.java
 * Version 1.0
 * @ Author: Jordan A.
 * @ 05/24 - 06/28
 * This class is used for the terminal interactions on the map
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

class Terminal extends Entity{

    private int facing;
    private boolean hacked;
    private int tier, type, termY, termX, maxY, maxX;
    private String text;
    private char hackType;
    private Rectangle hitbox;

    // constructor for making a new terminal
    Terminal(int y, int x, int facing, int tier, int type){
        maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
        maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
        setY(y);
        setX(x);
        setFacing(facing);
        setHacked(false);
        setTier(tier);
        setType(type); // 0 for doors, 1 for lasers, 2 for lights
        hitbox = new Rectangle(x, y, Size, Size);
    }

    // set terminal values
    void setY(int y){
        this.termY = y;
    } void setX(int x){
        this.termX = x;
    } void setFacing(int i){
        this.facing = i;
    } void setText(String text){
        this.text = text;
    } void setHacked(boolean hacked){
        this.hacked = hacked;
    } void setTier(int i){
        this.tier = i;
    } void setType(int t){
        this.type = t;
    }

    // return terminal values
    int returnY(){
        return this.termY;
    } int returnX(){
        return this.termX;
    } int returnFacing(){
        return this.facing;
    } String returnText(){
        return this.text;
    } int returnTier(){
        return this.tier;
    } int returnType(){
        return this.type;
    }

    void draw(Graphics g, int ratio){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(ratio, ratio, maxY - ratio, maxY - ratio);
        g.setColor(Color.GRAY);
        g.fillRect(ratio*3, ratio*3, maxY - (ratio*3), maxY - (ratio*3));
    }

    void hackType(){
        if (type == 0){
            this.hackType = 'd';
        } else if (type == 1){
            this.hackType = 'l';
        } else if (type == 2){
            this.hackType = 'i';
        }

    }
//  void gameCheck(int hackTier){
//    if (this.text.equals("LASERHACK") && hackTier >= this.tier){
//      System.out.println("Beginning hack...");
//    } else if (this.text.equals("DOORHACK") && hackTier >= this.tier){
//      System.out.println("Beginning hack...");
//    } else if (this.text.equals("LASERHACK") && hackTier < this.tier){
//      System.out.println("The security is too advanced...");
//      System.out.println("You may need to upgrade your skills before you can hack this.\n");
//    } else {
//      System.out.println("Unknown input. Please try again.");
//    }
//  }

    // modifies nodes to delete the obstacle effect
    void setHacked(char[][] map, int range){
        int xUpper;
        int yUpper;
        int xLower;
        int yLower;

        //check to make sure that the node doesn't check outside the map
        if (this.returnX() + range > map.length) {
            xUpper = map.length;
        } else {
            xUpper = this.returnX() + range;
        } if (this.returnY() + range > map[0].length) {
            yUpper = map[0].length;
        } else {
            yUpper = this.returnY() + range;
        }

        if (this.returnX() - range < 0) {
            xLower = 0;
        } else {
            xLower = this.returnX() - range;
        } if (this.returnY() - range < 0) {
            yLower = 0;
        } else {
            yLower = this.returnY() - range;
        }

        for(int vc = yLower; vc < yUpper; vc ++) {
            for(int hc = xLower; hc < xUpper; hc ++) {
                if (map[vc][hc] == 'l'){
                    System.out.println("Hacked node! ");
                }
            }
        }



    }



}