import javax.swing.*;

public class ElMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Taller2();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500,400);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }
}
