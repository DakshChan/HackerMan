/**
 * MazeCell.java
 * Version 1.0
 * This public class links together tiles for the maze generation
 */

import java.util.Random;

public class MazeCell{
  public MazeCell north, east, south, west;
  public String openLinks = "";
  public boolean visited;
  
  private Random rnd = new Random();
  
  public void addLink(String link){
    openLinks += link;
  }
  
  public void removeLink(String link){
    openLinks = openLinks.replace(link, "");
  }
  
  public boolean checkLinksVisited(){
    if (!visited){
      return false;
    } else if (north != null && !north.visited){
      return false;
    } else if (east != null && !east.visited){
      return false;
    } else if (south != null && !south.visited){
      return false;
    } else if (west != null && !west.visited){
      return false;
    } return true;
  }
  
  public void startLinkPath(){
    visited = false;
    linkPath();
  }
  
  public void linkPath(){
    if (visited){
      return;
    } else {
      visited = true;
    }
    
    int choiceIndex = rnd.nextInt(openLinks.length());
    String choice = "" + openLinks.charAt(choiceIndex);
    removeLink(choice);
    
    if (choice.equals("N")){
      north.removeLink("S");
      north.linkPath();
      
    } else if (choice.equals("E")){
      east.removeLink("W");
      east.linkPath();
      
    } else if (choice.equals("S")){
      south.removeLink("N");
      south.linkPath();
      
    } else if (choice.equals("W")){
      west.removeLink("E");
      west.linkPath();
    }
  }
}

