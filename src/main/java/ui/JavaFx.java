package ui;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class JavaFx extends Application{

    @Override
    public void start(Stage s){
        Stage other = new Stage();
        other.setTitle("Other Stage");
        other.show();

//        primaryStage.setTitle("Hello World!");
//        Label label = new Label("Hello World");
//        Scene scene = new Scene(label, 400, 200);
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }
}
