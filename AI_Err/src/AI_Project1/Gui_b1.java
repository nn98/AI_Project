package AI_Project1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui_b1 extends JFrame{
	public Gui_b1() {
		// TODO Auto-generated constructor stub
		setSize(800,800);
		setTitle("a알고리즘 gui");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		jpn2 jpanel2=new jpn2();
		jpn1 jpanel1=new jpn1(jpanel2);
		//기존 a*알고리즘을 선택했을 때\
		jpanel2.setGui();

		add(jpanel1,BorderLayout.PAGE_START);
		add(jpanel2,BorderLayout.LINE_START);
		setVisible(true);
	}
}
class jpn1 extends JPanel{ // 맨 상위 버튼 구현
	JButton button1,button2;

	public jpn1(jpn2 jp) {
		// TODO Auto-generated constructor stub
		button1=new JButton("기존 A*알고리즘");
		button2=new JButton("변형 A*알고리즘");
		button1.setBackground(Color.WHITE);
		button2.setBackground(Color.LIGHT_GRAY);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==button1)
					jp.setColor(Color.WHITE);
			}
		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==button2)
					jp.setColor(Color.LIGHT_GRAY);
			}
		});
		add(button1);
		add(button2);
	}
}
class jpn2 extends JPanel{
	Color color;
	static String[] input;
	static int ii;
	boolean flag=false;

	JLabel label2;
	JTextField text2;
	JButton button2;
	public void setGui() {
		Main4 m=new Main4();
		JLabel label1=new JLabel("Input Route");
		JTextField text1=new JTextField(7);
		JButton button1=new JButton("저장");
		add(label1);
		add(text1);
		add(button1);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==button1 || e.getSource()==text1) {
					input=text1.getText().split("-");
					if(input[0].equals("0")) {
						flag=true;
						System.out.println(m.getRoot().toString());
						//m.getRoot().print(0);
						JLabel label3=new JLabel("도착지 입력");
						JTextField text3=new JTextField(10);
						JButton button3=new JButton("저장");
						add(label3);
						add(text3);
						add(button3);
						label3.revalidate(); // 이벤트 실행후 바로 컴포넌트가 실행 안될 떄 
						text3.revalidate();
						button3.revalidate();
						label3.repaint();
						text3.repaint();
						button3.repaint();
						button3.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								if(e.getSource()==button3 || e.getSource()==text3) {
									m.setEnd(text3.getText());
									JLabel label4=new JLabel("출발지는 "+m.getStart());
									JLabel label5=new JLabel("도착지는 "+m.getEnd());
									add(label4);
									add(label5);
									label4.revalidate(); // 이벤트 실행후 바로 컴포넌트가 실행 안될 떄 
									label4.repaint();
									label5.revalidate(); // 이벤트 실행후 바로 컴포넌트가 실행 안될 떄 
									label5.repaint();
									if(m.getStart().equals(m.getEnd())) m.getRoot().searchAlgorithm1(0);
									else m.getRoot().searchAlgorithm2(0);
									m.getRoot().print2(0);
								}
							}
						});
						m.setStart(m.getRoot().getVertex());
						return;
					}
					m.setInput(input);
					//confirm.add(input[0]);
					String[] points=input[1].split("");
					Main4.findNodeNum(input[0],points);
					int[] lengths=new int[points.length];
					label2=new JLabel("Input "+input[0]+"-"+points[0]+" Length");
					text2=new JTextField(7);
					button2=new JButton("저장");
					add(label2);
					add(text2);
					add(button2);
					label2.revalidate(); // 이벤트 실행후 바로 컴포넌트가 실행 안될 떄 
					text2.revalidate();
					button2.revalidate();
					label2.repaint();
					text2.repaint();
					button2.repaint();
					ii=0;
					button2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if(e.getSource()==button2 || e.getSource()==text2) {
								lengths[ii]=Integer.parseInt(text2.getText());
								if(ii<points.length-1) {
									label2.setText("Input "+input[0]+"-"+points[ii+1]+" Length");
									text2.setText("");
									ii++;
								}
								else {
									m.histroy(points,lengths);
									ii=0;
									label2.setVisible(false);
									text2.setVisible(false);
									button2.setVisible(false);
									text1.setText("");
								}
							}
						}
					});

				}
			}
		});
	}
	public void setColor(Color c) {
		setBackground(c);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
}