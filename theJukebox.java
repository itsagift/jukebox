import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Class representing a jukebox.
 *
 * @author Chase Reynolds, Tess Avitabile
 */
public class theJukebox extends Application {
  /**
   * Starts the jukebox application.
   * 
   * @param primaryStage the primary stage
   * @throws IOException may throw an IOException
   */
  @Override
  public void start(Stage primaryStage) throws IOException {

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("goal5.fxml"));
    BorderPane root = fxmlLoader.load();

    Scene scene = new Scene(root, 640, 360);
    scene.getStylesheets().add(getClass().getResource("test.css").toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Launches the jukebox application.
   * 
   * @param args command line arguments are not used
   */
  public static void main(String[] args) {
    launch(args);
  }
}