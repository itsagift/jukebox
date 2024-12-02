 import java.nio.file.Paths;

import javafx.util.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class SongPlayer  {
    private MediaPlayer mediaPlayer;
    private PurchaseQueue purchaseQueue;
    private StringProperty currentSong = new SimpleStringProperty();
    private StringProperty currentArtist = new SimpleStringProperty();
    String[] song;


    public SongPlayer(String[] song, PurchaseQueue purchaseQueue){
        this.song = song;
        this.purchaseQueue = purchaseQueue;
    }
    
    public String getSongTitle(){
        return this.song[0];
    }

    public StringProperty getSongProperty() {
        return currentSong;
    }
    public StringProperty getArtistProperty() {
        return currentArtist;
    }

    public void playSong(String[] song){
        String uri = Paths.get(song[3]).toUri().toString();
        currentSong.set(song[0]);
        currentArtist.set(song[1]);
        Media media = new Media(uri);
        mediaPlayer = new MediaPlayer(media);
        
        System.out.println("playing song");
        System.out.println(song[0]);
        mediaPlayer.setOnReady(() -> {
            System.out.println("Duration: " + mediaPlayer.getMedia().getDuration());
            mediaPlayer.play();
        });
        mediaPlayer.setOnEndOfMedia(() -> {
            System.out.println("SongPlayer.java: music over");
            if (purchaseQueue.hasNextSong()){
                playSong(purchaseQueue.nextSong());
            } else {
                currentSong.set("");
                currentArtist.set("");
                mediaPlayer.dispose();
            }
           
        }); 
        mediaPlayer.setOnError(() -> {
            System.out.println("Error: " + mediaPlayer.getError());
        });
        
    }

    
}