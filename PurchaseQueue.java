import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class PurchaseQueue {
  private Deque<String[]> queue;
  private SongList songList;
  private Scanner scan;
  private BalanceBox balance;
  private SongPlayer songPlayer;

  public PurchaseQueue(String filename, Scanner scanArg) {
    this.balance = new BalanceBox(scanArg);
    this.songList = new SongList(filename);
    this.queue = new ArrayDeque<>();
    this.scan = scanArg;
    this.songPlayer = new SongPlayer();
  }

  public void takeSongIndex() {
    System.out.println(songList);
    System.out.print("Enter a song index: ");
    String indexString = scan.nextLine();
    try {
      int index = Integer.parseInt(indexString);
      String[] song = songList.getIndividualSong(index);
      int songCost = Integer.parseInt(song[2]);
      if (balance.deductFunds(songCost)) {
        queue.addLast(song);
        System.out.printf("Added song to queue: %s \n", song[SongList.SONG_TITLE]);
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
    balance.acceptFunds();
  }

  public void returnFunds() {
    balance.returnFunds();
  }

  public String nextSong() {
    return queue.pop()[SongList.SONG_URI];
  }

  public boolean hasNextSong() {
    return queue.size() > 0;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (String[] song : queue) {
      sb.append(song[SongList.SONG_TITLE]);
      sb.append("\n");
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    PurchaseQueue purchaseQueue = new PurchaseQueue("songListTest.csv", scan);
    while (true) {
      System.out.print(
          "Enter 1 to add funds, 2 to purchase a song, 3 to print the song queue, 4 to receive a refund, 5 to play a song, 6 to quit: ");
      String input = scan.nextLine();
      switch (input) {
        case "1":
          purchaseQueue.acceptFunds();
          break;
        case "2":
          purchaseQueue.takeSongIndex();
          break;
        case "3":
          System.out.println(purchaseQueue);
          break;
        case "4":
          purchaseQueue.returnFunds();
          break;
        case "5":
          if (purchaseQueue.hasNextSong()) {
            javafx.application.Application.launch(SongPlayer.class);
            purchaseQueue.songPlayer.playSong(purchaseQueue.nextSong());
            
          } else {
            System.out.println("No songs in queue");
          }
          break;
        case "6":
          return;
        default:
          System.out.println("Invalid option");
      }
    }
  }

}