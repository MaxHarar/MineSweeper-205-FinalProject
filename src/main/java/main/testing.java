package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class testing extends Application {
    public void start(Stage stage) {
        stage.setTitle("A Popup Example");
        Button button = new Button("Click to open a Popup");

        TilePane tilePane = new TilePane();
        Label label = new Label("This is a popup");

        Popup popup = new Popup();

        label.setStyle("-fx-background-color: grey;");
        popup.getContent().add(label);

        label.setMinWidth(100);
        label.setMinHeight(80);

        EventHandler<ActionEvent> actionEventHandler =
                et -> {
                    if (!popup.isShowing()) {
                        popup.show(stage);
                        button.setText("Click to Hide a Popup");
                    } else {
                        popup.hide();
                        button.setText("Click to open a Popup");

                    }
                };

        button.setOnAction(actionEventHandler);
        tilePane.getChildren().add(button);

        Scene scene = new Scene(tilePane, 450, 360);
        stage.setScene(scene);
        stage.show();


        MyPopup test = new MyPopup();

        test.show();
    }

    public static void main(String[] args) {

        launch(args);

    }
}