import javax.swing.JFrame;

import view.MainMenu;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainMenu mainMenu = new MainMenu(window);
        mainMenu.init();
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}