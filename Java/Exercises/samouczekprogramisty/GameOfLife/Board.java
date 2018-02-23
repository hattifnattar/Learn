/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Bartlomiej_Kedziora
 */
public class Board {

  private final int boardSize;
  private Set<Cell> Cells;
  private final Cell[][] board;

  public Board(int boardSize, Cell... cells) {
    this.boardSize = boardSize;
    board = new Cell[boardSize][boardSize];
    Cells = new HashSet<>();
    for (Cell cell : cells) {
      Cells.add(cell);
      board[cell.getColumnIndex()][cell.getRowIndex()] = cell;
    }
    fillBoard();
  }

  private void printBoard(Cell.CellState state, int cell) {
    System.out.print("|" + state);
    if (cell + 1 == boardSize) {
      System.out.println("|");
    }
  }

  private void printHeader() {
    for (int h = 0; h < boardSize; h++) {
      System.out.print("   " + h + " ");
    }
    System.out.println();
  }

  private void fillBoard() {
    printHeader();
    for (int r = 0; r < boardSize; r++) {
      System.out.print(r);
      for (int c = 0; c < boardSize; c++) {
        if (board[c][r] == null) {
          board[c][r] = Cell.dead(c, r);
        }
        printBoard(board[c][r].getState(), c);
      }
    }
    System.out.println();
  }

  public void playGame() {
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    clearConsole();
    printHeader();
    Set newCells = new HashSet<>();
    for (int r = 0; r < boardSize; r++) {
      System.out.print(r);
      for (int c = 0; c < boardSize; c++) {
        board[c][r].draw(boardSize, Cells);
        if (board[c][r].getState() == Cell.CellState.LIVE) {
          newCells.add(board[c][r]);
        }
        printBoard(board[c][r].getState(), c);
      }
    }
    Cells.clear();
    Cells = newCells;
    System.out.println();
    if (Cells.size() > 0) {
      playGame();
    }
  }

  public final static void clearConsole() {
    try {
      final String os = System.getProperty("os.name");
      if (os.contains("Windows")) {
        Runtime.getRuntime().exec("cmd /c cls");
      } else {
        Runtime.getRuntime().exec("clear");
      }
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }
}