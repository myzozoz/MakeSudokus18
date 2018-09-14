package makesudokus.domain;

import makesudokus.gui.JavaFXController;
import javafx.application.Application;

/**
 * Entry point for the application, only gets the JavaFX application running.
 */
public class Main {
    public static void main(String[] argv) {
        Application.launch(JavaFXController.class, argv);
    }
}
