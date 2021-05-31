package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Maze extends JPanel {

    private Pacman pacman;
    private RedGhost redGhost;
    private BlueGhost blueGhost;
    private PinkGhost pinkGhost;
    private OrangeGhost orangeGhost;
    private final int WIDTH = 28;
    private final int HEIGHT = 31;
    private final int FIELD_SIZE = 30;
    private final int SCREEN_WIDTH = WIDTH * FIELD_SIZE;
    private final int SCREEN_HEIGHT = HEIGHT * FIELD_SIZE;
    public static int score = 0;
    private final Font scoreFont = new Font("Arial", Font.BOLD, 18);

    public int[] actualMoveVector = {0, 0};

    public Maze() {
        pacman = new Pacman(mazeData, FIELD_SIZE, WIDTH);
        redGhost = new RedGhost(mazeData, FIELD_SIZE, WIDTH);
        blueGhost = new BlueGhost(mazeData, FIELD_SIZE, WIDTH);
        pinkGhost = new PinkGhost(mazeData, FIELD_SIZE, WIDTH);
        orangeGhost = new OrangeGhost(mazeData, FIELD_SIZE, WIDTH);
        addKeyListener(new PacmanAdapter());
        setFocusable(true);
        setBackground(Color.black);
    }

    /*
    * 0 - blue wall                    6 - Red ghost spawn
    * 1 - black empty path             7 - Blue ghost spawn
    * 2 - path with food               8 - Orange ghost spawn
    * 4 - white line                   9 - Pink ghost spawn
    * 5 - spawn place for Pacman
    * */
    private short[] mazeData = {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0,
            0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0,
            0, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 4, 4, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 6, 1, 1, 1, 1, 9, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 7, 1, 1, 1, 1, 8, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0,
            0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 5, 5, 2, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 0,
            0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0,
            0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0,
            0, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 0,
            0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0,
            0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0,
            0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    };

    public void drawMaze(Graphics2D graphics) {
        short i = 0;
        graphics.setStroke(new BasicStroke(5));
        Color blue = new Color(10, 10, 250);
        Color yellow = new Color(255, 179, 0);

        for (int x = 0; x < SCREEN_HEIGHT; x += FIELD_SIZE) {
            for (int y = 0; y < SCREEN_WIDTH; y += FIELD_SIZE) {

                if (mazeData[i] == 0) {
                    graphics.setColor(blue);
                    graphics.fillRect(y, x, FIELD_SIZE*4/5, FIELD_SIZE*4/5);
                }

                if (mazeData[i] == 1 || mazeData[i] == 3 || mazeData[i] == 5 || mazeData[i] > 6) {
                    graphics.setColor(Color.black);
                    graphics.fillRect(y, x, FIELD_SIZE, FIELD_SIZE);
                }

                if (mazeData[i] == 2) {
                    graphics.setColor(Color.black);
                    graphics.fillRect(y, x, FIELD_SIZE, FIELD_SIZE);
                    graphics.setColor(yellow);
                    graphics.fillOval(y + 10 , x + 10, 5, 5);
                }

                if (mazeData[i] == 4) {
                    graphics.setColor(Color.black);
                    graphics.fillRect(y, x, FIELD_SIZE, FIELD_SIZE);
                    graphics.setColor(Color.white);
                    graphics.drawLine(387,370, 447, 370);
                }
                i++;
            }
        }
    }

    public void drawPacman(Graphics2D graphics, int row, int column) {
        graphics.drawImage(pacman.getImage(), row, column, this);
    }

    public void drawGhosts(Graphics2D graphics) {
        graphics.drawImage(redGhost.getImage(), redGhost.actualX, redGhost.actualY, this);
        graphics.drawImage(blueGhost.getImage(), blueGhost.actualX, blueGhost.actualY, this);
        graphics.drawImage(orangeGhost.getImage(), orangeGhost.actualX, orangeGhost.actualY, this);
        graphics.drawImage(pinkGhost.getImage(), pinkGhost.actualX, pinkGhost.actualY, this);
    }

    public void eatFootIfPossible() {
        if (mazeData[pacman.arrayPlace] == 2){
            mazeData[pacman.arrayPlace] = 1;
            score += 1;
        }
    }

    private void drawScore(Graphics g) {
        String result = "Score: " + score;
        g.setColor(Color.yellow);
        g.setFont(scoreFont);
        g.drawString(result, 10, 950);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawMaze(g2d);
        pacman.makeMove(actualMoveVector, mazeData);
        eatFootIfPossible();
        drawPacman(g2d, pacman.actualX, pacman.actualY);
        drawGhosts(g2d);
        drawScore(g2d);
    }

    class PacmanAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int pressedKey = e.getKeyCode();
            if (pressedKey == KeyEvent.VK_UP) {
                actualMoveVector = new int[] {0, -1};
            }
            if (pressedKey == KeyEvent.VK_DOWN) {
                actualMoveVector = new int[] {0, 1};
            }
            if (pressedKey == KeyEvent.VK_LEFT) {
                actualMoveVector = new int[] {-1, 0};
            }
            if (pressedKey == KeyEvent.VK_RIGHT) {
                actualMoveVector = new int[] {1, 0};
            }
        }
    }
}
