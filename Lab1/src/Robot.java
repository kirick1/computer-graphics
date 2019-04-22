import javafx.scene.paint.Color;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.shape.*;

public class Robot extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Lab1");

    Group root = new Group();
    Scene scene = new Scene(root, 300, 220);

    Rectangle head = new Rectangle(130, 30, 40, 40);
    root.getChildren().add(head);
    head.setFill(Color.rgb(127, 0, 255));

    Rectangle leftEye = new Rectangle(135, 40, 10, 5);
    root.getChildren().add(leftEye);
    leftEye.setFill(Color.rgb(246, 255, 18));

    Rectangle rightEye = new Rectangle(155, 40, 10, 5);
    root.getChildren().add(rightEye);
    rightEye.setFill(Color.rgb(246, 255, 18));

    Polygon leftEar = new Polygon();
    leftEar.getPoints().addAll(100.0, 20.0,
        130.0, 10.0,
        130.0, 30.0, 10.0);
    root.getChildren().add(leftEar);
    leftEar.setFill(Color.rgb(0, 0, 254));

    Polygon rightEar = new Polygon();
    rightEar.getPoints().addAll(170.0, 10.0,
        200.0, 30.0,
        170.0, 30.0, 10.0);
    root.getChildren().add(rightEar);
    rightEar.setFill(Color.rgb(0, 0, 254));

    Rectangle torse = new Rectangle(100, 60, 100, 80);
    root.getChildren().add(torse);
    torse.setFill(Color.rgb(127, 0, 255));

    Rectangle leftHand = new Rectangle(80, 60, 20, 55);
    root.getChildren().add(leftHand);
    leftHand.setFill(Color.rgb(127, 0, 255));

    Rectangle rightHand = new Rectangle(200, 60, 20, 45);
    root.getChildren().add(rightHand);
    rightHand.setFill(Color.rgb(127, 0, 255));

    Rectangle leftLeg = new Rectangle(100, 140, 40, 50);
    root.getChildren().add(leftLeg);
    leftLeg.setFill(Color.rgb(127, 0, 255));

    Rectangle leftLegFingers = new Rectangle(140, 170, 10, 20);
    root.getChildren().add(leftLegFingers);
    leftLegFingers.setFill(Color.rgb(127, 0, 255));

    Rectangle rightLeg = new Rectangle(160, 140, 40, 50);
    root.getChildren().add(rightLeg);
    rightLeg.setFill(Color.rgb(127, 0, 255));

    Rectangle rightLegFingers = new Rectangle(200, 170, 10, 20);
    root.getChildren().add(rightLegFingers);
    rightLegFingers.setFill(Color.rgb(127, 0, 255));

    scene.setFill(Color.rgb(192, 192, 192));

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
