package polyfill;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class PolyFill extends JFrame
{
	public static void main(String[]args)
	{
		JFrame test=new JFrame();
		test.add(new ShowPanel());
		test.setSize(500,450);
		test.setTitle("��������");
		test.setVisible(true);
	}
}
//�ߵĽṹ
class edge implements Comparable
{
	double x;
	double dx;
	double ymax;
	double ymin;
	edge(double a,double b,double c,double z)
	{
		x=a;
		dx=b;
		ymax=c;
		ymin=z;
	}
	public int compareTo(Object o)
	{
		edge s=(edge)o;
		if(this.x>s.x)
			return 1;
		else
			return -1;
	}
}
//����Ľṹ
class node
{
	double x;
	double y;
	node(double a,double b)
	{
		x=a;
		y=b;
	}
}
class poly
{
	//�������
	int n;
	node[]peak=new node[7];
	poly()
	{
		n=6;
		peak[0]=new node(100,100);
		peak[1]=new node(250,40);
		peak[2]=new node(400,70);
		peak[3]=new node(400,400);
		peak[4]=new node(250,340);
		peak[5]=new node(100,385);
	}
}
class ShowPanel extends JComponent
{
	poly test=new poly();
	//���Ա߱�Ľṹ
	List<edge> AET=new ArrayList<edge>();
	//�±߱�Ľṹ
	Map<Integer,List<edge>> NET=new HashMap<Integer,List<edge>>();
	//��ʼ������εı�
	List<edge> PolyEdge=new ArrayList<edge>();
	int Ymax,Ymin;
	public ShowPanel()
	{
		getrange();
		initpoly();
		initNET();
		show();
	}
	//��ȡɨ���ߵķ�Χ
	public void getrange()
	{
		double ymin=500;
		double ymax=0;
		for(int i=0;i<test.n;i++)
		{
			if(test.peak[i].y>ymax)
				ymax=test.peak[i].y;
			if(test.peak[i].y<ymin)
				ymin=test.peak[i].y;
		}
		Ymax=(int)(ymax);
		Ymin=(int)(ymin);
	}
	//���ݶ���εĶ����ʼ������εı�
	public void initpoly()
	{
		for(int i=0;i<test.n-1;i++)
		{
		    int j=i+1;
			double x1,x2,y1,y2,ymax,ymin;
			double x,dx;
			x1=test.peak[i].x;
			y1=test.peak[i].y;
			x2=test.peak[j].x;
			y2=test.peak[j].y;
			if(x1==x2)
				dx=0;
			else
				dx=(x2-x1)/(y2-y1);
			if(y1>y2)
			 {
				ymin=y2;
				ymax=y1;
				x=x2;
			 }
			else
			{
				ymin=y1;
				ymax=y2;
				x=x1;
			}
			edge temp=new edge(x,dx,ymax,ymin);
			PolyEdge.add(temp);
		}
		double x1,x2,y1,y2,ymax,ymin;
		double x,dx;
		x1=test.peak[0].x;
		y1=test.peak[0].y;
		x2=test.peak[test.n-1].x;
		y2=test.peak[test.n-1].y;
		if(x1==x2)
			dx=0;
		else
			dx=(x2-x1)/(y2-y1);
		if(y1>y2)
		 {
			ymin=y2;
			ymax=y1;
			x=x2;
		 }
		else
		{
			ymin=y1;
			ymax=y2;
			x=x1;
		}
		edge temp=new edge(x,dx,ymax,ymin);
		PolyEdge.add(temp);
	}
	public void show()
	{
		for(int i=0;i<PolyEdge.size();i++)
		{
			edge temp=PolyEdge.get(i);
			System.out.println(temp.x+"  "+temp.dx+"  "+temp.ymax+"  "+temp.ymin);
		}
		Iterator<Integer> iterator = NET.keySet().iterator();
		while (iterator.hasNext()) {
		    int key = iterator.next();
		    if(NET.get(key).size()!=0)
		    	System.out.println(key  + " ��" + NET.get(key));
		}
	}
	//���õõ��Ķ���εı߳�ʼ���±߱�
	public void initNET()
	{
		for(int i=Ymin;i<=Ymax;i++)
		{
			List<edge>temp=new ArrayList<edge>();
			for(int j=0;j<PolyEdge.size();j++)
			{
				if(PolyEdge.get(j).ymin==i)
				{
					temp.add(PolyEdge.get(j));
				}
					
			}
			NET.put(i,temp);
		}
	}
	//����������л�ͼ���Ի�߱���в���
	public void paintComponent(Graphics g)
	{
		Graphics2D operator=(Graphics2D)g;
		Line2D p0p1=new Line2D.Double(test.peak[0].x,test.peak[0].y,
				test.peak[1].x,test.peak[1].y);
		Line2D p1p2=new Line2D.Double(test.peak[1].x,test.peak[1].y,
				test.peak[2].x,test.peak[2].y);
		Line2D p2p3=new Line2D.Double(test.peak[2].x,test.peak[2].y,
				test.peak[3].x,test.peak[3].y);
		Line2D p3p4=new Line2D.Double(test.peak[3].x,test.peak[3].y,
				test.peak[4].x,test.peak[4].y);
		Line2D p4p5=new Line2D.Double(test.peak[4].x,test.peak[4].y,
				test.peak[5].x,test.peak[5].y);
		Line2D p5p0=new Line2D.Double(test.peak[5].x,test.peak[5].y,
				test.peak[0].x,test.peak[0].y);
		operator.draw(p0p1);operator.draw(p1p2);operator.draw(p2p3);
		operator.draw(p3p4);operator.draw(p4p5);operator.draw(p5p0);
	    for(int i=Ymin;i<=Ymax;i++)
	    {
	    	//System.out.println(i);
	    	//�����ǰ�±߱���������,���뵽list��
	    	if(NET.get(i).size()!=0)
	    	{
	    		List<edge> temp=NET.get(i);
	    		for(int j=0;j<temp.size();j++)
	    			{
	    			  AET.add(temp.get(j));
	    			  //System.out.println(temp.get(j));
	    			}
	    		Collections.sort(AET);
	    	}
	    	Vector v1=new Vector<>();
	    	for(int k=0;k<AET.size();k++)
	    	{
	    		v1.add(AET.get(k).x);
	    		//System.out.println("�����"+AET.get(k).x);
	    	}
	    	for(int p=0;p<v1.size()-1;p+=2)
	    	{
	    		//System.out.println("�ж��ٸ���"+v1.size());
	    		int q=p+1;
	    		double m;
	    		//System.out.println("��˵�"+v1.get(p));
	    		//System.out.println("�Ҷ˵�"+v1.get(q));
	    		for(m=(double)(v1.get(p));m<(double)(v1.get(q));m++)
	    		{
	    			Line2D point=new Line2D.Double(m,i,m,i);
	    			operator.setPaint(Color.RED);
	    			operator.draw(point);
	    		}
	    	}
	    	for(int n=0,len=AET.size();n<len;n++)
    		{
    			if(AET.get(n).ymax>i)
    				AET.get(n).x+=AET.get(n).dx;
    			//���ymax=i,��Ѹýڵ��AET��ɾ��
    			//�˴����ж�Ӧ����ymax==i+1
    			if(AET.get(n).ymax==i+1)
    			{
    				AET.remove(n);
    				n--;
    				len--;
    			}
    		}
	    	Collections.sort(AET);
	    }
	}
}