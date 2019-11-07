package sample.controller;

import javafx.fxml.FXML;

public class WinController {

    private MainController parent;

    void setParent(MainController parent){
        this.parent = parent;
    }

    @FXML
    private void close(){
        parent.getWinWindow().close();
    }
}
