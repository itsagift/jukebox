import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class SongList {
  private String songInfo[][];
  private static final int SONG_TITLE = 0;
  private static final int SONG_ARTIST = 1;
  private static final int SONG_COST = 2;
  private static final int SONG_URI = 3;


  public SongList(String fileName){
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
  }

  public String[][] getSongInfo(){
    return this.songInfo;
  }
  public String[] getIndividualSong(int index){
    return this.songInfo[index];
  }

  public String toString(){
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < songInfo.length; i++){
      for (int j = 0; j < songInfo.length; j++){
        sb.append(String.format("%s \n", songInfo[i][j]));
      }
    }
    return sb.toString();
  }


  public static void main(String[] args) {

  }
}
