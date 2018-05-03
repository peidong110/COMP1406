import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class AlarmApp extends Application {
    RadioButton[] group;
    public void start(Stage primaryStage) {
        Pane aPane = new Pane();
        aPane.setStyle("-fx-background-color: white;");

        Pane currentTime = new Pane();
        currentTime.setStyle("-fx-background-color: white;-fx-border-color: gray;-fx-padding:  4 4;");
        currentTime.relocate(220,10);
        currentTime.setPrefSize(200,40);

        Label tittle = new Label("Current Time");
        tittle.setStyle("-fx-background-color: white; -fx-translate-y: -8; -fx-translate-x: 10;");
        tittle.relocate(220,10);

        TextField currentTimeTextField = new TextField("01:54:16 PM");
        currentTimeTextField.relocate(225,20);
        currentTimeTextField.setPrefSize(190,25);
        currentTimeTextField.setAlignment(Pos.BASELINE_RIGHT);

        Pane alarmTime = new Pane();
        alarmTime.setStyle("-fx-background-color: white; -fx-border-color: gray; -fx-padding: 4 4");
        alarmTime.relocate(220,60);
        alarmTime.setPrefSize(200,40);
        Label title2 = new Label("Alarm Time");
        title2.setStyle("-fx-background-color: white; -fx-translate-y: -8;-fx-translate-x: 10");
        title2.relocate(220,60);
        TextField alrmText = new TextField("10:30:00 AM");
        alrmText.relocate(225,70);
        alrmText.setPrefSize(190,25);
        alrmText.setAlignment(Pos.BASELINE_RIGHT);

        Pane remaining = new Pane();
        remaining.setStyle("-fx-background-color: white; -fx-border-color: gray; -fx-padding: 4 4");
        remaining.relocate(10,10);
        remaining.setPrefSize(200,90);
        Label title3 = new Label("Remaining Time");
        title3.setStyle("-fx-background-color: white; -fx-translate-y: -8;-fx-translate-x: 10");
        title3.relocate(10,10);
        Label rem = new Label("00:00:00");
        rem.relocate(22,20);
        rem.setStyle("-fx-text-fill: DarkGreen");
        rem.setFont(new Font(48));





        //drop-down list
        ObservableList<String> alarms = FXCollections.observableArrayList("Weekday","Saturday","Sunday");
        ComboBox alarmOptions = new ComboBox(alarms);
        alarmOptions.setValue("Select Alarm");
        alarmOptions.relocate(10,110);
        alarmOptions.setPrefSize(410,30);
        //newAlarm button
        group = new RadioButton[5];
        String[] toggleGroup = {"New Alarm","Edit","Delete","Yes","No"};

        Button newAlarm = new Button(toggleGroup[0]);
        newAlarm.relocate(10,150);
        newAlarm.setPrefSize(100,30);
        newAlarm.setAlignment(Pos.CENTER);
        //Edit button
        Button edit = new Button(toggleGroup[1]);
        edit.relocate(120,150);
        edit.setPrefSize(80,30);
        edit.setAlignment(Pos.CENTER);
        //delate
        Button delate = new Button(toggleGroup[2]);
        delate.relocate(210,150);
        delate.setPrefSize(80,30);
        delate.setAlignment(Pos.CENTER);
        //YES BUTTON
        RadioButton yes = new RadioButton(toggleGroup[3]);
        yes.relocate(310,150);
        yes.setPrefSize(50,30);
        yes.setSelected(true);
        //NO BUTTON
        RadioButton no = new RadioButton(toggleGroup[4]);
        no.relocate(360,150);
        no.setPrefSize(50,30);
        aPane.getChildren().addAll(alarmOptions,newAlarm,edit,delate,yes,no,currentTime,tittle,currentTimeTextField,alarmTime,alrmText,title2,remaining,title3,rem);

        primaryStage.setTitle("Alarm App"); // Set window title
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane,430,190));
        primaryStage.show(); // Show window

    }
    public static void main(String[] args) {
        launch(args); // Initialize/start
    }
}
