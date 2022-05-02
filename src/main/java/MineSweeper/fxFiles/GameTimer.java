/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022* Instructor: Prof. Brian King
 * Name: Sam Vickers* Section: 1
 * Date: 4/13/2022
 * Time: 11:27 AM
 * Project: csci205_FinalProject
 * Package: MineSweeper
 * Class: Timer
 * Description:
 ******************************************/
package MineSweeper.fxFiles;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;



public class GameTimer extends Label {
    private int seconds;
    private Timeline timeline;

    public GameTimer(){
        seconds = 0;

    }

    public void start(){

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            seconds++;
            setTime();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setTime(){
        int s = seconds%60;
        String time = String.format("%s:%s", "" + seconds/60, (s > 9) ? "" + s : "0" + s);
        this.setText(time);
    }

    int getTime(){

        return seconds;
    }

    public void stop(){
        timeline.stop();
    }

}