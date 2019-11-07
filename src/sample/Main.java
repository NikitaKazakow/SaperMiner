package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage mainStage;

    public static Stage getMainStage(){
        return mainStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setTitle("Сапёр");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.getIcons().add(new Image(("/resources/mine.png")));
        primaryStage.setResizable(false);
        primaryStage.show();
        mainStage = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
