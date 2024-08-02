import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Play {
    static int level = 1;
    static int ammoLeft;
    static ArrayList<Duck> ducks = new ArrayList<>();
    static int duckAmount;
    static Label text1;
    static Label text2;

    public static void startLevel(int level) {
        ducks.clear();
        Duck.pane2.getChildren().clear();
        if (level == 1) {
            ducks.add(new Duck("str", 1));
            duckAmount = 1;
        }
        if (level == 2) {
            ducks.add(new Duck("cro", 1));
            duckAmount = 1;
        }
        if (level == 3) {
            ducks.add(new Duck("str", 1));
            ducks.add(new Duck("str", 2));
            duckAmount = 2;
        }
        if (level == 4) {
            ducks.add(new Duck("cro", 1));
            ducks.add(new Duck("cro", 2));
            duckAmount = 2;
        }
        if (level == 5) {
            ducks.add(new Duck("str", 1));
            ducks.add(new Duck("str", 2));
            ducks.add(new Duck("cro", 3));
            duckAmount = 3;
        }
        if (level == 6) {
            ducks.add(new Duck("cro", 1));
            ducks.add(new Duck("cro", 2));
            ducks.add(new Duck("cro", 3));
            duckAmount = 3;
        }
        ammoLeft = ducks.size()*3;
        text1.setText("Level " + level + "/6");
        text2.setText("Ammo Left: " + ammoLeft);
    }

    public static void main(Node crHair) {
        DuckHunt.scene.setCursor(Cursor.NONE);
        DuckHunt.scene.setOnMouseMoved(event -> {
            crHair.setTranslateX(event.getX()-256);
            crHair.setTranslateY(event.getY()-240);
        });

        text1 = new Label("Level " + level + "/6");
        text2 = new Label("Ammo Left: " + ammoLeft);

        DuckHunt.scene.setOnMouseClicked(event -> {
            if (DuckHunt.screen.equals("Game")) {
                if ((ammoLeft > 0) && (DuckHunt.screen.equals("Game"))) {
                    ammoLeft--;
                    text2.setText("Ammo Left: " + ammoLeft);
    
                    for (int i = 0; i<ducks.size(); i++) {
                        ducks.get(i).ammoShoot(event.getX(), event.getY());
                    }
                }
    
                if ((ammoLeft == 0) && (duckAmount>0)) {
                    DuckHunt.screen = "Game Over/Next Level";
    
                    Label text3 = new Label("GAME OVER!");
                    Label text4 = new Label("Press ENTER to play again\n       Press ESC to exit");
                    text3.setFont(Font.font("Arial", 30));
                    text4.setFont(Font.font("Arial", 30));
                    text3.setStyle("-fx-text-fill: yellow");
                    text4.setStyle("-fx-text-fill: yellow");
                    VBox vbox = new VBox(2);
                    vbox.setAlignment(Pos.CENTER);
                    vbox.getChildren().addAll(text3, text4);
                    DuckHunt.pane.getChildren().addAll(vbox);
    
                    Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(1), event2 -> {
                            text4.setVisible(!text4.isVisible());
                        })
                    );
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                }
            }
        });

        
        text1.setTextFill(Color.YELLOW);
        text2.setTextFill(Color.YELLOW);
        text1.setFont(Font.font("Arial", 15));
        text2.setFont(Font.font("Arial", 15));
        DuckHunt.pane.setMargin(text1, new Insets(-450, 0, 0, 0));
        DuckHunt.pane.setMargin(text2, new Insets(-450, 0, 0, 410));
        DuckHunt.pane.getChildren().addAll(text1, text2);

        startLevel(1);
    }
}
