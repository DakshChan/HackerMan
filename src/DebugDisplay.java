import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.lang.invoke.ConstantCallSite;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class DebugDisplay {
	public static void main (String[]args) {
		Random rand = new Random();
		Entity[][] map;
		try {
			map = MapLoader.load("C:\\Users\\daksh\\IdeaProjects\\HackerMan\\src\\map.txt");
		} catch (Exception e){
			System.out.println(e);
			map = new Entity[0][0];
		}
		int ratio = Toolkit.getDefaultToolkit().getScreenSize().height/map.length;
		Entity.Size = ratio;
		Window window = new Window(map);
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (Exception e){
				System.out.println(e);
			}
			window.repaint();
		}
	}
}
class Window extends JFrame{
	private int maxX,maxY,screenRatio;
	public Window(Entity[][] map) {
		this.maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
	    this. maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
	    this.screenRatio = maxY/map.length;
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
		for(int i = 0; i < map.length; i++ ) {
			for(int j = 0; j < map[0].length; j ++) {
				if(map[i][j] instanceof Terminal){
					Terminal target = (Terminal)map[i][j];
					g.setColor(Color.RED);
					g.fillRect(target.x*ratio, target.y*ratio, Entity.Size, Entity.Size);
				}
				if(map[i][j] instanceof LaserNode){
					LaserNode target = (LaserNode)map[i][j];
					g.setColor(Color.MAGENTA);
					g.fillRect(target.x*ratio, target.y*ratio, Entity.Size, Entity.Size);
				}

				/*
				if(map[i][j] instanceof Guard) {
					Guard target = (Guard)map[i][j];
					g.setColor(Color.RED);
					g.fillRect(target.x*ratio, target.y*ratio, Entity.Size, Entity.Size);
					//((Guard)map[hc][vc]).;
				}
				 */
			}
		}
	}
}

