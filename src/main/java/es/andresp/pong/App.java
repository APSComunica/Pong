package es.andresp.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
    
    
    final int SCENE_TAM_X = 640;
    final int SCENE_TAM_Y = 480;
    final int STICK_WIDTH = 7;
    final int STICK_HEIGHT = 50;
    int stickPosY = (SCENE_TAM_Y - STICK_HEIGHT) / 2;

    int stickCurrentSpeed = 0;
    
    
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        var scene = new Scene(root, SCENE_TAM_X, SCENE_TAM_Y);
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
        
        //rectangulo 1
        Rectangle rectStick = new Rectangle(SCENE_TAM_X*0.9, stickPosY, STICK_WIDTH, STICK_HEIGHT);
        rectStick.setFill(Color.WHITE);
        root.getChildren().add(rectStick);
        
        
        
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
                    
                    if(ballCenterX >= SCENE_TAM_X) {
                        
                    
                    }
                    
                    //JOISTICK
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()){
                case UP:
                //PULSADA TECLA ARRIBA
                    stickCurrentSpeed = -6;
                    break;
                case DOWN:
                //PULSADA TECLA ABAJO
                    stickCurrentSpeed = 6;
                    break;
            }
        }); 
    
        scene.setOnKeyReleased((KeyEvent event) -> {
            stickCurrentSpeed = 0;
        });
     
        //actualizar posicion de la pala
        stickPosY += stickCurrentSpeed;
        if(stickPosY < 0) {
            //No sobrepasar borde superior de la ventana
            stickPosY = 0;
        } else {
            // No sobrepasar borde inferior de la ventana
           if(stickPosY > SCENE_TAM_Y - STICK_HEIGHT) {
                stickPosY = SCENE_TAM_Y - STICK_HEIGHT;
            }
        }
        rectStick.setY(stickPosY);
        
                }
            })                
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();  
        
        
        
   Shape.intersect(circleBall, rectStick);     
   Shape shapeColision = Shape.intersect(circleBall, rectStick);
   boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();
   if(colisionVacia == false) {
       //colision detectada. Mover bola hacia izquierda
        ballCurrentSpeedX = -3;
        
    }     
        
        
        
        
        
    }

    public static void main(String[] args) {
        launch();
    }

}