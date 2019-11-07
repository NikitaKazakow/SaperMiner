package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.model.MainSaperClass;

public class NewGameController {
    private MainController parent;

    void setParent(MainController parent){
        this.parent = parent;
    }

    @FXML
    private TextField mineCountTextBlock; //Textblock количества мин

    @FXML
    private TextField widthGameField; //Textblock ширины игрового поля в клетках

    @FXML
    private TextField heightGameField; //Textblock высоты игрововго поля

    @FXML
    private TextArea errorsLog; //TextArea для вывода ошобок ввода параметров новой игры

    @FXML
    private void startNewGame(){
        parent.getGameFieldPane().setDisable(false);

        Integer mineCount, width, height;

        try {
            mineCount = Integer.parseUnsignedInt(mineCountTextBlock.getText());
        }
        catch (NumberFormatException ex){
            mineCount = null;
            mineCountTextBlock.setText("");
            errorsLog.setText("Ошибка: Недопустимые символы в полях ввода!");
        }

        try {
            width = Integer.parseUnsignedInt(widthGameField.getText());
            if(width < 3){
                width = height = 3;
                widthGameField.setText("3");
                heightGameField.setText("3");
                errorsLog.setText("Минимальный размер игрового поля 3х3!");
                return;
            }

        }
        catch (NumberFormatException ex){
            width = null;
            widthGameField.setText("");
            errorsLog.setText("Ошибка: Недопустимые символы в полях ввода!");
        }

        try {
            height = Integer.parseUnsignedInt(heightGameField.getText());
            if(height < 3){
                height = width = 3;
                widthGameField.setText("3");
                heightGameField.setText("3");
                errorsLog.setText("Минимальный размер игрового поля 3х3!");
                return;
            }
        }
        catch (NumberFormatException ex){
            height = null;
            heightGameField.setText("");
            errorsLog.setText("Ошибка: Недопустимые символы в полях ввода!");
        }

        if(mineCount != null && width != null && height != null){
            if(mineCount >= width * height){
                errorsLog.setText("Количество мин должно быть не более 70% от количества клеток игрового поля");
                mineCount = 70 * width * height / 100;
                mineCountTextBlock.setText(mineCount.toString());
            }
            else if(mineCount == 0){
                errorsLog.setText("Количество мин должно быть больше 0");
                mineCount = 1;
                mineCountTextBlock.setText(mineCount.toString());
            }
            else{
                parent.setSaperGame( new MainSaperClass(mineCount, width, height));
                parent.updateView();
                parent.getNewGameWindow().close();
            }
        }
    }


}