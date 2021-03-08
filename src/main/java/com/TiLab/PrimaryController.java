package com.TiLab;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PrimaryController {

    @FXML
    private Button decode;

    @FXML
    private RadioButton buttrail;

    @FXML
    private ToggleGroup SV;

    @FXML
    private RadioButton buttcol;

    @FXML
    private RadioButton buttgrid;

    @FXML
    private RadioButton buttvig;

    @FXML
    private TextField text;

    @FXML
    private TextField result;

    @FXML
    private Button encode;

    @FXML
    private TextField key;
    private String keyres;

    //вывод ключа в решетке
    public void setKeyres(String tkey) {
        this.keyres = tkey;
    }

    @FXML
    void initialize() {

        RailwayFence rf = new RailwayFence();
        VigenerMethod vm = new VigenerMethod();
        GridMethod gm = new GridMethod();
        ColumnMethod cm = new ColumnMethod();

        encode.setOnAction(event -> {
            RadioButton selectedRadio1 = (RadioButton) SV.getSelectedToggle();
            if(selectedRadio1 != null){
                if (buttcol.isSelected()) {
                    System.out.println("Encode colomn");
                    result.setText(cm.encode(text.getText(), key.getText()));
                }
                if (buttvig.isSelected()) {
                    System.out.println("Encode Vigener");
                    result.setText(vm.encode(text.getText(), key.getText()));
                }
                if (buttrail.isSelected()) {
                    System.out.println("Encode RailR");//
                    result.setText(rf.encode(text.getText(), key.getText()));
                }
                if (buttgrid.isSelected()) {
                    System.out.println("Encode Grid");//
                    result.setText(gm.encode(text.getText(), key.getText()));
                    key.setText(gm.getReskey());
                }

            }
        });

        decode.setOnAction(event ->{
            RadioButton selectedRadio = (RadioButton) SV.getSelectedToggle();
            if(selectedRadio != null){
                if (buttcol.isSelected()) {
                    System.out.println("Decode colomn");
                    result.setText(cm.decode(text.getText(), key.getText()));
                }
                if (buttvig.isSelected()) {
                    System.out.println("Decode Vigener");
                    result.setText(vm.decode(text.getText(), key.getText()));
                }
                if (buttrail.isSelected()) {
                    System.out.println("Decode RailR");//
                    result.setText(rf.decode(text.getText(), key.getText()));
                }
                if (buttgrid.isSelected()) {
                    result.setText(gm.decode(text.getText(), key.getText()));
                    System.out.println("Decode Grid");//
                }
            }

        });
    }
}

