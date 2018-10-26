package bezier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
public class BerzierTest extends JFrame
{
	public static void main(String[]args)
	{
		JFrame test=new JFrame();
		test.add(new BerzierDraw());
		test.setTitle("贝塞尔曲线");
		test.setSize(600,600);
		test.setVisible(true);
	}
}
class point
{
	float x;
	float y;
	point()
	{
		x=0;y=0;
	}
	point(float a,float b)
	{
		x=a;y=b;
	}
}
class ControlPoint
{
	point[]peak=new point[6];
	ControlPoint()
	{
		peak[0]=new point(50,500);
		peak[1]=new point(200,120);
		peak[2]=new point(280,400);
		peak[3]=new point(360,150);
		peak[4]=new point(500,280);
		peak[5]=new point(400,350);
	}
}
class BerzierDraw extends JPanel
{
	//存放结果点的链表
	List<point>result=new ArrayList(); 
	int sum=6;//控制点的个数
	int number=1000;//画多少个点
	ControlPoint input=new ControlPoint();
	//当为6个点的时候，二项式系数的变化是1,5,10,10,5,1;
	int xishu[]=new int[] {1,5,10,10,5,1};
	BerzierDraw()
	{
		GetPoint();
	}
	public void GetPoint()
	{
		int i,j;
		for(i=0;i<number;i++)
		{
			float t=(float)(i)/(float)(number);
			point ans=new point();
			for(j=0;j<sum;j++)
			{
				ans.x+=xishu[j]*Math.pow((1-t),(sum-j-1))*Math.pow(t,j)*input.peak[j].x;
				ans.y+=xishu[j]*Math.pow((1-t),(sum-j-1))*Math.pow(t,j)*input.peak[j].y;
			}
			result.add(ans);
		}
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D operator=(Graphics2D)g;
		point temp=input.peak[0];
		for(int i=1;i<sum;i++)
		{
			Point2D a=new Point2D.Float(temp.x,temp.y);
			Point2D b=new Point2D.Float(input.peak[i].x,input.peak[i].y);
			Line2D line=new Line2D.Float(a,b);
			operator.draw(line);
			temp=input.peak[i];
		}
		for(int i=0;i<result.size();i++)
		{
			Line2D point1=new Line2D.Float(result.get(i).x, result.get(i).y, result.get(i).x, result.get(i).y);
			operator.setPaint(Color.red);
			operator.draw(point1);
		}
	}
}
