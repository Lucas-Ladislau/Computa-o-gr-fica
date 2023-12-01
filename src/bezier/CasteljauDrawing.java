package bezier;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CasteljauDrawing extends JFrame {

    private List<Point> pontosCalculados = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CasteljauDrawing frame = new CasteljauDrawing();
            frame.setSize(600, 400);
            frame.setTitle("Bezier Curve");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public CasteljauDrawing() {
        BezierPanel bezierPanel = new BezierPanel();
        add(bezierPanel);
    }

    private class BezierPanel extends JPanel {

        public BezierPanel() {
            double P0x = 50, P0y = 300;
            double P1x = 150, P1y = 200;
            double P2x = 250, P2y = 200;
            double P3x = 350, P3y = 300;
            
            double t = 1;
            Casteljau(P0x, P0y, P1x, P1y, P2x, P2y, P3x, P3y, t);

            // Print or visualize the results as needed
            for (Point p : pontosCalculados) {
                System.out.println("x: " + p.x + ", y: " + p.y);
            }
        }

        private void PMCurva(double Ponto0x, double Ponto0y, double Ponto1x, double Ponto1y,
                             double Ponto2x, double Ponto2y, double Ponto3x, double Ponto3y,
                             double[] result) {

            double MEDIA01x = (Ponto0x + Ponto1x) / 2;
            double MEDIA01y = (Ponto0y + Ponto1y) / 2;

            double MEDIA12x = (Ponto1x + Ponto2x) / 2;
            double MEDIA12y = (Ponto1y + Ponto2y) / 2;

            double MEDIA23x = (Ponto2x + Ponto3x) / 2;
            double MEDIA23y = (Ponto2y + Ponto3y) / 2;

            double MEDIA012x = (MEDIA01x + MEDIA12x) / 2;
            double MEDIA012y = (MEDIA01y + MEDIA12y) / 2;

            double MEDIA123x = (MEDIA12x + MEDIA23x) / 2;
            double MEDIA123y = (MEDIA12y + MEDIA23y) / 2;

            double MEDIA0123x = (MEDIA012x + MEDIA123x) / 2;
            double MEDIA0123y = (MEDIA012y + MEDIA123y) / 2;

            pontosCalculados.add(new Point(MEDIA0123x, MEDIA0123y));

            result[0] = Ponto0x;
            result[1] = Ponto0y;
            result[2] = MEDIA01x;
            result[3] = MEDIA01y;
            result[4] = MEDIA012x;
            result[5] = MEDIA012y;
            result[6] = MEDIA0123x;
            result[7] = MEDIA0123y;

            result[8] = MEDIA0123x;
            result[9] = MEDIA0123y;
            result[10] = MEDIA123x;
            result[11] = MEDIA123y;
            result[12] = MEDIA23x;
            result[13] = MEDIA23y;
            result[14] = Ponto3x;
            result[15] = Ponto3y;
        }

        private void Casteljau(double P0x, double P0y, double P1x, double P1y,
                               double P2x, double P2y, double P3x, double P3y, double t) {
            if (t > 0.005) {
                double[] result = new double[16];
                PMCurva(P0x, P0y, P1x, P1y, P2x, P2y, P3x, P3y, result);

                double e = t / 2;
                Casteljau(result[0], result[1], result[2], result[3],
                        result[4], result[5], result[6], result[7], e);
                Casteljau(result[8], result[9], result[10], result[11],
                        result[12], result[13], result[14], result[15], e);

            } else {
                // Draw the final point when the stopping criterion is reached
                pontosCalculados.add(new Point(P3x, P3y));
                repaint();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);

            for (int i = 1; i < pontosCalculados.size(); i++) {
                Point p1 = pontosCalculados.get(i - 1);
                Point p2 = pontosCalculados.get(i);
                g2d.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
            }
        }
    }

    private static class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
