/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn;

import java.util.*;

/**
 *
 * @author Bartlomiej_Kedziora
 */
public class Learn {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    Board board = new Board(4, Cell.live(1, 0), Cell.live(1, 1), Cell.live(1, 2));
    board.playGame();
  }
}