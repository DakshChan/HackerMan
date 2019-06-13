/**
 * MazeGame.java
 * Version 1.0
 * Actual run code for the maze game
 */

class MazeGame{
  
  RandomMaze mazeGen;
  char[][] charMap;
  MazeEntity[][] mazeMap;
  MazeRunner player;
  
  MazeGame(){
    mazeGen = new RandomMaze(10);
    charMap = mazeGen.getCharMap();
    mazeMap = new MazeEntity[charMap[0].length][charMap[0].length];
  }
  
  void loadMazeMap(){
    
    // charger is the first to be checked for 
    for (int i = 0; i < charMap.length; i++){
      for (int j = 0; j < charMap.length; j++){
        if (charMap[i][j] == 'c'){
          mazeMap[i][j] = new MazeRunner(j, i);
          player = new MazeRunner(j, i);
          mazeMap[i][j] = new Charger(j, i, player);
        } 
      }
    }
    
    for (int i = 0; i < charMap.length; i++){
      for (int j = 0; j < charMap.length; j++){
        if (charMap[i][j] == 't'){
          mazeMap[i][j] = new Turret(j, i, player);
        } else if (charMap[i][j] == 'n'){
          mazeMap[i][j] = new Battery(j, i, player);
        } else if (charMap[i][j] == 'W'){
          mazeMap[i][j] = new MazeWall(j, i);
        } else if (charMap[i][j] == 'l'){
          mazeMap[i][j] = new Battery(j, i, player, true);
        }
      }
    }
  }
  
  void entityUpdates(){
    for (int i = 0; i < charMap.length; i++){
      for (int j = 0; j < charMap.length; j++){
        if (mazeMap[i][j] instanceof Battery){
          if (player.x == mazeMap[i][j].x && player.y == mazeMap[i][j].y){
            mazeMap[i][j].update();
          }
        } else if (mazeMap[i][j] instanceof Key){
          if (player.x == mazeMap[i][j].x && player.y == mazeMap[i][j].y){
            mazeMap[i][j].update();
          }
        } else if (mazeMap[i][j] instanceof Turret){
          mazeMap[i][j].update();
        }
      }
    }
  }
  
  void damageCheck(){
    
  }
  
  
  
  
}