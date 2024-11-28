import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.Group;

public class SongPlayerFX extends Application{
  public SongPlayer player;
  public PurchaseQueue queue;
  
  
    @Override
    public void start(Stage primaryStage) throws Exception{
      String[] nextSong = getParameters().getRaw().toArray(new String[0]);
      
      Group root = new Group();
        try {
            player = new SongPlayer(nextSong);
            Scene scene = new Scene(root, 720, 535, Color.BLACK);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Song Player");
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
