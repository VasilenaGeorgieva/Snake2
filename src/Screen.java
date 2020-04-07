import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Screen extends JPanel implements Runnable, KeyListener{

    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 500, HEIGHT = 500;

    private Thread thread;

    private boolean running;

    private boolean right = true, left = false, up = false, down = false;

    private BodyPart bodyPart;

    private ArrayList<BodyPart> snake;

    private Apple apple;

    private ArrayList<Apple> apples;

    private Random random;

    private int xCoordinate = 10, yCoordinate = 10, size = 15;

    private int ticks = 0;

    public Screen() {
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        addKeyListener(this);

        snake = new ArrayList<BodyPart>();

        apples = new ArrayList<Apple>();

        random = new Random();

        start();
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        if (snake.size() == 0) {
            bodyPart = new BodyPart(xCoordinate, yCoordinate,
                    10);
            snake.add(bodyPart);
        }

        ticks++;

        if (ticks > 250000) {
            if (right) xCoordinate++;
            if (left) xCoordinate--;
            if (up) yCoordinate--;
            if (down) yCoordinate++;

            ticks = 0;

            bodyPart = new BodyPart(xCoordinate, yCoordinate, 10);
            snake.add(bodyPart);

            if (snake.size() > size) {
                snake.remove(0);
            }
        }

        if (apples.size() == 0) {
            int xCoordinate = random.nextInt(49);
            int yCoordinate = random.nextInt(49);

            apple = new Apple(xCoordinate, yCoordinate, 10);
            apples.add(apple);
        }

        for (int i = 0; i < apples.size(); i++) {
            if (xCoordinate == apples.get(i).getxCoordinate()
            && yCoordinate == apples.get(i).getyCoordinate()) {
                size++;
                apples.remove(0);
                i++;
            }
        }

        //collision on snake body part
        for (int i = 0; i < snake.size(); i++) {
            if (xCoordinate == snake.get(i).getxCoordinate() &&
            yCoordinate == snake.get(i).getyCoordinate()) {
                if (i != snake.size() - 1) {
                    System.out.println("Game Over!");
                    stop();
                }
            }
        }

        //collision on border
        if (xCoordinate < 0 || xCoordinate > 49 || yCoordinate < 0 ||
        yCoordinate > 49) {
            System.out.println("Game Over!");
            stop();
        }
    }

    public void paint(Graphics graphics) {
        graphics.clearRect(0, 0, WIDTH, HEIGHT);

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        for (int i = 0; i < WIDTH/10; i++) {
            graphics.drawLine(i * 10, 0, i * 10, HEIGHT);
        }

        for (int i = 0; i < HEIGHT/10; i++) {
            graphics.drawLine(0, i * 10, HEIGHT, i * 10);
        }

        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(graphics);
        }

        for (int i = 0; i < apples.size(); i++) {
            apples.get(i).draw(graphics);
        }
    }

    @Override
    public void run() {
        while (running) {
            tick();
            repaint();
        }
     }

     @Override
     public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT && !left) {
            right = true;
            up = false;
            down = false;
        }

         if (key == KeyEvent.VK_LEFT && !right) {
             left = true;
             up = false;
             down = false;
         }

         if (key == KeyEvent.VK_UP && !down) {
             up = true;
             left = false;
             right = false;
         }

         if (key == KeyEvent.VK_DOWN && !up) {
             down = true;
             left = false;
             right = false;
         }
     }

     @Override
     public void keyReleased(KeyEvent e) {

     }

     @Override
     public void keyTyped(KeyEvent e) {

     }
}
