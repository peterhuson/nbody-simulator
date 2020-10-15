package com.mycompany.app;

import javafx.util.Duration;

/**
 * Contains constants that define the operation and characteristics of the
 * operation of the application and properties of the simulator.
 */
public class Configuration {
    public double gravityConstant;
    public int windowHeight, windowWidth, avgBalls, avgVelocity, avgSize;
    public Duration frameDuration;
    public int numBallsSTD, velocitySTD, sizeSTD;

    /**
     * @param filepath Filepath of configuration file
     */
    public void readFromFile(String filepath) {
        gravityConstant = 9.8;
        windowHeight = 500;
        windowWidth = 700;
        avgBalls = 18;
        numBallsSTD = 4;
        avgVelocity = 4;
        velocitySTD = 2;
        avgSize = 5;
        sizeSTD = 3;
        frameDuration = Duration.millis(10);
    }
}
