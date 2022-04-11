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

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MineSweeperMain extends Application {

    private MineSweeperView theView;
    private MineSweeperController theController;
    private Stage primaryStage;
    private Game game;

    @Override
    public void init() throws Exception{
        super.init();
        game = new Game();
        this.theView = new MineSweeperView(this.game);
        this.theController = new MineSweeperController(this.theView, this.game, this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(theView.getRoot());
        try {
            scene.getStylesheets().add(
                    Objects.requireNonNull(getClass().getResource("/MineSweeperStyle.css"))
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
        game = new Game();
        this.theView = new MineSweeperView(this.game);
        this.theController = new MineSweeperController(this.theView, this.game, this);
        try{ start(primaryStage); }catch(Exception e){}
    }

    public static void main(String[] args) {
        launch(args);
    }
}