package circunference;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BresenhamCircle extends JPanel {

   public void drawCircle(int xc, int yc, int r, Graphics g) {
       int x = 0, y = r;
       int p = 1 - r;
       while (x <= y) {
           drawPixelAndReflect(xc, yc, x, y, g);
           if (p >= 0) {
               y--;	
               p += 2 * x - 2 * y + 5;
               x++;
           }else {
        	   p += 2 * x + 3;
        	   x++;
           }
       }
   }

   private void drawPixelAndReflect(int xc, int yc, int x, int y, Graphics g) {
       g.drawLine(xc + x, yc + y, xc + x, yc + y);
       g.drawLine(xc - x, yc + y, xc - x, yc + y);
       g.drawLine(xc + x, yc - y, xc + x, yc - y);
       g.drawLine(xc - x, yc - y, xc - x, yc - y);
       g.drawLine(xc + y, yc + x, xc + y, yc + x);
       g.drawLine(xc - y, yc + x, xc - y, yc + x);
       g.drawLine(xc + y, yc - x, xc + y, yc - x);
       g.drawLine(xc - y, yc - x, xc - y, yc - x);
   }

   @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       drawCircle(150, 150, 100, g);
   }

   public static void main(String[] args) {
       JFrame frame = new JFrame("Bresenham Circle");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(new BresenhamCircle());
       frame.setSize(300, 300);
       frame.setVisible(true);
   }
}
