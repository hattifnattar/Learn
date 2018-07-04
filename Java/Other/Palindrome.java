package learn;

/**
 *
 * @author Bartlomiej_Kedziora
 */
public class Palindrome {
  public boolean palindrome(String word){
    String[] array = word.split("");
    for(int i=0;i<array.length;i++){
      if(!array[i].equals(array[array.length-1-i])){
        return false;
      }
      if(i == (array.length/2)+1){
          return true;
      }
    }
    return true;
  }
}
//new comment