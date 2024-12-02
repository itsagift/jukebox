import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class PurchaseQueue {
  private Deque<String[]> queue;
  private SongList songList;
  private Scanner scan;
  private BalanceBox balance;
  

  public PurchaseQueue(SongList songList, BalanceBox balanceBox) {
    this.balance = balanceBox;
    this.songList = songList;
    this.queue = new ArrayDeque<>();
  }

  public void takeSongIndex(int songIndex, String sortBy, boolean addFirst) {
    // System.out.println(songList);
    // System.out.print("Enter a song index: ");
    // String indexString = scan.nextLine();
    try {
      // int index = Integer.parseInt(indexString);
      String[] song = songList.getIndividualSong(songIndex);
      if (sortBy.equals("Title")) {
        song = songList.getSongInfoByTitle()[songIndex];
      } else if (sortBy.equals("Artist")) {
        song = songList.getSongInfoByArtist()[songIndex];
      }
      int songCost = Integer.parseInt(song[2]);
      if (balance.deductFunds(songCost + (addFirst ? 5 : 0))) {
        if (addFirst) {
          queue.addFirst(song);
        } else {
          queue.addLast(song);
        }
        // System.out.printf("Added song to queue: %s \n", song[SongList.SONG_TITLE]);
      } else {
        System.out.println("Not enough funds");
      }
    } catch (NumberFormatException e) {
      System.out.println("Must enter a valid song number");
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Must enter a valid song number");
    }
  }

  public void acceptFunds() {
    balance.acceptFunds("", "");
  }

  public void returnFunds() {
    balance.returnFunds();
  }

  public String[] nextSong() {
    return queue.pop();
  }

  public boolean hasNextSong() {
    return queue.size() > 0;
  }

  public int getQueueLength() {
    return queue.size();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (String[] song : queue) {
      sb.append(song[SongList.SONG_TITLE]);
      sb.append("\n");
    }
    return sb.toString();
  }

  public void playlist(){
    
  }

  public static void main(String[] args) {
    // Scanner scan = new Scanner(System.in);
    // PurchaseQueue purchaseQueue = new PurchaseQueue("songListTest.csv", scan);
    // while (true) {
    //   System.out.print(
    //       "Enter 1 to add funds, 2 to purchase a song, 3 to print the song queue, 4 to receive a refund, 5 to play a song, 6 to quit: ");
    //   String input = scan.nextLine();
    //   switch (input) {
    //     case "1":
    //       purchaseQueue.acceptFunds();
    //       break;
    //     case "2":
    //       purchaseQueue.takeSongIndex(2);
    //       break;
    //     case "3":
    //       System.out.println(purchaseQueue);
    //       break;
    //     case "4":
    //       purchaseQueue.returnFunds();
    //       break;
    //     case "5":
    //       if (purchaseQueue.hasNextSong()) {
    //         System.out.println("buying");
    //         SongPlayerFX.launch(SongPlayerFX.class, purchaseQueue.nextSong());
    //       } else {
    //         System.out.println("No songs in queue");
    //       }
    //       break;
    //     case "6":
    //       return;
    //     default:
    //       System.out.println("Invalid option");
    //   }
    // }
  }

}