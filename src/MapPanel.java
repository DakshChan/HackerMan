import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class MapPanel extends JPanel {
	Entity[][] map;
	Entity[][] lasers;
	Guard[] guards;

	MapPanel(Entity[][] map, Entity[][] lasers, Guard[] guards) {
		this.map = map;
		this.lasers = lasers;
		this.guards = guards;
		setFocusable(true);
		requestFocusInWindow();
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int hc = 0; hc < map.length; hc++) {
			for (int vc = 0; vc < map[0].length; vc++) {
				if (map[hc][vc] instanceof LaserNode) {
					if (((LaserNode) map[hc][vc]).hacked == false) {
						((LaserNode) map[hc][vc]).connect(map, lasers);
					}
					((LaserNode) map[hc][vc]).drawSelf(g);
				} else if (map[hc][vc] != null) {
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
		repaint();
	}

}
