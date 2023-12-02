package tenaya.suomi.tampere.logistiikka.logistiikka;
import com.google.gson.*;
import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The Utils class provides utility methods for handling JSON
 * data, updating storage, and refreshing JavaFX ListViews.
 */
public class Utils{

    /**
     * Saves changes to the storage based on the specified shoe model, size, and action (add or remove).
     *
     * @param model The shoe model.
     * @param size  The shoe size.
     * @param add   A boolean indicating whether to add or remove the shoe from storage.
     * @throws IOException If an error occurs while reading or writing to the storage file.
     */
    public static void saveChanges(String model, String size, Boolean add) throws IOException {
        // Implementation for saving changes to the storage using gson extension
        Gson gson = new Gson();
        // read the .json file
        JsonArray root = gson.fromJson(new FileReader("varasto.json"), JsonArray.class);
        // loop until param "model" is found from object name
        for (JsonElement element : root) {
            JsonObject object = element.getAsJsonObject();
            if(object.get("name").getAsString().equals(model)) {
                Map.Entry<String, String> entry = SizeInStringAndDouble.findPair(size);
                assert entry != null;
                String sizeKey = entry.getKey();
                JsonElement sizeElement = object.get(entry.getKey());
                JsonElement allElement = object.get("all");

                if (add) {
                    // init for adding
                    int newValue = sizeElement.getAsInt() + 1;
                    int newAllValue = allElement.getAsInt() + 1;
                    object.addProperty(sizeKey, newValue);
                    object.addProperty("all", newAllValue);
                } else {
                    // init for deleting
                    if(sizeElement.getAsInt() > 0) {
                        int newValue = sizeElement.getAsInt() - 1;
                        int newAllValue = allElement.getAsInt() - 1;
                        object.addProperty(sizeKey, newValue);
                        object.addProperty("all", newAllValue);
                    } else {
                        // skip if no shoes left
                        System.out.println("kenkiä liian vähän");
                        return;
                    }
                }

                Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
                // Write the updated JSON back to the file
                try (FileWriter fileWriter = new FileWriter("varasto.json")) {
                    gsonBuilder.toJson(root, fileWriter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(object);
                break;  // Exit the loop since the update is done
            }
        }
    }

    /**
     * Creates a Shoe object from JSON data based on the specified shoe model name.
     *
     * @param name The name of the shoe model.
     * @return A Shoe object created from the JSON data, or null if not found.
     * @throws FileNotFoundException If the JSON file is not found.
     */
    public Shoe makeShoeClassFromJson(String name) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonArray root = gson.fromJson(new FileReader("varasto.json"), JsonArray.class);
        for (JsonElement element : root) {
            JsonObject object = element.getAsJsonObject();
            if(object.get("name").getAsString().equals(name)) {
                return gson.fromJson(object, Shoe.class);
            }
        }
        return null;
    }

    /**
     * Searches for a barcode value in a JSON file and returns the corresponding shoe model and size.
     *
     * @param targetValue The barcode value to search for.
     * @return A HashMap containing the shoe model and size, or null if not found.
     * @throws FileNotFoundException If the JSON file is not found.
     */
    public static HashMap<String, String> searchJson(String targetValue) throws FileNotFoundException {
        HashMap<String, String> shoeModelAndSize = new HashMap<>();

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(new FileReader("barcodes.json"), JsonObject.class);
        for (String key : jsonObject.keySet()) {
            JsonElement element = jsonObject.get(key);
            JsonObject object = element.getAsJsonObject();
            for (String shoeSize : object.keySet()) {
                if (object.get(shoeSize).getAsString().equals(targetValue)) {
                    shoeModelAndSize.put(key, shoeSize);
                    return shoeModelAndSize;
                }
            }
        }
        return null;
    }
}
