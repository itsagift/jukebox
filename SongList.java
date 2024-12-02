import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SongList {
  private String songInfo[][];
  private String songInfoByTitle[][];
  private String songInfoByArtist[][];
  public static final int SONG_TITLE = 0;
  public static final int SONG_ARTIST = 1;
  public static final int SONG_COST = 2;
  public static final int SONG_URI = 3;

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

  public String[][] getSongInfo() {
    return this.songInfo;
  }

  public String[][] getSongInfoByTitle() {
    return songInfoByTitle;
  }

  public String[][] getSongInfoByArtist() {
    return songInfoByArtist;
  }

  public String[] getSongTitleList(){
    String[] songTitles = new String[songInfo.length];
    for (int i = 0; i < songInfo.length; i++) {
      songTitles[i] = songInfo[i][0];
    }
    return songTitles;
  }

  public String[] getIndividualSong(int index) {
    return this.songInfo[index];
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < songInfo.length; i++) {
      sb.append(String.format("%d: %s, %s, %s\n", i, songInfo[i][SONG_TITLE], songInfo[i][SONG_ARTIST],
          songInfo[i][SONG_COST]));
    }
    return sb.toString();
  }
  
}
