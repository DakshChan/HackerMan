package src;

import java.awt.CardLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Display {
	public static final String MAINPANEL = "Main Panel";
	public static final String SECONDPANEL = "Secondary Panel";
	
	public static void main(String[] args) {
		Entity[][] map = new Entity[8][8];
		Entity[][] laser = new Entity[8][8];
		Guard[] guards = new Guard[8];
		int ratio = Toolkit.getDefaultToolkit().getScreenSize().height/8;
		Entity.Size = ratio;
		int x = 6;
		int y = 6;
		Pair[] path = new Pair[4];
		path[0] = new Pair(x,y);
		path[1] = new Pair(x,y-5);
		path[2] = new Pair(x-5,y-5);
		path[3] = new Pair(x-5,y-1);
		guards[0] = new Guard(x,y,0,path);
		map[7][0] = new LaserNode(7, 0, 0);
		map[0][0] = new LaserNode(0, 0, 0);
		map[0][7] = new LaserNode(0, 7, 0);
		GameWindow window = new GameWindow();
		CardLayout savedLayout = new CardLayout();
		JPanel cardStack = new JPanel(savedLayout);
		SnakePanel snake = new SnakePanel();
		cardStack.add(snake, SECONDPANEL);
		cardStack.add(new MapPanel(map, laser, guards), MAINPANEL);	
		window.add(cardStack);
		window.pack();
		window.setVisible(true);
		Timer t = new Timer(100, snake);
		t.start();
		for (int i = 0; i < 10; i++) {
			if(i % 2 == 0) {
				t.restart();
			}
			else {
				t.stop();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			savedLayout.next(cardStack);
		}
		
	}
}

class GameWindow extends JFrame {
	GameWindow() {
		setTitle("Sad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
	}
}

