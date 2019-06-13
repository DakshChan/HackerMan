import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Terminal extends Entity{
    private int type;
    private int tier;
    private boolean hacked;
    private boolean minigameStart;
    
	public static Image[] terminalTex = new Image[2]; 

    // constructor for making a new terminal
	/**
	 * @param x, see super constructor
	 * @param y, see super constructor
	 * @param facing, see super constructor
	 * @param type, an int representing the type of obstacle that the terminal will hack, 1 for lasers, 2 for lights
	 * @param tier, higher tiers make the minigame harder
	 */
	Terminal(int x, int y, int facing, int type, int tier) {
        super(x,y,facing);
        this.setType(type); 
        this.setTier(tier);
        this.setHacked(false);
    }
	/**
	 * Standard getter for hacked
	 * @return, true if hacked, else false
	 */
	public boolean getHacked() {
		return hacked;
	}

	/**
	 * Standard setter for hacked
	 * @param hacked, new value for hacked
	 */
	public void setHacked(boolean hacked) {
		this.hacked = hacked;
	}
	
	/**
	 * Standard getter for type
	 * @return type, an int representing the type of obstacle that the terminal will hack, 1 for lasers, 2 for lights
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * Standard setter for type
	 * @param type, new value of type
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * Standard getter for tier
	 * @return tier of Terminal
	 */
	public int getTier() {
		return tier;
	}
	
	/**
	 * Standard setter for tier
	 * @param tier, new value of tier
	 */
	public void setTier(int tier) {
		this.tier = tier;
	}
	
	/**
	 * Standard getter for minigamestart
	 * @return, true if a minigame is started, else false
	 */
	public boolean getMinigameStart() {
		return minigameStart;
	}
	
	/**
	 * Standard setter for minigamestart
	 * @param minigameStart, new value for minigamestart
	 */
	public void setMinigameStart(boolean minigameStart) {
		this.minigameStart = minigameStart;
	}
    /**
     * Scans a map for certain obstacles and sets them to hacked
     * @param input, a 2D array representing the map the terminal is in
     */
    public void hack(Entity[][] input) {
		this.setHacked(true);
		for (int vc = 0; vc < input[0].length; vc++) {
			for (int hc = 0; hc < input.length; hc++) {
				if (this.getType() == 1) {
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

    /* (non-Javadoc)
     * @see src.Entity#drawSelf(java.awt.Graphics)
     */
    @Override
    public void drawSelf(Graphics g) {
		/*
		 * if(this.type == 1) { g.drawImage(terminalTex[0], getX()*Size, getY()*Size,
		 * Size, Size, null); } else if(this.type == 2) { g.drawImage(terminalTex[1],
		 * getX()*Size, getY()*Size, Size, Size, null); }
		 */
    	g.setColor(Color.GREEN);
    	g.fillRect(getX()*Size, getY()*Size, Size, Size);
    }

}
