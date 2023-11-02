package circunference;
import javax.swing.*;
import java.awt.*;

public class Symmetry extends JPanel {
    private int xc, yc, radius;

    public Symmetry(int xc, int yc, int radius) {
        this.xc = xc;
        this.yc = yc;
        this.radius = radius;
    }

    public void drawOctant(Graphics g, int x, int y) {
        g.drawLine(x, y, x, y);
    }

    public void drawSymmetricCircle(Graphics g, int xc, int yc, int r) {
        double pi = 3.141593;
        double dtheta = 1.0 / r;
        double theta = 0;
        double xk = r;
        double yk = 0;
        while (theta <= pi / 4) {
            double x = xk * Math.cos(theta) - yk * Math.sin(theta);
            double y = yk * Math.cos(theta) + xk * Math.sin(theta);
            drawOctant(g, (int) (xc + x), (int) (yc + y));
            drawOctant(g, (int) (xc + x), (int) (yc - y));
            drawOctant(g, (int) (xc - x), (int) (yc + y));
            drawOctant(g, (int) (xc - x), (int) (yc - y));
            drawOctant(g, (int) (xc + y), (int) (yc + x));
            drawOctant(g, (int) (xc + y), (int) (yc - x));
            drawOctant(g, (int) (xc - y), (int) (yc + x));
            drawOctant(g, (int) (xc - y), (int) (yc - x));
            theta += dtheta;
            xk -= yk * dtheta;
            yk += xk * dtheta;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSymmetricCircle(g, xc, yc, radius);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Symmetry Circle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Symmetry(150, 150, 100));
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
