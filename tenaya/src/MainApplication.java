/*
* This is a project to store different shoe models in a virtual storage application
* made by: Aaro Karhu
* Email: aaro.karhu19@gmail.com
*/

package tenaya.suomi.tampere.logistiikka.logistiikka;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The MainApplication class serves as the entry point for the JavaFX application.
 */
public class MainApplication extends Application {

    /**
     * The start method is called when the JavaFX application is launched.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the main view
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));

        // Create a scene from the loaded FXML file
        Scene scene = new Scene(fxmlLoader.load());

        // Set the title of the stage
        stage.setTitle("Tenaya Suomi");

        // Set the scene for the stage
        stage.setScene(scene);

        // Display the stage
        stage.show();
    }

    /**
     * The main method is the entry point of the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch();
    }
}
