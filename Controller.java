
import java.beans.EventHandler;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private RadioButton button1;
    @FXML
    private RadioButton button2;
    @FXML private ToggleGroup radioToggleGroup;
    @FXML private ToggleGroup paymentToggleGroup;
    @FXML private Text actiontarget;
    @FXML private ListView<String> listView;
    @FXML private Label nowPlayingLabel;
    @FXML private ToggleButton addChangeButton;
    @FXML private ToggleButton swipeCardButton;
    @FXML private TextField textFieldField;
    @FXML private HBox textField;
    // @FXML private Label textFieldLabel;
    @FXML private HBox bottomBar;
    @FXML private Rectangle rectid;
    @FXML private Button payButton;
    @FXML private Label balanceLabel;
    SongList songList = new SongList("songListTest.csv");
    BalanceBox balanceBox = new BalanceBox(null);

    public void initializePaymentUI(){

        paymentToggleGroup = new ToggleGroup();
        addChangeButton.setToggleGroup(paymentToggleGroup);
        swipeCardButton.setToggleGroup(paymentToggleGroup);
        addChangeButton.setText("Add Change");
        swipeCardButton.setText("Swipe Card");
        addChangeButton.setUserData("Enter coin:");
        swipeCardButton.setUserData("Add credit from card:");
        balanceLabel.setText("Balance:");
        textField.setVisible(false);
        paymentToggleGroup.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) -> {
        if (new_toggle != null){
            textField.setVisible(true);
            textFieldField.setPromptText("Enter amount:");
        }
        else
            textField.setVisible(false);
            // textFieldField.setPromptText(paymentToggleGroup.getSelectedToggle().getUserData().toString());
        });
        payButton.setOnAction(event -> {
            ToggleButton selectedToggle = (ToggleButton) paymentToggleGroup.getSelectedToggle();
            String payInput = textFieldField.getText();
            if (selectedToggle == addChangeButton){
                balanceBox.acceptFunds(payInput, "coin");
            } else if (selectedToggle == swipeCardButton){
                balanceBox.acceptFunds(payInput, "credit");
            }
            textFieldField.clear();
            balanceLabel.setText("Balance:" + Integer.toString(balanceBox.getFunds()));
        });
    }

    public void initialize() {
        nowPlayingLabel.setText("hello world");
        initializePaymentUI();
        radioToggleGroup = new ToggleGroup();
        button1.setToggleGroup(radioToggleGroup);
        button2.setToggleGroup(radioToggleGroup);
        button1.setUserData("Artist");
        button2.setUserData("Title");
        button2.setSelected(true);
        radioToggleGroup.selectedToggleProperty().addListener(
        (ObservableValue<? extends Toggle> ov, Toggle old_toggle, 
        Toggle new_toggle) -> {
            if (radioToggleGroup.getSelectedToggle() != null) {
                String placeholder = radioToggleGroup.getSelectedToggle().getUserData().toString();
                actiontarget.setText(placeholder);
            }
        });
        if (radioToggleGroup.getSelectedToggle() != null) {
            String placeholder = radioToggleGroup.getSelectedToggle().getUserData().toString();
            actiontarget.setText(placeholder);
        }


    ObservableList<String> items =FXCollections.observableArrayList ();
    items.addAll(songList.getSongTitleList());
    listView.setItems(items);

}}