import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
 
public class testGoal5 extends Application {
  @Override
    public void start(Stage primaryStage) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("goal5.fxml"));
        BorderPane root = fxmlLoader.load();

        Scene scene = new Scene(root,640, 360);
        scene.getStylesheets().add(getClass().getResource("test.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
      launch(args);
  }
}