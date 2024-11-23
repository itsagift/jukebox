 

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

public class SongPlayer extends Application {
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;

    private static final String MEDIA_URL =
    "https://www.kozco.com/tech/piano2-Audacity1.2.5.mp3";
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Parent root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
        // primaryStage.setTitle("Embedded Media Player");
        Group root = new Group();
        
        Scene scene = new Scene(root, 540, 210);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        
        mediaView = new MediaView();
        root.getChildren().add(mediaView);
        primaryStage.show();
    }
    public void playSong(String song){
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            System.out.println("already exists");
        } else {
            Media media = new Media(MEDIA_URL);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaView.setMediaPlayer(mediaPlayer);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}