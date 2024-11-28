 import java.nio.file.Paths;
 
 import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class SongPlayer  {
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    String[] song;


    public SongPlayer(String[] song){
        this.song = song;
    }
    
    public SongPlayer(){
        this.song = new String[]{"title", "https://www.kozco.com/tech/piano2-Audacity1.2.5.mp3"};
    }

    public String getSongTitle(){
        return this.song[0];
    }
    
    public void playSong(){
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            System.out.println("already exists");
        } else {
            String uri = Paths.get(song[3]).toUri().toString();
            Media media = new Media(uri);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaView = new MediaView(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
            mediaView.setMediaPlayer(mediaPlayer);
        }
    }

    
}