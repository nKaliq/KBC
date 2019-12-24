//KBC 2017

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class KBC17
{
	public static void main(String arr[])
	{
		GameWindow g=new GameWindow();
		g.showFrame();
	}
}

class GameWindow extends JFrame implements ActionListener
{	
	JButton temp;

	static int count=1;
	String selected="X";
	
	Quiz x;
	JButton quest;					//contains the question
	JButton oa,ob,oc,od;			//option a,b,c,d respectively
	JButton h1,h2,h3,h4;			//help line
	JButton qnum,amt;				//question number and amount prize
	JButton lock;					//lock answer
	
	public void showFrame()
	{
		//int x=800,y=600;
		Container c=this.getContentPane();
		c.setLayout(null);
		//c.setBackground(Color.BLACK);
	
		//------Creating Components
		//JLabel logo=new JLabel("logo");
		//logo.setIcon(new ImageIcon("img\\kbc.jpg"));
		
		quest=new JButton("");
		oa=new JButton("");
		ob=new JButton("");
		oc=new JButton("");
		od=new JButton("");
		lock=new JButton("LOCK");
		lock.setEnabled(false);
		
		
		h1=new JButton("Phone");
		h2=new JButton("50-50");
		h3=new JButton("Poll");
		h4=new JButton("???");
		
		qnum=new JButton(String.valueOf(count));
		amt=new JButton("Rs"+"00000000");
		
		//------ adding quesiont to the buttons
		x=new Quiz();
		
		updateValues();

		//------ Resizing
		//logo.setSize(400,400);

		quest.setSize(700,80);
		oa.setSize(200+30,50);
		ob.setSize(200+30,50);
		oc.setSize(200+30,50);
		od.setSize(200+30,50);
		lock.setSize(100,100);
		
		
		h1.setSize(80,60);
		h2.setSize(80,60);
		h3.setSize(80,60);
		h4.setSize(80,60);
		
		qnum.setSize(50,40);
		amt.setSize(150,40);
		
		//------ Positioning
		//logo.setLocation(200,15);
		
		quest.setLocation(50,300+50);
		oa.setLocation(150+10,500-100+40);
		ob.setLocation(300+150,500-100+40);
		oc.setLocation(150+10,600-100);
		od.setLocation(300+150,600-100);
		lock.setLocation(50,440);
		
		qnum.setLocation(100-50,250+40);
		amt.setLocation(150-50,250+40);
		
		h1.setLocation(600,140+30);
		h2.setLocation(680,140+30);
		h3.setLocation(600,200+30);
		h4.setLocation(680,200+30);
		
		//----- Adding to Container
		//c.add(logo);
		c.add(quest);
		c.add(oa);
		c.add(ob);
		c.add(oc);
		c.add(od);
		
		c.add(qnum);
		c.add(amt);
		
		c.add(h1);
		c.add(h2);
		c.add(h3);
		c.add(h4);
		
		c.add(lock);
		
		//----- Setting Frame Properties
		this.setTitle("KBC 2017");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//------ Adding Listeners
		
		oa.addActionListener(this);
		ob.addActionListener(this);
		oc.addActionListener(this);
		od.addActionListener(this);
		
		h1.addActionListener(this);
		h2.addActionListener(this);
		h3.addActionListener(this);
		h4.addActionListener(this);

		lock.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==oa)
		{
			lock.setEnabled(true);
			setSelectedButton(oa,"A");
			setCheckButton(oa);
		}
		else if(e.getSource()==ob)
		{
			lock.setEnabled(true);
			setSelectedButton(ob,"B");
			setCheckButton(ob);
		}
		else if(e.getSource()==oc)
		{
			lock.setEnabled(true);
			setSelectedButton(oc,"C");
			setCheckButton(oc);
		}
		else if(e.getSource()==od)
		{
			lock.setEnabled(true);
			setSelectedButton(od,"D");
			setCheckButton(od);
		}
		else if(e.getActionCommand().equals("LOCK"))
		{
			
			JButton b=getCheckButton();
			
			if(selected==x.getAnswer())
			{
				lock.setEnabled(false);
				setCorrect(b);
				JOptionPane.showMessageDialog(this,"Mubarak Ho! Sahi Jawab");
				setFreshButton();
				count++;
				
				updateValues();

					oa.setEnabled(true);
					ob.setEnabled(true);
					oc.setEnabled(true);
					od.setEnabled(true);
			}
			else
			{
				
				setIncorrect(b);
				JOptionPane.showMessageDialog(this,"Afsos! Galat Jawab");
				count=0;
				
				//System.exit(0);
			}
			
		}
		else if(e.getActionCommand().equals("50-50"))
		{
			updateValues();
			
				h2.setEnabled(false);
				if(x.getAnswer()=="A")
				{
				ob.setEnabled(false);
				oc.setEnabled(false);
				}
				else if(x.getAnswer()=="B")
				{
					oa.setEnabled(false);
					od.setEnabled(false);
				}
				else if(x.getAnswer()=="C")
				{
					ob.setEnabled(false);
					oc.setEnabled(false);
				}
				else if(x.getAnswer()=="D")
				{
					ob.setEnabled(false);
					oc.setEnabled(false);
				}
		}
		else if(e.getActionCommand().equals("Phone"))
		{
			h1.setEnabled(false);
		}
		else if(e.getActionCommand().equals("???"))
		{
			h4.setEnabled(false);
		}
		else if(e.getActionCommand().equals("Poll"))
		{
			h3.setEnabled(false);
		}
	
	}
	
	public void updateValues()
	{
		x.outQ(count);
		quest.setText(x.getQuestion());
		oa.setText(x.getOptionA());
		ob.setText(x.getOptionB());
		oc.setText(x.getOptionC());
		od.setText(x.getOptionD());
		selected=x.getAnswer();
		
		qnum.setText(String.valueOf(count));
		amt.setText(String.valueOf(setAmount(count)));
	}
	
	public void setCheckButton(JButton b)
	{
		temp=b;
	}
	public JButton getCheckButton()
	{
		return temp;
	}
	
	public void setFreshButton()
	{
		selected="X";
		oa.setBackground(null);
		ob.setBackground(null);
		oc.setBackground(null);
		od.setBackground(null);
		
		oa.setForeground(null);
		ob.setForeground(null);
		oc.setForeground(null);
		od.setForeground(null);
	}
	
	public void setSelectedButton(JButton b,String s)
	{	
		selected=s;
		
		oa.setBackground(null);
		ob.setBackground(null);
		oc.setBackground(null);
		od.setBackground(null);
		
		oa.setForeground(null);
		ob.setForeground(null);
		oc.setForeground(null);
		od.setForeground(null);
		
		b.setBackground(Color.YELLOW);
		b.setForeground(Color.BLACK);
	}
	
	public void setCorrect(JButton b)
	{
		b.setBackground(Color.GREEN);
		b.setForeground(Color.BLACK);
	}
	public void setIncorrect(JButton b)
	{
		b.setBackground(Color.RED);
		b.setForeground(Color.WHITE);
	}
	
	public double setAmount(int c)
	{
		if(c==1)
			return 1000;
		else if(c==2)
			return 2000;
		else if(c==3)
			return 4000;
		else if(c==4)
			return 6000;
		else if(c==5)
			return 8000;
		else if(c==6)
			return 10000;
		else if(c==7)
			return 20000;
		else if(c==8)
			return 000;
		else if(c==9)
			return 40000;
		else if(c==10)
			return 60000;
		else if(c==11)
			return 80000;
		else if(c==12)
			return 100000;
		else if(c==13)
			return 200000;
		else if(c==14)
			return 300000;
		else if(c==15)
			return 400000;
		else if(c==16)
			return 500000;
		else if(c==17)
			return 600000;
		else if(c==18)
			return 700000;
		else if(c==19)
			return 800000;
		else if(c==20)
			return 1000000;
		else 
			return 0;
	}
	
}

