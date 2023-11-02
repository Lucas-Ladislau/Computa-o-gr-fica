package line;
import java.awt.*;
import javax.swing.JFrame;
import java.util.Scanner;
import java.lang.*;

public class BresenhamCG extends Canvas {
	public static final int SCALE = 10;
	public static final int b_width = 150 * SCALE;
	public static final int b_height = 100 * SCALE;
	static int x0, y0, x1, y1;

	public BresenhamCG(int x0, int y0, int x1, int y1) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}

	public void paint(Graphics g) {

		for (int i = 0; i < b_height; i += SCALE) {
			if (i == b_height / 2) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setStroke(new BasicStroke(2));
				g.drawLine(0, i, (int) b_width, i);
				g2d.setStroke(new BasicStroke(1));
			} else {
				g.drawLine(0, i, (int) b_width, i);

			}
		}

		for (int i = 0; i < b_width; i += SCALE) {
			if (i == b_width / 2) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setStroke(new BasicStroke(2));
				g.drawLine(i, 0, i, (int) b_height);
				g2d.setStroke(new BasicStroke(1));
			} else {
				g.drawLine(i, 0, i, (int) b_height);
			}
		}

		/* Bresenham Algorithm*/
		//Calculo somente com inteiros
		int dx = Math.abs(x1 - x0), dy = Math.abs(y1 - y0);
		int p = 2 * dy - dx;
		int x, y;

		/* Determinar qual o ponto vai iniciar.*/
		if (x0 > x1) {
			x = x1;
			y = y1;
			x1 = x0;
		}else {
			x = x0;
			y = y0;
		}
		g.fillRect(Math.round(x / SCALE) * SCALE, Math.round(y / SCALE) * SCALE, SCALE, SCALE);
		//System.out.println("(x,y)= (" + x + "," + Math.round(y) + ")  float de p: " + p);
		while (x < x1) {
			x+=10;
			if (p < 0)
				p += 2 * dy;
			else {
				y+=10;
				p += 2 * (dy - dx);
			}
			g.fillRect(Math.round(x / SCALE) * SCALE, Math.round(y / SCALE) * SCALE, SCALE, SCALE);
			//System.out.println("(x,y)= (" + x + "," + Math.round(y) + ")  float de p: " + p);
		}
		

	}

	public static void main(String args[]) {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter first end x0 and y0");
//		int x0 = sc.nextInt();
//		int y0 = sc.nextInt();
//		System.out.println("Enter last end x1 and y1");
//		int x1 = sc.nextInt();
//		int y1 = sc.nextInt();
//
//		int dx = Math.abs(x1 - x0), dy = Math.abs(y1 - y0);
//		int p = 2 * dy - dx;
//		int x, y;
//
//		/* Determine which endpoint to use as start position.*/
//		if (x0 > x1) {
//			x = x1;
//			y = y1;
//			x1 = x0;
//		}else {
//			x = x0;
//			y = y0;
//		}
//		System.out.println("(x,y)= (" + x + "," + Math.round(y) + ")  float de p: " + p);
//		while (x < x1) {
//			x++;
//			if (p < 0)
//				p += 2 * dy;
//			else {
//				y++;
//				p += 2 * (dy - dx);
//			}
//			System.out.println("(x,y)= (" + x + "," + Math.round(y) + ")  float de p: " + p);
//		}

		Scanner sc=new Scanner(System.in);
		System.out.println("Enter first end x0 and y0");
		int x0=sc.nextInt()*SCALE + b_width/2;
		int y0=-1*sc.nextInt()*SCALE + b_height/2;
		System.out.println("Enter last end x1 and y1");
		int x1=sc.nextInt()*SCALE + b_width/2;
		int y1=-1*sc.nextInt()*SCALE + b_height/2;
		
		BresenhamCG d=new BresenhamCG(x0,y0,x1,y1);
		JFrame f=new JFrame("Bresenham Line");
		f.add(d);
		f.setSize(b_width,b_height);
		f.setVisible(true);
	}
}