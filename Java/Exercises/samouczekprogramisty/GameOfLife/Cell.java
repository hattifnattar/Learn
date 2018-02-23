/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn;

import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Bartlomiej_Kedziora
 */
public class Cell {

  private final int rowIndex;
  private final int columnIndex;

  private CellState state;

  public enum CellState {
    LIVE, DEAD
  }

  private Cell(CellState state, int posx, int posy) {
    this.state = state;
    columnIndex = posx;
    rowIndex = posy;
  }

  public static Cell live(int posx, int posy) {
    return new Cell(CellState.LIVE, posx, posy);
  }

  public static Cell dead(int posx, int posy) {
    return new Cell(Cell.CellState.DEAD, posx, posy);
  }

  public void draw(int boardSize, Set<Cell> liveCells) {
    int amountOfLiveNeighbors = 0;
    int[] moveArray = {-1, 0, 1};
    for (int hopCols : moveArray) {
      for (int hopRows : moveArray) {
        for (Iterator<Cell> k = liveCells.iterator(); k.hasNext();) {
          Cell item = k.next();
          if (hopCols == 0 && hopRows == 0) {
            continue;
          } else {
            if (checkNeighborMarginRule(rowIndex + hopRows, boardSize) == item.getRowIndex()
                    && checkNeighborMarginRule(columnIndex + hopCols, boardSize) == item.getColumnIndex()) {
              amountOfLiveNeighbors++;
            }
          }
        }
      }
    }
    if (state == CellState.LIVE) {
      if (amountOfLiveNeighbors < 2 || amountOfLiveNeighbors > 3) {
        state = CellState.DEAD;
      }
    } else if (state == CellState.DEAD) {
      if (amountOfLiveNeighbors == 3) {
        state = CellState.LIVE;
      }
    }
  }

  private int checkNeighborMarginRule(int newarg, int margin) {
    if (newarg < 0) {
      return margin - 1;
    }
    if (newarg >= margin) {
      return 0;
    }
    return newarg;
  }

  public int getRowIndex() {
    return rowIndex;
  }

  public int getColumnIndex() {
    return columnIndex;
  }

  public CellState getState() {
    return state;
  }
}