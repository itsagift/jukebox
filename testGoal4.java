import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.Group;

public class testGoal4 extends Application{
  SongPlayer player;
  PurchaseQueue queue;
  
  
    @Override
    public void start(Stage primaryStage) throws Exception{
      String nextSong = getParameters().getRaw().get(0);
      Group root = new Group();
        try {
            player = new SongPlayer(nextSong);
            Scene scene = new Scene(root, 720, 535, Color.BLACK);
            primaryStage.setScene(scene);
            primaryStage.show();
            player.playSong();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
  
  public static void main(String[] args) {
    launch(args);
  }
}
