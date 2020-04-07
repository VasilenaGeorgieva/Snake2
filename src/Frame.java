import javax.swing.*;

public class Frame {

    public Frame() {
        JFrame jFrame = new JFrame();

        Screen screen = new Screen();

        jFrame.add(screen);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("Snake");
        jFrame.setResizable(false);

        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Frame();
    }
}
