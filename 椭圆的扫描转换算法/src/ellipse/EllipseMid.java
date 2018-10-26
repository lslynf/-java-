package ellipse;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
//椭圆的扫描转换算法:
//依据法向量来判断，当法向量的x坐标小于y坐标时:即上半部分，此时y的分量大于x的分量，y每递增一，x的递增量大于1
public class EllipseMid extends JFrame
{
	public static void main(String[]args)
	{
		JFrame test=new JFrame();
		test.add(new EllipsePanel());
		test.setSize(500,320);
		test.setTitle("椭圆的扫描转换算法");
		test.setVisible(true);
	}
}
class EllipsePanel extends JComponent
{
	public void EllipsePoint(double x,double y,Graphics g)
	{
		Graphics2D operator=(Graphics2D)g;
		Line2D point1=new Line2D.Double(x,y,x,y);
		Line2D point2=new Line2D.Double(x,300-y,x,300-y);
		Line2D point3=new Line2D.Double(500-x,y,500-x,y);
		Line2D point4=new Line2D.Double(500-x,300-y,500-x,300-y);
		operator.draw(point1);
		operator.draw(point2);
		operator.draw(point3);
		operator.draw(point4);
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D operator=(Graphics2D)g;
		Line2D xline=new Line2D.Double(0,150,500,150);
		operator.draw(xline);
		Line2D yline=new Line2D.Double(250,0,250,300);
		operator.draw(yline);
		double a,b,x0,y0,x,y,d1,d2;
		x0=250;y0=150;
		a=80;b=60;
		x=250;y=150-b;
		d1=b*b+a*a*(0.25-b);
		EllipsePoint(x,y,g);
		while(b*b*(x-250+1)<a*a*(150+0.5-y))
		{
			if(d1<0)
			 {
			  d1+=b*b*(2*(x-x0)+3);
			  x++;
	     	 }
			else
			{
				d1+=b*b*(2*(x-x0)+3)+a*a*(2-2*(150-y));
				x++;
				y++;
			}
			EllipsePoint(x,y,g);
		}
		d2=Math.sqrt(b*(x-x0+0.5))+Math.sqrt(a*(y0-y+1))-Math.sqrt(a*b);
		while(y<150)
		{
			if(d2<0)
			{
				d2+=b*b*(2*(x-x0)+2)+a*a*(3-2*(y0-y));
				x++;
				y++;
			}
			else
			{
				d2+=a*a*(3-2*(y0-y));
				y++;
			}
			EllipsePoint(x,y,g);
		}
	}
}