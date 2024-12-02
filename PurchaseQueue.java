import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class representing a purchase queue.
 *
 * @author Chase Reynolds, Tess Avitabile
 */
public class PurchaseQueue {
  private static final int ADD_FIRST_COST = 5;
  private Deque<String[]> queue;
  private SongList songList;
  private BalanceBox balance;

  /**
   * Constructs a PurchaseQueue.
   * 
   * @param songList   the songs available for purchase
   * @param balanceBox the balance box associated with this purchase queue
   */
  public PurchaseQueue(SongList songList, BalanceBox balanceBox) {
    this.balance = balanceBox;
    this.songList = songList;
    this.queue = new ArrayDeque<>();
  }

  /**
   * Adds a song to the queue and deducts funds.
   * 
   * @param songIndex the index of the song
   * @param sortBy    whether the songs are sorted by "Title" or "Artist"
   * @param addFirst  whether to add the song to the front of the queue
   */
  public void takeSongIndex(int songIndex, String sortBy, boolean addFirst) {
    try {
      String[] song;
      if (sortBy.equals("Title")) {
        song = songList.getSongInfoByTitle()[songIndex];
      } else {
        song = songList.getSongInfoByArtist()[songIndex];
      }
      int songCost = Integer.parseInt(song[SongList.SONG_COST]);
      if (balance.deductFunds(songCost + (addFirst ? ADD_FIRST_COST : 0))) {
        if (addFirst) {
          queue.addFirst(song);
        } else {
          queue.addLast(song);
        }
      } else {
        System.out.println("Not enough funds");
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Must enter a valid song number");
    }
  }

  /**
   * Returns all funds from the balance box.
   */
  public void returnFunds() {
    balance.returnFunds();
  }

  /**
   * Pops the next song from the queue.
   * 
   * @return the next song to play
   */
  public String[] nextSong() {
    return queue.pop();
  }

  /**
   * Returns whether the queue has a next song.
   * 
   * @return whether the queue has a next song
   */
  public boolean hasNextSong() {
    return queue.size() > 0;
  }

  /**
   * Returns a string representation of the queue.
   * 
   * @return string representation of the queue
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (String[] song : queue) {
      sb.append(song[SongList.SONG_TITLE]);
      sb.append("\n");
    }
    return sb.toString();
  }
}