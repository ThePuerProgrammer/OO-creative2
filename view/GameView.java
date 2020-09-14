package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Container;
import java.awt.Dimension;

public class GameView {
    private JFrame window;
    private GameCanvas canvas;

    public GameView(JFrame window) {
        this.window = window;
        window.setTitle("Climber");
    }

    public void init() {
        Container contentPane = window.getContentPane();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 800));
        contentPane.add(panel);

        canvas = new GameCanvas(this);
        contentPane.add(canvas);
    }
}
