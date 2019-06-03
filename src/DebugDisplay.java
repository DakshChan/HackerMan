import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class DebugDisplay {
	public static void main (String[]args) {
		Random rand = new Random();
		Entity[][] map = new Entity[8][8];
		int ratio = Toolkit.getDefaultToolkit().getScreenSize().height/8;
		Entity.Size = ratio;
		int x = 7;
		int y = 7;
		ArrayList<Pair>path = new ArrayList<Pair>();
		path.add(new Pair(x*ratio,y*ratio));
		path.add(new Pair(x*ratio,(y-5)*ratio));
		path.add(new Pair((x-5)*ratio,(y-5)*ratio));
		path.add(new Pair((x-5)*ratio,(y-1)*ratio));
		map[x][y] = new Guard(x*ratio,y*ratio,0,path);
		Window window = new Window(map);
	}
}
class Window extends JFrame{
	private int maxX,maxY,screenRatio;
	public Window(Entity[][] map) {
		this.maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
	    this.maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
		setTitle("Sad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(maxX,maxY);
		setResizable(false);
		getContentPane().add(new MapPanel(map));
		pack();
		setVisible(true);
	}
}
class MapPanel extends JPanel{
	Entity[][] map;
	MapPanel(Entity[][] map){
		this.map = map;
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
					target.drawSelf(g);
					//((Guard)map[hc][vc]).;
				}
			}
		}
		repaint();
	}
}

