module csci205_FinalProject {

    requires lombok;
    requires java.desktop;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires smile.core;
    requires smile.io;
    requires smile.plot;
    requires smile.math;
    requires smile.data;
    requires commons.csv;
    requires org.apache.logging.log4j;

    exports MineSweeper;
    exports main;
    exports MineSweeper.GameThings;
    exports MineSweeper.fxFiles;
}