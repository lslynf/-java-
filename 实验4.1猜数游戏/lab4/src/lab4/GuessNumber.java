package lab4;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
public class GuessNumber {
	public static void main(String[]args)
	{
		GuessFrame f1=new GuessFrame();
	}
}
//˼·���������������ñ�ǩ������򣬰�ť
class GuessFrame extends JFrame implements ActionListener
{
	JPanel p1,p2,p3;
	JLabel label1,label2,label3;
	JTextField text1;
	JButton b1,b2,b3;
	int cnt=0;//��¼�û��¶ԵĴ���
	int number=(int)Math.random()*20;
	String ans="���Ѿ��¶���"+cnt+"��";
	String warn="";
	public GuessFrame()
	{
		setTitle("������Ϸ");
		setSize(400,260);
		p3=new JPanel();
		label1=new JLabel(ans);
		add(label1,BorderLayout.NORTH);
		String tips="����²����";
		label2=new JLabel(tips);
		text1=new JTextField(15);
		text1.setEditable(true);
		label3=new JLabel(warn);
		p1=new JPanel();
		p1.add(label2);p1.add(text1);p1.add(label3);
		p3.add(p1,BorderLayout.CENTER);
		b1=new JButton("ȷ��"); 
		b2=new JButton("���¿�ʼ");
		b3=new JButton("�˳�");
		b1.addActionListener(this); b2.addActionListener(this);b3.addActionListener(this);
		p2=new JPanel();
		p2.add(b1);p2.add(b2);p2.add(b3);
		p3.add(p2,BorderLayout.SOUTH);
		add(p3);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			int input=Integer.parseInt(text1.getText());
			if(input==number)
			{
				text1.setEditable(false);
				cnt++;
				ans="���Ѿ��¶���"+cnt+"��";
				label1.setText(ans);
				warn="�¶���";
				label3.setText(warn);
			}
			else if(input<number)
			{
				p3.setBackground(Color.blue);
				warn="̫С";
				label3.setText(warn);
			}
			else
			{
				p3.setBackground(Color.red);
				warn="̫��";
				label3.setText(warn);
			}
		}
		if(e.getSource()==b2)
		{
			warn="";
			label3.setText(warn);
			text1.setText(null);
			text1.setEditable(true);
			p3.setBackground(null);
		}
		if(e.getSource()==b3)
		{
			System.exit(0);
		}
	}
	
}
