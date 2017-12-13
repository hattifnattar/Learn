package learn;

/**
 *
 * @author Bartlomiej_Kedziora
 */
public class BubbleSort_2 {
  public int[] bubbleSort(int[] array){
    boolean notmoved = true;
    int tmp;
    while(notmoved){
      for(int i=0;i<(array.length-1);i++){
          if(array[i+1] < array[i]){
            tmp=array[i+1];
            array[i+1] = array[i];
            array[i] = tmp;
            notmoved = false;
          }
      }
      for(int i=0;i<(array.length-1);i++){
        if(array[i] > array[i+1]){
          notmoved = true;
        }
      }
    }
    return array;
  }
}
