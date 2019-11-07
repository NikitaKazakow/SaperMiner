package sample.model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class SaperButton extends Button {

    //region Поля
    private boolean isMine; //Кнопка - мина
    private boolean isOpened; //Нажималась ли кнопка
    private boolean isFlaged; //Установлен ли на клетку флажок
    private int value; //Количество мин вокуруг клетки. Если мин нет = 0

    private boolean checked; //Проходил ли клетку рекурсивный алгоритм открытия рядом расположенных клеток без мин

    //region Фоны для кнопок игрового поля

    private static Background[] backgroundsNumbers;
    private static Background backgroundMine;
    private static Background backgroundIsClosed;
    private static Background backgroundIsOpened;
    private static Background backgroundFlag;
    //endregion
    //endregion

    //region Инициализация фонов кнопок игрового поля
    static {
        backgroundsNumbers = new Background[8];
        for(int i = 0; i < 8; i++){
                backgroundsNumbers[i] = new Background(new BackgroundImage(new Image("/resources/" + (i + 1) + ".png"), BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        }

        backgroundMine = new Background(new BackgroundImage(new Image("/resources/mine.png"),BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        backgroundIsClosed = new Background(new BackgroundImage(new Image("/resources/closed.png"),BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        backgroundIsOpened = new Background(new BackgroundImage(new Image("/resources/opened.png"),BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        backgroundFlag = new Background(new BackgroundImage(new Image("/resources/flag.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
    }
    //endregion

    //region Конструкторы
    SaperButton(){
        super();
        super.setBackground(backgroundIsClosed);
        super.setPrefSize(32,32);
        isOpened = false;
        checked = false;
        isFlaged = false;
    }
    //endregion

    boolean isMine(){
        return isMine;
    } //Возвращает true - кнопка мина

    public boolean isFlaged(){
        return !this.isFlaged;
    } //Стоит ли флажок

    void setFlaged(boolean value){
        this.isFlaged = value;
    } //Установка флажка

    void setOpened(){
        this.isOpened = true;
    }

    boolean isChecked(){
        return this.checked;
    }

    void setChecked(){
        this.checked = true;
    }

    public boolean isOpened() {return !isOpened;}

    int getValue(){
        return value;
    }

    void incValue(){
        this.value += 1;
    }

    void setMineStatus(){
        this.isMine = true;
    }

    void setBackgroundOpened(){
        super.setBackground(backgroundIsOpened);
    }

    void setBackgroundIsClosed(){
        super.setBackground(backgroundIsClosed);
    }

    void setBackgroundsNumber(){
        if(getValue() > 0)
            super.setBackground(backgroundsNumbers[getValue() - 1]);
        else
            super.setBackground(backgroundIsOpened);
    }

    void setBackgroundMine(){
        super.setBackground(backgroundMine);
    }

    void setBackgroundFlag(){
        super.setBackground(backgroundFlag);
    }
}
