package pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import Utils.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Game {
    private static final int BIRD_WIDTH = 40;
    private static final int BIRD_HEIGHT = 40;
    private static final int JUMP_POWER = -10;
    private static final double GRAVITY = 1;
    private static final int GAP = 180;
    private static final int GAME_SPEED = 5;
    private static final int INITIAL_Y_POSITION = 350;
    private static final int INITIAL_X_POSITION = 100;

    private Bird bird;
    private int yPos = INITIAL_Y_POSITION;
    private int velocityY = 0; // Start with no vertical velocity
    private boolean isGameOver = false;
    private boolean isGameStarted = false;
    private int fps = 60; // Max FPS
    private List<Pipe> pipes = new ArrayList<>(2);
    private Text hint;
    private Timer gameLoop;
    private Pipe topPipe;
    private Pipe bottomPipe;
    private Random random;
    private int gamePaneHeight;

    private Sound jumpSound;
    private Sound fallSound;
    private Sound hitSound;
    private Sound pointSound;

    @FXML
    private Pane gamePane;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            gamePaneHeight = (int) gamePane.getHeight();
            initializeGameElements();
            initializeGameSounds();
        });
    }

    private void initializeGameSounds() {
        jumpSound = new Sound("src\\sounds\\audio_jump.wav");
        fallSound = new Sound("src\\sounds\\audio_fall.wav");
        hitSound = new Sound("src\\sounds\\audio_hit.wav");
        pointSound = new Sound("src\\sounds\\audio_point.wav");
    }

    private void initializeGameElements() {
        random = new Random();
        initializeFirstPipes();
        initializeHintText();
        initializeFlappyBird();
        setupGameLoop();
        gamePane.setOnMouseClicked(this::handleJump);
    }

    private void setupGameLoop() {
        gameLoop = new Timer(1000 / fps, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isGameOver) {
                    updatePositions();
                    updatePipes();
                    redrawItems();
                } else {
                    gameLoop.stop();
                }
            }
        });
    }

    private void initializeHintText() {
        hint = new Text(45, 200, "Tap to start playing");
        hint.setFont(new Font("Freestyle Script", 50));
        gamePane.getChildren().add(hint);
    }

    private void initializeFlappyBird() {
        bird = new Bird(INITIAL_X_POSITION, yPos, BIRD_WIDTH, BIRD_HEIGHT);
        gamePane.getChildren().add(bird);
    }

    private void initializeFirstPipes() {
        int pipeHeight = 100; // Define the height of the pipe
        pipes.add(new Pipe(400, 0, pipeHeight));
        topPipe = pipes.get(0);
        pipes.add(new Pipe(400, pipeHeight + GAP, (gamePaneHeight + pipeHeight + GAP)));
        bottomPipe = pipes.get(1);
        topPipe.setBackgroundImage("ImageRessources/pipe_up.png");
        bottomPipe.setBackgroundImage("ImageRessources/pipe_down.png");
        gamePane.getChildren().addAll(pipes);
    }

    private void handleJump(MouseEvent event) {
        if (!isGameOver) {
            jumpSound.play();
            velocityY = JUMP_POWER; // Set a negative velocity to jump
            hint.setText(""); // Clear the hint text when the game starts
            if (!isGameStarted) {
                gameLoop.start();
                isGameStarted = true;
            }
        } else {
            resetGame(); // Reset the game
        }
    }

    private void updatePositions() {
        yPos += velocityY;
        velocityY += GRAVITY; // Apply gravity
        checkGameOverConditions();
        checkGettingPointConditions();
    }

    private void checkGettingPointConditions() {
        if (topPipe.getX() == INITIAL_X_POSITION - Pipe.PIPE_WIDTH - GAME_SPEED) {
            pointSound.play();
        }
    }

    private void checkGameOverConditions() {
        // Falling
        if (yPos > gamePane.getHeight()) {
            fallSound.play();
            setGameOver("Game Over!");
        }
        // Hitting the Pipes
        if (CollisionUtil.isColliding(topPipe, bird) || CollisionUtil.isColliding(bottomPipe, bird)) {
            hitSound.play();
            setGameOver("Game Over!");
        }
    }

    private void setGameOver(String message) {
        isGameOver = true;
        hint.setText(message);
        hint.setX(100);
        hint.setY(250);
    }

    private void resetGame() {
        for (Pipe pipe : pipes) {
            pipe.setX(400);
        }
        yPos = INITIAL_Y_POSITION;
        velocityY = 0;
        isGameOver = false;
        hint.setText("");
        gameLoop.start();
    }

    private void redrawItems() {
        bird.setY(Math.max(yPos, 0));
    }

    private void updatePipes() {
        topPipe.moveLeft(GAME_SPEED);
        bottomPipe.moveLeft(GAME_SPEED);
        if (topPipe.getX() < (0 - Pipe.PIPE_WIDTH)) {
            resetPipePositions();
        }
    }

    private void resetPipePositions() {
        topPipe.setX(400);
        bottomPipe.setX(400);
        int minPipeHeight = 100;
        int topPipeNewHeight = random.nextInt((gamePaneHeight - GAP - minPipeHeight - minPipeHeight) + 1) + minPipeHeight;
        int bottomPipeNewYcoordinate = topPipeNewHeight + GAP;
        bottomPipe.setY(bottomPipeNewYcoordinate);
        topPipe.updateHeight(topPipeNewHeight);
        bottomPipe.updateHeight(gamePaneHeight - bottomPipeNewYcoordinate);
    }
}