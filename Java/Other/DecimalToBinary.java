package learn;

/**
 *
 * @author Bartlomiej_Kedziora
 */
public class DecimalToBinary {
  public String d2b(int no){
    String result = "";
    int tmp;
    while(no !=0) {
      tmp = no%2;
      no = no/2;
      if(tmp == 0){
        result="0"+result;
      }
      else {
        result="1"+result;
      }
    }
    return result;
  }
}
