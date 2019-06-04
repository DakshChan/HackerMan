package basic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel; 

public class Display{
	public static void main (String[]args) {
		BallWindow window = new BallWindow();
	}
}
class BallWindow extends JFrame{
	public BallWindow() {
		setTitle("Sad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setResizable(true );
		getContentPane().add(new BallPanel());
		pack();
		setVisible(true);
	}
}
class BallPanel extends JPanel implements KeyListener{
	Set<Character> pressed = new HashSet<Character>();
	Random rand;
	Ball[]balls = new Ball[5];
	Framerate fps;
	Timer time;
	Player me;
	
	public BallPanel() { 
		rand = new Random();
		fps = new Framerate();
	    time = new Timer();
	    me = new Player();
	    addKeyListener(this);
	    setFocusable(true);
	    requestFocusInWindow();
	    setPreferredSize(new Dimension(800,600));
	    for(int c = 0; c < balls.length; c++) {
	    	balls[c] = new Ball(c*10, c*100, rand.nextDouble(), rand.nextDouble(), rand.nextInt(25)+20 );
	    }

	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		time.update();
		me.update(time.getTime(), balls, pressed);
		me.drawSelf(g);
		for(int c = 0; c < balls.length; c++) {
			balls[c].update(time.getTime(), balls);
			balls[c].drawSelf(g);
		}
		fps.update();
		fps.drawSelf(g);
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent key) {
		char keyChar = key.getKeyChar();
		if(keyChar == 'w') {
			pressed.add('w');
			if(pressed.contains('s')) {
				pressed.remove('s');
			}
		}
		else if(keyChar == 's') {
			pressed.add('s');
			if(pressed.contains('w')) {
				pressed.remove('w');
			}
		}
		else if(keyChar == 'a') {
			pressed.add('a');
			if(pressed.contains('d')) {
				pressed.remove('d');
			}
		}
		else if(keyChar == 'd') {
			pressed.add('d');
			if(pressed.contains('a')) {
				pressed.remove('a');
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent key) {
		char keyChar = key.getKeyChar();
		if(keyChar == 'w' && pressed.contains('w')) {
			pressed.remove('w');
		}
		if(keyChar == 's' && pressed.contains('s')) {
			pressed.remove('s');
		}
		if(keyChar == 'a' && pressed.contains('a')) {
			pressed.remove('a');
		}
		if(keyChar == 'd' && pressed.contains('d')) {
			pressed.remove('d');
		}	
	}
	@Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub	
	}
}

