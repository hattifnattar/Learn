/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Bartlomiej_Kedziora
 */
public class DrawLotsMachine {
  private String path;
  private int amountOfWinners;
  private Set<String> winners;
  private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
  
  public DrawLotsMachine(){
    setPathToFile();
    setAmountOfWinners();
    readCSVfromFile();
    parseOutFromCSV();
    randomWinners();
  }
  private void setPathToFile(){
    System.out.println("Please set path to the CSV file: ");
    Scanner scanner = new Scanner(System.in);
    path = scanner.nextLine();
    //path = "D:\\bin\\22_zadanie_maszyna_losujaca_input.csv";
  }
  private void setAmountOfWinners(){
    System.out.println("Please set amount of winners");
    Scanner scanner = new Scanner(System.in);
    amountOfWinners = scanner.nextInt();
    if (amountOfWinners < 1) {
      amountOfWinners = 1;
    }
  }
  private boolean emailValidate(String email) {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
    return matcher.find();
  }
  private List<String> readCSVfromFile(){
    List<String> fileContentList = new LinkedList<>();
    try {
          BufferedReader br = new BufferedReader(new FileReader(path));
          String line;
          while ( (line = br.readLine()) != null){
            fileContentList.add(line);
          }
          br.close(); 
        } catch (FileNotFoundException e) {
          System.out.println("You didn't set correct path to CSV file!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    return fileContentList;
  }
  private List<String> parseOutFromCSV(){
    Map<String, String> NameEmailMap = new HashMap<>();
    int amountOfFieldsInOneCSVLine = readCSVfromFile().get(0).replaceAll("[^,]", "").length()+1;
    int namePosition = 1;
    int emailPosition = 2;
    int recommendPosition = 4;
    String[] splittedArray = new String[amountOfFieldsInOneCSVLine];
    List<String> poolList = new ArrayList<>();
    try {
      for (Iterator<String> i = readCSVfromFile().iterator(); i.hasNext();) {
        String item = i.next();
        splittedArray = item.split(",");
        String name = splittedArray[namePosition].replace("\"", "");
        String email = splittedArray[emailPosition].replace("\"", "");
        if(emailValidate(email)) {
          NameEmailMap.put(name, email);
          poolList.add(email);

          if (!"\"\"".equals(splittedArray[recommendPosition])) {
            poolList.add(NameEmailMap.get(splittedArray[recommendPosition].replace("\"", "")));
          }
        }
      }
    } catch (IndexOutOfBoundsException e) {
      System.out.println("");
    }
    return poolList;
  }
  private void randomWinners(){
    winners = new HashSet<>();
    int randomIndex;
    while(winners.size() < amountOfWinners) {
      randomIndex = (int)(Math.random()*parseOutFromCSV().size());
      winners.add(parseOutFromCSV().get(randomIndex));
    }
  }
  public void showWinners(){
    System.out.println("Today the Winner(s) is(are): ");
    for(Iterator<String> i = winners.iterator(); i.hasNext();) {
      String item = i.next();
      System.out.println(item);
    }
  }
}
