import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

public class MapPanel extends JPanel implements KeyListener {
	boolean ingame;
	StackPanel parent;
	Entity[][] map;
	Entity[][] lasers;
	Guard[] guards;
	Set<Character> pressed;
	Player player;

	MapPanel(Entity[][] map, Guard[] guards, StackPanel parent) {
		ingame = true;
		this.map = map;
		this.lasers = new Entity[map.length][map[0].length];
		this.guards = guards;
		this.parent = parent;
		this.player = new Player(0, 0, 0);
		pressed = new HashSet<Character>();
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (ingame == true) {
			player.update(pressed);
			player.drawSelf(g);
			for (int hc = 0; hc < map.length; hc++) {
				for (int vc = 0; vc < map[0].length; vc++) {
					if (map[hc][vc] instanceof LaserNode) {
						if (((LaserNode) map[hc][vc]).hacked == false) {
							((LaserNode) map[hc][vc]).connect(map, lasers);
						} 
						else if (((LaserNode) map[hc][vc]).hacked == true && ((LaserNode) map[hc][vc]).connected.size() > 0) {
							((LaserNode) map[hc][vc]).disconnect(map, lasers);
						}
						((LaserNode) map[hc][vc]).drawSelf(g);
					} 
					else if (map[hc][vc] instanceof Terminal) {
						((Terminal) map[hc][vc]).drawSelf(g);
					} 
					else if (map[hc][vc] instanceof WarpTile) {
						((WarpTile) map[hc][vc]).drawSelf(g);
						if (((WarpTile) map[hc][vc]).warp == true) {
							pressed.clear();
							((WarpTile) map[hc][vc]).warp = false;
							CardLayout savedLayout = (CardLayout)parent.getLayout();
							String mapname = ((WarpTile) map[hc][vc]).connectedMapName;
							((MapPanel)parent.getComponentByName(mapname)).player.x = ((WarpTile) map[hc][vc]).connectedX;
							((MapPanel)parent.getComponentByName(mapname)).player.y = ((WarpTile) map[hc][vc]).connectedY;
							savedLayout.show(parent, mapname);
							((JPanel)parent.getComponentByName(mapname)).requestFocusInWindow();
						}
					}
					else if (map[hc][vc] != null) {
						g.setColor(Color.BLACK);
						g.fillRect(map[hc][vc].x * Entity.Size, map[hc][vc].y * Entity.Size, Entity.Size, Entity.Size);
					}
					if (lasers[hc][vc] instanceof LaserBeam) {
						((LaserBeam) lasers[hc][vc]).drawSelf(g);
					}
				}
			}
			for (int c = 0; c < guards.length; c++) {
				if (guards[c] != null) {
					guards[c].followPath();
					guards[c].setSightLine(map);
					guards[c].drawSelf(g);
				}
			}
		} else {
			g.setColor(Color.white);
			g.setFont(new Font("Comic Sans", Font.BOLD, 20));
			g.drawString("Game Over!", Toolkit.getDefaultToolkit().getScreenSize().height / 4, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
		}
		repaint();
	}

	public void updateMap(Entity[][] map) {
		this.map = map;
	}

	@Override
	public void keyPressed(KeyEvent key) {
		char keyChar = key.getKeyChar();
		if (keyChar == 'w') {
			pressed.add('w');
		} 
		else if (keyChar == 's') {
			pressed.add('s');
		} 
		else if (keyChar == 'a') {
			pressed.add('a');
		} 
		else if (keyChar == 'd') {
			pressed.add('d');
		} 
		else if (keyChar == 'e') {
			pressed.add('e');
		}

	}

	@Override
	public void keyReleased(KeyEvent key) {
		char keyChar = key.getKeyChar();
		if (keyChar == 'w' && pressed.contains('w')) {
			pressed.remove('w');
		} 
		else if (keyChar == 's' && pressed.contains('s')) {
			pressed.remove('s');
		} 
		else if (keyChar == 'a' && pressed.contains('a')) {
			pressed.remove('a');
		} 
		else if (keyChar == 'd' && pressed.contains('d')) {
			pressed.remove('d');
		} 
		else if (keyChar == 'e') {
			pressed.remove('e');
		}
	}

	@Override
	public void keyTyped(KeyEvent key) {
	}

}
