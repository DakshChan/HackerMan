public class Main {
	public static void main(String[] args) {
		String[][] map;
		try {
			map = MapLoader.load("C:\\Users\\daksh\\IdeaProjects\\HackerMan\\src\\map.txt");
		} catch (Exception e){
			System.out.println(e);
			return;
		}

		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map.length; j++){
				System.out.print(map[i][j]+" ");
			}
			System.out.println("");
		}
	}
}