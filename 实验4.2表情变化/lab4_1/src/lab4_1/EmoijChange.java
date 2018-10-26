package lab4_1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.*;
public class EmoijChange {
	public static void main(String[]args)
	{
		ButtonFrame text=new ButtonFrame();
	}
}
//˼·������һ�����ڣ��ڴ����з���������壬һ�������ð�ť��һ�������ñ����ͼƬ
//���⣬�ڷ��ñ�����Ǹ�����У�ÿһ�������ַ�����һ������У���ʼʱ���ѿɼ�������Ϊfalse��
//�л�ʱ������Ϊtrue��ͬʱ�Ƴ��������
class ButtonFrame extends JFrame 
{
	JFrame frame;
	JPanel panel,emoijpanel;
	JButton b1,b2,b3,b4;
	ShowSmile j1;
	ShowTear j2;
	ShowAngry j3;
	public ButtonFrame()
	{	
		JFrame frame=new JFrame();
		JPanel panel=new JPanel();
		JPanel emoijpanel=new JPanel();
		frame.setSize(300, 400);
		frame.setLayout(new BorderLayout());
		j1=new ShowSmile();
		j2=new ShowTear();
		j3=new ShowAngry();
		j1.setVisible(false);
		j2.setVisible(false);
		j3.setVisible(false);
		emoijpanel.add(j1);emoijpanel.add(j2);emoijpanel.add(j3);
		b1=new JButton("΢Ц");
		b2=new JButton("����");
		b3=new JButton("����");
		b4=new JButton("�˳�");
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		frame.add(panel,BorderLayout.SOUTH);
		frame.add(emoijpanel);
		frame.setVisible(true);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j1.setVisible(true);
				emoijpanel.removeAll();
				emoijpanel.add(j1);
				emoijpanel.updateUI();
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j2.setVisible(true);
				emoijpanel.removeAll();
				emoijpanel.add(j2);
				emoijpanel.updateUI();
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j3.setVisible(true);
				emoijpanel.removeAll();
				emoijpanel.add(j3);
				emoijpanel.updateUI();
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
class ShowSmile extends JPanel
{
	private static final int DEFAULT_WIDTH=200;
	private static final int DEFAULT_HEIGHT=200;
	private Image image;
	public ShowSmile()
	{
		image=new ImageIcon("E:\\eclipse-workspace\\lab4_1\\src\\lab4_1\\smile.png").getImage();
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D operator=(Graphics2D)g;
		if(image==null)
			return;
		operator.drawImage(image,10,10,150,150,null);
	}
	public Dimension getPreferredSize()
	{
		return new Dimension(DEFAULT_WIDTH,DEFAULT_WIDTH);
	}
}
class ShowTear extends JPanel
{
	private static final int DEFAULT_WIDTH=200;
	private static final int DEFAULT_HEIGHT=200;
	private Image image;
	public ShowTear()
	{
		image=new ImageIcon("E:\\eclipse-workspace\\lab4_1\\src\\lab4_1\\tear.png").getImage();
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D operator=(Graphics2D)g;
		if(image==null)
			return;
		operator.drawImage(image,10,10,150,150,null);
	}
	public Dimension getPreferredSize()
	{
		return new Dimension(DEFAULT_WIDTH,DEFAULT_WIDTH);
	}
}
class ShowAngry extends JPanel
{
	private static final int DEFAULT_WIDTH=200;
	private static final int DEFAULT_HEIGHT=200;
	private Image image;
	public ShowAngry()
	{
		image=new ImageIcon("E:\\eclipse-workspace\\lab4_1\\src\\lab4_1\\angry.png").getImage();
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D operator=(Graphics2D)g;
		if(image==null)
			return;
		operator.drawImage(image,10,10,150,150,null);
	}
	public Dimension getPreferredSize()
	{
		return new Dimension(DEFAULT_WIDTH,DEFAULT_WIDTH);
	}
}