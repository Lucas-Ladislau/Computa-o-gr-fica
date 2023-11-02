package line;
import java.awt.*;
import javax.swing.JFrame;
import java.util.Scanner;
import java.lang.*;


public class DDA extends Canvas
{
	public static final int SCALE = 10;	
	public static final int b_width =150*SCALE;	
	public static final int b_height =100*SCALE;
	static int  x0, y0,x1,y1;
	

	public DDA(int x0,int y0,int x1,int y1){
		this.x0=x0;
		this.y0=y0;
		this.x1=x1;
		this.y1=y1;
	}


	public void paint(Graphics g)
	{

		for(int i = 0; i < b_height; i += SCALE) {
			if (i == b_height/2) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setStroke(new BasicStroke(2));	
				g.drawLine(0, i, (int) b_width, i);
				g2d.setStroke(new BasicStroke(1));
			}
			else {
				g.drawLine(0, i, (int) b_width, i);
				
			}
		}

		for(int i = 0; i < b_width; i += SCALE) {
			if (i == b_width/2) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setStroke(new BasicStroke(2));
				g.drawLine(i, 0, i, (int) b_height);
				g2d.setStroke(new BasicStroke(1));
			}
			else {
				g.drawLine(i, 0, i, (int) b_height);				
			}
		}
		
		/* DDA Algorithm*/
		//é um algoritmo incremental
		int dx=x1-x0,dy=y1-y0;	
		float xIncrement, yIncrement, steps, x=x0,	y=y0;
		System.out.println(dx+"|||"+dy);

		if (dx>dy) {	// <=45
			steps=Math.abs(dx)*SCALE;
		}
		else{	// >45
			steps=Math.abs(dy)*SCALE;
		}
		xIncrement=dx/steps;
		yIncrement=dy/steps;

		g.fillRect(x0,y0,SCALE,SCALE);	//setPixel()
		while(steps!=0)	{
			System.out.println(steps);
			steps--;
			x += xIncrement;
			y += yIncrement;
			g.fillRect(Math.round(x/SCALE)*SCALE,Math.round(y/SCALE)*SCALE,SCALE,SCALE);	// setPixel()
		}
	}
	
	public static void main(String args[])
	{
//		Scanner sc=new Scanner(System.in);
//		System.out.println("Enter first end x0 and y0");
//		int x0=sc.nextInt();
//		int y0=sc.nextInt();
//		System.out.println("Enter last end x1 and y1");
//		int x1=sc.nextInt();
//		int y1=sc.nextInt();
//		
//		int dx=x1-x0,dy=y1-y0;	// deltaX, deltaY
//		float xIncrement, yIncrement, steps, x=x0,	y=y0;
//		System.out.println(dx+"|||"+dy);
//
//		if (dx>dy) {	// 0<= m <=1
//			steps=Math.abs(dx);
//			System.out.println("entrou");
//		}
//		else{	// 1< m
//			System.out.println("entrou2");
//			steps=Math.abs(dy);
//		}
//		xIncrement=dx/steps;
//		yIncrement=dy/steps;
//
//
//		while(steps!=0)	{
//			steps--;
//			x += xIncrement;
//			y += yIncrement;
//			System.out.println("(x,y)= ("+Math.round(x)+","+Math.round(y)+ ")  float de y: "+x);
//		}
		
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter first end x0 and y0");
		int x0=sc.nextInt()*SCALE + b_width/2;
		int y0=-1*sc.nextInt()*SCALE + b_height/2;
		System.out.println("Enter last end x1 and y1");
		int x1=sc.nextInt()*SCALE + b_width/2;
		int y1=-1*sc.nextInt()*SCALE + b_height/2;
		DDA d=new DDA(x0,y0,x1,y1);
		JFrame f=new JFrame("DDA Line");
		f.add(d);
		f.setSize(b_width,b_height);
		f.setVisible(true);
	}
}