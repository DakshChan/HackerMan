package src;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MapPanel extends JPanel implements KeyListener, ActionListener {
	boolean ingame;
	boolean startMinigame;
	StackPanel parent;
	Entity[][] map;
	Entity[][] lasers;
	Entity[][] guards;
	Player player;
	Timer t;
	Set<Character> pressed;

	/**
	 * @param map
	 * @param guards
	 * @param parent
	 */
	MapPanel(Entity[][] map, Entity[][] guards, StackPanel parent) {
		ingame = true;
		startMinigame = false;
		this.map = map;
		this.lasers = new Entity[map.length][map[0].length];
		this.guards = guards;
		this.parent = parent;
		this.player = new Player(0, 0, 0);
		this.t = new Timer(16,this);
		this.pressed = new HashSet<Character>();
		addKeyListener(this);
		setFocusable(true);
		t.start();
		requestFocusInWindow();
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
	}

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if (ingame == true) {
			for (int vc = 0; vc < map.length; vc++) {
				for (int hc = 0; hc < map[0].length; hc++) {
					if (map[hc][vc] instanceof LaserNode) {
						((LaserNode) map[hc][vc]).drawSelf(g);
					} 
					else if (map[hc][vc] instanceof Terminal) {
						((Terminal) map[hc][vc]).drawSelf(g);
					} 
					else if (map[hc][vc] instanceof WarpTile) {
						((WarpTile) map[hc][vc]).drawSelf(g);
					}
					if(guards[hc][vc] != null) {
						((Guard)guards[hc][vc]).drawSelf(g);
					}
					if (lasers[hc][vc] instanceof LaserBeam) {
						((LaserBeam)lasers[hc][vc]).drawSelf(g);
					}
				}
			}
			player.drawSelf(g);
		} else {
			this.setBackground(Color.BLACK);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (ingame == true) {
			player.update(pressed, map, guards);
			for (int vc = 0; vc < map.length; vc++) {
				for (int hc = 0; hc < map[0].length; hc++) {
					if (map[hc][vc] instanceof Terminal) {
						map[hc][vc].rejectPlayer(player);
					}
					if (map[hc][vc] instanceof LaserNode) {
						if (((LaserNode) map[hc][vc]).isHacked() == false) {
							((LaserNode) map[hc][vc]).connect(map, lasers);
						} 
						else if (((LaserNode) map[hc][vc]).isHacked() == true && ((LaserNode) map[hc][vc]).connected.size() > 0) {
							((LaserNode) map[hc][vc]).disconnect(map, lasers);
						}
						map[hc][vc].rejectPlayer(player);
					} 
					else if (map[hc][vc] instanceof WarpTile) {
						if (((WarpTile) map[hc][vc]).isWarp() == true) {
							pressed.clear();
							((WarpTile) map[hc][vc]).setWarp(false);
							CardLayout savedLayout = (CardLayout) parent.getLayout();
							String mapname = ((WarpTile) map[hc][vc]).getConnectedMapName();
							((MapPanel) parent.getComponentByName(mapname)).player.setX(((WarpTile) map[hc][vc]).getConnectedX());
							((MapPanel) parent.getComponentByName(mapname)).player.setY(((WarpTile) map[hc][vc]).getConnectedY());
							savedLayout.show(parent, mapname);
							((JPanel) parent.getComponentByName(mapname)).requestFocusInWindow();
						}
					}
					if (guards[hc][vc] != null) {
						((Guard) guards[hc][vc]).followPath();
						((Guard) guards[hc][vc]).setSightLine(map);
						((Guard) guards[hc][vc]).killPlayer(player, this);
					}
					if (lasers[hc][vc] instanceof LaserBeam) {
						((LaserBeam) lasers[hc][vc]).killPlayer(player, this);
					}
				}
			}
		}
	}

}
