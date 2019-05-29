package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class DebugDisplay {
	public static void main (String[]args) {
		Random rand = new Random();
		Entity[][] map = new Entity[8][8];
		int ratio = Toolkit.getDefaultToolkit().getScreenSize().height/8;
		Entity.Size = ratio;
		for(int c = 0; c < 2; c++) {
			int x = rand.nextInt(8);
			int y = rand.nextInt(8);
			map[x][y] = new Guard(x,y,0,null);
		}
		Window window = new Window(map);
	}
}
class Window extends JFrame{
	private int maxX,maxY,screenRatio;
	public Window(Entity[][] map) {
		this.maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
	    this. maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
	    this.screenRatio = maxY / (map.length+1); 
		setTitle("Sad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(maxX,maxY);
		setResizable(false);
		getContentPane().add(new MapPanel(map, screenRatio));
		pack();
		setVisible(true);
	}
}
class MapPanel extends JPanel{
	Entity[][] map;
	int ratio;
	MapPanel(Entity[][] map, int screenRatio){
		this.map = map;
		this.ratio = screenRatio;
		setFocusable(true);
	    requestFocusInWindow();
	    setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int hc = 0; hc < map.length; hc++ ) {
			for(int vc = 0; vc < map[0].length; vc ++) {
				if(map[hc][vc] instanceof Guard) {
					Guard target = (Guard)map[hc][vc];
					g.setColor(Color.RED);
					g.fillRect(target.x*ratio, target.y*ratio, Entity.Size, Entity.Size);
					//((Guard)map[hc][vc]).;
				}
			}
		}
	}
}

