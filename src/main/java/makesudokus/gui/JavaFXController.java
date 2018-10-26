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
    private final int SCREEN_WIDTH = 1000;
    private final int SCREEN_HEIGHT = 1000;

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

        for (int i = 0; i < 9; i++){
            grid.getColumnConstraints().add(colConstraint);
            grid.getRowConstraints().add(rowConstraints);
        }

        //For each cell in the grid, create a new button.
        addButtonsToGrid(grid);

        //Setting grid layout options
        grid.setMinSize(300,300);
        grid.setStyle("-fx-background-color: darkorange;" +
                "-fx-grid-lines-visible: true;" +
                "-fx-alignment: center");

        //Create the scene
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


    /** <p>Method for adding buttons to the sudoku</p>
     *  Used to initially create the buttons but also to refresh the buttons.
     *  Reform through rebirth
     */
    private void addButtonsToGrid(GridPane grid) {
        int[][] sudoku = sudokuController.getSudoku();
        for (int y = 0; y < 9; y++){

            for (int x = 0; x < 9; x++) {
                String number = sudoku[y][x] == 0 ? "" : Integer.toString(sudoku[y][x]);

                Button button = new Button(number);
                button.setId("x" + x + "y" + y);
                int[] coordinates = {x,y};
                buttonMap.put(button.getId(), coordinates);

                //Handler function to update the button/cell.
                button.setOnAction((ActionEvent e) -> {
                    int[] buttonCoordinates = buttonMap.get(button.getId());
                    int currentNumber = sudokuController.getNumber(buttonCoordinates[0], buttonCoordinates[1]);

                    int newNumber = currentNumber + 1;
                    if (newNumber > 9) {
                        newNumber = 0;
                    }

                    System.out.println("Button pressed! id:" + button.getId());

                    sudokuController.updateNumber(buttonCoordinates[0], buttonCoordinates[1], newNumber);
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

        String solveColor = "white";
        if (sudokuController.isSolved()) {
            solveColor = "lime";
        } else {
            solveColor = "crimson";
        }

        //Creating the "Solve" button for the backtracker algorithm
        Button backtrackerButton = new Button(sudokuController.isSolved() ? "Solved!" : "Backtracker");
        backtrackerButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        backtrackerButton.setStyle("-fx-background-color: " + solveColor + ";" +
                "-fx-font-size: 40px;" +
                "-fx-alignment: center;" +
                "-fx-border-width: 5px;" +
                "-fx-border-color: black;");
        grid.add(backtrackerButton,0,9,3,1);
        //Backtracking Solve Button event handler
        backtrackerButton.setOnAction((ActionEvent e) -> {
            sudokuController.solveWithBacktracker();
            addButtonsToGrid(grid);
        });

        //creating the "Solve" button for Crook's algorithm
        Button crookButton = new Button(sudokuController.isSolved() ? "Solved!" : "Crook");
        crookButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        crookButton.setStyle("-fx-background-color: " + solveColor + ";" +
                "-fx-font-size: 40px;" +
                "-fx-alignment: center;" +
                "-fx-border-width: 5px;" +
                "-fx-border-color: black;");
        grid.add(crookButton,3,9,3,1);
        //Crook's Solve Button event handler
        crookButton.setOnAction((ActionEvent e) -> {
            sudokuController.solveWithCrook();
            addButtonsToGrid(grid);
        });

        //Create a reset button
        Button resetButton = new Button("Reset");
        resetButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        resetButton.setStyle("-fx-background-color: deepskyblue;" +
                "-fx-font-size: 40px;" +
                "-fx-alignment: center;" +
                "-fx-border-width: 5px;" +
                "-fx-border-color: black;");
        grid.add(resetButton,6,9,3,1);
        //Reset Button event handler
        resetButton.setOnAction((ActionEvent e) -> {
            System.out.println("resetting...");
            sudokuController.resetSudoku();
            addButtonsToGrid(grid);
        });

    }
}

