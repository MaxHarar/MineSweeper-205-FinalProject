/* ***************************************** * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Max Harar
 * Section: MWF 11am
 *Date: 4/24/22
 * Time: 9:13 PM
 *
 * Project: csci205_FinalProject
 * Package: MineSweeper
 * Class: EndGamePopUp
 ** Description:
 *
 *
 *****************************************/
package MineSweeper.fxFiles;

import MineSweeper.MineSweeperMain;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class EndGamePopUp extends Stage {

    private final Button exitBtn;
    private final Label gameOverMsg;
    private final Scene s;
    private final Button playAgainBtn;
    private final VBox root;
    private DIFFICULTY currentDiff;

    MineSweeperMain main;

    public EndGamePopUp(MineSweeperMain main,String message, DIFFICULTY currentDiff) {
        super();
        this.main = main;
        this.currentDiff = currentDiff;
        this.setTitle(message);
        this.setResizable(false);
        this.initModality(Modality.APPLICATION_MODAL);

        root = new VBox();
        gameOverMsg = new Label(message);
        playAgainBtn = new Button("Play again");
        exitBtn = new Button("Exit");

        root.getChildren().addAll(gameOverMsg, playAgainBtn, exitBtn);

        s = new Scene(root);
        initHandlers();


        this.setScene(s);
    }

    private void initHandlers(){

        playAgainBtn.setOnAction(event -> {
            if (this.currentDiff == null){
                this.currentDiff = DIFFICULTY.EASY;
            }else{
                main.resetGame(currentDiff);
            }
            this.close();
        });

        exitBtn.setOnAction(event -> {

            Platform.exit();
            System.exit(0);

        });
    }

    public void initStyle(boolean isDark){

        if (isDark){
            try {
                s.getStylesheets().add(
                        Objects.requireNonNull(getClass().getResource("/MineSweeperStyleDark.css"))
                                .toExternalForm());
            } catch(NullPointerException e){
                System.out.println("NullPointer Exception ");
            }
        }else{

            try {
                s.getStylesheets().add(
                        Objects.requireNonNull(getClass().getResource("/MineSweeperStyleLight.css"))
                                .toExternalForm());
            } catch(NullPointerException e){
                System.out.println("NullPointer Exception ");
            }
        }


        root.getStyleClass().add("EndGamePopUpRoot");
        gameOverMsg.getStyleClass().add("EndGamePopUpLabel");


    }

}
