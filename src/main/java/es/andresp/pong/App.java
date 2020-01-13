package es.andresp.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    int ballCenterX = 10;
    int ballCurrentSpeedX = 5;
    int ballDirectionX = 1;
    
    int ballCenterY = 10;
    int ballCurrentSpeedY = 5;
    int ballDirectionY = 1;
    
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        var scene = new Scene(root, 640, 480);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
        
        //new Circle()=> Creamos objeto clase Circle
        Circle circleBall = new Circle();
        //Llamando a metodos del objeto CircleBall
        circleBall.setCenterX(10);
        circleBall.setCenterY(30);
        circleBall.setRadius(7);
        circleBall.setFill(Color.WHITE);
        root.getChildren().add(circleBall);
        
      Timeline timeline = new Timeline(
            // 0.017 ~= 60 FPS
            new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    circleBall.setCenterX(ballCenterX);
                    ballCenterX+=ballCurrentSpeedX * ballDirectionX;
                    if(ballCenterX >= 640){
                        ballDirectionX = -1;
                    } else if(ballCenterX <= 0){
                        ballDirectionX = 1;
                        
                    }
                    
                    circleBall.setCenterY(ballCenterY);
                    ballCenterY+=ballCurrentSpeedY * ballDirectionY;
                    if(ballCenterY >= 480){
                        ballDirectionY = -1;
                    } else if(ballCenterY <= 0){
                        ballDirectionY = 1;
                        
                    }
                }
            })                
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();   
    }

    public static void main(String[] args) {
        launch();
    }

}