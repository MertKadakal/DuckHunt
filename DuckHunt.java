import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;



public class DuckHunt extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    static StackPane pane = new StackPane();
    static Scene scene;
    static String screen = "Title";
    static Stage stage;

    public static void main() {
        String imagePath = "DuckHunt\\assets\\favicon\\1.png";
        File imageFile = new File(imagePath);
        Image image = new Image(imageFile.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(512);
        imageView.setFitHeight(480);
        pane.getChildren().add(imageView);

        Label text = new Label("PRESS ENTER TO PLAY\n   PRESS ESC TO EXIT");
        text.setStyle("-fx-text-fill: yellow");
        text.setFont(Font.font("Arial", 35));
        pane.getChildren().add(text);
        pane.setMargin(text, new Insets(175, 0, 0, 0));
        
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), event -> {
                text.setVisible(!text.isVisible());
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                timeline.stop();
                pane.getChildren().clear();

                SettingBackground.main();
                DuckHunt.screen = "Setting Background";
            }
            else if (event.getCode() == KeyCode.ESCAPE) {
                timeline.stop();
                stage.close();
            }
        });
    }

    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        scene = new Scene(pane, 512, 517);
        primaryStage.setScene(scene);
        primaryStage.setTitle("HUBBM Duck Hunt");
        primaryStage.setMaxHeight(517);
        primaryStage.setMinHeight(517);
        primaryStage.setMinWidth(512);
        primaryStage.show();
        
        this.main();
    }
}