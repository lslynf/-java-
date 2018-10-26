package polycut;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
public class PolyCut extends JFrame 
{
	public static void main(String[]args)
	{
		JFrame test=new JFrame();
		test.add(new ShowPanel());
		test.setTitle("����βü�");
		test.setSize(600,600);
		test.setVisible(true);
	}
}
//��Ľṹ
class point
{
	int x;
	int y;
	point(){}
	point(int a,int b)
	{
		x=a;
		y=b;
	}
}
//�ߵĽṹ�������յ�
class edge
{
	point[]se=new point[2];
	edge(int x1,int y1,int x2,int y2)
	{
		se[0]=new point(x1,y1);
		se[1]=new point(x2,y2);
	}
}
//�ü��Ĵ��ڵĽṹ
class ClipBoundary
{
	edge[]Clip=new edge[4];
	ClipBoundary() 
	{
		Clip[0]=new edge(100,500,500,500);
		Clip[1]=new edge(500,500,500,100);
		Clip[2]=new edge(500,100,100,100);
		Clip[3]=new edge(100,100,100,500);
	}
}
//��Ҫ�ü��Ķ����
class poly
{
	int n;
	point[]peak=new point[6];
	poly()
	{
		n=5;
		peak[0]=new point(300,50);
		peak[1]=new point(550,400);
		peak[2]=new point(280,520);
		peak[3]=new point(30,420);
		peak[4]=new point(70,300);
	}
}
class ShowPanel extends JComponent
{
    ClipBoundary B=new ClipBoundary();	
    poly input=new poly();
    List input1=new ArrayList<point>();
    List output1=new ArrayList<point>();
    List output2=new ArrayList<point>();
    List output3=new ArrayList<point>();
    List output4=new ArrayList<point>();
    ShowPanel()
    {
    	init();
    	//�Ĵβü�
    	Hodgman(B.Clip[0],input1,output1);
    	Hodgman(B.Clip[1],output1,output2);
    	Hodgman(B.Clip[2],output2,output3);
    	Hodgman(B.Clip[3],output3,output4);
    	//System.out.println(output4.size());
    }
    public void init()
    {
    	for(int i=0;i<input.n;i++)
    		input1.add(input.peak[i]);
    }
    //�ü��㷨
    public void Hodgman(edge cutline,List in,List out)
    {
       point S,P;
       int j;
       S=(point)in.get(in.size()-1);
       for(j=0;j<in.size();j++)
       {
    	   P=(point)in.get(j);
    	   if(Inside(cutline,P)==1)
    	   {
    		   if(Inside(cutline,S)==1)//���S��P���ڿɼ�һ�࣬����P��
    		   {
    			   out.add(P);
    		   }
    		   else//���S�㲻�ɼ�������P��ͽ���
    		   {
    			  point ip=new point();//������ĵط���ÿ���½�һ���ڵ�
    			  Intersect(S,P,cutline,ip);
    			  out.add(ip);
    			  out.add(P);
    		   }
    	   }
    	   else if(Inside(cutline,S)==1)//���S�ڿɼ�һ�࣬P�ڴ����⣬��������
    	   {
    		   point ip=new point();
    		   Intersect(S,P,cutline,ip);
    		   out.add(ip);
    	   }
    	   S=P;
       }    
    }
    //�жϵ��ڴ�����
    public int Inside(edge cutline,point TestPt)
    {
    	if(cutline.se[1].x>cutline.se[0].x)//���Ϊ�����±�
    	{
    		if(TestPt.y<=cutline.se[0].y)
    			return 1;
    	}
    	else if(cutline.se[1].x<cutline.se[0].x)//���Ϊ�����ϱ�
    	{
    		if(TestPt.y>=cutline.se[0].y)
    			return 1;
    	}
    	else if(cutline.se[1].y<cutline.se[0].y)//���Ϊ�����ұ�
    	{
    		if(TestPt.x<=cutline.se[0].x)
    			return 1;
    	}
    	else if(cutline.se[1].y>cutline.se[0].y)//���Ϊ�������
    	{
    		if(TestPt.x>=cutline.se[0].y)
    			return 1;
    	}
    	return 0;
    }
    //�󽻵�
    public void Intersect(point s,point p,edge cutline,point Inp)
    {
    	//�����ˮƽ�ü���
       	if(cutline.se[0].y==cutline.se[1].y)
       	{
       		Inp.y=cutline.se[0].y;
       		Inp.x=s.x+(cutline.se[0].y-s.y)*(p.x-s.x)/(p.y-s.y);
       	}
       	//����Ǵ�ֱ�ü���
       	else
       	{
       		Inp.x=cutline.se[0].x;
       		Inp.y=s.y+(cutline.se[0].x-s.x)*(p.y-s.y)/(p.x-s.x);
       	}
    }
	public void paintComponent(Graphics g)
	{
		g.drawLine(B.Clip[0].se[0].x,B.Clip[0].se[0].y,B.Clip[0].se[1].x,B.Clip[0].se[1].y);
		g.drawLine(B.Clip[1].se[0].x,B.Clip[1].se[0].y,B.Clip[1].se[1].x,B.Clip[1].se[1].y);
		g.drawLine(B.Clip[2].se[0].x,B.Clip[2].se[0].y,B.Clip[2].se[1].x,B.Clip[2].se[1].y);
		g.drawLine(B.Clip[3].se[0].x,B.Clip[3].se[0].y,B.Clip[3].se[1].x,B.Clip[3].se[1].y);
		point end=input.peak[input.n-1];
		for(int j=0;j<input.n;j++)
		{
			point start=input.peak[j];
			g.drawLine(end.x, end.y, start.x,start.y);
			end=start;
		}
		//point e=(point) output4.get(output4.size()-1);
		int[]xpoint=new int[output4.size()];
		int[]ypoint=new int[output4.size()];
		for(int m=0;m<output4.size();m++)
		{
			point s=(point)output4.get(m);
			g.setColor(Color.RED);
			xpoint[m]=s.x;
			ypoint[m]=s.y;
		}
		g.fillPolygon(xpoint, ypoint,output4.size());
	}
}