package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Container;
import java.awt.Dimension;

public class MainMenu {
    private JFrame window;

    public MainMenu(JFrame window) {
        this.window = window;
    }

    public void init() {
        Container contentPane = window.getContentPane();
        JPanel mPanel = new JPanel();

        mPanel.setPreferredSize(new Dimension(500, 500));
    }
}
