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



public class JavaFXController extends Application {
    private final int[][] EXAMPLE_SUDOKU_EASY = {
            {0,0,0,8,9,0,0,0,4},
            {9,8,5,1,3,4,6,2,0},
            {0,7,4,0,0,0,0,1,9},
            {0,0,0,4,1,9,0,5,2},
            {0,0,9,7,6,5,1,0,0},
            {7,5,1,0,8,3,0,0,6},
            {0,1,7,0,0,0,3,6,0},
            {6,9,0,5,0,1,2,4,0},
            {5,4,0,3,0,0,0,0,0},
    };
    private final int SCREEN_WIDTH = 900;
    private final int SCREEN_HEIGHT = 900;

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Starting application");

        GridPane grid = new GridPane();

        grid.setMinSize(900,900);
        grid.setAlignment(Pos.CENTER);

        //Contents of grid
        ColumnConstraints colConstraint = new ColumnConstraints();
        colConstraint.setPercentWidth(100/9);
        colConstraint.setHalignment(HPos.CENTER);
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100/9);
        rowConstraints.setValignment(VPos.CENTER);
        for (int y = 0; y < 9; y++){
            grid.getColumnConstraints().add(colConstraint);
            grid.getRowConstraints().add(rowConstraints);
            for (int x = 0; x < 9; x++) {
                String number= "";
                if (EXAMPLE_SUDOKU_EASY[y][x] > 0 && EXAMPLE_SUDOKU_EASY[y][x] < 10) {
                    number = Integer.toString(EXAMPLE_SUDOKU_EASY[y][x]);
                } else {

                }
                Button button = new Button(number);

                button.setOnAction((ActionEvent e) -> button.setText("C"));

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
