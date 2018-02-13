/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Bartlomiej_Kedziora
 */
public class CSVParser {

  private List<String[]> ListOfLines;
  private List<String> ListOfHeader;

  public CSVParser(String filePath, String delimiter) {
    readCSVfromFile(filePath, delimiter);
  }

  public List<String[]> getContent() {
    return ListOfLines;
  }

  public List<String> getHeader() {
    return ListOfHeader;
  }

  private void readCSVfromFile(String filePath, String delimiter) {
    ListOfLines = new LinkedList<>();
    ListOfHeader = new LinkedList<>();
    int rows = 0;
    try {
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      String line;
      while ((line = br.readLine()) != null) {
        String[] cells = line.split(delimiter);
        rows++;
        if (rows == 1) {
          ListOfHeader.addAll(Arrays.asList(cells));
        } else {
          ListOfLines.add(cells);
        }
      }
      br.close();
    } catch (FileNotFoundException e) {
      System.out.println("You didn't set correct path to CSV file!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
