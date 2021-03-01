package com.TiLab;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PrimaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button decode;

    @FXML
    private ToggleGroup SV;

    @FXML
    private TextField text;

    @FXML
    private TextField result;

    @FXML
    private Button encode;

    @FXML
    private TextField key;

    @FXML
    void initialize() {
        RailwayFence rf = new RailwayFence();
        encode.setOnAction(event -> {
            System.out.println("Encode");//
            result.setText(rf.encode(text.getText(), key.getText()));
          //  result.setText(rf.getResult());//encode text
           // System.out.println(rf.getResult());//
        });

        decode.setOnAction(event ->{
            System.out.println("Decode");//

        });
    }
}

