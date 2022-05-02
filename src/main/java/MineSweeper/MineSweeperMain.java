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

    private MineSweeperView theEasyView;
    private MineSweeperController theEasyController;
    private Game gameEasy;

    private MineSweeperView theMediumView;
    private MineSweeperController theMediumController;
    private Game gameMedium;

    private MineSweeperView theHardView;
    private MineSweeperController theHardController;
    private Game gameHard;

    private MineSweeperView theInsaneView;
    private MineSweeperController theInsaneController;
    private Game gameInsane;
    private Scene scene;

    @Override
    public void init() throws Exception{
        super.init();

        game = new Game(DIFFICULTY.EASY);
        this.theView = new MineSweeperView(this.game);
        this.theController = new MineSweeperController(this.theView, this.game, this);

        initEasyDiff();
        initMediumDiff();
        initHardDiff();
        initInsaneDiff();
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

    public void resetGame(){
        try {
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initEasyDiff() throws IOException, URISyntaxException {
        this.gameEasy = new Game(DIFFICULTY.EASY);
        this.theEasyView = new MineSweeperView(this.gameEasy);
        this.theEasyController = new MineSweeperController(this.theEasyView,this.gameEasy,this);
    }

    private void initMediumDiff() throws IOException, URISyntaxException {
        this.gameMedium = new Game(DIFFICULTY.MEDIUM);
        this.theMediumView = new MineSweeperView(this.gameMedium);
        this.theMediumController = new MineSweeperController(this.theMediumView,this.gameMedium,this);
    }

    private void initHardDiff() throws IOException, URISyntaxException {
        this.gameHard = new Game(DIFFICULTY.HARD);
        this.theHardView = new MineSweeperView(this.gameHard);
        this.theHardController = new MineSweeperController(this.theHardView,this.gameHard,this);
    }
    private void initInsaneDiff() throws IOException, URISyntaxException {
        this.gameInsane = new Game(DIFFICULTY.INSANE);
        this.theInsaneView = new MineSweeperView(this.gameInsane);
        this.theInsaneController = new MineSweeperController(this.theInsaneView, this.gameInsane, this);
    }

    public void setToEasy() throws IOException, URISyntaxException {
        initEasyDiff();
        this.game = this.gameEasy;
        this.theView = this.theEasyView;
        this.theController = this.theEasyController;
        resetGame();
    }

    public void setToMedium() throws IOException, URISyntaxException {
        initMediumDiff();
        this.game = this.gameMedium;
        this.theView = this.theMediumView;
        this.theController = this.theMediumController;
        resetGame();
    }

    public void setToHard() throws IOException, URISyntaxException {
        initHardDiff();
        this.game = this.gameHard;
        this.theView = this.theHardView;
        this.theController = this.theHardController;
        resetGame();
    }

    public void setToInsane() throws IOException, URISyntaxException {
        initInsaneDiff();
        this.game = this.gameInsane;
        this.theView = this.theInsaneView;
        this.theController = this.theInsaneController;
        resetGame();
    }


    public static void main(String[] args) {
        launch(args);
    }
}