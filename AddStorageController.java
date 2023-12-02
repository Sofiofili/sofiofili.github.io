package tenaya.suomi.tampere.logistiikka.logistiikka;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class AddStorageController{
    @FXML
    private ListView<String> listView;
    @FXML
    private ListView<String> howManyListView;
    @FXML
    private TextField textField;
    @FXML
    private Text errorText;

    @FXML
    void showContextMenu() {} // shows the Context menu where
                              // you can delete shoes from the list

    /**
     * Method to handle the save button click.
     * @param event The MouseEvent triggered by the save button.
     * @throws IOException If an IO error occurs.
     */
    @FXML
    void handleSave(MouseEvent event) throws IOException {
        if(event.getButton() == MouseButton.PRIMARY) {
            for (String item : listView.getItems()) {
                String[] itemSplit = item.split(": ");
                String shoeModel = itemSplit[0];
                String size = itemSplit[1];
                // Value true is for adding to storage (below)
                Utils.saveChanges(shoeModel, size, true);
            }
        }
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the delete action for removing items from the list.
     */
    @FXML
     void handleDelete() {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        String[] stringArray = selectedItem.split(":");
        String key = stringArray[0];
        summaryArray.remove(String.format("%s * %s", key,
                howManyEachModle.get(key)));
        if (howManyEachModle.get(key) > 1) {
            summaryArray.add(String.format("%s * %s", key,
                    howManyEachModle.get(key) - 1));
        }
        howManyEachModle.put(key, howManyEachModle.get(key) - 1);
        listView.getItems().remove(selectedItem);
        listView.refresh();
    }

    // Map to store the count of each shoe model.
    HashMap<String, Integer> howManyEachModle = new HashMap<>();

    // ObservableList to store the summary of added shoes.
    ObservableList<String> summaryArray = FXCollections.observableArrayList();

    // ObservableList to store the added shoes.
    ObservableList<String> addedShoes = FXCollections.observableArrayList();

    /**
     * Handles the barcode read action.
     *
     * @throws FileNotFoundException If the file is not found.
     */
    @FXML
    void onBarCodeRead() throws FileNotFoundException {
        errorText.setText("");
        // execute when barcode scanned
        if (textField.getText().length() == 13) {
            String scanndCode = textField.getText();

            textField.setText("");
            // search for the scanned barcode and returns a HashMap<key> <shoesize>
            HashMap<String, String> shoeModelAndSize = Utils.searchJson(scanndCode);

            if (shoeModelAndSize == null) {
                // makes a notice for the user that invalid barcode
                errorText.setText("kyseistä kenkää ei löydy tiedostosta");
                textField.setText("");
                return;
            }

            for (HashMap.Entry<String, String> entry : shoeModelAndSize.entrySet())
            {
                if (!howManyEachModle.containsKey(entry.getKey())) {
                    howManyEachModle.put(entry.getKey(), 1);
                    summaryArray.add(String.format("%s * %s", entry.getKey(),
                            howManyEachModle.get(entry.getKey())));
                } else {
                    String itemToRemove = String.format("%s * %s", entry.getKey(),
                            howManyEachModle.get(entry.getKey()));

                    // Store the index of the item you want to remove
                    int indexToRemove = summaryArray.indexOf(itemToRemove);

                    // Remove the item from the list
                    summaryArray.remove(itemToRemove);

                    // Add the item back at the original index
                    summaryArray.add(indexToRemove, String.format("%s * %s",
                            entry.getKey(),
                            howManyEachModle.get(entry.getKey()) + 1));

                    // Add 1 to the summary listView
                    howManyEachModle.put(entry.getKey(),
                            howManyEachModle.get(entry.getKey()) + 1);
                }
                // format the item to String
                String key = entry.getKey();
                String value = entry.getValue();
                String item = key + ": " + value;
                addedShoes.add(0, item);
            }
            // update listView with new items
            listView.setItems(addedShoes);
            // update the other listView with new values
            howManyListView.setItems(summaryArray);
        } else if (textField.getText().length() > 13) {
            errorText.setText("Hidasta tahtia");
        }
    }

    @FXML
    void onTextFieldClicked() {
        textField.setText("");
    } // hide the text of the textfield when clicked
}
