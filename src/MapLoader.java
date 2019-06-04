//30x30 maps

import java.io.File;
import java.util.Scanner;
public class MapLoader {
	public static Entity[][] loadMap(String input)throws Exception{
		Scanner file = new Scanner(new File(input));
		int amount = 0;
		while(file.hasNext()) {
			file.nextLine();
			amount++;
		}
		file.close();
		String[][] stringMap = new String[amount][amount];
		file = new Scanner(new File(input));
		for(int i = 0; i < amount; i++){
			for (int j = 0; j < amount; j++){
				stringMap[i][j] = file.next();
			}
		}
		file.close();

		Entity[][] map = new Entity[amount][amount];
		for(int i = 0; i < amount; i++){
			for(int j = 0; j < amount; j++){
				map[i][j] = entityParser(stringMap[i][j],i,j);
			}
		}
		return map;
	}

	public static Entity entityParser(String toParse,int i,int j) {
		//Entity Parsing
		String[] temp = toParse.split("/");
		Entity entity;
		// terminal/int facing/int tier/int type
		// terminal/0/0/0
		if(temp[0].equals("terminal")){
			System.out.println("terminal");
			entity = new Terminal(i,j,Integer.parseInt(temp[1]),Integer.parseInt(temp[2]),Integer.parseInt(temp[3]));
		}
		// lasernode/int facing
		// lasernode/0
		else if (temp[0].equals("lasernode")){
			System.out.println("laser node");
			entity = new LaserNode(i,j,Integer.parseInt(temp[1]));
		}
		// null
		else if (temp[0].equals("null")){
			System.out.println("null");
			entity = null;
		}
		else {
			entity = null;
		}

		return entity;
	}
}
