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
package MineSweeper;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Label {
    private int seconds;

    public GameTimer(){
        seconds = 0;
    }

    public void start(){

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            seconds++;
            setTime();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setTime(){
        String time = String.format("%d:%d", seconds/60, seconds%60);
        this.setText(time);
    }
}