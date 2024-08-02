import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SettingBackground {
    static int num = 1;
    public static void changeTheBackground(int num) {
        String imagePath = "DuckHunt\\assets\\background\\"+num+".png";
        File imageFile = new File(imagePath);
        Image image = new Image(imageFile.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(512);
        imageView.setFitHeight(480);
        DuckHunt.pane.getChildren().remove(0);
        DuckHunt.pane.getChildren().add(0, imageView);
    }

    public static void changeTheCrosshair(int num) {
        String imagePath = "DuckHunt\\assets\\crosshair\\"+num+".png";
        File imageFile = new File(imagePath);
        Image image = new Image(imageFile.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        DuckHunt.pane.getChildren().remove(1);
        DuckHunt.pane.getChildren().add(1, imageView);
    }

    public static void main() {
        String imagePath = "DuckHunt\\assets\\background\\1.png";
        File imageFile = new File(imagePath);
        Image image = new Image(imageFile.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(512);
        imageView.setFitHeight(480);
        DuckHunt.pane.getChildren().add(imageView);

        String imagePath2 = "DuckHunt\\assets\\crosshair\\1.png";
        File imageFile2 = new File(imagePath2);
        Image image2 = new Image(imageFile2.toURI().toString());
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(20);
        imageView2.setFitHeight(20);
        DuckHunt.pane.getChildren().add(imageView2);

        VBox vBox = new VBox(2);
        Label text1 = new Label("USE ARROW KEYS TO NAVIGATE");
        Label text2 = new Label("PRESS ENTER TO START");
        Label text3 = new Label("PRESS ESC TO EXIT");
        vBox.getChildren().addAll(text1, text2, text3);
        vBox.setAlignment(Pos.TOP_CENTER);
        text1.setTextFill(Color.YELLOW);
        text1.setFont(Font.font("Arial", 15));
        text2.setTextFill(Color.YELLOW);
        text2.setFont(Font.font("Arial", 15));
        text3.setTextFill(Color.YELLOW);
        text3.setFont(Font.font("Arial", 15));
        DuckHunt.pane.setMargin(vBox, new Insets(10, 0, 0, 0));
        DuckHunt.pane.getChildren().add(vBox);

        DuckHunt.scene.setOnKeyPressed(event -> {
            if (DuckHunt.screen.equals("Setting Background")) {
                if (event.getCode() == KeyCode.RIGHT) {
                    if (num == 6) {
                        changeTheBackground(1);
                        num = 1;
                    }
                    else {
                        changeTheBackground(++num);
                    }
                }
                else if (event.getCode() == KeyCode.LEFT) {
                    if (num == 1) {
                        changeTheBackground(6);
                        num = 6;
                    }
                    else {
                        changeTheBackground(--num);
                    }
                }
                else if (event.getCode() == KeyCode.UP) {
                    if (num == 6) {
                        changeTheCrosshair(1);
                        num = 1;
                    }
                    else {
                        changeTheCrosshair(++num);
                    }
                }
                else if (event.getCode() == KeyCode.DOWN) {
                    if (num == 1) {
                        changeTheCrosshair(6);
                        num = 6;
                    }
                    else {
                        changeTheCrosshair(--num);
                    }
                }
                else if (event.getCode() == KeyCode.ENTER) {
                    DuckHunt.pane.getChildren().remove(2);
                    Play.main(DuckHunt.pane.getChildren().get(1));

                    String imagePath3 = "DuckHunt\\assets\\foreground\\" + num + ".png";
                    File imageFile3 = new File(imagePath3);
                    Image image3 = new Image(imageFile3.toURI().toString());
                    ImageView imageView3 = new ImageView(image3);
                    imageView3.setFitWidth(512);
                    imageView3.setFitHeight(480);
                    DuckHunt.pane.getChildren().add(2,imageView3);
                    
                    DuckHunt.screen = "Game";
                }
                else if (event.getCode() == KeyCode.ESCAPE) {
                    DuckHunt.screen = "Title";
                    DuckHunt.scene.setCursor(Cursor.DEFAULT);
                    DuckHunt.main();
                }
            }
        });
    }
}
