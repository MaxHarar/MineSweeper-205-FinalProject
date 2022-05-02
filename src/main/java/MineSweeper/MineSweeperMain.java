/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022* Instructor: Prof. Brian King
 * Name: Sam Vickers* Section: 1
 * Date: 4/8/2022
 * Time: 1:09 AM
 * Project: csci205_FinalProject
 * Package: MineSweeper
 * Class: Main
 * Description:
 ******************************************/
package MineSweeper;

import MineSweeper.GameThings.Game;
import MineSweeper.fxFiles.DIFFICULTY;
import MineSweeper.fxFiles.MineSweeperController;
import MineSweeper.fxFiles.MineSweeperView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class MineSweeperMain extends Application {

    private MineSweeperView theView;
    private MineSweeperController theController;
    private Stage primaryStage;
    private Game game;


    private Scene scene;

    @Override
    public void init() throws Exception{
        super.init();

        game = new Game(DIFFICULTY.EASY);
        this.theView = new MineSweeperView(this.game);
        this.theController = new MineSweeperController(this.theView, this.game, this);

    }

    public Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {



        scene = new Scene(theView.getRoot());

        try {
            scene.getStylesheets().add(
                    Objects.requireNonNull(getClass().getResource("/MineSweeperStyleLight.css"))
                            .toExternalForm());
        } catch(NullPointerException e){
            System.out.println("NullPointer Exception ");
        }

        this.primaryStage = primaryStage;
        primaryStage.setTitle("MineSweeper");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public void resetGame(DIFFICULTY difficulty){
        try {
            Game tempGame = new Game(difficulty);
            MineSweeperView tempView = new MineSweeperView(tempGame);
            MineSweeperController tempController = new MineSweeperController(tempView, tempGame, this);

            this.game = tempGame;
            this.theView = tempView;
            this.theController = tempController;

            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}