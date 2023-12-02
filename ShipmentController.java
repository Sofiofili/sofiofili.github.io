package tenaya.suomi.tampere.logistiikka.logistiikka;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * The ShipmentController class manages the shipment view,
 * handling user interactions and managing shoe shipments.
 */
public class ShipmentController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private Text errorText;

    @FXML
    private ListView<String> howManyListView;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button shipButton;

    @FXML
    private TextField textField;

    // HashMap to track the quantity of each shoe model
    HashMap<String, Integer> howManyEachModle = new HashMap<>();

    // ObservableLists to store the summary and added shoes
    ObservableList<String> summaryArray = FXCollections.observableArrayList();
    ObservableList<String> addedShoes = FXCollections.observableArrayList();

    /**
     * Shows the context menu. where is the
     * option to delete unwanted shoes
     */
    @FXML
    void showContextMenu() {}

    /**
     * Handles the click event on the text field by clearing its content.
     */
    @FXML
    void onTextFieldClicked() {
        textField.setText("");
    }

    @FXML
    void handleShipment(MouseEvent event) throws IOException {
        // Iterate through the items in the list view and update storage
        for (String item : listView.getItems()) {
            String[] itemSplit = item.split(": ");
            String shoeModel = itemSplit[0];
            String size = itemSplit[1];
            // Value false is for removing shoes from storage (below)
            Utils.saveChanges(shoeModel, size, false);
        }
        // Close the stage
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the delete context menu item by adjusting the summary and removing the selected item.
     */
    @FXML
    void handleDelete() {
        // Implementation for handling the delete action
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        String[] stringArray = selectedItem.split(":");
        String key = stringArray[0];
        summaryArray.remove(String.format("%s = %s", key, howManyEachModle.get(key)));
        if (howManyEachModle.get(key) > 1) {
            summaryArray.add(String.format("%s = %s", key, howManyEachModle.get(key) - 1));
        }
        howManyEachModle.put(key, howManyEachModle.get(key) - 1);
        listView.getItems().remove(selectedItem);
        listView.refresh();
    }

    /**
     * Handles the barcode read event by searching for shoe information and updating the view.
     *
     * @throws FileNotFoundException If the shoe information file is not found.
     */
    @FXML
    void onBarCodeRead() throws FileNotFoundException {
        errorText.setText("");
        if (textField.getText().length() == 13) {
            // Process the scanned code and update the view
            String scanndCode = textField.getText();
            // Search for shoe model and size based on the scanned code
            HashMap<String, String> shoeModelAndSize = Utils.searchJson(scanndCode);
            // Handle the case where shoe information is not found
            if (shoeModelAndSize == null) {
                errorText.setText("kyseistä kenkää ei löydy tiedostosta");
                textField.setText("");
                return;
            }
            // Update the summary and added shoes lists based on the retrieved information
            for (HashMap.Entry<String, String> entry : shoeModelAndSize.entrySet()) {
                if(!howManyEachModle.containsKey(entry.getKey())) {
                    howManyEachModle.put(entry.getKey(), 1);
                    summaryArray.add(String.format("%s = %s",
                            entry.getKey(), howManyEachModle.get(entry.getKey())));
                } else {
                    summaryArray.remove(String.format("%s = %s", entry.getKey(),
                            howManyEachModle.get(entry.getKey())));
                    summaryArray.add(String.format("%s = %s", entry.getKey(),
                            howManyEachModle.get(entry.getKey()) + 1));
                    howManyEachModle.put(entry.getKey(),
                            howManyEachModle.get(entry.getKey()) + 1);
                }

                String key = entry.getKey();
                String value = entry.getValue();
                String item = key + ": " + value;
                addedShoes.add(item);
            }
            // Reverse the order of added shoes
            // so the newest item shoe is on top
            FXCollections.reverse(addedShoes);
            listView.setItems(addedShoes);

            // Reverse the order of the summary
            // so that the newest item is on top
            FXCollections.reverse(summaryArray);
            howManyListView.setItems(summaryArray);
            // Clear the text field
            textField.setText("");
        }
    }
}
