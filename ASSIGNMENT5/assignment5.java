import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.Year;

import java.util.Random;


public class assignment5 extends Application {
    private Image missing_piece;
    private Image set;
    private Image[][] images;


    Pane timePane;
    Pane aPane = new Pane();
    Pane listPane;
    Pane buttonPane;
    GridPane leftGrid = new GridPane();
    Button[][] buttons;
    ListView<String> options;
    Label load;

    private int counter;
    private String concatenating;
    private int timer;
    private int x_random;
    private int y_random;
    private int curr_black_x;
    private int curr_black_y;
    private String current_file;
    private boolean clicked;
    private boolean finished_status;
    private boolean finish_the_game;
    private boolean stop_start;
    private boolean controlDefault;
    public void start(Stage primaryStage) {
        update();
        loadLabel();
        listView();
        startButton();
        time(timer);
        //set up the scene
        aPane.getChildren().addAll(leftGrid);
        primaryStage.setTitle("Slider Puzzle Game");
        primaryStage.setScene(new Scene(aPane, 968, 768));
        primaryStage.show();
    }
    public void time(int val){
        timePane = new Pane();
        timePane.relocate(768,390);
        timePane.setPrefSize(187,25);
        Label label = new Label();
        label.setText("Time: ");
        label.relocate(0,8);
        TextField time = new TextField("0:00");
        time.setText(String.format("%d:%02d",val/60, val %60));
        time.relocate(70,3);
        time.setPrefSize(117,20);

        timePane.getChildren().addAll(label,time);
        aPane.getChildren().add(timePane);
    }
    public void startGameCounting(int val){
        timePane = new Pane();
        timePane.relocate(768,390);
        timePane.setPrefSize(187,25);
        Label label = new Label();
        label.setText("Time: ");
        label.relocate(0,8);
        TextField time = new TextField("0:00");
        time.relocate(70,3);
        time.setPrefSize(117,20);
        Timeline updateTimer = new Timeline(new KeyFrame(Duration.millis(1000),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        timer ++;
                        time.setText(String.format("%d:%02d",timer/60, timer %60));
                    }
                }));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        if (!stop_start) {
            updateTimer.stop();
            timer =0;

        }
        else
            updateTimer.play();
