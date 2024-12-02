import java.nio.file.Paths;

import javafx.util.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * Class representing a song player.
 *
 * @author Chase Reynolds, Tess Avitabile
 */
public class SongPlayer {
    private MediaPlayer mediaPlayer;
    private PurchaseQueue purchaseQueue;
    private StringProperty currentSong = new SimpleStringProperty();
    private StringProperty currentArtist = new SimpleStringProperty();

    /**
     * Constructs a song player.
     * 
     * @param purchaseQueue the purchase queue for this song player
     */
    public SongPlayer(PurchaseQueue purchaseQueue) {
        this.purchaseQueue = purchaseQueue;
    }

    /**
     * Returns the song title.
     * 
     * @return song title
     */
    public StringProperty getSongProperty() {
        return currentSong;
    }

    /**
     * Returns the song artist.
     * 
     * @return song artist
     */
    public StringProperty getArtistProperty() {
        return currentArtist;
    }

    /**
     * Plays the current song, then plays the next song from the queue, if it
     * exists.
     * 
     * @param song the current song
     */
    public void playSong(String[] song) {
        String uri = Paths.get(song[3]).toUri().toString();
        currentSong.set(song[0]);
        currentArtist.set(song[1]);
        Media media = new Media(uri);
        mediaPlayer = new MediaPlayer(media);

        System.out.printf("playing song: %s\n", song[0]);
        mediaPlayer.setOnReady(() -> {
            System.out.println("Duration: " + mediaPlayer.getMedia().getDuration());
            mediaPlayer.play();
        });
        mediaPlayer.setOnEndOfMedia(() -> {
            System.out.println("song over");
            if (purchaseQueue.hasNextSong()) {
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