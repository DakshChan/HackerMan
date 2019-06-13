package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Terminal extends Entity{
    private int type;
    private int tier;
    private boolean hacked;
    private boolean minigameStart;
    
	public static Image[] terminalTex; 

    // constructor for making a new terminal
	Terminal(int x, int y, int facing, int type, int tier) {
        super(x,y,facing);
        this.setType(type); // 0 for doors, 1 for lasers, 2 for lights
        this.setTier(tier);
        this.setHacked(false);
    }
	public boolean getHacked() {
		return hacked;
	}

	public void setHacked(boolean hacked) {
		this.hacked = hacked;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getTier() {
		return tier;
	}
	
	public void setTier(int tier) {
		this.tier = tier;
	}
	
	public boolean getMinigameStart() {
		return minigameStart;
	}
	
	public void setMinigameStart(boolean minigameStart) {
		this.minigameStart = minigameStart;
	}
    // modifies nodes to delete the obstacle effect
    public void hack(Entity[][] input) {
		this.setHacked(true);
		for (int vc = 0; vc < input[0].length; vc++) {
			for (int hc = 0; hc < input.length; hc++) {
				if (this.getType() == 0) {
					if (input[hc][vc] instanceof Door) {
						((Door) input[hc][vc]).locked = false;
					}
				} 
				else if (this.getType() == 1) {
					if (input[hc][vc] instanceof LaserNode) {
						((LaserNode) input[hc][vc]).setHacked(true);
					}
				}
				else if (this.getType() == 2) {
					if (input[hc][vc] instanceof Guard) {
						((Guard) input[hc][vc]).setHacked(true);
					}
				}
			}
		}
	}

    @Override
    public void drawSelf(Graphics g) {
//    	if(this.type == 0) {
//        	
//        }
//        else if(this.type == 1) {
//        	
//        }
//        else if(this.type == 2) {
//        	
//        }
    	g.setColor(Color.GREEN);
    	g.fillRect(getX()*Size, getY()*Size, Size, Size);
    }

}
