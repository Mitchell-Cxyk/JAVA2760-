/*
  StartPanel.java:ʵ��̰������Ϸ��ʼǰ���ý���
*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StartFrame extends JFrame{
	//������ʼ���ô���
	public static int Gamespeed=150; //Ĭ����Ϸ�Ѷȼ�
	public static int GameMode=2;  //Ĭ����ǽ�ڵ�ͼ
	public JFrame frame =new JFrame("Snake");
	public StartFrame() {
		frame.setBounds(10,10,900,720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new StartPanel());
		frame.setVisible(true);
	};
    
	//�ڴ�������Ӱ�ť�����������
	public class StartPanel extends JPanel implements ActionListener{
		
		//��ʼ��
		private ButtonGroup map;
		private ButtonGroup level;
		private int addressx1=380;
		private int addressy1=200;
		private int addressx2=380;
		private int addressy2=400;
		
		ImageIcon title=new ImageIcon("title.jpg");
		JButton StartButton=new JButton("Start!");
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			//���������汳��
			this.setBackground(Color.WHITE);
			title.paintIcon(this,g,25,11); //����titleͼƬ
			g.fillRect(25, 75, 850, 600);
			g.setColor(Color.WHITE);
			
			//������ͼ���Ѷȱ���
			g.drawString("Map:", 300, 200);
			g.drawString("Level:", 300, 400);
		}
		public StartPanel() {
			this.setLayout(null);
			//��ӿ�ʼ��ť
		    super.add(StartButton);
		    StartButton.setBounds(410, 560, 80, 40);
		    StartButton.addActionListener(this);
		    map=new ButtonGroup();
			level=new ButtonGroup();
			
		    //��ӵ�ͼѡ��ѡť
			addRadioButtonOfMap("No wall",2);
			addRadioButtonOfMap("Classic",1);
		    
		    //����Ѷ�ѡ��ѡť
			addRadioButtonOfLevel("Easy",120);
			addRadioButtonOfLevel("Normal",80);
			addRadioButtonOfLevel("Hard",40);
		}
		
		//������ͼ����ʵ��
	    private void addRadioButtonOfMap(String KindOfMap,int mode) {
	    	boolean selected=mode==2;
			JRadioButton button = new JRadioButton(KindOfMap,selected);
			map.add(button);
			super.add(button);
			button.setBounds(addressx1,addressy1,80,40);
			ActionListener listener=event->GameMode=mode;
			button.addActionListener(listener);
			addressx1=addressx1+100;
			
		}
	    
		//����Ѷ�ѡ��ѡť�ķ���ʵ��
		private void addRadioButtonOfLevel(String name, int speed) {
			boolean selected=speed==120;
			JRadioButton button = new JRadioButton(name,selected);
			level.add(button);
			super.add(button);
			button.setBounds(addressx2, addressy2, 80, 40);
			ActionListener listener=event->Gamespeed=speed;
			button.addActionListener(listener);
			addressx2=addressx2+100;
		}
		
		@Override
		//ʵ�ֵ��Start!��ť������Ϸ���н���
		public void actionPerformed(ActionEvent e) {
	        frame.dispose();
			JFrame frame1 =new JFrame("Snake");
			frame1.setBounds(10,10,900,720);
			frame1.setResizable(false);
			frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame1.add(new SnakePanel());
			frame1.setVisible(true);
		}	
	}
}
