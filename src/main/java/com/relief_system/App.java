package com.relief_system;
/**
 * JavaFX App
 * @author Fantastic Four
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    /**
     * start method initializes the JavaFX application
     * @param stage primary stage for this application
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("home"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * setRoot method sets the root of the scene to the specified FXML file
     * @param fxml name of the FXML file (without extension)
     * @throws IOException if the FXML file cannot be loaded
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    /**
     * loadFXML method loads the specified FXML file and returns the root node
     * @param fxml name of the FXML file (without extension)
     * @return root node of the loaded FXML file
     * @throws IOException if the FXML file cannot be loaded
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    /**
     * main method launches the JavaFX application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch();
    }

}