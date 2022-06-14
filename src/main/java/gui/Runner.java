package gui;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Runner {
    public static void main(String[] args) {
        Runner runner = new Runner();
        SwingUtilities.invokeLater(runner::showPaintWindow);
    }

    private void showPaintWindow(){
        PaintWindow.instance().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PaintWindow.instance().setVisible(true);
    }
}
