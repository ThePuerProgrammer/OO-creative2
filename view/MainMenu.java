package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class MainMenu {
    private JFrame window;

    public MainMenu(JFrame window) {
        this.window = window;
        window.setTitle("Climber");
    }

    public void init() {
        Dimension buttonSize = new Dimension(100, 40);
        Container contentPane = window.getContentPane();
        JPanel mPanel = new JPanel();
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(500, 400));
        contentPane.add(BorderLayout.NORTH, spacer);

        mPanel.setPreferredSize(new Dimension(500, 100));
        contentPane.add(BorderLayout.SOUTH, mPanel);
        JButton start = new JButton("Start");
        start.setPreferredSize(buttonSize);
        mPanel.add(start);
        JButton quit = new JButton("Exit");
        quit.setPreferredSize(buttonSize);
        mPanel.add(quit);


        start.addActionListener( e -> {
            window.getContentPane().removeAll();
            GameView game = new GameView(window);
            game.init();
            window.pack();
            window.setLocationRelativeTo(null);
            window.revalidate();
        });

        quit.addActionListener( e -> {
            System.exit(0);
        });
    }
}