class Quiz
{
	//Q stands for question
	
	String q;
	String a,b,c,d;
	String ans;
	
	public void setQ(String q,String a,String b,String c,String d,String ans)
	{
		this.q=q;
		this.a=a;
		this.b=b;
		this.c=c;
		this.d=d;
		this.ans=ans;
	}
	
	public void outQ(int i)
	{
		if(i==1)
			setQ("What is Your Name?","Pata nhi","Nitin","Rajjo","Babli","A");
		else if(i==2)
			setQ("What is your age?","10 weeks","40 years","100 year","3 sec","D");
		else if(i==3)
			setQ("How many girlfriend Kamesh have?","40","2","0","1600","D");
		else if(i==4)
			setQ("Who has the hottest in class?","Lalbalpal","moti","Kamesh","Amarjeet","D");
		else if(i==5)
			setQ("Who loves moti the most?","Sambhu","Amarjeet","Keshav","Kamesh","A");
		else if(i==6)
			setQ("Which is Amarjeet's favourite sweet?","peda","rasgulla","jalebi","chai","D");
		else if(i==7)
			setQ("PP still loves?","Amar","Kamu","Billo","Kesu","B");
		else if(i==8)
			setQ("Why amar loves lalbalpal because of","her glamour","her smile","her talent","other","C");
		else if(i==9)
			setQ("who is the most pakau girl in the class","Puja","PP","Neha","Sambhu","D");
		else if(i==10)
			setQ("Do you think pp is pagal","yes","no","may be","kehav ko pata h","D");
		else
			System.exit(0);
	}
	
	public String getQuestion()
	{
		return q;
	}
	public String getOptionA()
	{
		return a;
	}
	public String getOptionB()
	{
		return b;
	}
	public String getOptionC()
	{
		return c;
	}
	public String getOptionD()
	{
		return d;
	}
	public String getAnswer()
	{
		return ans;
	}
}
