package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements KeyListener, ActionListener {
	private final int TILE_SIZE = 20;
	private final int BOARD_WIDTH = TILE_SIZE * 30; // Tilesize * number of columns
	private final int BOARD_HEIGHT = TILE_SIZE * 30;
	private final int ALL_TILES = 900; // Total number of tiles
	private final int DELAY = 100;

	/* The coordinates of the snake. */
	private int[] xCoor = new int[ALL_TILES];
	private int[] yCoor = new int[ALL_TILES];

	/* Coordinates for apple. */
	private int apple_x, apple_y;

	/* Pressed Key. */
	int pressedKey;
	int oldPressedKey;
	int snakeSize;
	boolean inGame;
	String connectedMapName;
	Timer t;

	SnakePanel() {
		pressedKey = KeyEvent.VK_DOWN;
		snakeSize = 3;
		inGame = true;
		setFocusable(true);
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		setBackground(Color.black);
		addKeyListener(this);
		t = new Timer(100, this);
		//t.start();
		// Set snake starting coordinates.
		for (int i = 0; i < snakeSize; i++) {
			yCoor[i] = 140 - (i * 30);
			xCoor[i] = 140;
		}
		spawnAppleCoor();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (inGame) {
			/* Draw apple. */
			g.setColor(Color.red);
			g.fillRect(apple_x, apple_y, TILE_SIZE, TILE_SIZE);
			/* Draw snake. */
			for (int i = 0; i < snakeSize; i++) {
				if (i == 0) {
					g.setColor(Color.yellow); // Snakes head yellow
				} else {
					g.setColor(Color.green);
				}
				g.fillRect(xCoor[i], yCoor[i], TILE_SIZE, TILE_SIZE);
			}
			/* Draw score */
			g.setFont(new Font("Sans serif", Font.BOLD, 20));
			g.drawString(getScore(), 550, 30);
			g.setColor(Color.white);
			g.drawRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		} else {
			g.setColor(Color.white);
			g.setFont(new Font("Comic Sans", Font.BOLD, 20));
			g.drawString(("Game Over! You ate " + (getScore()) + " apples!"), BOARD_WIDTH / 4, BOARD_HEIGHT / 2);
			g.drawString("Press space to exit", BOARD_WIDTH / 4 + 20, BOARD_HEIGHT / 2 + 30);
			if(pressedKey == KeyEvent.VK_SPACE) {
				quitGame();
			}
		}
	}
	public void quitGame() {
	}

	public void actionPerformed(ActionEvent e) {
//		System.out.println("action");
		checkTile();
		moveSnakeCoor();
		repaint();
	}

	/* Saves pressedKeyCode to pressedKey. */
	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println("key was pressed");
		oldPressedKey = pressedKey;
		pressedKey = e.getKeyCode();
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}

	private void checkTile() {
		/* Check if outside of wall. */
		if (xCoor[0] > BOARD_WIDTH || xCoor[0] < 0 || yCoor[0] > BOARD_HEIGHT || yCoor[0] < 0) {
			inGame = false;
		}
		/* Check for collisions. */
		for (int i = 1; i < xCoor.length; i++) {
			if (xCoor[0] == xCoor[i] && yCoor[0] == yCoor[i]) {
				inGame = false;
			}
		}
		/* Check for apples. */
		if ((xCoor[0] == apple_x) && (yCoor[0] == apple_y)) {
			snakeSize++;
			spawnAppleCoor();
		}
	}

	/** Generates random coordinates for apple. */
	private void spawnAppleCoor() {
		int r = (int) (Math.random() * Math.sqrt(ALL_TILES) - 1);
		apple_x = ((r * TILE_SIZE));

		r = (int) (Math.random() * Math.sqrt(ALL_TILES) - 1);
		apple_y = ((r * TILE_SIZE));
	}

	private void moveSnakeCoor() {
		/* Move coordinates up one in the matrix. */
		for (int i = snakeSize; i > 0; i--) {
			xCoor[i] = xCoor[(i - 1)];
			yCoor[i] = yCoor[(i - 1)];
		}
		/*
		 * Depending on what key was pressed, change coordinates accordingly.
		 */
		switch (pressedKey) {
		case KeyEvent.VK_DOWN:
			yCoor[0] += TILE_SIZE;
			break;
		case KeyEvent.VK_UP:
			yCoor[0] -= TILE_SIZE;
			break;
		case KeyEvent.VK_LEFT:
			xCoor[0] -= TILE_SIZE;
			break;
		case KeyEvent.VK_RIGHT:
			xCoor[0] += TILE_SIZE;
			break;
		}
	}

	private String getScore() {
		return "" + (snakeSize - 3);
	}

}
