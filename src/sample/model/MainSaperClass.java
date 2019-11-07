package sample.model;

import java.util.Random;

public class MainSaperClass {

    private SaperButton[][] mainField;

    private int mineCount;
    private int fieldWidth;
    private int fieldHeight;
    private int flagCount;

    public MainSaperClass(int mineCount, int fieldWidth, int fieldHeight){
        this.mineCount = mineCount;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        startNewGame();
    }

    public SaperButton[][] getMainField(){
        return this.mainField;
    }

    public int getFlagCount(){
        return this.flagCount;
    }

    public int getMineCount(){
        return this.mineCount;
    }

    public int getFieldWidth(){
        return this.fieldWidth;
    }

    public int getFieldHeight(){
        return this.fieldHeight;
    }

    private void startNewGame(){
        this.mainField = new SaperButton[fieldWidth][fieldHeight];
        for(int i = 0; i < fieldWidth; i++)
            for (int j = 0; j < fieldHeight; j++){
                mainField[i][j] = new SaperButton();
            }

        Random rnd = new Random();

        int x, y;

        for (int i = 0; i < mineCount; i++){
            x = rnd.nextInt(fieldWidth - 1);
            y = rnd.nextInt(fieldHeight - 1);

            if(!mainField[x][y].isMine())
                mainField[x][y].setMineStatus();
            else
                i--;
        }

        for(int i = 0; i < fieldWidth; i++)
            for (int j = 0; j < fieldHeight; j++){
                calcNumbers(i, j);
            }
    }

    private void calcNumbers(int i, int j){
        if(!mainField[i][j].isMine()){
            try {
                if (mainField[i - 1][j].isMine())
                    mainField[i][j].incValue();
            }
            catch (IndexOutOfBoundsException ignored){}

            try {
                if (mainField[i - 1][j - 1].isMine())
                    mainField[i][j].incValue();
            }
            catch (IndexOutOfBoundsException ignored){ }

            try {
                if(mainField[i - 1][j + 1].isMine())
                    mainField[i][j].incValue();
            }
            catch (IndexOutOfBoundsException ignored){ }

            try {
                if(mainField[i + 1][j].isMine())
                    mainField[i][j].incValue();
            }
            catch (IndexOutOfBoundsException ignored){ }
            try {
                if(mainField[i + 1][j - 1].isMine())
                    mainField[i][j].incValue();
            }
            catch (IndexOutOfBoundsException ignored){ }

            try {
                if (mainField[i + 1][j + 1].isMine())
                    mainField[i][j].incValue();
            }
            catch (IndexOutOfBoundsException ignored){ }

            try {
                if (mainField[i][j - 1].isMine())
                    mainField[i][j].incValue();
            }
            catch (IndexOutOfBoundsException ignored) { }

            try {
                if (mainField[i][j + 1].isMine())
                    mainField[i][j].incValue();
            }
            catch (IndexOutOfBoundsException ignored){ }
        }
    }

    public void openButton(int i, int j){
        if(mainField[i][j].isOpened()){
            if(mainField[i][j].isMine()){
                for (SaperButton[] button : mainField) {
                    for (SaperButton saperButton : button) {
                        if (saperButton.isMine()) {
                            saperButton.setBackgroundMine();
                        }
                    }
                }
                setDisable();
            }
            else {
                if(mainField[i][j].getValue() > 0) {
                    mainField[i][j].setBackgroundsNumber();
                    mainField[i][j].setOpened();
                }
                else
                    openNotMine(i, j);
            }
        }
    }

    private void openNotMine(int i, int j){
        if(mainField[i][j].isChecked())
            return;
        else{
            mainField[i][j].setBackgroundOpened();
            mainField[i][j].setChecked();
            mainField[i][j].setOpened();
        }

        if(i + 1 <= fieldHeight - 1 && mainField[i + 1][j].getValue() == 0)
            openNotMine(i + 1, j);
        else if(i + 1 <= fieldHeight - 1 && mainField[i + 1][j].getValue() > 0){
            mainField[i + 1][j].setBackgroundsNumber();
            mainField[i + 1][j].setOpened();
            if(j + 1 <= fieldWidth - 1 && mainField[i + 1][j + 1].getValue() > 0) {
                mainField[i + 1][j + 1].setBackgroundsNumber();
                mainField[i + 1][j + 1].setOpened();
            }
            if(j - 1 >= 0 && mainField[i + 1][j - 1].getValue() > 0) {
                mainField[i + 1][j - 1].setBackgroundsNumber();
                mainField[i + 1][j - 1].setOpened();
            }
        }

        if(i - 1 >= 0 && mainField[i - 1][j].getValue() == 0)
            openNotMine(i - 1, j);
        else if(i - 1 >= 0 && mainField[i - 1][j].getValue() > 0){
            mainField[i - 1][j].setBackgroundsNumber();
            mainField[i - 1][j].setOpened();
            if(j + 1 <= fieldWidth - 1 && mainField[i - 1][j + 1].getValue() > 0){
                mainField[i - 1][j + 1].setBackgroundsNumber();
                mainField[i - 1][j + 1].setOpened();
            }
            if(j - 1 >= 0 && mainField[i - 1][j - 1].getValue() > 0) {
                mainField[i - 1][j - 1].setBackgroundsNumber();
                mainField[i - 1][j - 1].setOpened();
            }
        }

        if( j + 1 <= fieldWidth - 1 && mainField[i][j + 1].getValue() == 0)
            openNotMine(i, j + 1);
        else if(j + 1 <= fieldWidth - 1 && mainField[i][j + 1].getValue() > 0){
            mainField[i][j + 1].setBackgroundsNumber();
            mainField[i][j + 1].setOpened();
            if(i + 1 <= fieldHeight - 1 && mainField[i + 1][j + 1].getValue() > 0){
                mainField[i + 1][j + 1].setBackgroundsNumber();
                mainField[i + 1][j + 1].setOpened();
            }
            if(i - 1 >= 0 && mainField[i - 1][j + 1].getValue() > 0) {
                mainField[i - 1][j + 1].setBackgroundsNumber();
                mainField[i - 1][j + 1].setOpened();
            }
        }

        if(j - 1 >= 0 && mainField[i][j - 1].getValue() == 0)
            openNotMine(i, j - 1);
        else if(j - 1 >= 0 && mainField[i][j - 1].getValue() > 0){
            mainField[i][j - 1].setBackgroundsNumber();
            mainField[i][j - 1].setOpened();
        }
    }

    private void setDisable(){
        for(int i = 0; i < fieldWidth; i++){
            for (int j = 0; j < fieldHeight; j++){
                mainField[i][j].setDisable(true);
            }
        }
    }

    public void setFlag(int i, int j, boolean flag){
        if(flag){
            mainField[i][j].setBackgroundFlag();
            if(mainField[i][j].isMine())
                flagCount++;
        }
        else{
            mainField[i][j].setBackgroundIsClosed();
            flagCount--;
        }
        mainField[i][j].setFlaged(flag);
    }

    public boolean isWin(){
        boolean result = true;
        for(int i = 0; i < fieldWidth; i++)
            for(int j = 0; j < fieldHeight; j++){
                if (mainField[i][j].isOpened() && !mainField[i][j].isMine()) {
                    result = false;
                }
            }
        return result;
    }
}
