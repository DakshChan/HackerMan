import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window extends JFrame {
	private int maxX, maxY, screenRatio;

	public Window(Entity[][] map, Entity[][] laser, Guard[] guards) {
		this.maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
		this.maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
		setTitle("Sad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(maxX, maxY);
		setResizable(false);
		getContentPane().add(new MapPanel(map, laser, guards));
		pack();
		setVisible(true);
	}

}
