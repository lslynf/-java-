package drawCanva;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import javax.swing.*;
//»­°å¹¦ÄÜ
public class DrawCanva extends JFrame{
	public static void main(String[]args)
	{
		Base test=new Base();
	}
}
class Base extends JFrame
{
	JFrame J1;
	JPanel bottom,lefttool,rightdraw;
	Base()
	{
		J1=new JFrame();
		JMenuBar menubar=new JMenuBar();
		menubar.setBackground(Color.orange);
		J1.setJMenuBar(menubar);
		JMenu menu=new JMenu("File");
		menubar.add(menu);
		JMenuItem openitem=new JMenuItem("open");
		menu.add(openitem);
		JMenuItem newitem=new JMenuItem("new");
		menu.add(newitem);
		JMenuItem saveitem=new JMenuItem("save");
		menu.add(saveitem);
		JMenuItem exititem=new JMenuItem("exit");
		menu.add(exititem);
		bottom=new JPanel();
		lefttool=new JPanel();
		lefttool.setPreferredSize(new Dimension(100,400));
		lefttool.setLayout(null);
		lefttool.setBackground(Color.pink);
		rightdraw=new JPanel();
		rightdraw.setPreferredSize(new Dimension(450,450));
		rightdraw.setBackground(Color.YELLOW);
		bottom.setBackground(Color.black);
		int x=0;int y=10;
		type t=new type();
		ButtonGroup IsFill=new ButtonGroup();
		JRadioButton r1=new JRadioButton("Ìî³ä");
		JRadioButton r2=new JRadioButton("²»Ìî³ä");
		r1.setBounds(x, 260, 80, 40);
		r2.setBounds(x, 320, 80, 40);
		r1.addActionListener(t);r2.addActionListener(t);
		IsFill.add(r1);IsFill.add(r2);
		lefttool.add(r1);lefttool.add(r2);
		String[]tool=new String[] {"Ö±Ïß","ÍÖÔ²","Ô²","¾ØÐÎ","ÑÕÉ«"};
		JButton[]choice=new JButton[5];
		for(int i=0;i<tool.length;i++)
		{
			choice[i]=new JButton(tool[i]);
			choice[i].addActionListener(t);
			choice[i].setBounds(x, y+i*50, 80,40);
			lefttool.add(choice[i]);
		}
		bottom.add(lefttool,BorderLayout.WEST);
		bottom.add(rightdraw,BorderLayout.CENTER);
		rightdraw.addMouseListener(t);
		 
		J1.addMouseListener(t);
//		t.types(J1.getGraphics());
		
		J1.add(bottom);
		J1.setVisible(true);
		t.types(rightdraw.getGraphics());
	}
}
class type implements ActionListener,MouseListener
{
	
	int x1,y1,x2,y2;
	Graphics g;
	String b;
	static int fill=0;//¾²Ì¬±äÁ¿¿ØÖÆÊÇ·ñÌî³ä
	static Color selected;
	void types(Graphics g1)
	{
		g=g1;	
	}
	public void mousePressed(MouseEvent e) {
		x1=e.getX();
		y1=e.getY();
	}
	public void mouseReleased(MouseEvent e) {
		x2=e.getX();
		y2=e.getY();
		if(b=="Ö±Ïß")
		  g.drawLine(x1, y1, x2, y2);
		else if(b=="ÍÖÔ²")
		  {
			if(fill==1)
				{
				  g.setColor(selected);
				  g.fillOval(x1, y1,Math.abs(x2-x1), Math.abs(y2-y1));
				}
			else
				g.drawOval(x1, y1,Math.abs(x2-x1), Math.abs(y2-y1));
		  }
		else if(b=="Ô²")
		  {
			int r=(int)Math.sqrt(Math.abs(x2-x1)*Math.abs(x2-x1)+Math.abs(y2-y1)*Math.abs(y2-y1))/2;
			
			if(fill==1)
				{
					g.setColor(selected);
					g.fillOval(x1,y1,(int)r,(int)r);
				}
			else
				g.drawOval(x1,y1,(int)r,(int)r);
		  }
		else if(b=="¾ØÐÎ")
			if(fill==1)
				{
				   g.setColor(selected);
				   g.fillRect(x1, y1, Math.abs(x1-x2),Math.abs(y1-y2));
				}
			else
				g.drawRect(x1, y1, Math.abs(x1-x2),Math.abs(y1-y2));
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void actionPerformed(ActionEvent e) {
		b=e.getActionCommand();
		if(b=="Ìî³ä")
			fill=1;
		else if(b=="²»Ìî³ä")
			fill=0;
		if(b=="ÑÕÉ«")
		selected=JColorChooser.showDialog(null,"Ñ¡ÔñÌî³äÑÕÉ«",Color.gray);
	}
}