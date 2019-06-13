/**
 * RandomMaze.java
 * Version 1.0
 * This public class generates a random maze
 */

import java.util.Random;

public class RandomMaze{
  private int size = 11;
  private int mapSize, turretCount;
  private char[][] map;
  private Random rnd = new Random();
  private MazeCell[][] cellGrid;
  
  public static void main(String[] args){
    new RandomMaze(10);
  }
  
  public char[][] getCharMap(){
    return map;
  }
  
  RandomMaze(int turretCount){
    this.turretCount = turretCount;
    genCellGrid();
    generatePaths();
    genCharMap();
  }
  
  private void genCellGrid(){

    cellGrid = new MazeCell[size][size];
    for (int i = 0; i < size; i++){
      for (int j = 0; j < size; j++){
        cellGrid[i][j] = new MazeCell();
      }
    }

    // vert links
    for (int row = 1; row < size; row++){
      for (int col = 0; col < size; col++){
        cellGrid[row][col].north = cellGrid[row - 1][col];
        cellGrid[row][col].addLink("N");
        cellGrid[row - 1][col].south = cellGrid[row][col];
        cellGrid[row - 1][col].addLink("S");
      }
    }

    // horz links
    for (int col = 1; col < size; col++){
      for (int row = 0; row < size; row++){
        cellGrid[row][col].west = cellGrid[row][col - 1];
        cellGrid[row][col].addLink("W");
        cellGrid[row][col - 1].east = cellGrid[row][col];
        cellGrid[row][col - 1].addLink("E");
      }
    }
  }
  
  private void generatePaths(){
    cellGrid[0][0].startLinkPath();
    while (!checkVisited()){
      for (int row = 0; row < size; row++){
        for (int col = 0; col < size; col++){
          if (!cellGrid[row][col].checkLinksVisited()){
            cellGrid[row][col].startLinkPath();
          }
        }
      }
    }
  }
  
  private boolean checkVisited(){
    for (int row = 0; row < size; row++){
      for (int col = 0; col < size; col++){
        if (!cellGrid[row][col].visited){
          return false;
        }
      }
    }
    return true;
  }
  
  private void buildCharCell(char c){
    int x = rnd.nextInt(size);
    int y = rnd.nextInt(size);
    while (map[x][y] != ' '){ // don't overwrite
      x = rnd.nextInt(size);
      y = rnd.nextInt(size);
    }
    map[x * 2 + 1][y * 2 + 1] = c;
  }
  
  private void buildCharWall(char c){
    int x = rnd.nextInt(mapSize);
    int y = rnd.nextInt(mapSize);
    while (map[x][y] != 'W'){ // don't overwrite
      x = rnd.nextInt(mapSize);
      y = rnd.nextInt(mapSize);
    }
    map[x][y] = c;
  }
  
  private void buildKey(){
    int x = rnd.nextInt(size);
    int y = rnd.nextInt(size);
    while (map[x][y] != ' '){ // don't overwrite
      x = rnd.nextInt(size);
      y = rnd.nextInt(size);
    }
    int mapX = x * 2 + 1;
    int mapY = y * 2 + 1;
    map[mapX][mapY] = 'k';
    map[mapX + 1][mapY + 1] = 't'; // overwrites turrets
    map[mapX + 1][mapY - 1] = 't';
    map[mapX - 1][mapY + 1] = 't';
    map[mapX - 1][mapY - 1] = 't';
  }
  
  private void genCharMap() {
    mapSize = size * 2 + 1;
    map = new char[mapSize][mapSize];

    // central walls
    for (int row = 1; row < mapSize - 1; row++) {
      for (int col = 1; col < mapSize - 1; col++) {
        map[row][col] = 'W';
      }
    }

    // paths
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        String links = cellGrid[row][col].openLinks;
        int mapRow = row * 2 + 1;
        int mapCol = col * 2 + 1;
        map[mapRow][mapCol] = ' ';
        if (!links.contains("N")) {
          map[mapRow - 1][mapCol] = ' ';
        }
        if (!links.contains("E")) {
          map[mapRow][mapCol + 1] = ' ';
        }
        if (!links.contains("S")) {
          map[mapRow + 1][mapCol] = ' ';
        }
        if (!links.contains("W")) {
          map[mapRow][mapCol - 1] = ' ';
        }
      }
    }

    // borders (overwrites paths)
    for (int row = 0; row < mapSize; row++) {
      map[row][0] = 'W';
      map[row][mapSize - 1] = 'W';
    }
    for (int col = 0; col < mapSize; col++) {
      map[0][col] = 'W';
      map[mapSize - 1][col] = 'W';
    }

    for (int i = 0; i < 3; i++) {
      buildCharCell('n'); // cell
    }
    buildCharCell('c'); // charger
    buildCharCell('l'); // locked

    for (int i = 0; i < turretCount; i++) {
      buildCharWall('t'); // turret
    }

    buildKey(); // key

    // printing
    for (int i = 0; i < mapSize; i++) {
      for (int j = 0; j < mapSize; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.println();
    }
  }
  
  
}