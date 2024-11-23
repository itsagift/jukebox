 

import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.Group;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class SongPlayer2 extends Application {
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;

    private static final String MEDIA_URL =
    "https://www.kozco.com/tech/piano2-Audacity1.2.5.mp3";
    
    @Override
    public void start(Stage primaryStage) throws Exception{
      Group root = new Group();
        
      Scene scene = new Scene(root, 540, 210);

      primaryStage.setScene(scene);
      primaryStage.sizeToScene();
      primaryStage.show();
      Media media = new Media(MEDIA_URL);
      MediaPlayer mediaPlayer = new MediaPlayer(media);
      mediaPlayer.setAutoPlay(true);
      MediaView mediaView = new MediaView(mediaPlayer);
      ((Group)scene.getRoot()).getChildren().add(mediaView);
    }
    // public void playSong(String song){
    //     if (mediaPlayer != null) {
    //         mediaPlayer.stop();
    //         mediaPlayer.dispose();
    //     } else {
    //         Media media = new Media(MEDIA_URL);
    //         MediaPlayer mediaPlayer = new MediaPlayer(media);
    //         mediaPlayer.setAutoPlay(true);
    //         mediaView.setMediaPlayer(mediaPlayer);
    //     }
    // }

    public static void main(String[] args) {
        
        launch(args);
    }
}
