/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Bartlomiej_Kedziora
 */
public class DrawLotsMachine {

  private String path;
  private String delimiter;
  private int amountOfWinners;
  private Set<String> winners;
  private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
  List<String> poolList;

  public DrawLotsMachine() {
    delimiter = ",";
  }

  public void setPathToFile(String path) {
    this.path = path;
  }

  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }

  public void setAmountOfWinners(int amountOfWinners) {
    if (amountOfWinners < 1) {
      this.amountOfWinners = 1;
    } else {
      this.amountOfWinners = amountOfWinners;
    }
  }

  public void showWinners() {
    try {
      parseFromCSV();
      randomWinners();
      System.out.println("Today the Winner(s) is(are): ");
      for (Iterator<String> i = winners.iterator(); i.hasNext();) {
        String item = i.next();
        System.out.println(item);
      }
    } catch (IndexOutOfBoundsException e) {
      ;
    }
  }

  private boolean emailValidate(String email) {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
    return matcher.find();
  }

  private void parseFromCSV() throws IndexOutOfBoundsException {
    CSVParser parser = new CSVParser(path, delimiter);
    List<String[]> DataFromFile = parser.getContent();
    Map<String, String> NameEmailMap = new HashMap<>();
    int namePosition = 1;
    int emailPosition = 2;
    int recommendPosition = 4;
    poolList = new ArrayList<>();
    try {
      for (Iterator<String[]> i = DataFromFile.iterator(); i.hasNext();) {
        String[] items = i.next();
        String name = items[namePosition].replace("\"", "");
        String email = items[emailPosition].replace("\"", "");

        if (emailValidate(email)) {
          NameEmailMap.put(name, email);
          poolList.add(email);

          if (!"\"\"".equals(items[recommendPosition])) {
            poolList.add(NameEmailMap.get(items[recommendPosition].replace("\"", "")));
          }
        }
      }
    } catch (IndexOutOfBoundsException e) {
      ;
    }
  }

  private void randomWinners() {
    winners = new HashSet<>();
    int randomIndex;
    while (winners.size() < amountOfWinners) {
      randomIndex = (int) (Math.random() * poolList.size());
      winners.add(poolList.get(randomIndex));
    }
  }
}
