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
  
  

  public static void main(String[] args) {
    SongList testList = new SongList(args[0]);
    for (int i = 0; i < testList.getSongInfo().length; i++){
      System.out.println(Arrays.toString(testList.getSongInfo()[i]));
    }
  }
}
