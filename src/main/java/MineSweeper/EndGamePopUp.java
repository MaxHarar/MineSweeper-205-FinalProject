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
package MineSweeper;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EndGamePopUp extends Stage {


    private final Button exitBtn;

    public Button getExitBtn() {
        return exitBtn;
    }

    public Button getPlayAgainBtn() {
        return playAgainBtn;
    }

    private final Button playAgainBtn;
    private final Label gameOverMsg;
    private DIFFICULTY currentDiff;

    MineSweeperMain main;

    public EndGamePopUp(MineSweeperMain main,String message, DIFFICULTY currentDiff) {
        super();
        this.main = main;
        this.currentDiff = currentDiff;

        this.setTitle(message);
        this.setResizable(false);

        this.initModality(Modality.APPLICATION_MODAL);




        VBox root = new VBox();
        gameOverMsg = new Label(message);
        playAgainBtn = new Button("Play again");
        exitBtn = new Button("Exit");


        playAgainBtn.setOnAction(event -> {


            if (this.currentDiff == null){
               this.currentDiff = DIFFICULTY.EASY;
            }

            switch(this.currentDiff){
                case EASY:
                    main.setToEasy();
                    break;
                case MEDIUM:
                    main.setToMedium();
                    break;
                case HARD:
                    main.setToHard();
                    break;
                case INSANE:
                    main.setToInsane();
                    break;

            }

            main.resetGame();
            this.close();

        });


        exitBtn.setOnAction(event -> {

            Platform.exit();
            System.exit(0);

        });






        root.getChildren().addAll(gameOverMsg, playAgainBtn, exitBtn);







        Scene s = new Scene(root);
        this.setScene(s);
    }

    private void handleCurrentDiff(){

    }

}
