/*
    SnakePanel.java:ʵ��̰������Ϸ�Ľ������
*/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class SnakePanel extends JPanel implements KeyListener, ActionListener{
	//���������壬��ͷ�Լ�ʳ���ͼ��
	ImageIcon title = new ImageIcon("title.jpg");
	ImageIcon body = new ImageIcon("body.png");
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon food = new ImageIcon("food.png");
	
	//��ʼ���ߵ����ݣ��߳��ȣ��÷�
	int len=3;
	int score=0;
	int[] snake_x=new int[750];
	int[] snake_y=new int[750];
	String fx ="R"; //����:R,L,U,D,�������˶�����
	boolean isStarted=false; //������Ϸ��ʼ
	boolean isFailed=false; //������Ϸ����
	Timer timer=new Timer(100,this); //��������
	
	//ʳ������
	int food_x;
	int food_y;
	
	Random rand =new Random();
	public SnakePanel() {
		initSnake();
		this.setFocusable(true);
		this.addKeyListener(this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		title.paintIcon(this,g,25,11);
		g.fillRect(25, 75, 850, 600);
		g.setColor(Color.WHITE);
		g.drawString("Len:"+len, 750, 35);
		g.drawString("Score:"+score, 750, 50);
		
		//������ͷ�ķ����벻ͬ�������ͷͼƬ
		if(fx=="R") {
			right.paintIcon(this, g, snake_x[0], snake_y[0]);
		}else if(fx=="L") {
			left.paintIcon(this, g, snake_x[0], snake_y[0]);
		}else if(fx=="D") {
			down.paintIcon(this, g, snake_x[0], snake_y[0]);
		}else if(fx=="U") {
			up.paintIcon(this, g, snake_x[0], snake_y[0]);
		}
		
		//����������ͼƬ
		for (int i=1;i<len;i++) {
			body.paintIcon(this, g, snake_x[i], snake_y[i]);
		}
		
		//����ʳ��ͼƬ
		food.paintIcon(this, g, food_x, food_y);
		
		//�����Ϸû��ʼ��������ʾ���ո����ʼ��Ϸ
		if (isStarted==false) {
		    g.setColor(Color.WHITE);
		    g.setFont(new Font("arial",Font.BOLD,40));
		    g.drawString("Press Space to Start",300,300);
		}
		
		//�����Ϸ�����ˣ�������ʾ��Ϸ���������ո�����¿�ʼ
		if(isFailed) {
			g.setColor(Color.RED);
			g.setFont(new Font("arial",Font.BOLD,40));
			g.drawString("Game Over:Press Space to Restart", 200, 300);
		}
	}
	
	//��ʼ������Ϸ��ʼʱ�ߣ�ʳ���λ�ã�����÷�
	public void initSnake() {
		len=3;
		fx="R";
		snake_x[0]=100;
		snake_y[0]=100;
		snake_x[1]=75;
		snake_y[1]=100;
		snake_x[2]=50;
		snake_y[2]=100;
		food_x=25+25*rand.nextInt(34);
		food_y=75+25*rand.nextInt(24);
		score=0;
	}
	
	@Override
	//ʵ����Ϸ�а�������
	public void keyPressed(KeyEvent e) {
		int keyCode =e.getKeyCode();
		/*ʵ������Ϸδ��ʼʱ���ո�ʼ��Ϸ
		  ��Ϸ��ʼʱ�����ո���ͣ��Ϸ
		  ��Ϸ����ʱ�����ո�����¿�ʼ
		*/
		if(keyCode==KeyEvent.VK_SPACE) {
			if(isFailed) {
				isFailed=false;
				initSnake();
			}
			isStarted=!isStarted;
			repaint();
			
		//ͨ���������Ұ����������ƶ��ķ���
		}else if (keyCode==KeyEvent.VK_LEFT && fx!="R") {
			fx="L";
		}else if (keyCode==KeyEvent.VK_RIGHT && fx!="L") {
			fx="R";
		}else if (keyCode==KeyEvent.VK_UP && fx!="D") {
			fx="U";
		}else if (keyCode==KeyEvent.VK_DOWN && fx!="U") {
			fx="D";
		}
		
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	@Override
	//
	public void actionPerformed(ActionEvent arg0) {
		if (isStarted && !isFailed) {
			//�������Ӽ�ʱ�ٶ��ƶ�����
		    for (int i=len-1;i>0;i--) {
		    	snake_x[i]=snake_x[i-1];
		    	snake_y[i]=snake_y[i-1];
	    	}
		    if (fx=="R") {
		    	snake_x[0]=snake_x[0]+25;
		        if(snake_x[0]>850) snake_x[0]=25;
		    }else if(fx=="L") {
		    	snake_x[0]=snake_x[0]-25;
		        if(snake_x[0]<25) snake_x[0]=850;
		    }else if(fx=="U") {
		    	snake_y[0]=snake_y[0]-25;
		        if(snake_y[0]<75) snake_y[0]=650;
		    }else if(fx=="D") {
		    	snake_y[0]=snake_y[0]+25;
		        if(snake_y[0]>670) snake_y[0]=75;
		    }
		    
		    //�߳Ե�ʳ������������µ�ʳ��ߵ���������
		    if (snake_x[0]==food_x && snake_y[0]==food_y) {
		    	len++;
		    	snake_x[len-1]=snake_x[len-2];
		    	snake_y[len-1]=snake_y[len-2];
		    	score=score+10;
		    	food_x=25+25*rand.nextInt(34);
				food_y=75+25*rand.nextInt(24);
		    }
		    
		    //��ͷײ��������Ϸ����
		    for (int i=1;i<len;i++) {
		    	if(snake_x[i]==snake_x[0] && snake_y[i]==snake_y[0]) {
		    		isFailed=true;
		    	}
		    }
		    repaint();
		}
		timer.start();
	}
}
