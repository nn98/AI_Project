//package AI_Project1;
//
//import java.awt.BorderLayout;
//import java.awt.Graphics;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
//public class Gui2 extends JFrame{
//	public Gui2() {
//		// TODO Auto-generated constructor stub
//		setSize(1000,1000);
//		setTitle("a알고리즘 gui");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLayout(new BorderLayout());      
//		//jpn5 jpanel5=new jpn5();
//		jpn4 jpanel4=new jpn4();
//		jpn3 jpanel3=new jpn3();
//		jpn2 jpanel2=new jpn2();
//		jpn1 jpanel1=new jpn1(jpanel2,jpanel3,jpanel4);
//		add(jpanel3,BorderLayout.CENTER);
//		add(jpanel1,BorderLayout.PAGE_START);
//		add(jpanel2,BorderLayout.LINE_START);
//		add(jpanel4,BorderLayout.CENTER);
//		setVisible(true);
//	}
//}
//class jpn1 extends JPanel{ // 맨 상위 버튼 구현
//	JButton button1,button2;
//
//	public jpn1(jpn2 jp2,jpn3 jp3,jpn4 jpn4) {
//		// TODO Auto-generated constructor stub
//		button1=new JButton("기존 A*알고리즘");
//		button2=new JButton("변형 A*알고리즘");
//		button1.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if(e.getSource()==button1) {
//					jpn4.setVisible(false);
//					jp2.setVisible(true);
//					jp2.setGui(jp3);
//					jp2.repaint();
//					jp2.revalidate();
//				}   
//			}
//		});
//		button2.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if(e.getSource()==button2) {
//					jp2.setVisible(false);
//					jpn4.setVisible(true);
//					jpn4.setGui();
//					jpn4.repaint();
//					jpn4.revalidate();
//				}
//			}
//		});
//		add(button1);
//		add(button2);
//	}
//}
//class jpn2 extends JPanel{
//	static String[] input;
//	static int ii;
//	Main4 m;
//	boolean flag=false;
//
//	JLabel label2;
//	JTextField text2;
//	JButton button2;
//
//	public void setGui(jpn3 jpanel3) {
//		m=new Main4();
//		JLabel label1=new JLabel("Input Route");
//		JTextField text1=new JTextField(7);
//		JButton button1=new JButton("저장");
//		add(label1);
//		add(text1);
//		add(button1);
//		button1.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if(e.getSource()==button1 || e.getSource()==text1) {
//					input=text1.getText().split("-");
//					if(input[0].equals("0")) {
//						flag=true;
//						System.out.println(m.getRoot().toString());
//						//m.getRoot().print(0);
//						JLabel label3=new JLabel("도착지 입력");
//						JTextField text3=new JTextField(10);
//						JButton button3=new JButton("저장");
//						add(label3);
//						add(text3);
//						add(button3);
//						label3.revalidate(); // 이벤트 실행후 바로 컴포넌트가 실행 안될 떄 
//						text3.revalidate();
//						button3.revalidate();
//						label3.repaint();
//						text3.repaint();
//						button3.repaint();
//						button3.addActionListener(new ActionListener() {
//							@Override
//							public void actionPerformed(ActionEvent e) {
//								// TODO Auto-generated method stub
//								if(e.getSource()==button3 || e.getSource()==text3) {
//									m.setEnd(text3.getText());
//									JLabel label4=new JLabel("출발지는 "+m.getStart());
//									JLabel label5=new JLabel("도착지는 "+m.getEnd());
//									add(label4);
//									add(label5);
//									label4.revalidate(); // 이벤트 실행후 바로 컴포넌트가 실행 안될 떄 
//									label4.repaint();
//									label5.revalidate(); // 이벤트 실행후 바로 컴포넌트가 실행 안될 떄 
//									label5.repaint();
//									if(m.getStart().equals(m.getEnd())) {
//										m.getRoot().searchAlgorithm1(0);
//										m.getRoot().print(0);
//									}
//									else {
//										m.getRoot().searchAlgorithm2(0);
//										m.getRoot().print2(0);
//									}
//									jpanel3.setMain(getMain());
//									System.out.println("-------------------------");
//									//jpanel3.setGui(0);
//								}
//							}
//						});
//						m.setStart(m.getRoot().getVertex());
//						return;
//					}
//					m.setInput(input);
//					//confirm.add(input[0]);
//					String[] points=input[1].split("");
//					m.findNodeNum(input[0],points);
//					int[] lengths=new int[points.length];
//					label2=new JLabel("Input "+input[0]+"-"+points[0]+" Length");
//					text2=new JTextField(7);
//					button2=new JButton("저장");
//					add(label2);
//					add(text2);
//					add(button2);
//					label2.revalidate(); // 이벤트 실행후 바로 컴포넌트가 실행 안될 떄 
//					text2.revalidate();
//					button2.revalidate();
//					label2.repaint();
//					text2.repaint();
//					button2.repaint();
//					ii=0;
//					button2.addActionListener(new ActionListener() {
//						@Override
//						public void actionPerformed(ActionEvent e) {
//							// TODO Auto-generated method stub
//							if(e.getSource()==button2 || e.getSource()==text2) {
//								lengths[ii]=Integer.parseInt(text2.getText());
//								if(ii<points.length-1) {
//									label2.setText("Input "+input[0]+"-"+points[ii+1]+" Length");
//									text2.setText("");
//									ii++;
//								}
//								else {
//									m.histroy(points,lengths);
//									ii=0;
//									label2.setVisible(false);
//									text2.setVisible(false);
//									button2.setVisible(false);
//									text1.setText("");
//								}
//							}
//						}
//					});
//
//				}
//			}
//		});
//	}
//
//	@Override
//	public void paint(Graphics g) {
//		super.paint(g);
//	}
//	public Main4 getMain() {
//		return m;
//	}
//
//}
////
////for(int i=0;i<this.getSize();i++) {
////   for(int j=0;j<depth;j++) System.out.print("\t");
////   if(this.g.get(i)==0 || this.finalF.get(i)==0)
////      System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+",h,g,f");
////   else
////      System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i));
////   points.get(i).print(depth+1);
////}
//
//class jpn3 extends JPanel {
//	Main4 m;
//
//	//   public void setGui(int depth) { // 시작노드==도착노드
//	//      for(int i=0;i<m.getRoot().getSize();i++) {
//	//         for(int j=0;j<depth;j++) System.out.print("\t");
//	//         if(m.getRoot().getAllG().get(i)==0 || m.getRoot().getAllFinalF().get(i)==0)
//	//            System.out.println("+++++"+m.getRoot().getAllFinalF().get(i));
//	//         //System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+",h,g,f");
//	//         else
//	//            System.out.println("+++++"+m.getRoot().getAllFinalF().get(i));
//	//         //System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i));
//	//         m.getRoot().getAllPoints().get(i).setGui(depth+1);
//	//      }
//	//
//	//   }
//	public void setMain(Main4 m) {
//		this.m=m;
//	}
//}
//class jpn4 extends JPanel {
//	public void setGui() {
//		setLayout(new BorderLayout());
//		jpn1_2 jpn1_2=new jpn1_2();
//		jpn1_1 jpn1_1=new jpn1_1();
//		jpn1_1.setGui(jpn1_2);
//		add(jpn1_1,BorderLayout.LINE_START);
//		add(jpn1_2,BorderLayout.CENTER);
//	}
//}
//class jpn1_1 extends JPanel {
//	public void setGui(jpn1_2 jp) {
//		JButton button1=new JButton("예제");
//		JButton button2=new JButton("실습");
//		button1.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if(e.getSource()==button1) {
//					jp.setGui();
//					jp.revalidate();
//					jp.repaint();
//					add(jp);
//				}
//			}
//		});
//		button2.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if(e.getSource()==button2) {
//
//				}
//			}
//		});
//		add(button1);
//		add(button2);
//
//	}
//}
//class jpn1_2 extends JPanel {
//	private ImageIcon image=new ImageIcon(Main4.class.getResource("image/캡처.jpg"));
//	private JButton imageButton=new JButton(image);
//	public void setGui() {
//		imageButton.setBounds(0,0,500,499);
//		imageButton.setBorderPainted(false);
//		imageButton.setContentAreaFilled(false);
//		imageButton.setFocusPainted(false);
//		add(imageButton);
//	}
//}