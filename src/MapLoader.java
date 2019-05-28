import java.io.File;
import java.util.Scanner;
public class MapLoader {
    public static String[][] load(String input)throws Exception{
        Scanner file = new Scanner(new File(input));
        int amount = 0;
        while(file.hasNext()) {
            file.nextLine();
            amount++;
        }
        file.close();
        String[][] map = new String[amount][amount];
        file = new Scanner(new File(input));
        for(int i = 0; i < amount; i++){
            for (int j = 0; j < amount; j++){
                map[i][j] = file.next();
            }
        }
        file.close();
        return map;
    }
}
