package src;

import java.awt.Toolkit;
import java.util.Random;


public class DebugDisplay {
	public static void main (String[]args) {
		Random rand = new Random();
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
		Window window = new Window(map, laser, guards);
	}
}

/*
 * class MapPanel extends JPanel{ boolean focus; Entity[][] map; Entity[][]
 * lasers; Guard[] guards; MapPanel(Entity[][] map, Entity[][] lasers, Guard[]
 * guards){ this.focus = true; this.map = map; this.lasers = lasers; this.guards
 * = guards; setFocusable(true); requestFocusInWindow();
 * setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize()); }
 * 
 * @Override protected void paintComponent(Graphics g) { if(focus == true) {
 * super.paintComponent(g); for(int hc = 0; hc < map.length; hc++ ) { for(int vc
 * = 0; vc < map[0].length; vc ++) { if(map[hc][vc] instanceof LaserNode){
 * if(((LaserNode)map[hc][vc]).hacked == false){
 * ((LaserNode)map[hc][vc]).connect(map, lasers); }
 * ((LaserNode)map[hc][vc]).drawSelf(g); } else if(map[hc][vc] != null){
 * g.setColor(Color.BLACK); g.fillRect(map[hc][vc].x*Entity.Size,
 * map[hc][vc].y*Entity.Size, Entity.Size, Entity.Size); } if(lasers[hc][vc]
 * instanceof LaserBeam) { ((LaserBeam)lasers[hc][vc]).drawSelf(g); } } }
 * for(int c = 0; c < guards.length; c++) { if(guards[c] != null){
 * guards[c].followPath(); guards[c].setSightLine(map); guards[c].drawSelf(g); }
 * } repaint(); } } }
 */

