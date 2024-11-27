
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

public class Controller {

     @FXML
    private RadioButton button1;

    @FXML
    private RadioButton button2;

    @FXML
    private RadioButton button3;

    private ToggleGroup toggleGroup;

    @FXML private Text actiontarget;
    
    
    

    @FXML
    public void initialize() {
        toggleGroup = new ToggleGroup();
        button1.setToggleGroup(toggleGroup);
        button2.setToggleGroup(toggleGroup);
        button1.setUserData("Artist");
        button2.setUserData("Title");
        button2.setSelected(true);
        toggleGroup.selectedToggleProperty().addListener(
    (ObservableValue<? extends Toggle> ov, Toggle old_toggle, 
    Toggle new_toggle) -> {
        if (toggleGroup.getSelectedToggle() != null) {
            String placeholder = toggleGroup.getSelectedToggle().getUserData().toString();
            actiontarget.setText(placeholder);
    }
    });
    if (toggleGroup.getSelectedToggle() != null) {
      String placeholder = toggleGroup.getSelectedToggle().getUserData().toString();
      actiontarget.setText(placeholder);
    }
}}