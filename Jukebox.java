import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Jukebox {
  private SongList songList;
  private PurchaseQueue purchaseQueue;
  private static Scanner scan = new Scanner(System.in);
  private BalanceBox balance;

  public Jukebox() {
    this.songList = new SongList("songListTest.csv");
    this.balance = new BalanceBox(scan);
    this.purchaseQueue = new PurchaseQueue("songListTest.csv", scan, balance);
    
  }

  public Jukebox(String filename){
    this.songList = new SongList(filename);
  }

  
  public static void main(String[] args) {
    Jukebox test = new Jukebox();
    
    test.balance.promptBalance();
    test.purchaseQueue.takeSongIndex();
  }

}
