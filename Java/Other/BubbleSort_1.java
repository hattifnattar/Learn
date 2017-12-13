package learn;

/**
 *
 * @author Bartlomiej_Kedziora
 */
public class BubbleSort_1 {
  public int[] bubbleSort(int[] array){
    int tmp;
    int n = array.length;
    while(n>0) {
      for(int i=0;i<(array.length-1);i++){
        if(array[i+1] < array[i]){
          tmp=array[i+1];
          array[i+1] = array[i];
          array[i] = tmp;
          }
      }
      n=n-1;
    }
    return array;
  }
}
