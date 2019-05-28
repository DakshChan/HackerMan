import java.io.File;
import java.util.Scanner;

public class LoadMap {

	public static void main(String[] args) throws Exception {
		Scanner fileIn = new Scanner(new File("fileName"));
		String[]line  = fileIn.nextLine().split(" ",0);//takes the first line of the file, splits it, and finds the length
		int size = line.length;

	}

}
