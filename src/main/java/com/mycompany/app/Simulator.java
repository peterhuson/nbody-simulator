package com.mycompany.app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * The Simulator models all the physics
 */
public class Simulator {
    private Random random = new Random();
    private Configuration config;
    private int numBalls;
    protected ArrayList<ArrayList> velocities = new ArrayList<ArrayList>();
    private ArrayList<Node> sphereList = new ArrayList<Node>();

    public Simulator(Configuration config) {
        this.config = config;
    }

    /**
     * Use Gaussians to randomly initialize the application over a Normal distribution
     */
    public ArrayList<Node> Initialize() {

        this.numBalls = (int) (random.nextGaussian() * config.numBallsSTD) + config.avgBalls;
        for (int i = 0; i < this.numBalls; i++) {
            Sphere sphere = new Sphere(random.nextGaussian() * config.sizeSTD + config.avgSize);
            sphere.setTranslateX(random.nextInt(config.windowWidth - 100) + 50);
            sphere.setTranslateY(random.nextInt(config.windowHeight - 100) + 50);

            double theta = random.nextDouble() * 360;
            double magnitude = random.nextGaussian() * config.velocitySTD + config.avgVelocity;

            ArrayList velocity = new ArrayList(Arrays.asList(magnitude * Math.sin(theta), magnitude * Math.cos(theta)));
            velocities.add(velocity);
            sphereList.add(sphere);
        }
        return sphereList;
    }

    /**
     * Handle each frame of the simulation, detecting collisions and updating velocities.
     */
    public void Simulate(final ArrayList<Node> sphereList) {
        Timeline timeline = new Timeline(new KeyFrame(config.frameDuration, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                for (int i = 0; i < sphereList.size(); i++) {
                    Sphere sphere = (Sphere) sphereList.get(i);

                    ArrayList vel = velocities.get(i);
                    // Update Positions
                    sphere.setTranslateX(sphere.getTranslateX() + (double) vel.get(0));
                    sphere.setTranslateY(sphere.getTranslateY() + (double) vel.get(1));
                    //Left or right border collisions
                    if (sphere.getTranslateX() <= (sphere.getRadius()) ||
                            sphere.getTranslateX() >= (config.windowWidth - sphere.getRadius())) {
                        vel.set(0, -(double) vel.get(0));
                    }

                    //Top or bottom border collisions
                    if ((sphere.getTranslateY() >= (config.windowHeight - sphere.getRadius())) ||
                            (sphere.getTranslateY() <= (sphere.getRadius()))) {
                        vel.set(1, -(double) vel.get(1));
                    }
                    velocities.set(i, vel);
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
