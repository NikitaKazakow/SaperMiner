package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.model.MainSaperClass;
import sample.model.SaperButton;

public class MainController {

    private static MainSaperClass saperGame;

    private Stage newGameWindow;

    private Stage winWindow;

    @FXML
    private  Pane gameFieldPane;

    Stage getNewGameWindow(){
        return this.newGameWindow;
    }

    Stage getWinWindow(){
        return winWindow;
    }

    Pane getGameFieldPane(){
        return this.gameFieldPane;
    }

    @FXML
    private void exitApp(){
        System.exit(0);
    }

    @FXML
    private void newGameShowWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/newgame.fxml"));

            Parent root = loader.load();
            NewGameController children = loader.getController();
            children.setParent(this);

            newGameWindow = new Stage();
            newGameWindow.setTitle("Новая игра");
            newGameWindow.setScene(new Scene(root, 165, 230));
            newGameWindow.initModality(Modality.WINDOW_MODAL);
            newGameWindow.initOwner(Main.getMainStage());
            newGameWindow.initStyle(StageStyle.UTILITY);
            newGameWindow.show();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void updateView(){
        gameFieldPane.getChildren().removeAll(gameFieldPane.getChildren());
        gameFieldPane.requestLayout();
        SaperButton[][] buttons = saperGame.getMainField();
        for( int i = 0; i < saperGame.getFieldWidth(); i++) {
            for (int j = 0; j < saperGame.getFieldHeight(); j++) {
                try {
                    buttons[i][j].setLayoutX(i * 32);
                    buttons[i][j].setLayoutY(j * 32);

                    int finalI = i;
                    int finalJ = j;

                    //Нововведение Java 8 - лямбда выражения
                    buttons[i][j].setOnMouseClicked(mouseEvent -> {
                        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                            if (buttons[finalI][finalJ].isFlaged())
                                saperGame.openButton(finalI, finalJ);
                        }
                        else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                            if (buttons[finalI][finalJ].isFlaged() && buttons[finalI][finalJ].isOpened()){
                                saperGame.setFlag(finalI, finalJ, true);
                                if(saperGame.getFlagCount() == saperGame.getMineCount())
                                    openWinWindow();
                            }
                            else if(buttons[finalI][finalJ].isOpened())
                                saperGame.setFlag(finalI, finalJ, false);
                        }
                        if(saperGame.isWin()){
                            gameFieldPane.setDisable(true);
                            openWinWindow();
                        }
                    });

                    gameFieldPane.getChildren().add(buttons[i][j]);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        double height = saperGame.getFieldHeight() * buttons[0][0].getPrefHeight();
        double width = saperGame.getFieldWidth() * buttons[0][0].getPrefWidth();

        if(height + 100 > 300)
            Main.getMainStage().setHeight(height + 75);
        else
            Main.getMainStage().setHeight(300);
        if(width + 70 > 500)
            Main.getMainStage().setWidth(width  + 17);
        else
            Main.getMainStage().setWidth(500);
    }

    private void openWinWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/win.fxml"));

            Parent root = loader.load();

            WinController children = loader.getController();
            children.setParent(this);

            winWindow = new Stage();
            winWindow.setTitle("Победа");
            winWindow.setScene(new Scene(root, 200, 80));
            winWindow.initModality(Modality.WINDOW_MODAL);
            winWindow.initOwner(Main.getMainStage());
            winWindow.initStyle(StageStyle.UTILITY);
            winWindow.show();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    void setSaperGame(MainSaperClass value){
        saperGame = value;
    }
}
