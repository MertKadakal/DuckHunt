import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Duck {
    ImageView duck;
    int defaultY;
    int[] arr = {4,5,6};
    int[] arr2 = {1,2,3};
    int i = 0;
    String situation = "Flying";
    Timeline timeline;
    static StackPane pane2 = new StackPane();

    public void iIncrease() {
        i++;
        i = i%3;
    }

    public void dead() {
        String imagePath = "DuckHunt\\assets\\duck_black\\8.png";
        File imageFile = new File(imagePath);
        Image image = new Image(imageFile.toURI().toString());
        duck.setImage(image);
    }
    
    public void ammoShoot(double mx, double my) {
        if (((Math.abs((mx-256)-duck.getTranslateX()) < 20) && (Math.abs((my-240)-duck.getTranslateY()) < 20)) && !(situation.equals("dead"))) {
            Play.duckAmount--;
            if (Play.duckAmount==0) {
                DuckHunt.screen = "Game Over/Next Level";

                Label text;
                Label text2;
                if (Play.level == 6) {
                    text = new Label("You have completed the game!");
                    text2 = new Label("Press ENTER to play again\n         Press ESC to exit");
                }
                else {
                    text = new Label("YOU WIN!");
                    text2 = new Label("Press ENTER to play next level");
                }

                text.setFont(Font.font("Arial", 30));
                text2.setFont(Font.font("Arial", 30));
                text.setStyle("-fx-text-fill: yellow");
                text2.setStyle("-fx-text-fill: yellow");
                VBox vbox = new VBox(2);
                vbox.setAlignment(Pos.CENTER);
                vbox.getChildren().addAll(text, text2);
                Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), event -> {
                        text2.setVisible(!text2.isVisible());
                    })
                );
                timeline.setCycleCount(Timeline.INDEFINITE);

                timeline.play();
                DuckHunt.pane.getChildren().addAll(vbox);
            }
            
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

            situation = "Shot";

            String imagePath = "DuckHunt\\assets\\duck_black\\7.png";
            File imageFile = new File(imagePath);
            Image image = new Image(imageFile.toURI().toString());
            duck.setImage(image);

            if (duck.getScaleY() == -1) {
                duck.setRotate(180);
            }
            else {
                duck.setRotate(0);
            }

            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dead();
                situation = "Dead";
            }).start();

        }
    }

    public Duck(String direction, int index) {

        if (direction.equals("str")) {
            String imagePath = "DuckHunt\\assets\\duck_black\\4.png";
            File imageFile = new File(imagePath);
            Image image = new Image(imageFile.toURI().toString());
            duck = new ImageView(image);
            duck.setFitWidth(55);
            duck.setFitHeight(55);
            pane2.getChildren().add(duck);


            if (Play.level == 1) {
                duck.setTranslateY(-150);
                DuckHunt.pane.getChildren().add(1,pane2);

            }
            if (Play.level == 3) {
                if (index == 1) {
                    duck.setTranslateY(-150);
                    DuckHunt.pane.getChildren().add(1,pane2);

                }
                if (index == 2) {
                    duck.setTranslateY(-90);
                    duck.setRotate(180);
                    duck.setScaleY(-1);
                }
            }
            if (Play.level == 5) {
                if (index == 1) {
                    duck.setTranslateY(-150);
                    DuckHunt.pane.getChildren().add(1,pane2);

                }
                if (index == 2) {
                    duck.setTranslateY(-90);
                    duck.setRotate(180);
                    duck.setScaleY(-1);
                }
            }
        }
        else if (direction.equals("cro")) {
            String imagePath = "DuckHunt\\assets\\duck_black\\1.png";
            File imageFile = new File(imagePath);
            Image image = new Image(imageFile.toURI().toString());
            duck = new ImageView(image);
            duck.setFitWidth(55);
            duck.setFitHeight(55);
            pane2.getChildren().add(duck);


            if ((Play.level == 2) || (Play.level == 5)) {
                duck.setTranslateY(-150);
                if (index == 1) {
                    DuckHunt.pane.getChildren().add(1,pane2);
                }

            }
            if (Play.level == 4) {
                if (index == 1) {
                    duck.setTranslateY(-150);
                    DuckHunt.pane.getChildren().add(1,pane2);

                }
                if (index == 2) {
                    duck.setTranslateY(-90);
                    duck.setRotate(180);
                }
            }
            if (Play.level == 6) {
                if (index == 1) {
                    duck.setTranslateY(-150);
                    DuckHunt.pane.getChildren().add(1,pane2);

                }
                if (index == 2) {
                    duck.setTranslateY(-90);
                    duck.setRotate(180);

                }
                if (index == 3) {
                    duck.setTranslateY(-60);
                }
            }
        }

        do {
            timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.2), event -> {
                    if (situation.equals("Flying")) {
                        if (direction.equals("str")) {
                            if (duck.getRotate() == 0) {
                                duck.setTranslateX(duck.getTranslateX()+30);
                                if (duck.getTranslateX() > 230) {
                                    duck.setRotate(180);
                                    duck.setScaleY(-1);
                                }
                            }
                            else {
                                duck.setTranslateX(duck.getTranslateX()-30);
                                if (duck.getTranslateX() < -230) {
                                    duck.setRotate(0);
                                    duck.setScaleY(1);
                                }
                            }

                            for (int i = 0; i < Play.ducks.size(); i++) {
                                if (!(i == index-1)) {
                                    if ((Math.abs(Play.ducks.get(i).duck.getTranslateX()-duck.getTranslateX()) < 40) && (Math.abs(Play.ducks.get(i).duck.getTranslateY()-duck.getTranslateY()) < 40)) {
                                        if (duck.getRotate() == 0) {
                                            duck.setRotate(180);
                                            duck.setScaleY(-1);
                                        }
                                        else if (duck.getRotate() == 180) {
                                            duck.setRotate(0);
                                            duck.setScaleY(1);
                                        }
                                    }
                                }
                            }

                            String imagePath = "DuckHunt\\assets\\duck_black\\" + arr[i] + ".png";
                            File imageFile = new File(imagePath);
                            Image image = new Image(imageFile.toURI().toString());
                            duck.setImage(image);
                            iIncrease();
                        }
                        else if (direction.equals("cro")) {
                            if (duck.getRotate()%360 == 0) {
                                duck.setTranslateX(duck.getTranslateX()+30);
                                duck.setTranslateY(duck.getTranslateY()-30);
                    
                            }
                            else if ((duck.getRotate()%360 == 90) || (duck.getRotate()%360 == -270)) {
                                duck.setTranslateX(duck.getTranslateX()+30);
                                duck.setTranslateY(duck.getTranslateY()+30);
                                
                            }
                            else if ((duck.getRotate()%360 == 180) || (duck.getRotate()%360 == -180)) {
                                duck.setTranslateX(duck.getTranslateX()-30);
                                duck.setTranslateY(duck.getTranslateY()+30);
                                
                            }
                            else if ((duck.getRotate()%360 == 270) || (duck.getRotate()%360 == -90)) {
                                duck.setTranslateX(duck.getTranslateX()-30);
                                duck.setTranslateY(duck.getTranslateY()-30);
                            
                            }
                            if ((duck.getTranslateY() > 220) || (duck.getTranslateX() < -220) || (duck.getTranslateY() < -220) || (duck.getTranslateX() > 220)) {
                                if (duck.getTranslateY() > 220) {
                                    if ((duck.getRotate()%360 == 90) || (duck.getRotate()%360 == -270)) {
                                        duck.setRotate(duck.getRotate()-90);
                                    }
                                    else if ((duck.getRotate()%360 == 180) || (duck.getRotate()%360 == -180)) {
                                        duck.setRotate(duck.getRotate()+90);
                                    }
                                }
                                if (duck.getTranslateY() < -220) {
                                    if (duck.getRotate()%360 == 0) {
                                        duck.setRotate(duck.getRotate()+90);
                                    }
                                    else if ((duck.getRotate()%360 == 270) || (duck.getRotate()%360 == -90)) {
                                        duck.setRotate(duck.getRotate()-90);
                                    }
                                }
                                if (duck.getTranslateX() > 220) {
                                    if (duck.getRotate()%360 == 0) {
                                        duck.setRotate(duck.getRotate()-90);
                                    }
                                    else if ((duck.getRotate()%360 == 90) || (duck.getRotate()%360 == -270)) {
                                        duck.setRotate(duck.getRotate()+90);
                                    }
                                }
                                if (duck.getTranslateX() < -220) {
                                    if ((duck.getRotate()%360 == 180) || (duck.getRotate()%360 == -180)) {
                                        duck.setRotate(duck.getRotate()-90);
                                    }
                                    else if ((duck.getRotate()%360 == 270) || (duck.getRotate()%360 == -90)) {
                                        duck.setRotate(duck.getRotate()+90);
                                    }
                                }
                            }

                            for (int i = 0; i < Play.ducks.size(); i++) {
                                if (!(i == index-1)) {
                                    if ((Math.abs(Play.ducks.get(i).duck.getTranslateX()-duck.getTranslateX()) < 90) && (Math.abs(Play.ducks.get(i).duck.getTranslateY()-duck.getTranslateY()) < 90)) {
                                        if (duck.getRotate()%360 == 0) {
                                            duck.setRotate(duck.getRotate()-90);
                                        }
                                        else if ((duck.getRotate()%360 == 90) || (duck.getRotate()%360 == -270)) {
                                            duck.setRotate(duck.getRotate()+90);
                                        }
                                        else if ((duck.getRotate()%360 == 180) || (duck.getRotate()%360 == -180)) {
                                            duck.setRotate(duck.getRotate()-90);
                                        }
                                        else if ((duck.getRotate()%360 == 270) || (duck.getRotate()%360 == -90)) {
                                            duck.setRotate(duck.getRotate()+90);
                                        }
                                    }
                                }
                            }

                            String imagePath = "DuckHunt\\assets\\duck_black\\" + arr2[i] + ".png";
                            File imageFile = new File(imagePath);
                            Image image = new Image(imageFile.toURI().toString());
                            duck.setImage(image);
                            iIncrease();
                        }
                    }
                    else if (situation.equals("Dead")) {
                        duck.setTranslateY(duck.getTranslateY()+30);
                        if (duck.getTranslateY() > 150) {
                            timeline.stop();
                        }
                    }
                })
            );
        } while (DuckHunt.screen.equals("Game") && (duck.getTranslateY() < 480));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        DuckHunt.scene.setOnKeyPressed(event -> {
            if (DuckHunt.screen.equals("Game Over/Next Level")) {
                if (event.getCode() == KeyCode.ENTER) {
                    if ((Play.duckAmount > 0) || (Play.level == 6)) {
                        DuckHunt.pane.getChildren().remove(1);
                        Play.level = 1;
                        Play.startLevel(1);
                        DuckHunt.screen = "Game";
                        DuckHunt.pane.getChildren().removeLast();
                    }
                    else {
                        DuckHunt.pane.getChildren().remove(1);
                        Play.startLevel(++Play.level);
                        DuckHunt.screen = "Game";
                        DuckHunt.pane.getChildren().removeLast();
                    }
                }
                if (event.getCode() == KeyCode.ESCAPE) {
                    if ((Play.duckAmount > 0) || (Play.level == 6)) {
                        DuckHunt.pane.getChildren().clear();
                        Play.level = 1;
                        DuckHunt.screen = "Title";
                        DuckHunt.scene.setCursor(Cursor.DEFAULT);
                        DuckHunt.main();
                    }
                }
            }
        });
    }
}