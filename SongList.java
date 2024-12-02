import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class representing a song list.
 *
 * @author Chase Reynolds, Tess Avitabile
 */
public class SongList {
  private String songInfo[][];
  private String songInfoByTitle[][];
  private String songInfoByArtist[][];

  /**
   * The index of the song title in the array representing the song.
   */
  public static final int SONG_TITLE = 0;

  /**
   * The index of the song artist in the array representing the song.
   */
  public static final int SONG_ARTIST = 1;

  /**
   * The index of the song cost in the array representing the song.
   */
  public static final int SONG_COST = 2;

  /**
   * The index of the song URI in the array representing the song.
   */
  public static final int SONG_URI = 3;

  /**
   * Constructs a song list from a song csv file.
   * 
   * @param fileName the song csv file
   */
  public SongList(String fileName) {
    int lineCount = 0;
    try {
      File file = new File(fileName);
      Scanner myReader = new Scanner(file);
      while (myReader.hasNextLine()) {
        myReader.nextLine();
        lineCount++;
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    songInfo = new String[lineCount][4];

    int index = 0;
    try {
      File file = new File(fileName);
      Scanner myReader = new Scanner(file);
      while (myReader.hasNextLine()) {
        String line = myReader.nextLine();
        String[] columns = line.split(",");
        songInfo[index][SONG_TITLE] = columns[SONG_TITLE];
        songInfo[index][SONG_ARTIST] = columns[SONG_ARTIST];
        songInfo[index][SONG_COST] = columns[SONG_COST];
        songInfo[index][SONG_URI] = columns[SONG_URI];
        index++;
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    songInfoByTitle = sort(songInfo, SONG_TITLE);
    songInfoByArtist = sort(songInfo, SONG_ARTIST);
  }

  /**
   * Sorts an array of songs by the desired parameter.
   * 
   * @param songs the array of songs
   * @param by    the parameter to sort by
   * @return the sorted array
   */
  private String[][] sort(String[][] songs, int by) {
    String sorted[][] = songs.clone();
    for (int i = 0; i < sorted.length; i++) {
      for (int j = i; j < sorted.length; j++) {
        if (sorted[i][by].compareTo(sorted[j][by]) > 0) {
          String[] tmp = sorted[i];
          sorted[i] = sorted[j];
          sorted[j] = tmp;
        }
      }
    }
    return sorted;
  }

  /**
   * Returns the list of songs.
   * 
   * @return the list of songs
   */
  public String[][] getSongInfo() {
    return this.songInfo;
  }

  /**
   * Returns the list of songs sorted by title.
   * 
   * @return the list of songs sorted by title
   */
  public String[][] getSongInfoByTitle() {
    return songInfoByTitle;
  }

  /**
   * Returns the list of songs sorted by artist.
   * 
   * @return the list of songs sorted by artist
   */
  public String[][] getSongInfoByArtist() {
    return songInfoByArtist;
  }

  /**
   * Returns a string representation of the song list.
   * 
   * @return string representation of the song list
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < songInfo.length; i++) {
      sb.append(String.format("%d: %s, %s, %s\n", i, songInfo[i][SONG_TITLE], songInfo[i][SONG_ARTIST],
          songInfo[i][SONG_COST]));
    }
    return sb.toString();
  }

}
