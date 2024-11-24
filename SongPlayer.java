 



import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class SongPlayer  {
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    String song;

    private static final String MEDIA_URL =
    "https://www.kozco.com/tech/piano2-Audacity1.2.5.mp3";

    public SongPlayer(String song){
        this.song = song;
    }
    
    public SongPlayer(){
        this.song = "https://www.kozco.com/tech/piano2-Audacity1.2.5.mp3";
    }
    
    public void playSong(){
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            System.out.println("already exists");
        } else {
            Media media = new Media(song);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaView = new MediaView(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
            mediaView.setMediaPlayer(mediaPlayer);
        }
    }

    
}