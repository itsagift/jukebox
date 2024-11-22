package hellofx; 

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

    private static final String MEDIA_URL =
    "file:/Users/aa/Documents/JokerOST.mp3";

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Parent root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
        // primaryStage.setTitle("Embedded Media Player");
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


    public static void main(String[] args) {
        launch(args);
    }
}