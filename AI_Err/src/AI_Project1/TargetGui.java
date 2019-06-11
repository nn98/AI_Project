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
		setTitle("a알고리즘 gui");
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

	static class jpn1 extends JPanel{ // 맨 상위 버튼 구현
		JButton button1,button2;

		public jpn1(jpn2 jp2,jpn3 jp3,jpn4 jpn4) {
			// TODO Auto-generated constructor stub
			button1=new JButton("기존 A*알고리즘");
			button2=new JButton("변형 A*알고리즘");
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

		//			public void setGui(int depth) { // 시작노드==도착노드
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
			JButton button1=new JButton("예제");
			JButton button2=new JButton("실습");
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
		private ImageIcon image=new ImageIcon(Main.class.getResource("image/캡처.jpg"));
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
		static String start,end; // 시작노드와 도착노드 각각 저장
		static Node root; // 루트 노드 저장
		static int recursionNum=0; // searchAlgorithm 재귀 횟수
		static String nodeVertex=""; // 모든 정점들을 문자열로 저장
		static String[] input;
		static int ii;
		String[] points;
		int[] lengths;

		static class Node{

			private String vertex;                  //현재 정점
			private ArrayList<String> vertexs=null;
			private ArrayList<Node> points=null;       //연결 정점
			private ArrayList<Integer> lengths=null;   //연결 길이
			//정점-길이 합쳐서 경로 Route
			private ArrayList<Integer> h=null; // h값 저장
			private ArrayList<Integer> g=null; // g값 저장
			private ArrayList<Integer> finalF=null; // f값 저장

			//생성자
			public Node(String vertex,String point,int length) {
				//현재 정점 설정
				this.vertex=vertex;   
				//연결 경로 설정
				if(point!=null) this.add(point,length);   
				//point가 null이면 말단 경로
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
			//경로 추가 메소드
			private void add(String points,int length) {
				if(!f) {
					if(confirm.contains(points)) {
						System.out.printf("point %s is exist at %s\n", points,confirm.toString());
						return;
					}
				}
				//경로 미존시 경로 초기화
				if(this.isEmpty()) this.setRoute();
				if(vertexs.contains(points)) return;
				else vertexs.add(points);
				//         System.out.println("V "+this.vertex+", add "+points+", Vs "+this.vertexs.toString());
				//정점 저장-연결 정점 null로 설정해 말단 경로 생성
				this.points.add(new Node(points,null,length));
				//길이 저장
				this.lengths.add(length);
				this.h.add(0);
				this.g.add(0);
				this.finalF.add(0);
			}

			//경로 연결 메소드
			public void addPoints(String vertex,String point,int length) {
				if(this.vertex.equals(point)) return;
				//현재 정점이면 추가
				if(this.vertex.equals(vertex)) {
					this.add(point,length);
				}
				//없으면 연결된 정점들 탐색
				if(!this.isEmpty()) for(Node i:this.points) i.addPoints(vertex, point,length);
				//존재하지 않는 정점이면 생략
			}

			//경로 존재 확인 메소드
			public boolean isEmpty() {
				return this.points==null&&this.lengths==null;
			}

			//경로 초기화 메소드
			public void setRoute() {
				this.vertexs=new ArrayList<String>();
				this.points=new ArrayList<Node>();
				this.lengths=new ArrayList<Integer>();
				this.h=new ArrayList<Integer>();
				this.g=new ArrayList<Integer>();
				this.finalF=new ArrayList<Integer>();
			}

			//경로 조회 메소드
			public String getRoute() {
				String result="";
				for(int i=0;i<this.points.size();i++) result+=this.points.get(i).toString()+" - "+this.lengths.get(i)+" ";
				return result;
			}

			//길이 조회 메소드
			public String getLengths() {
				if(isEmpty()) return "Empty";
				else return this.lengths.toString();
			}

			//정점 조회 메소드
			public String getPoints() {
				if(isEmpty()) return "Empty";
				else return this.points.toString();
			}

			//크기 조회 메소드
			public int getSize() {
				if(isEmpty()) return 0;
				else return points.size();
			}

			//toString 재정의
			@Override 
			public String toString() {
				if(this.isEmpty()) return "[To "+this.vertex+"]";
				else return "| Vertex "+this.vertex+" - "+this.getRoute()+"|";

			}

			//출력 메소드
			public void print(int depth) { // 시작노드==도착노드
				if(this.points==null) {// 중간에 도착지 노드가 나오면 출력이 안되게.
					for(int j=0;j<depth;j++) System.out.print("\t");
					System.out.println("\t"+this.vertex+"-a : "+root.lengths.get(root.vertexs.indexOf(this.vertex)));
				}
				for(int i=0;i<this.getSize();i++) {
					//if(this.points.get(i).points==null) // 자식노드가 리프노드면 
					for(int j=0;j<depth;j++) System.out.print("\t");
					if(this.g.get(i)==0 || this.finalF.get(i)==0)
						System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+",h,g,f");
					else
						System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i));
					points.get(i).print(depth+1);
				}
			}
			public void print2(int depth,int n) { // 시작노드!= 도착노드
				for(int i=0;i<this.getSize();i++) {
					if(points.get(i).vertex.equals(end) && n!=recursionNum)  // 중간에 도착지 노드가 나오면 출력이 안되게.
						continue;
					for(int j=0;j<depth;j++) System.out.print("\t");
					if(this.g.get(i)==0 || this.finalF.get(i)==0)
						System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+",h,g,f");
					else
						System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i));
					points.get(i).print2(depth+1,n+1);
				}
			}

			// 시작노드=도착노드 알고리즘 탐색
			public void searchAlgorithm1(int gLength) { 
				recursionNum++;
				for(int i=0;i<this.points.size();i++) { // 자식 노드 수만큼 반복
					int gg=gLength+this.lengths.get(i);
					this.g.add(i,gg);
					System.out.println(this.points.get(i).vertex+"의 g값:"+this.g.get(i));
					if(this.points.get(i).points==null ) { // 자식노드가 리프노드면
						if(recursionNum!=nodeVertex.length()-1) {//모든 정점을 다 돌았는지 확인
							System.out.println("모든 정점을 돌지 않았습니다.탐색완료되지 않고 종료됩니다.");
							return;
						}
						System.out.println("리프노드여서 자동으로 선택된 정점:"+this.points.get(i).vertex);
						this.h.add(i,this.points.get(i).findRoot());
						this.finalF.add(i,this.h.get(i)+this.g.get(i));
						System.out.println("리프노드의 h값:"+this.h.get(i));
						System.out.println("리프노드의 f값:"+this.finalF.get(i));
						if(this.points.get(i).findRoot()==0)
							System.out.println("리프노드가 시작노드와 연결되지 않아 탐색이 불가능합니다");
						else
							System.out.println("리프노드가 시작노드와 연결되어 정상적으로 탐색 끝났습니다");
						return;
					}
					int h1=0;
					for(int j=0;j<this.points.get(i).points.size();j++) { // 자식 노드의 자식 노드 수만큼 반복
						h1+=this.points.get(i).lengths.get(j); // 자식노드의 모든 g값 더하기
					}
					h1+=this.points.get(i).findRoot(); // 루트 정점과 연결된 것이 있는지 확인하고 그 값 더하기
					this.points.get(i).h.add(i,h1);
					System.out.println(this.points.get(i).vertex+"의 h값:"+this.points.get(i).h.get(i));
					this.finalF.add(i,this.points.get(i).h.get(i)+this.g.get(i));
					System.out.println(this.points.get(i).vertex+"의 f값:"+this.finalF.get(i));
				}
				int min=Integer.MAX_VALUE; // 최솟값 f를 가진 자식노드 선택하기 위해
				int minIndex=0;
				for(int i=0;i<this.points.size();i++) { // 자식 노드 수만큼 반복
					if(min>this.finalF.get(i)) {
						min=this.finalF.get(i);
						minIndex=i;
					}
				}
				System.out.println("가장 작은 인덱스 값:"+minIndex+", 선택된 정점:"+this.points.get(minIndex).vertex);
				this.points.get(minIndex).searchAlgorithm1(this.g.get(minIndex));
			}
			// 현재정점과 루트노드와 연결된 g값 찾기
			public int findRoot() {
				for(int i=0;i<root.points.size();i++) {
					if(this.vertex.equals(root.points.get(i).vertex)) {
						System.out.println(this.vertex+"->"+root.vertex+"의 g값:"+root.lengths.get(i));
						return root.lengths.get(i);
					}
				}
				return 0; // 루트노드와 현재 정점과 연결된 것이 없음
			}
			// 시작노드와 도착노드가 다른 알고리즘 탐색
			public void searchAlgorithm2(int gLength) { 
				recursionNum++;
				for(int i=0;i<this.points.size();i++) { // 자식 노드 수만큼 반복
					if(end.equals(this.points.get(i).vertex) && recursionNum!=nodeVertex.length()-1) {
						continue;
					}
					int gg=gLength+this.lengths.get(i);
					this.g.add(i,gg);
					System.out.println(this.points.get(i).vertex+"의 g값:"+this.g.get(i));
					if(this.points.get(i).points==null) { // 자식노드가 리프노드면
						if(recursionNum!=nodeVertex.length()-1  ) {//모든 정점을 다 돌았는지 확인
							System.out.println("모든 정점을 돌지 않았습니다.탐색완료되지 않고 종료됩니다.");
							return;
						}
						System.out.println("리프노드여서 자동으로 선택된 정점:"+this.points.get(i).vertex);
						this.h.add(i,0);
						this.finalF.add(i,this.h.get(i)+this.g.get(i));
						System.out.println("리프노드의 h값:"+this.h.get(i));
						System.out.println("리프노드의 f값:"+this.finalF.get(i));
						if(this.points.get(i).vertex.equals(end)==false)
							System.out.println("리프노드가 도착노드와 같지않아 탐색이 불가능합니다");
						else
							System.out.println("리프노드가 도착노드와 같아 정상적으로 탐색 끝났습니다");
						return;
					}
					int h1=0;
					for(int j=0;j<this.points.get(i).points.size();j++) { // 자식 노드의 자식 노드 수만큼 반복
						h1+=this.points.get(i).lengths.get(j); // 자식노드의 모든 g값 더하기
					}
					this.points.get(i).h.add(i,h1);
					System.out.println(this.points.get(i).vertex+"의 h값:"+this.points.get(i).h.get(i));
					this.finalF.add(i,this.points.get(i).h.get(i)+this.g.get(i));
					System.out.println(this.points.get(i).vertex+"의 f값:"+this.finalF.get(i));
				}
				int min=Integer.MAX_VALUE; // 최솟값 f를 가진 자식노드 선택하기 위해
				int minIndex=0;
				for(int i=0;i<this.points.size();i++) { // 자식 노드 수만큼 반복
					if(this.finalF.get(i)!=0) {
						if(min>this.finalF.get(i)) {
							min=this.finalF.get(i);
							minIndex=i;
						}
					}
				}
				System.out.println("가장 작은 인덱스 값:"+minIndex+", 선택된 정점:"+this.points.get(minIndex).vertex);
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
