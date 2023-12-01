package bezier;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class BezierDrawing extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    public BezierDrawing() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Control points
        Point2D.Double p0 = new Point2D.Double(50, 300);
        Point2D.Double p1 = new Point2D.Double(150, 250);
        Point2D.Double p2 = new Point2D.Double(250, 100);
        Point2D.Double p3 = new Point2D.Double(350, 300);

        // Draw control points
        g2d.setColor(Color.RED);
        drawPoint(g2d, p0);
        drawPoint(g2d, p1);
        drawPoint(g2d, p2);
        drawPoint(g2d, p3);

        // Draw Bezier curve
        g2d.setColor(Color.BLUE);
        drawBezierCurve(g2d, p0, p1, p2, p3);
    }

    private void drawPoint(Graphics2D g2d, Point2D.Double point) {
        int diameter = 6;
        int x = (int) point.getX() - diameter / 2;
        int y = (int) point.getY() - diameter / 2;
        g2d.fillOval(x, y, diameter, diameter);
    }

    private void drawBezierCurve(Graphics2D g2d, Point2D.Double p0, Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        Path2D.Double path = new Path2D.Double();
        path.moveTo(p0.getX(), p0.getY());

        for (double t = 0; t <= 1; t += 0.01) {
            double x = Math.pow(1 - t, 3) * p0.getX() + 3 * Math.pow(1 - t, 2) * t * p1.getX() +
                    3 * (1 - t) * Math.pow(t, 2) * p2.getX() + Math.pow(t, 3) * p3.getX();

            double y = Math.pow(1 - t, 3) * p0.getY() + 3 * Math.pow(1 - t, 2) * t * p1.getY() +
                    3 * (1 - t) * Math.pow(t, 2) * p2.getY() + Math.pow(t, 3) * p3.getY();

            path.lineTo(x, y);
        }

        g2d.draw(path);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BezierDrawing bezierDrawing = new BezierDrawing();
            bezierDrawing.setVisible(true);
        });
    }
}
