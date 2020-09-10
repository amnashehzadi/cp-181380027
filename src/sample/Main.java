
package sample;
import java.io.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {

    private TextField marks;
    private DatePicker date;


    public Main(){
        marks = new TextField();
        marks.setPrefWidth(200);
        date = new DatePicker();
        date.setPrefWidth(200);
    }
    @Override
    public void start(Stage primaryStage) {
        Label titleLb=new Label("My CP Tracker");
        titleLb.setFont(new Font("Arial",22));
        Label dateLb=new Label("Date:");
        dateLb.setFont(new Font(16));
        Label marksLb=new Label("Marks:");
        marksLb.setFont(new Font(16));
        Button saveBtn = new Button("Save Data");
        saveBtn.setFont(new Font(16));
        saveBtn.setOnAction((ActionEvent event) -> {
            save();
        });

        HBox dBox=new HBox(70);
        dBox.getChildren().add(dateLb);
        dBox.getChildren().add(date);
        dBox.setAlignment(Pos.CENTER);
        HBox mBox=new HBox(70);
        mBox.getChildren().add(marksLb);
        mBox.getChildren().add(marks);
        mBox.setAlignment(Pos.CENTER);
        HBox bBox=new HBox();
        bBox.getChildren().add(saveBtn);
        bBox.setAlignment(Pos.CENTER_RIGHT);
        bBox.setPadding(new Insets(0,20,0,0));
        VBox root = new VBox(20);
        root.getChildren().add(titleLb);
        root.getChildren().add(dBox);
        root.getChildren().add(mBox);
        root.getChildren().add(bBox);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 350, 300);
        primaryStage.setTitle("RollNumber CP Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void save(){
        try(PrintWriter writer=new PrintWriter(new FileWriter("cp.txt",true))){
            String data="----- CP Marks on "+date.getValue().toString()+" ----------\n";
            data+=" Marks: "+marks.getText();
            writer.println(data);
            alert(data);
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    private void alert(String data){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("CP Data Saved");
        alert.setHeaderText("Your CP data is saved successfully");
        alert.setContentText(data);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

}