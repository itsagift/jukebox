import java.util.Arrays;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javafx.animation.Timeline;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

/**
 * Class representing the UI controller for a jukebox.
 *
 * @author Chase Reynolds, Tess Avitabile
 */
public class Controller {

    @FXML
    private RadioButton button1;
    @FXML
    private RadioButton button2;
    @FXML
    private ToggleGroup radioToggleGroup;
    @FXML
    private CheckBox addFirstCheckBox;
    @FXML
    private ToggleGroup paymentToggleGroup;
    @FXML
    private ListView<String[]> listView;
    @FXML
    private Label nowPlayingLabel;
    @FXML
    private Label songPlayingLabel;
    @FXML
    private Label artistPlayingLabel;
    @FXML
    private ToggleButton addChangeButton;
    @FXML
    private ToggleButton swipeCardButton;
    @FXML
    private TextField textFieldField;
    @FXML
    private HBox textField;
    @FXML
    private HBox bottomBar;
    @FXML
    private Button payButton;
    @FXML
    private Label balanceLabel;
    @FXML
    private Button buySongButton;
    @FXML
    private VBox visualization;
    @FXML
    private ParallelTransition animation;
    private SongList songList = new SongList("songListTest.csv");
    private BalanceBox balanceBox = new BalanceBox();
    private PurchaseQueue purchaseQueue = new PurchaseQueue(songList, balanceBox);
    private SongPlayer songPlayer;
    private boolean playlistInitialized = false;

    /**
     * Initializes the payment UI.
     */
    public void initializePaymentUI() {
        paymentToggleGroup = new ToggleGroup();
        addChangeButton.setToggleGroup(paymentToggleGroup);
        swipeCardButton.setToggleGroup(paymentToggleGroup);
        addChangeButton.setText("Add Change");
        swipeCardButton.setText("Swipe Card");
        addChangeButton.setUserData("Enter coin:");
        swipeCardButton.setUserData("Add credit from card:");
        balanceLabel.setText("Balance:");
        textField.setVisible(false);
        paymentToggleGroup.selectedToggleProperty()
                .addListener((@SuppressWarnings("unused") ObservableValue<? extends Toggle> ov, Toggle toggle,
                        Toggle new_toggle) -> {
                    if (new_toggle != null) {
                        textField.setVisible(true);
                        ToggleButton selectedToggle = (ToggleButton) paymentToggleGroup.getSelectedToggle();
                        if (selectedToggle == addChangeButton) {
                            textFieldField.setPromptText("Enter coin:");
                        } else if (selectedToggle == swipeCardButton) {
                            textFieldField.setPromptText("Enter amount:");
                        }
                    } else
                        textField.setVisible(false);
                });
        payButton.setOnAction(event -> {
            ToggleButton selectedToggle = (ToggleButton) paymentToggleGroup.getSelectedToggle();
            String payInput = textFieldField.getText();
            if (selectedToggle == addChangeButton) {
                balanceBox.acceptFunds(payInput, "coin");
            } else if (selectedToggle == swipeCardButton) {
                balanceBox.acceptFunds(payInput, "credit");
            }
            textFieldField.clear();
            balanceLabel.setText("Balance:" + Integer.toString(balanceBox.getFunds()));
        });
    }

    /**
     * Initializes the song list UI.
     * 
     * @param sortBy whether to sort songs by Title or Artists
     */
    public void initializeSongListUI(String sortBy) {
        ObservableList<String[]> items = FXCollections.observableArrayList();
        if (sortBy.equals("Title")) {
            items.addAll(songList.getSongInfoByTitle());
        } else {
            items.addAll(songList.getSongInfoByArtist());
        }
        listView.setCellFactory(
                (Callback<ListView<String[]>, ListCell<String[]>>) new Callback<ListView<String[]>, ListCell<String[]>>() {
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
                });
        listView.setItems(items);

        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String[]> ov, String[] old_val,
                        String[] new_val) -> {
                    if (new_val != null) {
                        buySongButton.setDisable(false);
                        buySongButton.setText("Buy Song: " + new_val[SongList.SONG_TITLE]);

                    } else {
                        buySongButton.setDisable(true);
                        buySongButton.setText("Buy Song: ");
                    }
                });
        buySongButton.setOnAction(event -> {
            purchaseQueue.takeSongIndex(listView.getSelectionModel().getSelectedIndex(),
                    radioToggleGroup.getSelectedToggle().getUserData().toString(), addFirstCheckBox.isSelected());
            balanceLabel.setText("Balance:" + Integer.toString(balanceBox.getFunds()));
            if (!playlistInitialized && purchaseQueue.hasNextSong()) {
                String[] song = purchaseQueue.nextSong();
                System.out.printf("queue has items! %s \n", Arrays.toString(song));
                songPlayer = new SongPlayer(purchaseQueue);
                songPlayer.playSong(song, this);
                playlistInitialized = true;
                songPlayingLabel.textProperty().bind(songPlayer.getSongProperty());
                artistPlayingLabel.textProperty().bind(songPlayer.getArtistProperty());
            } else if (purchaseQueue.hasNextSong() && songPlayer.getSongProperty().get() == "") {
                String[] song = purchaseQueue.nextSong();
                songPlayer.playSong(song, this);
            }
        });

    };

    /**
     * Initializes the playing UI.
     */
    public void initializePlayingUI() {
        nowPlayingLabel.setText("Now playing");
    }

    /**
     * Initializes the visualization.
     */
    public void initializeVisualization() {
        HBox hbox = new HBox(15);
        visualization.getChildren().add(hbox);
        Color colors[] = new Color[] { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA };
        animation = new ParallelTransition();
        for (int i = 0; i < 12; i++) {
            Circle circle = new Circle(10, colors[i % colors.length]);
            hbox.getChildren().add(circle);
            TranslateTransition translate = new TranslateTransition(Duration.millis((i + 1) * 1000), circle);
            translate.setToX(0);
            translate.setToY(300);
            animation.getChildren().add(translate);
        }
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.setAutoReverse(true);
    }

    /**
     * Starts the animation.
     */
    public void startAnimation() {
        animation.play();
    }

    /**
     * Stops the animation.
     */
    public void stopAnimation() {
        animation.pause();
    }

    /**
     * Initialize the controller.
     */
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
        initializeVisualization();
    }
}