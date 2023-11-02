package line;
import java.awt.*;
import javax.swing.JFrame;
import java.util.Scanner;
import java.lang.*;

public class Analytic extends Canvas {
	public static final int SCALE = 10;
	public static final int b_width = 150 * SCALE;
	public static final int b_height = 100 * SCALE;
	static int x0, y0, x1, y1;

	public Analytic(int x0, int y0, int x1, int y1) {
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

		/* Analytic Algorithm*/
		float m, b;
		Integer Y;
		
		if (x0 != x1) {
			//(y1 - y0)= m (x1 - x0)
			m = (float) (y1 - y0) / (x1 - x0);//coeficiente angular
			System.out.println("m= " + m);
			b = y1 - m * x1;//termo independente
			System.out.println("b= " + b);
			for (int i = x0; i <= x1; i += 10) {
				Y = Math.round(m * i + b);
				System.out.println("(x,y) =" + i + ",   " + Y + " float:" + (m * i + b));
				g.fillRect(Math.round(i / SCALE) * SCALE, Math.round(Y / SCALE) * SCALE, SCALE, SCALE);
			}
		} else {
			for (int i = y0; i >= y1; i-=10) {
				System.out.println("(x,y) =" + x1 + "," + i);
				g.setColor(Color.blue);
				g.fillRect(x1, i, SCALE, SCALE);
			}
		}
	}

	public static void main(String args[]) {
//		// sem escala
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter first end x0 and y0");
//		int x0 = sc.nextInt();
//		int y0 =  sc.nextInt() ;
//		System.out.println("Enter last end x1 and y1");
//		int x1 = sc.nextInt();
//		int y1 = sc.nextInt();
//
//		float m, b;
//		Integer Y;
//		if (x0 != x1) {
//			m = (float) (y1 - y0) / (x1 - x0);
//			System.out.println("m= " + m);
//			b = y1 - m * x1;
//			System.out.println("b= " + b);
//			for (int i = x0; i <= x1; i++) {
//				Y = Math.round(m * i + b);
//				System.out.println("(x,y) =" + i + "," + Y + " float:" + (m * i + b));
//			}
//		} else {
//			System.out.println("entrou");
//			for (int i = y0; i <= y1; i++) {
//				System.out.println("(x,y) =" + x1 + "," + i);
//
//			}
//		}

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter first end x0 and y0");
		int x0 = sc.nextInt() * SCALE + b_width / 2;
		int y0 = -1 * sc.nextInt() * SCALE + b_height / 2;
		System.out.println(x0);
		System.out.println(y0);
		System.out.println("Enter last end x1 and y1");
		int x1 = sc.nextInt() * SCALE + b_width / 2;
		int y1 = -1 * sc.nextInt() * SCALE + b_height / 2;
		System.out.println(x1);
		System.out.println(y1);

		Analytic d=new Analytic(x0,y0,x1,y1);
		JFrame f=new JFrame("Analytic Line");
		f.add(d);
		f.setSize(b_width,b_height);
		f.setVisible(true);

	}
}