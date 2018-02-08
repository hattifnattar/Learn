/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn;

/**
 *
 * @author Bartlomiej_Kedziora
 */
public class ChristmasTree {
  private final int height;
  
  public ChristmasTree(int height) {
    if(height<0) {
      this.height = 1;
    }
    else {
      this.height = height;
    }
  }
  
  public String build(){
    char star = '*';
    char space= ' ';
    StringBuilder tree = new StringBuilder();
    for(int r=1;r<height+1;r++) {
      for(int k=1;k<(height+r);k++) {
        if(r<=(height-k)) {
          tree.append(space);
        }
        else
          tree.append(star);
      }
      tree.append(System.lineSeparator());
    }
    String christmasTree = tree.toString();
    return christmasTree;
  }
}
