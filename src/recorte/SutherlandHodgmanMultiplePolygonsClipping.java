package clipping;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class SutherlandHodgmanMultiplePolygonsClipping extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private int xmin, xmax, ymin, ymax;

    private List<Point2D.Double[]> clippedPolygons;

    public SutherlandHodgmanMultiplePolygonsClipping() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set window boundaries
        xmin = 100;
        xmax = 300;
        ymin = 150;
        ymax = 300;

        // Initialize clipped polygons list
        clippedPolygons = new ArrayList<>();

        // Define the right triangle
        Point2D.Double[] rightTriangleVertices = {
                new Point2D.Double(150, 70),
                new Point2D.Double(150, 270),
                new Point2D.Double(350, 270)
        };

        // Define the pentagon
        Point2D.Double[] pentagonVertices = {
        		new Point2D.Double(30, 170),
                new Point2D.Double(130, 70),
                new Point2D.Double(230, 170),
                new Point2D.Double(180, 270),
                new Point2D.Double(80, 270)
        };

        // Define the Sinal de mais
        Point2D.Double[] plusVertices = {
        		new Point2D.Double(130, 325),
                new Point2D.Double(130, 275),
                new Point2D.Double(180, 275),//ponto
                new Point2D.Double(180, 225),
                new Point2D.Double(230, 225),
                new Point2D.Double(230, 275),//ponto
                new Point2D.Double(280, 275),
                new Point2D.Double(280, 325),
                new Point2D.Double(230, 325),//ponto
                new Point2D.Double(230, 375),
                new Point2D.Double(180, 375),
                new Point2D.Double(180, 325)//ponto
        };
        
     // Define the pentagon
        Point2D.Double[] rectangleWithPoints = {
        		new Point2D.Double(150, 115),
                new Point2D.Double(250, 115),
                new Point2D.Double(250, 165),//ponto
                new Point2D.Double(225, 165),
                new Point2D.Double(225, 140),
                new Point2D.Double(175, 140),//ponto
                new Point2D.Double(175, 165),
                new Point2D.Double(150, 165)
        };

        // Clip the polygons
        
        clipPolygon(rightTriangleVertices);
//        clipPolygon(pentagonVertices);
//        clipPolygon(plusVertices);
//        clipPolygon(rectangleWithPoints);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the window
        g2d.setColor(Color.BLACK);
        g2d.drawRect(xmin, ymin, xmax - xmin, ymax - ymin);

        // Draw the original polygons
        g2d.setColor(Color.RED);
        //Triângulo
        drawPolygon(g2d, new Point2D.Double(150, 70),
                new Point2D.Double(150, 270),
                new Point2D.Double(350, 270));

        //Pentagono
//        drawPolygon(g2d, new Point2D.Double(30, 170),
//                new Point2D.Double(130, 70),
//                new Point2D.Double(230, 170),
//                new Point2D.Double(180, 270),
//                new Point2D.Double(80, 270));
        
        //Sinal de mais
//        drawPolygon(g2d,  new Point2D.Double(130, 325),
//                new Point2D.Double(130, 275),
//                new Point2D.Double(180, 275),//ponto
//                new Point2D.Double(180, 225),
//                new Point2D.Double(230, 225),
//                new Point2D.Double(230, 275),//ponto
//                new Point2D.Double(280, 275),
//                new Point2D.Double(280, 325),
//                new Point2D.Double(230, 325),//ponto
//                new Point2D.Double(230, 375),
//                new Point2D.Double(180, 375),
//                new Point2D.Double(180, 325));
//        
        //Bloco retangular
//        drawPolygon(g2d,  new Point2D.Double(150, 115),
//                new Point2D.Double(250, 115),
//                new Point2D.Double(250, 165),//ponto
//                new Point2D.Double(225, 165),
//                new Point2D.Double(225, 140),
//                new Point2D.Double(175, 140),//ponto
//                new Point2D.Double(175, 165),
//                new Point2D.Double(150, 165));

        // Draw the clipped polygons
        g2d.setColor(Color.BLUE);
        for (Point2D.Double[] clippedPolygon : clippedPolygons) {
            drawPolygon(g2d, clippedPolygon);
        }
    }

    private void clipPolygon(Point2D.Double[] vertices) {
        List<Point2D.Double> inputPolygon = new ArrayList<>();
        for (Point2D.Double vertex : vertices) {
            inputPolygon.add(vertex);
        }

        List<Point2D.Double> outputPolygon = inputPolygon;

        // Clip against each edge of the window
        outputPolygon = clipAgainstEdge(outputPolygon, new Line2D.Double(xmin, ymin, xmax, ymin));
        outputPolygon = clipAgainstEdge(outputPolygon, new Line2D.Double(xmax, ymin, xmax, ymax));
        outputPolygon = clipAgainstEdge(outputPolygon, new Line2D.Double(xmax, ymax, xmin, ymax));
        outputPolygon = clipAgainstEdge(outputPolygon, new Line2D.Double(xmin, ymax, xmin, ymin));

        clippedPolygons.add(outputPolygon.toArray(new Point2D.Double[0]));
    }


    private List<Point2D.Double> clipAgainstEdge(List<Point2D.Double> polygon, Line2D.Double edge) {
        List<Point2D.Double> output = new ArrayList<>();
        int size = polygon.size();

        Point2D.Double S = polygon.get(size - 1);

        for (int i = 0; i < size; i++) {
            Point2D.Double E = polygon.get(i);

            if (isInside(S, edge)) {
                if (isInside(E, edge)) {
                	// Case 1: AMBOS PONTOS ESTÃO DENTRO S e E
                    output.add(E);
                } else {
                	// Case 2: S esta dentro e E esta fora
                    Point2D.Double intersection = getIntersection(S, E, edge);
                    output.add(intersection);
                }
            } else if (isInside(E, edge)) {
            	 // Case 3: S esta fora e E está dentro
                Point2D.Double intersection = getIntersection(S, E, edge);
                output.add(intersection);
                output.add(E);
            }

            S = E;
        }

        return output;
    }

    private boolean isInside(Point2D.Double point, Line2D.Double edge) {
        return (edge.getX2() - edge.getX1()) * (point.getY() - edge.getY1()) -
                (edge.getY2() - edge.getY1()) * (point.getX() - edge.getX1()) >= 0;
    }

    private Point2D.Double getIntersection(Point2D.Double S, Point2D.Double E, Line2D.Double edge) {
        double x1 = S.getX();
        double y1 = S.getY();
        double x2 = E.getX();
        double y2 = E.getY();
        double x3 = edge.getX1();
        double y3 = edge.getY1();
        double x4 = edge.getX2();
        double y4 = edge.getY2();

        double x = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) /
                ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));

        double y = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) /
                ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));

        return new Point2D.Double(x, y);
    }

    private void drawPolygon(Graphics2D g2d, Point2D.Double... vertices) {
        int[] xPoints = new int[vertices.length];
        int[] yPoints = new int[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            xPoints[i] = (int) vertices[i].getX();
            yPoints[i] = (int) vertices[i].getY();
        }

        g2d.drawPolygon(xPoints, yPoints, vertices.length);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SutherlandHodgmanMultiplePolygonsClipping frame = new SutherlandHodgmanMultiplePolygonsClipping();
            frame.setVisible(true);
        });
    }
}
