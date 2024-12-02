import java.util.Arrays;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class Controller {

    @FXML
    private RadioButton button1;
    @FXML
    private RadioButton button2;
    @FXML private ToggleGroup radioToggleGroup;
    @FXML private ToggleGroup paymentToggleGroup;
    
    @FXML private ListView<String[]> listView;
    @FXML private Label nowPlayingLabel;
    @FXML private Label songPlayingLabel;
    @FXML private Label artistPlayingLabel;
    @FXML private ToggleButton addChangeButton;
    @FXML private ToggleButton swipeCardButton;
    @FXML private TextField textFieldField;
    @FXML private HBox textField;
    // @FXML private Label textFieldLabel;
    @FXML private HBox bottomBar;
    @FXML private Rectangle rectid;
    @FXML private Button payButton;
    @FXML private Label balanceLabel;
    @FXML private Button buySongButton;
    SongList songList = new SongList("songListTest.csv");
    BalanceBox balanceBox = new BalanceBox(null);
    PurchaseQueue purchaseQueue = new PurchaseQueue(songList, balanceBox);
    SongPlayer songPlayer;

    private boolean playlistInitialized = false;

    @SuppressWarnings("unused")
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
        paymentToggleGroup.selectedToggleProperty().addListener((@SuppressWarnings("unused") ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) -> {
        if (new_toggle != null){
            textField.setVisible(true);
            ToggleButton selectedToggle = (ToggleButton) paymentToggleGroup.getSelectedToggle();
            if (selectedToggle == addChangeButton){
                textFieldField.setPromptText("Enter coin:");
            } else if (selectedToggle == swipeCardButton){
                textFieldField.setPromptText("Enter amount:");
            }
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

    public void initializeSongListUI(String sortBy){
        ObservableList<String[]> items =FXCollections.observableArrayList ();
        if (sortBy.equals("Title")) {
            items.addAll(songList.getSongInfoByTitle());
        } else {
            items.addAll(songList.getSongInfoByArtist());
        }
        listView.setCellFactory((Callback<ListView<String[]>, ListCell<String[]>>) new Callback<ListView<String[]>, ListCell<String[]>>() {
            @Override 
            public ListCell<String[]> call(ListView<String[]> param) {
                return new ListCell<String[]>() {
                    @Override
                    protected void updateItem(String[] item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            setText(item[0] + ", " + item[1] + ", " + item[2]);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        }
        );
        listView.setItems(items);
        
        listView.getSelectionModel().selectedItemProperty().addListener(
            (ObservableValue<? extends String[]> ov, String[] old_val, 
                String[] new_val) -> {
                    if (new_val != null){
                        buySongButton.setDisable(false);
                        buySongButton.setText("Buy Song: " + new_val[SongList.SONG_TITLE]);
                        
                    }
                    else{
                        buySongButton.setDisable(true);
                        buySongButton.setText("Buy Song: ");
                    }
                });
        buySongButton.setOnAction(event -> { 
            purchaseQueue.takeSongIndex(listView.getSelectionModel().getSelectedIndex(),
                radioToggleGroup.getSelectedToggle().getUserData().toString());
            balanceLabel.setText("Balance:" + Integer.toString(balanceBox.getFunds()));
            if (!playlistInitialized && purchaseQueue.hasNextSong()) {
                String[] song = purchaseQueue.nextSong();
                System.out.printf("queue has items! %s \n", Arrays.toString(song));
                songPlayer = new SongPlayer(song, purchaseQueue);
                songPlayer.playSong(song);
                playlistInitialized = true;
                songPlayingLabel.textProperty().bind(songPlayer.getSongProperty());
                artistPlayingLabel.textProperty().bind(songPlayer.getArtistProperty());
            } else if (purchaseQueue.hasNextSong() && songPlayer.getSongProperty().get() == "") {
                String[] song = purchaseQueue.nextSong();
                songPlayer.playSong(song);
            }
        });
        
    };

    public void initializePlayingUI(){
        nowPlayingLabel.setText("Now playing");
    }

    @SuppressWarnings("unused")
    public void initialize() {
        
        initializePaymentUI();
        radioToggleGroup = new ToggleGroup();
        button1.setToggleGroup(radioToggleGroup);
        button2.setToggleGroup(radioToggleGroup);
        button1.setUserData("Title");
        button2.setUserData("Artist");
        button1.setSelected(true);
        radioToggleGroup.selectedToggleProperty().addListener(
        (ObservableValue<? extends Toggle> ov, Toggle old_toggle, 
        Toggle new_toggle) -> {
            if (radioToggleGroup.getSelectedToggle() != null) {
                String sortBy = radioToggleGroup.getSelectedToggle().getUserData().toString();
                initializeSongListUI(sortBy);
            }
        });
        initializePlayingUI();
        initializeSongListUI("Title");
}}