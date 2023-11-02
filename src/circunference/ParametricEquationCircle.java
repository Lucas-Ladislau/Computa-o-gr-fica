package circunference;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ParametricEquationCircle extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = 100;
        for (double theta = 1; theta < 360; theta++) {
            int x = (int) (centerX + radius * Math.cos((Math.PI*theta)/180));
            int y = (int) (centerY + radius * Math.sin((Math.PI*theta)/180));
            g.drawLine(x, y, x, y);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Parametric Circle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ParametricEquationCircle());
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
