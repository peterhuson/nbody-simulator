package com.mycompany.app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Renderer extends Application {
    private final Random random = new Random();

    /**
     * Override JavaFX Application interface
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("N-Body Simulator");
        final Configuration config = new Configuration();
        // Load constants
        config.readFromFile("settings.cfg");

        Simulator simulator = new Simulator(config);
        ArrayList<Node> sphereList = simulator.Initialize();
        Group group = new Group(sphereList);

        // Perspective Camera
        PerspectiveCamera camera = new PerspectiveCamera(false);
        Scene scene = new Scene(group, config.windowWidth, config.windowHeight);
        scene.setCamera(camera);

        // Show
        primaryStage.setScene(scene);
        primaryStage.show();

        simulator.Simulate(sphereList);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
