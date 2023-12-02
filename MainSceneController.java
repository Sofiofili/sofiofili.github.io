package tenaya.suomi.tampere.logistiikka.logistiikka;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The MainSceneController class controls the main scene of the application,
 * handling user interactions and managing the display of shoe information.
 */
public class MainSceneController extends Utils implements Initializable {
    // FXML annotations for injecting UI components
    @FXML
    private TableColumn<Shoe, Integer> all;

    @FXML
    private TableColumn<Shoe, Integer> eight;

    @FXML
    private TableColumn<Shoe, Integer> eightH;

    @FXML
    private TableColumn<Shoe, Integer> eleven;

    @FXML
    private TableColumn<Shoe, Integer> elevenH;

    @FXML
    private TableColumn<Shoe, Integer> five;

    @FXML
    private TableColumn<Shoe, Integer> fiveH;

    @FXML
    private TableColumn<Shoe, Integer> four;

    @FXML
    private TableColumn<Shoe, Integer> fourH;

    @FXML
    private TableColumn<Shoe, String> nimi;

    @FXML
    private TableColumn<Shoe, Integer> nine;

    @FXML
    private TableColumn<Shoe, Integer> nineH;

    @FXML
    private TableColumn<Shoe, Integer> one;

    @FXML
    private TableColumn<Shoe, Integer> oneH;

    @FXML
    private TableColumn<Shoe, Integer> seven;

    @FXML
    private TableColumn<Shoe, Integer> sevenH;

    @FXML
    private TableColumn<Shoe, Integer> six;

    @FXML
    private TableColumn<Shoe, Integer> sixH;

    @FXML
    private TableColumn<Shoe, Integer> ten;

    @FXML
    private TableColumn<Shoe, Integer> tenH;

    @FXML
    private TableColumn<Shoe, Integer> thirteen;

    @FXML
    private TableColumn<Shoe, Integer> three;

    @FXML
    private TableColumn<Shoe, Integer> threeH;

    @FXML
    private TableColumn<Shoe, Integer> twelve;

    @FXML
    private TableColumn<Shoe, Integer> twelveH;

    @FXML
    private TableColumn<Shoe, Integer> two;

    @FXML
    private TableColumn<Shoe, Integer> twoH;

    @FXML
    private TableView<Shoe> table;

    /**
     * Handles the F5 key press event to refresh the table data.
     *
     * @param event The key event triggered by the F5 key press.
     * @throws IOException If an error occurs while loading shoe data from JSON files.
     */
    @FXML
    void onF5Clicked(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.F5) {
            // Reload shoe data from JSON files and update the table
            list = FXCollections.observableArrayList(
                    makeShoeClassFromJson("Ra"),
                    makeShoeClassFromJson("Masai"),
                    makeShoeClassFromJson("Tarifa"),
                    makeShoeClassFromJson("Inti"),
                    makeShoeClassFromJson("Iati"),
                    makeShoeClassFromJson("Oasi"),
                    makeShoeClassFromJson("Tanta"),
                    makeShoeClassFromJson("Mundaka"),
                    makeShoeClassFromJson("Oasi LV"),
                    makeShoeClassFromJson("Mastia")
            );
            table.setItems(list);
        }
    }

    /**
     * Handles the button click event to open the shipment view.
     *
     * @throws IOException If an error occurs while loading the shipment view.
     */
    @FXML
    void onShipmentButtonClicked() throws IOException {
        // load the shipment fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class
                .getResource("shipment.fxml"));
        // creating a new window for the shipment fxml
        Stage shipmentStage = new Stage();
        Scene shipmentScene = new Scene(fxmlLoader.load());
        shipmentStage.setScene(shipmentScene);
        shipmentStage.setTitle("Lähetys");
        shipmentStage.show();
    }

    /**
     * Handles the button click event to open the shoe storage addition view.
     *
     * @throws IOException If an error occurs while loading the shoe storage addition view.
     */
    @FXML
    void onAddButtonClicked() throws IOException {
        // load and show the storage add fxml on a new window
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class
                .getResource("add-storage.fxml"));
        Stage storageAddStage = new Stage();
        Scene storageAddScene = new Scene(fxmlLoader.load());
        storageAddStage.setScene(storageAddScene);
        storageAddStage.setTitle("Kenkien lisäys");
        storageAddStage.show();
    }

    ObservableList<Shoe> list;
    // Initialization block to load initial shoe data from JSON files
    {
        try {
            list = FXCollections.observableArrayList(
                    makeShoeClassFromJson("Ra"),
                    makeShoeClassFromJson("Masai"),
                    makeShoeClassFromJson("Tarifa"),
                    makeShoeClassFromJson("Inti"),
                    makeShoeClassFromJson("Iati"),
                    makeShoeClassFromJson("Oasi"),
                    makeShoeClassFromJson("Tanta"),
                    makeShoeClassFromJson("Mundaka"),
                    makeShoeClassFromJson("Oasi LV"),
                    makeShoeClassFromJson("Mastia")
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object, or null if the
     *                       root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configure table columns and set cell value factories
        // to map the Shoe class properties to the appropriate columns
        nimi.setCellValueFactory(new PropertyValueFactory<>("name"));
        one.setCellValueFactory(new PropertyValueFactory<>("one"));
        oneH.setCellValueFactory(new PropertyValueFactory<>("oneH"));
        two.setCellValueFactory(new PropertyValueFactory<>("two"));
        twoH.setCellValueFactory(new PropertyValueFactory<>("twoH"));
        three.setCellValueFactory(new PropertyValueFactory<>("three"));
        threeH.setCellValueFactory(new PropertyValueFactory<>("threeH"));
        four.setCellValueFactory(new PropertyValueFactory<>("four"));
        fourH.setCellValueFactory(new PropertyValueFactory<>("fourH"));
        five.setCellValueFactory(new PropertyValueFactory<>("five"));
        fiveH.setCellValueFactory(new PropertyValueFactory<>("fiveH"));
        six.setCellValueFactory(new PropertyValueFactory<>("six"));
        sixH.setCellValueFactory(new PropertyValueFactory<>("sixH"));
        seven.setCellValueFactory(new PropertyValueFactory<>("seven"));
        sevenH.setCellValueFactory(new PropertyValueFactory<>("sevenH"));
        eight.setCellValueFactory(new PropertyValueFactory<>("eight"));
        eightH.setCellValueFactory(new PropertyValueFactory<>("eightH"));
        nine.setCellValueFactory(new PropertyValueFactory<>("nine"));
        nineH.setCellValueFactory(new PropertyValueFactory<>("nineH"));
        ten.setCellValueFactory(new PropertyValueFactory<>("ten"));
        tenH.setCellValueFactory(new PropertyValueFactory<>("tenH"));
        eleven.setCellValueFactory(new PropertyValueFactory<>("eleven"));
        elevenH.setCellValueFactory(new PropertyValueFactory<>("elevenH"));
        twelve.setCellValueFactory(new PropertyValueFactory<>("twelve"));
        twelveH.setCellValueFactory(new PropertyValueFactory<>("twelveH"));
        thirteen.setCellValueFactory(new PropertyValueFactory<>("thirteen"));
        all.setCellValueFactory(new PropertyValueFactory<>("all"));
        // finally set items into table
        table.setItems(list);
    }

}
