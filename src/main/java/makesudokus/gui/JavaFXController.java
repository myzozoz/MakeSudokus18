package makesudokus.gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import makesudokus.logic.SudokuController;

import java.util.HashMap;


/**
 * The main (and currently only) GUI class. For now.
 */
public class JavaFXController extends Application {
    private SudokuController sudokuController;
    private HashMap<String, int[]> buttonMap;
    private final int SCREEN_WIDTH = 900;
    private final int SCREEN_HEIGHT = 900;

    /**
     * Contains the GUI stuff.
     * @param primaryStage See JavaFX documentation.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        sudokuController = new SudokuController();
        buttonMap = new HashMap<>();
        System.out.println("Starting application");

        GridPane grid = new GridPane();

        grid.setMinSize(900,900);
        grid.setAlignment(Pos.CENTER);

        //Contents of grid
        ColumnConstraints colConstraint = new ColumnConstraints();
        colConstraint.setPercentWidth(100/9);
        colConstraint.setHalignment(HPos.CENTER);
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(10);
        rowConstraints.setValignment(VPos.CENTER);

        int[][] sudoku = sudokuController.getSudoku();

        //For each cell in the grid, create a new button.
        for (int y = 0; y < 9; y++){

            grid.getColumnConstraints().add(colConstraint);
            grid.getRowConstraints().add(rowConstraints);

            for (int x = 0; x < 9; x++) {
                String number = sudoku[y][x] == 0 ? "" : Integer.toString(sudoku[y][x]);

                Button button = new Button(number);
                button.setId("x" + x + "y" + y);
                int[] coordinates = {x,y};
                buttonMap.put(button.getId(), coordinates);
                //

                //Handler function to update the cell.
                button.setOnAction((ActionEvent e) -> {
                    int[] buttonCoordinates = buttonMap.get(button.getId());
                    int currentNumber = sudokuController.getNumber(buttonCoordinates[0], buttonCoordinates[1]);

                    int newNumber = currentNumber + 1;
                    if (newNumber > 9) {
                        newNumber = 0;
                    }

                    sudokuController.updateNumber(buttonCoordinates[0],buttonCoordinates[1], newNumber);
                    String newButtonText = newNumber == 0 ? "" : Integer.toString(newNumber);
                    button.setText(newButtonText);
                });

                button.setStyle("-fx-background-color: darkslategrey; " +
                        "-fx-font-size: 26px;" +
                        "-fx-text-fill: blanchedalmond;" +
                        "-fx-alignment: center;");

                button.setPrefHeight(60);
                button.setPrefWidth(60);
                button.setAlignment(Pos.CENTER);

                grid.add(button,x ,y);

            }
        }

        Button solveButton = new Button("Solve!");
        solveButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        solveButton.setStyle("-fx-background-color: crimson;" +
                "-fx-font-size: 40px;" +
                "-fx-alignment: center");
        solveButton.setOnAction((ActionEvent e) -> sudokuController.solve());
        grid.add(solveButton,0,9,9,1);

        grid.setMinSize(300,300);
        grid.setStyle("-fx-background-color: darkorange;" +
                "-fx-grid-lines-visible: true;" +
                "-fx-alignment: center");
        Scene scene = new Scene(grid, SCREEN_WIDTH, SCREEN_HEIGHT);

        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->
                System.out.println("Height: " + primaryStage.getHeight() + " Width: " + primaryStage.getWidth());

        scene.widthProperty().addListener(stageSizeListener);
        scene.heightProperty().addListener(stageSizeListener);

        primaryStage.setMinHeight(534);
        primaryStage.setMinWidth(534);
        primaryStage.setTitle("MakeSudokus18");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}
