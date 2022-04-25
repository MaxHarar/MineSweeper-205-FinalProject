package main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

public class MyPopup extends Stage {

    public MyPopup() {
        super();
        this.setTitle("Pop");
        this.setResizable(false);
        // The important part
        this.initModality(Modality.APPLICATION_MODAL);

        BorderPane borderPaneOptionPane = new BorderPane();
        borderPaneOptionPane.setCenter(new TextArea()); // For example

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {
            this.hide();
        });

        borderPaneOptionPane.setPadding(new Insets(0));

        Scene s = new Scene(borderPaneOptionPane);
        this.setScene(s);
    }}