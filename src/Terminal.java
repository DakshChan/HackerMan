import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Terminal extends Entity{
	static Image[] terminalTex;
    boolean hacked;
    int type, termY, termX;
    String mapContainedIn;
	CardLayout layoutContainedIn;
	JPanel panelContainedIn;

    // constructor for making a new terminal
    Terminal(int x, int y, int facing, int type){
        super(x,y,facing);
        this.hacked = false;
        this.type = type; // 0 for doors, 1 for lasers, 2 for lights
    }
    
    public void startMiniGame(CardLayout layout, JPanel cardStack, String cardName) {
    	layout.show(cardStack, cardName);
    }

    // modifies nodes to delete the obstacle effect
    public void setHacked(Entity[][] map, int range){
    	this.hacked = true;
        int xUpper;
        int yUpper;
        int xLower;
        int yLower;

        //check to make sure that the node doesn't check outside the map
        if (this.x + range > map.length) {
            xUpper = map.length;
        } else {
            xUpper = this.x + range;
        } if (this.y + range > map[0].length) {
            yUpper = map[0].length;
        } else {
            yUpper = this.y + range;
        }

        if (this.x - range < 0) {
            xLower = 0;
        } else {
            xLower = this.x - range;
        } if (this.x - range < 0) {
            yLower = 0;
        } else {
            yLower = this.x - range;
        }

        for(int vc = yLower; vc < yUpper; vc ++) {
            for(int hc = xLower; hc < xUpper; hc ++) {
                if(this.type == 0) {
//                	if(map[hc][vc] instanceof Door) {
//                		
//                	}
                }
                else if(this.type == 1) {
                	if(map[hc][vc] instanceof LaserNode) {
                		((LaserNode)map[hc][vc]).hacked = true;
                	}
                }
                else if(this.type == 2) {
                	if(map[hc][vc] instanceof Guard) {
                		((Guard)map[hc][vc]).hacked = true;
                	}
                }
            }
        }
    }

    @Override
    public void drawSelf(Graphics g) {
    	if(this.type == 0) {
        	
        }
        else if(this.type == 1) {
        	
        }
        else if(this.type == 2) {
        	
        }
    }

}
