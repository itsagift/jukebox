import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
public class PurchaseQueue {
  private Deque<String[]> queue;
  private SongList songList;
  private Scanner scan;
  private BalanceBox balance;
  
    public PurchaseQueue(String filename, Scanner scanArg, BalanceBox balanceArg){
      this.balance = balanceArg;
      this.songList = new SongList(filename);
      this.queue = new ArrayDeque<>();
      this.scan = scanArg;
    }
  
    public void takeSongIndex(){
      System.out.print("Enter a song index: ");
      String indexString = scan.nextLine();
      boolean conditionMet = false;
      while (!conditionMet){
        try {
          int index = Integer.parseInt(indexString);
          String[] song = songList.getIndividualSong(index);
          int songCost = Integer.parseInt(song[2]);
          if (balance.deductFunds(songCost)){
            queue.addLast(song);
            System.out.printf("Song: %s \n", Arrays.toString(song));
          } 
          else {
            conditionMet = true;
            System.out.println("Not enough funds");
          }
        } catch (NumberFormatException e){
          System.out.println("Must enter a number");
        }
      }
    }
    
}

/**
 * We need to get each song as its own individual piece of data
 * And then charge based on that
 */