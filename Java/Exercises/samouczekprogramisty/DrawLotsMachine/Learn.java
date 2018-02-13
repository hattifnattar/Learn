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

    Scanner scanner = new Scanner(System.in);
    DrawLotsMachine dlm = new DrawLotsMachine();
    System.out.print("Please set path to the CSV file: ");
    dlm.setPathToFile(scanner.nextLine());
    System.out.print("Please set amount of winners: ");
    dlm.setAmountOfWinners(scanner.nextInt());
    dlm.showWinners();
  }
}
