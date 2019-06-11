package AI_Project1;


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import Main.Node;
public class TargetGui extends JFrame{

	static Main m;
	static String[] input;
	static int ii;

	public TargetGui() {
		// TODO Auto-generated constructor stub
		setSize(1000,1000);
		setTitle("a�˰��� gui");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());		
		//jpn5 jpanel5=new jpn5();
		jpn4 jpanel4=new jpn4();
		jpn3 jpanel3=new jpn3();
		jpn2 jpanel2=new jpn2();
		jpn1 jpanel1=new jpn1(jpanel2,jpanel3,jpanel4);
		JButton jb=new JButton("ex");
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.root.print(0);
			}
		});
		jpanel1.add(jb);
		add(jpanel3,BorderLayout.CENTER);
		add(jpanel1,BorderLayout.PAGE_START);
		add(jpanel2,BorderLayout.LINE_START);
		add(jpanel4,BorderLayout.CENTER);
		setVisible(true);
	}

	static class jpn1 extends JPanel{ // �� ���� ��ư ����
		JButton button1,button2;

		public jpn1(jpn2 jp2,jpn3 jp3,jpn4 jpn4) {
			// TODO Auto-generated constructor stub
			button1=new JButton("���� A*�˰���");
			button2=new JButton("���� A*�˰���");
			button1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getSource()==button1) {
						jpn4.setVisible(false);
						jp2.setVisible(true);
						jp2.setGui(jp3);
						jp2.repaint();
						jp2.revalidate();
					}	
				}
			});
			button2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getSource()==button2) {
						jp2.setVisible(false);
						jpn4.setVisible(true);
						jpn4.setGui();
						jpn4.repaint();
						jpn4.revalidate();
					}
				}
			});
			add(button1);
			add(button2);
		}
	}

	class jpn2 extends JPanel{

		boolean flag=false;

		JLabel label2;
		JTextField text2;
		JButton button2;

		public void setGui(jpn3 jpanel3) {
			m=new Main();
			JLabel label1=new JLabel("Input Route");
			JTextField text1=new JTextField(7);
			JButton button1=new JButton("����");
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
							JLabel label3=new JLabel("������ �Է�");
							JTextField text3=new JTextField(10);
							JButton button3=new JButton("����");
							add(label3);
							add(text3);
							add(button3);
							label3.revalidate(); // �̺�Ʈ ������ �ٷ� ������Ʈ�� ���� �ȵ� �� 
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
										JLabel label4=new JLabel("������� "+m.getStart());
										JLabel label5=new JLabel("�������� "+m.getEnd());
										add(label4);
										add(label5);
										label4.revalidate(); // �̺�Ʈ ������ �ٷ� ������Ʈ�� ���� �ȵ� �� 
										label4.repaint();
										label5.revalidate(); // �̺�Ʈ ������ �ٷ� ������Ʈ�� ���� �ȵ� �� 
										label5.repaint();
										if(m.getStart().equals(m.getEnd())) {
											m.getRoot().searchAlgorithm1(0);
											m.getRoot().print(0);
										}
										else {
											m.getRoot().searchAlgorithm2(0);
											m.getRoot().print2(0,1);
										}
										//										jpanel3.setMain(getMain());
										System.out.println("-------------------------");
										//jpanel3.setGui(0);
									}
								}
							});
							m.setStart(m.getRoot().getVertex());
							return;
						}
						m.setInput(input);
						//confirm.add(input[0]);
						String[] points=input[1].split("");
						m.findNodeNum(input[0],points);
						int[] lengths=new int[points.length];
						label2=new JLabel("Input "+input[0]+"-"+points[0]+" Length");
						text2=new JTextField(7);
						button2=new JButton("����");
						add(label2);
						add(text2);
						add(button2);
						label2.revalidate(); // �̺�Ʈ ������ �ٷ� ������Ʈ�� ���� �ȵ� �� 
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

		@Override
		public void paint(Graphics g) {
			super.paint(g);
		}
		public Main getMain() {
			return m;
		}

	}
	//
	//for(int i=0;i<this.getSize();i++) {
	//		for(int j=0;j<depth;j++) System.out.print("\t");
	//		if(this.g.get(i)==0 || this.finalF.get(i)==0)
	//			System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+",h,g,f");
	//		else
	//			System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i));
	//		points.get(i).print(depth+1);
	//}

	static class jpn3 extends JPanel {

		//			public void setGui(int depth) { // ���۳��==�������
		//				for(int i=0;i<m.getRoot().getSize();i++) {
		//					for(int j=0;j<depth;j++) System.out.print("\t");
		//					if(m.getRoot().getAllG().get(i)==0 || m.getRoot().getAllFinalF().get(i)==0)
		//						System.out.println("+++++"+m.getRoot().getAllFinalF().get(i));
		//					//System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+",h,g,f");
		//					else
		//						System.out.println("+++++"+m.getRoot().getAllFinalF().get(i));
		//					//System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i));
		//					m.getRoot().getAllPoints().get(i).setGui(depth+1);
		//				}
		//		
		//			}
		//		public void setMain(Main m) {
		//			this.m=m;
		//		}
	}
	static class jpn4 extends JPanel {
		public void setGui() {
			setLayout(new BorderLayout());
			jpn1_2 jpn1_2=new jpn1_2();
			jpn1_1 jpn1_1=new jpn1_1();
			jpn1_1.setGui(jpn1_2);
			add(jpn1_1,BorderLayout.LINE_START);
			add(jpn1_2,BorderLayout.CENTER);
		}
	}
	static class jpn1_1 extends JPanel {
		public void setGui(jpn1_2 jp) {
			JButton button1=new JButton("����");
			JButton button2=new JButton("�ǽ�");
			button1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getSource()==button1) {
						jp.setGui();
						jp.revalidate();
						jp.repaint();
						add(jp);
					}
				}
			});
			button2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getSource()==button2) {

					}
				}
			});
			add(button1);
			add(button2);

		}
	}
	static class jpn1_2 extends JPanel {
		private ImageIcon image=new ImageIcon(Main.class.getResource("image/ĸó.jpg"));
		private JButton imageButton=new JButton(image);
		public void setGui() {
			imageButton.setBounds(0,0,500,499);
			imageButton.setBorderPainted(false);
			imageButton.setContentAreaFilled(false);
			imageButton.setFocusPainted(false);
			add(imageButton);
		}
	}

	static class Main{

		static boolean f=true;
		static ArrayList<String> confirm;
		static ArrayList<String> vHistory=new ArrayList<String>();
		static ArrayList<Integer> lHistory=new ArrayList<Integer>();
		static String start,end; // ���۳��� ������� ���� ����
		static Node root; // ��Ʈ ��� ����
		static int recursionNum=0; // searchAlgorithm ��� Ƚ��
		static String nodeVertex=""; // ��� �������� ���ڿ��� ����
		static String[] input;
		static int ii;
		String[] points;
		int[] lengths;

		static class Node{

			private String vertex;                  //���� ����
			private ArrayList<String> vertexs=null;
			private ArrayList<Node> points=null;       //���� ����
			private ArrayList<Integer> lengths=null;   //���� ����
			//����-���� ���ļ� ��� Route
			private ArrayList<Integer> h=null; // h�� ����
			private ArrayList<Integer> g=null; // g�� ����
			private ArrayList<Integer> finalF=null; // f�� ����

			//������
			public Node(String vertex,String point,int length) {
				//���� ���� ����
				this.vertex=vertex;   
				//���� ��� ����
				if(point!=null) this.add(point,length);   
				//point�� null�̸� ���� ���
			}
			public String getVertex() {
				return vertex;
			}
			public ArrayList<Node> getAllPoints() {
				return points;
			}
			public String getAllVertex() {
				return vertex;
			}
			public ArrayList<Integer> getAllFinalF() {
				return finalF;
			}
			public ArrayList<Integer> getAllG() {
				return g;
			}
			//��� �߰� �޼ҵ�
			private void add(String points,int length) {
				if(!f) {
					if(confirm.contains(points)) {
						System.out.printf("point %s is exist at %s\n", points,confirm.toString());
						return;
					}
				}
				//��� ������ ��� �ʱ�ȭ
				if(this.isEmpty()) this.setRoute();
				if(vertexs.contains(points)) return;
				else vertexs.add(points);
				//         System.out.println("V "+this.vertex+", add "+points+", Vs "+this.vertexs.toString());
				//���� ����-���� ���� null�� ������ ���� ��� ����
				this.points.add(new Node(points,null,length));
				//���� ����
				this.lengths.add(length);
				this.h.add(0);
				this.g.add(0);
				this.finalF.add(0);
			}

			//��� ���� �޼ҵ�
			public void addPoints(String vertex,String point,int length) {
				if(this.vertex.equals(point)) return;
				//���� �����̸� �߰�
				if(this.vertex.equals(vertex)) {
					this.add(point,length);
				}
				//������ ����� ������ Ž��
				if(!this.isEmpty()) for(Node i:this.points) i.addPoints(vertex, point,length);
				//�������� �ʴ� �����̸� ����
			}

			//��� ���� Ȯ�� �޼ҵ�
			public boolean isEmpty() {
				return this.points==null&&this.lengths==null;
			}

			//��� �ʱ�ȭ �޼ҵ�
			public void setRoute() {
				this.vertexs=new ArrayList<String>();
				this.points=new ArrayList<Node>();
				this.lengths=new ArrayList<Integer>();
				this.h=new ArrayList<Integer>();
				this.g=new ArrayList<Integer>();
				this.finalF=new ArrayList<Integer>();
			}

			//��� ��ȸ �޼ҵ�
			public String getRoute() {
				String result="";
				for(int i=0;i<this.points.size();i++) result+=this.points.get(i).toString()+" - "+this.lengths.get(i)+" ";
				return result;
			}

			//���� ��ȸ �޼ҵ�
			public String getLengths() {
				if(isEmpty()) return "Empty";
				else return this.lengths.toString();
			}

			//���� ��ȸ �޼ҵ�
			public String getPoints() {
				if(isEmpty()) return "Empty";
				else return this.points.toString();
			}

			//ũ�� ��ȸ �޼ҵ�
			public int getSize() {
				if(isEmpty()) return 0;
				else return points.size();
			}

			//toString ������
			@Override 
			public String toString() {
				if(this.isEmpty()) return "[To "+this.vertex+"]";
				else return "| Vertex "+this.vertex+" - "+this.getRoute()+"|";

			}

			//��� �޼ҵ�
			public void print(int depth) { // ���۳��==�������
				if(this.points==null) {// �߰��� ������ ��尡 ������ ����� �ȵǰ�.
					for(int j=0;j<depth;j++) System.out.print("\t");
					System.out.println("\t"+this.vertex+"-a : "+root.lengths.get(root.vertexs.indexOf(this.vertex)));
				}
				for(int i=0;i<this.getSize();i++) {
					//if(this.points.get(i).points==null) // �ڽĳ�尡 �������� 
					for(int j=0;j<depth;j++) System.out.print("\t");
					if(this.g.get(i)==0 || this.finalF.get(i)==0)
						System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+",h,g,f");
					else
						System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i));
					points.get(i).print(depth+1);
				}
			}
			public void print2(int depth,int n) { // ���۳��!= �������
				for(int i=0;i<this.getSize();i++) {
					if(points.get(i).vertex.equals(end) && n!=recursionNum)  // �߰��� ������ ��尡 ������ ����� �ȵǰ�.
						continue;
					for(int j=0;j<depth;j++) System.out.print("\t");
					if(this.g.get(i)==0 || this.finalF.get(i)==0)
						System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+",h,g,f");
					else
						System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i));
					points.get(i).print2(depth+1,n+1);
				}
			}

			// ���۳��=������� �˰��� Ž��
			public void searchAlgorithm1(int gLength) { 
				recursionNum++;
				for(int i=0;i<this.points.size();i++) { // �ڽ� ��� ����ŭ �ݺ�
					int gg=gLength+this.lengths.get(i);
					this.g.add(i,gg);
					System.out.println(this.points.get(i).vertex+"�� g��:"+this.g.get(i));
					if(this.points.get(i).points==null ) { // �ڽĳ�尡 ��������
						if(recursionNum!=nodeVertex.length()-1) {//��� ������ �� ���Ҵ��� Ȯ��
							System.out.println("��� ������ ���� �ʾҽ��ϴ�.Ž���Ϸ���� �ʰ� ����˴ϴ�.");
							return;
						}
						System.out.println("������忩�� �ڵ����� ���õ� ����:"+this.points.get(i).vertex);
						this.h.add(i,this.points.get(i).findRoot());
						this.finalF.add(i,this.h.get(i)+this.g.get(i));
						System.out.println("��������� h��:"+this.h.get(i));
						System.out.println("��������� f��:"+this.finalF.get(i));
						if(this.points.get(i).findRoot()==0)
							System.out.println("������尡 ���۳��� ������� �ʾ� Ž���� �Ұ����մϴ�");
						else
							System.out.println("������尡 ���۳��� ����Ǿ� ���������� Ž�� �������ϴ�");
						return;
					}
					int h1=0;
					for(int j=0;j<this.points.get(i).points.size();j++) { // �ڽ� ����� �ڽ� ��� ����ŭ �ݺ�
						h1+=this.points.get(i).lengths.get(j); // �ڽĳ���� ��� g�� ���ϱ�
					}
					h1+=this.points.get(i).findRoot(); // ��Ʈ ������ ����� ���� �ִ��� Ȯ���ϰ� �� �� ���ϱ�
					this.points.get(i).h.add(i,h1);
					System.out.println(this.points.get(i).vertex+"�� h��:"+this.points.get(i).h.get(i));
					this.finalF.add(i,this.points.get(i).h.get(i)+this.g.get(i));
					System.out.println(this.points.get(i).vertex+"�� f��:"+this.finalF.get(i));
				}
				int min=Integer.MAX_VALUE; // �ּڰ� f�� ���� �ڽĳ�� �����ϱ� ����
				int minIndex=0;
				for(int i=0;i<this.points.size();i++) { // �ڽ� ��� ����ŭ �ݺ�
					if(min>this.finalF.get(i)) {
						min=this.finalF.get(i);
						minIndex=i;
					}
				}
				System.out.println("���� ���� �ε��� ��:"+minIndex+", ���õ� ����:"+this.points.get(minIndex).vertex);
				this.points.get(minIndex).searchAlgorithm1(this.g.get(minIndex));
			}
			// ���������� ��Ʈ���� ����� g�� ã��
			public int findRoot() {
				for(int i=0;i<root.points.size();i++) {
					if(this.vertex.equals(root.points.get(i).vertex)) {
						System.out.println(this.vertex+"->"+root.vertex+"�� g��:"+root.lengths.get(i));
						return root.lengths.get(i);
					}
				}
				return 0; // ��Ʈ���� ���� ������ ����� ���� ����
			}
			// ���۳��� ������尡 �ٸ� �˰��� Ž��
			public void searchAlgorithm2(int gLength) { 
				recursionNum++;
				for(int i=0;i<this.points.size();i++) { // �ڽ� ��� ����ŭ �ݺ�
					if(end.equals(this.points.get(i).vertex) && recursionNum!=nodeVertex.length()-1) {
						continue;
					}
					int gg=gLength+this.lengths.get(i);
					this.g.add(i,gg);
					System.out.println(this.points.get(i).vertex+"�� g��:"+this.g.get(i));
					if(this.points.get(i).points==null) { // �ڽĳ�尡 ��������
						if(recursionNum!=nodeVertex.length()-1  ) {//��� ������ �� ���Ҵ��� Ȯ��
							System.out.println("��� ������ ���� �ʾҽ��ϴ�.Ž���Ϸ���� �ʰ� ����˴ϴ�.");
							return;
						}
						System.out.println("������忩�� �ڵ����� ���õ� ����:"+this.points.get(i).vertex);
						this.h.add(i,0);
						this.finalF.add(i,this.h.get(i)+this.g.get(i));
						System.out.println("��������� h��:"+this.h.get(i));
						System.out.println("��������� f��:"+this.finalF.get(i));
						if(this.points.get(i).vertex.equals(end)==false)
							System.out.println("������尡 �������� �����ʾ� Ž���� �Ұ����մϴ�");
						else
							System.out.println("������尡 �������� ���� ���������� Ž�� �������ϴ�");
						return;
					}
					int h1=0;
					for(int j=0;j<this.points.get(i).points.size();j++) { // �ڽ� ����� �ڽ� ��� ����ŭ �ݺ�
						h1+=this.points.get(i).lengths.get(j); // �ڽĳ���� ��� g�� ���ϱ�
					}
					this.points.get(i).h.add(i,h1);
					System.out.println(this.points.get(i).vertex+"�� h��:"+this.points.get(i).h.get(i));
					this.finalF.add(i,this.points.get(i).h.get(i)+this.g.get(i));
					System.out.println(this.points.get(i).vertex+"�� f��:"+this.finalF.get(i));
				}
				int min=Integer.MAX_VALUE; // �ּڰ� f�� ���� �ڽĳ�� �����ϱ� ����
				int minIndex=0;
				for(int i=0;i<this.points.size();i++) { // �ڽ� ��� ����ŭ �ݺ�
					if(this.finalF.get(i)!=0) {
						if(min>this.finalF.get(i)) {
							min=this.finalF.get(i);
							minIndex=i;
						}
					}
				}
				System.out.println("���� ���� �ε��� ��:"+minIndex+", ���õ� ����:"+this.points.get(minIndex).vertex);
				this.points.get(minIndex).searchAlgorithm2(this.g.get(minIndex));
			}

		}

		public static void findNodeNum(String a,String[] array) {
			if(nodeVertex.contains(a)==false)
				nodeVertex+=a;
			for(int i=0;i<array.length;i++) {
				if(nodeVertex.contains(array[i])==false)
					nodeVertex+=array[i];
			}
		}
		public static Node getRoot() {
			return root;
		}
		public void setEnd(String e) {
			end=e;
		}
		public String getEnd() {
			return end;
		}
		public void setStart(String s) {
			start=s;
		}
		public String getStart() {
			return start;
		}
		public void setInput(String[] s) {
			input=new String[s.length];
			for(int i=0;i<input.length;i++) {
				input[i]=s[i];
			}
		}
		public void histroy(String[] points,int[] lengths) {
			for(int i=0;i<points.length;i++) {
				if(root==null) root=new Node(input[0],points[i],lengths[i]);
				else {
					if(!input[0].equals("a")) {
						vHistory.add(""+input[0]+"-"+points[i]);
						lHistory.add(lengths[i]);
					}
					System.out.printf("vertex %s - add %s\n",input[0],points[i]);
					confirm=new ArrayList<String>();
					confirm.add(input[0]);
					root.addPoints(input[0],points[i],lengths[i]);
					if(!f) {
						vHistory.add(""+points[i]+"-"+input[0]);
						lHistory.add(lengths[i]);
						System.out.printf("vertex %s - add %s\n------------------\n",points[i],input[0]);
						confirm=new ArrayList<String>();
						confirm.add(points[i]);
						root.addPoints(points[i],input[0],lengths[i]);
					}
				}
				if(!f) {

					for(int h=vHistory.size()-1;h>=0;h--) {
						confirm=new ArrayList<String>();
						confirm.add(points[i]);
						System.out.println("add "+vHistory.get(h));
						String[] his=vHistory.get(h).split("-");
						if(confirm.contains(his[1])) continue;
						confirm.add(his[0]);
						System.out.println();
						root.addPoints(his[0], his[1], lHistory.get(h));
					}
				}
			}
			f=false;
		}
		public static void main(String[] args) {
			TargetGui gui=new TargetGui();
		}
	}

}