//        timer = 0;
        timePane.getChildren().addAll(label,time);
        aPane.getChildren().add(timePane);
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void update() {
        leftGrid.setPadding(new Insets(10, 10, 10, 10));
        leftGrid.setHgap(1);
        leftGrid.setVgap(1);
        images = new Image[4][4];
        buttons = new Button[4][4];
        int[] arr = {0, 1, 2, 3};
        for (int row : arr) {
            for (int col : arr) {
                images[col][row] = new Image(getClass().getResourceAsStream("BLANK.png"));
                buttons[col][row] = new Button();
                buttons[col][row].setPrefSize(187, 187);
                buttons[col][row].setPadding(new Insets(0, 0, 0, 0));
                buttons[col][row].setOnAction(event -> {
                    swap(col, row);
                });
                buttons[col][row].setGraphic(new ImageView(images[col][row]));
                leftGrid.add(buttons[col][row], row, col);
            }
        }
    }
    public void listView() {
        listPane = new Pane();
        options = new ListView<String>();
        String[] storeOptions = {"Pets", "Scenery", "Lego", "Numbers"};
        options.setPrefSize(187, 140);
        listPane.relocate(768, 207);
        loadLabel();
        options.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int tempIndex = options.getSelectionModel().getSelectedIndex();
                if (tempIndex != -1) {
                    System.out.println(storeOptions[tempIndex]);
                    current_file = storeOptions[tempIndex];
                    loadLabel(storeOptions[tempIndex]);
                    controlDefault = true;
                }
            }
        });
        options.setItems(FXCollections.observableArrayList(storeOptions));
        listPane.getChildren().add(options);
        aPane.getChildren().add(listPane);
    }
    public void update(String name) {
        leftGrid.setPadding(new Insets(10, 10, 10, 10));
        leftGrid.setHgap(1);
        leftGrid.setVgap(1);
        images = new Image[4][4];
        int[] arr = {0, 1, 2, 3};
        buttons = new Button[4][4];

        Random rand = new Random();

        x_random = rand.nextInt(4);
        y_random = rand.nextInt(4);
        curr_black_x = x_random;
        curr_black_y = y_random;
        concatenating = ""+x_random+y_random;
//        System.out.println("Game start with black row " + curr_black_x + "black col " + curr_black_y);
        String[][] arrStr = {{"00", "01", "02", "03"}, {"10", "11", "12", "13"}, {"20", "21", "22", "23"}, {"30", "31", "32", "33"}};
        for (int row : arr) {
            for (int col : arr) {
                if (!((concatenating.equals(arrStr[col][row]))) && name != null) {
                    images[col][row] = new Image(getClass().getResourceAsStream(name + "_" + arrStr[col][row] + ".png"));
                    buttons[col][row] = new Button();
                    buttons[col][row].setPrefSize(187, 187);
                    buttons[col][row].setPadding(new Insets(0, 0, 0, 0));
                    buttons[col][row].setGraphic(new ImageView(images[col][row]));
                    buttons[col][row].setOnAction(event -> {
                        swap(row, col);
                    });
                    leftGrid.add(buttons[col][row], row, col);

                } else {
                    buttons[col][row] = new Button();
//                    missing_piece = new Image(getClass().getResourceAsStream(name + "_" + arrStr[col][row] + ".png"));

                    images[col][row] = new Image(getClass().getResourceAsStream("BLANK.png"));
                    missing_piece = new Image(getClass().getResourceAsStream(name+"_"+arrStr[col][row]+".png"));
                    buttons[col][row].setGraphic(new ImageView(images[col][row]));
                    buttons[col][row].setOnAction(event -> {
                        System.out.println("You clicked black");
                        swap(row, col);
                    });
                    buttons[col][row].setPrefSize(187, 187);
                    buttons[col][row].setPadding(new Insets(0, 0, 0, 0));
                    leftGrid.add(buttons[col][row], row, col);
                }
            }

        }
    }
    public void loadLabel() {
        load = new Label();
        set = new Image(getClass().getResourceAsStream("BLANK.png"));
        load.setGraphic(new ImageView(set));
        load.setPadding(new Insets(10, 10, 10, 10));
        load.relocate(758, 0);
        aPane.getChildren().add(load);

    }
    public void loadLabel(String name) {
        load = new Label();
        set = new Image(getClass().getResourceAsStream(name + "_Thumbnail.png"));
        load.setGraphic(new ImageView(set));
        load.setPadding(new Insets(10, 10, 10, 10));
        load.relocate(758, 0);
        aPane.getChildren().add(load);
    }

    public void startButton() {
        buttonPane = new Pane();
        buttonPane.setPrefSize(187, 27);
        Button sButton = new Button("Start");
        sButton.setAlignment(Pos.BASELINE_CENTER);
        sButton.setPrefSize(187, 27);
        sButton.setStyle("-fx-base: GREEN;");
        buttonPane.relocate(768, 357);
        sButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (clicked) {
                    timer = 0;

                    time(timer);
                    update();
                    stop_start = false;
                    load.setDisable(false);
                    options.setDisable(false);
                    sButton.setText("Start");
                    sButton.setStyle("-fx-base: Green");
                    clicked = !clicked;
                } else {
                    if (controlDefault ) {
                        loadLabel();
                        load.setDisable(true);

                        update(current_file);
                        stop_start = true;
                        startGameCounting(timer);
                        timer = 0;
                        options.setDisable(true);
                        if (counter != 0)
                            finish_the_game = !finish_the_game;
                        sButton.setText("Stop");
                        sButton.setStyle("-fx-base: DarkRed");
                        clicked = !clicked;
                    }
                }
            }
        });
        buttonPane.getChildren().add(sButton);
        aPane.getChildren().add(buttonPane);
    }
    public void swap(int col, int row) {
        counter =0;
        if (!finish_the_game){
            if((row -1 == curr_black_x && col == curr_black_y || row+1 == curr_black_x && col == curr_black_y|| row == curr_black_x &&col-1 ==curr_black_y ||row == curr_black_x &&col+1==curr_black_y )) {
                if (checkWin(row, col) == false) {
                    Image temp = images[curr_black_x][curr_black_y];
                    buttons[curr_black_x][curr_black_y].setGraphic(new ImageView(images[row][col]));
                    images[curr_black_x][curr_black_y] = images[row][col];
                    buttons[row][col].setGraphic(new ImageView(temp));
                    images[row][col] = temp;
                    curr_black_x = row;
                    curr_black_y = col;
                    counter+=1;
                } else {
                    images[curr_black_x][curr_black_y] = missing_piece;
                    buttons[row][col].setGraphic(new ImageView(images[curr_black_x][curr_black_y]));
                    buttons[curr_black_x][curr_black_y].setGraphic(new ImageView(images[row][col]));
                    disable();
                    counter+=1;
                    time(timer);
                }
            }
        }
    }
    public void shuffle(){
        for (int i = 0 ; i < 5000;i++){
            Random rand = new Random();
            int randX = rand.nextInt(4)+0;
            int randY = rand.nextInt(4)+0;
            swap(randY,randX);
            System.out.println(i);
        }
        System.out.println("This is current X:::::;"+curr_black_x+"THis is current Y"+curr_black_y);
    }

    public boolean checkWin(int row, int col){
        int counter= 0;
        finished_status = false;
        int[] foreach = {0,1,2,3};
        String[][] standard = {{"00", "01", "02", "03"}, {"10", "11", "12", "13"}, {"20", "21", "22", "23"}, {"30", "31", "32", "33"}};
        String[][] arrStr = {{"00", "01", "02", "03"}, {"10", "11", "12", "13"}, {"20", "21", "22", "23"}, {"30", "31", "32", "33"}};
        int tempX = x_random;
        int tempY = y_random;
        String temp = arrStr[x_random][y_random];
        arrStr[row][col] = concatenating;
        for (int i: foreach){
            for (int j : foreach){
                if (standard[i][j].equals(arrStr[i][j])) {
                    finished_status = true;
                    counter+=1;
                }
            }
        }
        if (counter ==16){
            finished_status = true;
            finish_the_game = true;
        }
        else{
            finished_status = false;
            finish_the_game=false;
        }
        return finished_status;
    }
    public void disable(){
        int[] arr = {0,1,2,3};
        for (int i : arr ){
            for (int j :arr){
                buttons[i][j].setDisable(true);
            }
        }
    }
}
