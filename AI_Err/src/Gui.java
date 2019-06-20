
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



public class Gui extends JFrame{

	static ArrayList<String> resultSet=new ArrayList<>();
	static int resultCount=1;

	static String NodeS="<html><pre>";
	static String answer="���: ";
	static Main m;
	static String[] input;
	static int ii;
	//static JButton bb1;
	static int[] depthLength;
	static int x1,y1,x2,y2;
	static Point[] p=new Point[1000];
	static int index=0;
	static boolean bool;
	static int imageNum=1;
	static int weight=0;
	static class Main extends JPanel{

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
		public Main() {
			f=true;
			confirm=new ArrayList<String>();
			vHistory=new ArrayList<String>();
			lHistory=new ArrayList<Integer>();
			start="";
			end=""; // ���۳��� ������� ���� ����
			root=null; // ��Ʈ ��� ����
			recursionNum=0; // searchAlgorithm ��� Ƚ��
			nodeVertex=""; // ��� �������� ���ڿ��� ����
			input=null;
			ii=0;
			points=null;
			lengths=null;
		}
		public void setStartPoint(String s) {
			answer+=s;
		}
		static class LNode extends Node{
			public LNode(String vertex, String point, int length, String number) {
				super(vertex, point, length);
				//            String[] nA=number.split("");
				//            for(String i:nA)
				//               this.number.add(i);
				// TODO Auto-generated constructor stub
			}
		}
		static class Node extends JPanel{

			private String vertex;                  //���� ����
			private ArrayList<String> vertexs=null;
			private ArrayList<Node> points=null;       //���� ����
			private ArrayList<Integer> lengths=null;   //���� ����
			//����-���� ���ļ� ��� Route
			private ArrayList<Integer> h=null; // h�� ����
			private ArrayList<Integer> g=null; // g�� ����
			private ArrayList<Integer> finalF=null; // f�� ����
			//protected int weight;

			protected ArrayList<String> number=new ArrayList<>();

			//         public void setW(String w) {
			//            this.weight=Integer.parseInt(w);
			//         }

			public void setNumber(String vertex, String number) {
				if(this.vertex.equals(vertex)) {
					String[] nA=number.split(",");
					for(String i:nA)
						this.number.add(i);
				}
				else if(this.points!=null)
					for(Node i:this.points) {
						i.setNumber(vertex, number);
					}
			}

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
			public void addPoints(String vertex,String point,int length,String number) {
				if(this.vertex.equals(point)) return;
				//���� �����̸� �߰�
				if(this.vertex.equals(vertex)) {
					this.add(point,length,number);
				}
				//������ ����� ������ Ž��
				if(!this.isEmpty()) for(Node i:this.points) i.addPoints(vertex, point,length,number);
				//�������� �ʴ� �����̸� ����
			}

			protected void add(String points,int length,String number) {
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
				this.points.add(new LNode(points,null,length,number));
				//���� ����
				this.lengths.add(length);
				this.h.add(0);
				this.g.add(0);
				this.finalF.add(0);
			}

			public void printL(int depth,ArrayList<String> before,ArrayList<String> before2,int sum,int count) { // ���۳��==�������
				//            if(this.points==null) {// �߰��� ������ ��尡 ������ ����� �ȵǰ�.
				//               for(int j=0;j<depth;j++) System.out.print("\t");
				//               System.out.println("Err Check :"+this.vertex+this.points+this.lengths);
				//            }
				//            if(this.points==null) {// �߰��� ������ ��尡 ������ ����� �ȵǰ�.
				//               for(int j=0;j<depth;j++) System.out.print("\t");
				//               System.out.println("\t"+this.vertex+"-a : "+root.lengths.get(root.vertexs.indexOf(this.vertex)));
				//            }
				//            boolean C=false;
				//            if(b1!=null && b2!=null) C=isC(b1.number,b2.number); 
				String now="";
				String b=null;
				if(before2==null) now="3";
				else {
					//               System.out.println("before Line:"+before+"before Line2:"+before2+" this Line:"+this.number);
					for(String i:before) {
						if(before2.contains(i)) {
							now=i;
							b=i;
						}
					}
					//               for(String i:this.number) {
					//                  if(before.contains(i)) {
					//                     b=i;
					//                     break;
					//                  }
					//               }
				}
				if(!this.number.contains(now)) {
					for(String i:before) {
						if(this.number.contains(i)) {
							now=i;
							break;
						}
					}
				}
				if(this.isEmpty()) {
					for(int j=0;j<depth;j++) System.out.print("\t");
					if(b.equals(now)) {
						resultSet.add("Case "+(resultCount++)+"["+sum+" min, cross "+count+"]");
						System.out.println("now Line: "+now+" "+"before Line: "+b+" "+this.vertex+this.number+" : "+(sum+(weight*count))+" min, cross "+count);
					}
					else {
						resultSet.add("Case "+(resultCount++)+"["+sum+" min, cross "+(count+1)+"]");
						System.out.println("now Line: "+now+" "+"before Line: "+b+" "+this.vertex+this.number+" : "+(sum+(weight*(count+1)))+" min, cross "+(count+1));
					}

					System.out.println();
				}
				for(int i=0;i<this.getSize1();i++) {
					//if(this.points.get(i).points==null) // �ڽĳ�尡 �������� 
					for(int j=0;j<depth;j++) System.out.print("\t");

					//               searchL()
					//               if(C) {
					//                  System.out.println(this.vertex+this.number+"-"+points.get(i).vertex+points.get(i).number+" : "+(sum+this.lengths.get(i))+"min , cross "+(count+1)); //+"\tNum:"+points.get(i).number
					//                  points.get(i).printL(depth+1,this,b1,sum+this.lengths.get(i),count+1);
					//               }
					//               else {
					//                  System.out.println(this.vertex+this.number+"-"+points.get(i).vertex+points.get(i).number+" : "+(sum+this.lengths.get(i))+"min , cross "+count); //+"\tNum:"+points.get(i).number
					//                  points.get(i).printL(depth+1,this,b1,sum+this.lengths.get(i),count);
					//               }
					System.out.println("now Line: "+now+" "+"before Line: "+b+" "+this.vertex+this.number+"-"+points.get(i).vertex+points.get(i).number+" : "+(sum+this.lengths.get(i))+" min , cross "+count); //+"\tNum:"+points.get(i).number

					if(before2!=null) {
						if(b.equals(now))
							points.get(i).printL(depth+1,this.number,before,sum+this.lengths.get(i),count);
						else {
							points.get(i).printL(depth+1,this.number,before,sum+this.lengths.get(i),count+1);
						}
					} else {
						points.get(i).printL(depth+1,this.number,before,sum+this.lengths.get(i),count);
					}

				}
			}

			public boolean isC2(String before,String next) {
				return false;
			}

			public boolean isC(ArrayList<String> before1,ArrayList<String> before2) {
				System.out.printf("isC %s : %s , b1 : %s , b2 : %s\n",this.vertex, this.number, before1, before2);
				String bLine=null;
				ArrayList<String> a=(ArrayList<String>) this.number.clone();
				for(String i:before1) {
					if(before2.contains(i)) {
						System.out.println("before Line:"+i);
						bLine=i;
						break;
					}
				}
				boolean r;
				for(String i:this.number) {
					if(i.equals(bLine)) return false;
				}
				System.out.println("Line Change:"+bLine+" -> "+this.number);
				return true;
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
			public int getSize1() {
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
				for(int i=0;i<this.getSize1();i++) {
					//if(this.points.get(i).points==null) // �ڽĳ�尡 �������� 
					for(int j=0;j<depth;j++) System.out.print("\t");
					if(this.g.get(i)==0 || this.finalF.get(i)==0)
						System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+",h,g,f");
					else
						System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i));
					points.get(i).print(depth+1);
				}
			}
			public void printNode(int depth) { // ���۳��==�������
				if(this.points==null) {// �߰��� ������ ��尡 ������ ����� �ȵǰ�.
					for(int j=0;j<depth;j++) NodeS+="\t";
					NodeS+="\t"+this.vertex+"-a : "+root.lengths.get(root.vertexs.indexOf(this.vertex))+"<br>";
					System.out.println(this.vertex+" "+depth);
				}
				for(int i=0;i<this.getSize1();i++) {
					//if(this.points.get(i).points==null) // �ڽĳ�尡 �������� 
					for(int j=0;j<depth;j++) NodeS+="\t";
					if(this.g.get(i)==0 || this.finalF.get(i)==0)
						NodeS+=this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+",h,g,f"+"<br>";
					else
						NodeS+=this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i)+"<br>";
					points.get(i).printNode(depth+1);
				}
			}
			public void printNode2(int depth,int n) { // ���۳��!= �������
				for(int i=0;i<this.getSize1();i++) {
					if(points.get(i).vertex.equals(end) && n!=recursionNum)  // �߰��� ������ ��尡 ������ ����� �ȵǰ�.
						continue;
					for(int j=0;j<depth;j++) NodeS+="\t";
					if(this.g.get(i)==0 || this.finalF.get(i)==0)
						NodeS+=this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+",h,g,f"+"<br>";
					else
						NodeS+=this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i)+"<br>";
					points.get(i).printNode2(depth+1,n+1);
				}
			}
			public void printNodeGui(int depth,jpn3 panel,int x,int y,boolean boo) { // ���۳��!= �������
				boolean bool = false;
				JButton bb1;
				p[index]=new Point();
				if(depth==0) { // ���۳�� ���� ���
					JButton bb2=new JButton(start+" : length,h,g,f");
					bb2.setBounds(1,depth*100,150,30);
					bb2.setBackground(Color.red);
					panel.add(bb2);
					bool=true;

				}
				for(int i=0;i<this.getSize1();i++) {
					p[index]=new Point();
					if(this.g.get(i)==0 || this.finalF.get(i)==0) {
						NodeS+=this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+",h,g,f"+"<br>";
						bb1=new JButton(points.get(i).vertex+" : "+this.lengths.get(i));
					}
					else {
						NodeS+=this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i)+"<br>";
						bb1=new JButton(points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i));
					}
					bb1.setBounds(1+160*depthLength[depth],(depth+1)*100,150,30);
					System.out.println("boo:"+boo+"depth:"+depth);
					if(String.valueOf(bb1.getText().charAt(0)).equals(String.valueOf(answer.charAt(4+depth+1))) && boo==true) {
						bool=true;
						bb1.setBackground(Color.red);

					}
					else {
						bool=false;
						bb1.setBackground(Color.white);

					}
					System.out.println("bb1.getText().charAt(0)"+bb1.getText().charAt(0)+" "+answer.charAt(4+depth+1));
					System.out.println("depthLength[depth]:"+depthLength[depth]);
					System.out.println("4+depth+1:"+(5+depth));
					//bb1.setBackground(Color.white);
					panel.add(bb1);
					p[index].x1=x;
					p[index].y1=y;
					p[index].x2=76+depthLength[depth]*160;
					p[index].y2=30*(depth+1)+70*(depth+1);
					depthLength[depth]++;
					repaint();
					//System.out.println(points.get(i).vertex+"***index:"+index+" "+p[index].x1+" "+p[index].y1+" "+p[index].x2+" "+p[index].y2);
					index++;
					if(depth==0)
						points.get(i).printNodeGui(depth+1,panel,p[index-1].x2,p[index-1].y2+30,bool);
					else
						points.get(i).printNodeGui(depth+1,panel,p[index-1].x2,p[index-1].y2+30,bool);
				}
				if(this.points==null && (root.lengths.get(root.vertexs.indexOf(this.vertex)))!=null) {// ��������϶� ���۳�带 ���� ����ϱ�
					NodeS+="\t"+this.vertex+"-"+start+" : "+root.lengths.get(root.vertexs.indexOf(this.vertex))+"<br>";
					JButton bb3=new JButton(start+" : "+root.lengths.get(root.vertexs.indexOf(this.vertex)));
					bb3.setBounds(1+160*depthLength[depth],(depth+1)*100,150,30);
					System.out.println("bool:"+bool+" boo:"+boo);
					if(boo==true)
						bb3.setBackground(Color.red);
					else
						bb3.setBackground(Color.white);
					panel.add(bb3);
					p[index].x1=x;
					p[index].y1=y;
					p[index].x2=76+depthLength[depth]*160;
					p[index].y2=30*(depth+1)+70*(depth+1);
					depthLength[depth]++;
					repaint();
					//System.out.println(this.vertex+" index:"+index+" "+p[index].x1+" "+p[index].y1+" "+p[index].x2+" "+p[index].y2);
					index++;
				}
			}
			public void printNodeGui2(int depth,int n,jpn3 panel,int x,int y,boolean boo) { // ���۳��!= �������
				JButton bb1;
				boolean bool = false;
				p[index]=new Point();
				if(depth==0) {
					JButton bb2=new JButton(start+" : length,h,g,f");
					bb2.setBounds(1,depth*100,150,30);
					bb2.setBackground(Color.red);
					bool=true;
					panel.add(bb2);
				}
				for(int i=0;i<this.getSize1();i++) {
					if(points.get(i).vertex.equals(end) && n!=recursionNum)  // �߰��� ������ ��尡 ������ ����� �ȵǰ�.
						continue;
					if(this.g.get(i)==0 || this.finalF.get(i)==0) {
						NodeS+=this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+",h,g,f"+"<br>";
						bb1=new JButton(points.get(i).vertex+" : "+this.lengths.get(i));
					}
					else {
						NodeS+=this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i)+"<br>";
						bb1=new JButton(points.get(i).vertex+" : "+this.lengths.get(i)+","+(this.finalF.get(i)-this.g.get(i))+","+this.g.get(i)+"."+this.finalF.get(i));
					}
					bb1.setBounds(1+160*depthLength[depth],(depth+1)*100,150,30);
					if(String.valueOf(bb1.getText().charAt(0)).equals(String.valueOf(answer.charAt(4+depth+1))) && boo==true) {
						bool=true;
						bb1.setBackground(Color.red);

					}
					else {
						bool=false;
						bb1.setBackground(Color.white);

					}
					//bb1.setBackground(Color.white);
					panel.add(bb1);
					p[index].x1=x;
					p[index].y1=y;
					p[index].x2=76+depthLength[depth]*160;
					p[index].y2=30*(depth+1)+70*(depth+1);
					depthLength[depth]++;
					repaint();
					//System.out.println(points.get(i).vertex+"***index:"+index+" "+p[index].x1+" "+p[index].y1+" "+p[index].x2+" "+p[index].y2);
					index++;
					if(depth==0)
						points.get(i).printNodeGui2(depth+1,n+1,panel,p[index-1].x2,p[index-1].y2+30,bool);
					else
						points.get(i).printNodeGui2(depth+1,n+1,panel,p[index-1].x2,p[index-1].y2+30,bool);
					//points.get(i).printNodeGui2(depth+1,n+1,panel,p[index-1].x2,p[index-1].y2+30);
				}
			}
			public void print2(int depth,int n) { // ���۳��!= �������
				for(int i=0;i<this.getSize1();i++) {
					if(points.get(i).vertex.equals(end) && n!=recursionNum)  // �߰��� ������ ��尡 ������ ����� �ȵǰ�.
						continue;
					for(int j=0;j<depth;j++) System.out.print("\t");
					if(this.g.get(i)==0 || this.finalF.get(i)==0)
						System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i)+",h,g,f");
					else
						System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i));
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
					if(this.points.get(i).points==null || recursionNum==nodeVertex.length()-1) { // �ڽĳ�尡 ��������
						if(recursionNum!=nodeVertex.length()-1) {//��� ������ �� ���Ҵ��� Ȯ��
							System.out.println("��� ������ ���� �ʾҽ��ϴ�.Ž���Ϸ���� �ʰ� ����˴ϴ�.");
							answer="��� ������ ���� �ʰ� Ž���� �������ϴ�.";
							return;
						}
						System.out.println("������忩�� �ڵ����� ���õ� ����:"+this.points.get(i).vertex);
						answer+=this.points.get(i).vertex;
						this.h.add(i,this.points.get(i).findRoot());
						this.finalF.add(i,this.h.get(i)+this.g.get(i));
						System.out.println("��������� h��:"+this.h.get(i));
						System.out.println("��������� f��:"+this.finalF.get(i));
						if(this.points.get(i).findRoot()==0) {
							System.out.println("������尡 ���۳��� ������� �ʾ� Ž���� �Ұ����մϴ�");
							answer="�����־� �������� Ž���� �Ұ����մϴ�";
						}
						else {
							System.out.println("������尡 ���۳��� ����Ǿ� ���������� Ž�� �������ϴ�");
							answer+=start;
						}
						return;
					}
					int h1=0;
					for(int j=0;j<this.points.get(i).points.size();j++) { // �ڽ� ����� �ڽ� ��� ����ŭ �ݺ�
						h1+=this.points.get(i).lengths.get(j); // �ڽĳ���� ��� g�� ���ϱ�
					}
					h1+=this.points.get(i).findRoot(); // ��Ʈ ������ ����� ���� �ִ��� Ȯ���ϰ� �� �� ���ϱ�
					this.h.add(i,h1);
					System.out.println(this.points.get(i).vertex+"�� h��:"+this.h.get(i));
					this.finalF.add(i,this.h.get(i)+this.g.get(i));
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

				answer+=this.points.get(minIndex).vertex;
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
					System.out.println("i:"+i);
					System.out.println("recursionNum:"+recursionNum);
					System.out.println("nodeVertex.length()-1:"+(nodeVertex.length()-1));
					if(end.equals(this.points.get(i).vertex) && recursionNum!=nodeVertex.length()-1) {
						System.out.println("continue");
						continue;
					}
					int gg=gLength+this.lengths.get(i);
					this.g.add(i,gg);
					System.out.println(this.points.get(i).vertex+"�� g��:"+this.g.get(i));
					if(this.points.get(i).points==null ||recursionNum==nodeVertex.length()-1) { // �ڽĳ�尡 ��������
						if(recursionNum!=nodeVertex.length()-1  ) {//��� ������ �� ���Ҵ��� Ȯ��
							System.out.println("��� ������ ���� �ʾҽ��ϴ�.Ž���Ϸ���� �ʰ� ����˴ϴ�.");
							answer="��� ������ ���� �ʰ� Ž���� �������ϴ�.";
							return;
						}
						System.out.println("������忩�� �ڵ����� ���õ� ����:"+this.points.get(i).vertex);
						answer+=this.points.get(i).vertex;
						this.h.add(i,0);
						this.finalF.add(i,this.h.get(i)+this.g.get(i));
						System.out.println("��������� h��:"+this.h.get(i));
						System.out.println("��������� f��:"+this.finalF.get(i));
						if(this.points.get(i).vertex.equals(end)==false) {
							System.out.println("������尡 �������� �����ʾ� Ž���� �Ұ����մϴ�");
							answer="�����־� �������� Ž���� �Ұ����մϴ�";
						}
						else
							System.out.println("������尡 �������� ���� ���������� Ž�� �������ϴ�");
						return;
					}
					int h1=0;
					for(int j=0;j<this.points.get(i).points.size();j++) { // �ڽ� ����� �ڽ� ��� ����ŭ �ݺ�
						h1+=this.points.get(i).lengths.get(j); // �ڽĳ���� ��� g�� ���ϱ�
					}
					this.h.add(i,h1);
					System.out.println("i:"+i+" "+this.h.get(i));
					System.out.println(this.points.get(i).vertex+"�� h��:"+this.h.get(i));
					this.finalF.add(i,this.h.get(i)+this.g.get(i));
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
				answer+=this.points.get(minIndex).vertex;
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
			depthLength=new int[nodeVertex.length()];
			p=new Point[1000];
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
			LNode r=new LNode("����","���",10,"3");
			int i=0;
			ArrayList<String[]> h=new ArrayList<>();
			for(;;) {
				h.add("\"����\", \"���ų�\", 12,\"3\"".replaceAll("\"", "").replaceAll(" ", "").split(","));
				//            r.addPoints("����", "���ų�", 12,"3");
				for(String[] j:h) {
					r.addPoints(j[0], j[1], Integer.parseInt(j[2]),j[3]);
				}
				r.addPoints("���", "ȫ���Ա�", 21,"����");
				for(String[] j:h) {
					r.addPoints(j[0], j[1], Integer.parseInt(j[2]),j[3]);
				}
				h.add("\"���\", \"ȫ���Ա�\", 21,\"����\"".replaceAll("\"", "").replaceAll(" ", "").split(","));

				r.addPoints("ȫ���Ա�", "���", 10,"����");
				for(String[] j:h) {
					r.addPoints(j[0], j[1], Integer.parseInt(j[2]),j[3]);
				}
				h.add("\"ȫ���Ա�\", \"���\", 10,\"����\"".replaceAll("\"", "").replaceAll(" ", "").split(","));

				r.addPoints("ȫ���Ա�", "����", 2,"2");
				for(String[] j:h) {
					r.addPoints(j[0], j[1], Integer.parseInt(j[2]),j[3]);
				}
				h.add("\"ȫ���Ա�\", \"����\", 2,\"2\"".replaceAll("\"", "").replaceAll(" ", "").split(","));

				r.addPoints("���", "�ŵ���", 12,"1");
				for(String[] j:h) {
					r.addPoints(j[0], j[1], Integer.parseInt(j[2]),j[3]);
				}
				h.add("\"���\", \"�ŵ���\", 12,\"1\"".replaceAll("\"", "").replaceAll(" ", "").split(","));

				r.addPoints("����", "�ŵ���", 9,"2");
				for(String[] j:h) {
					r.addPoints(j[0], j[1], Integer.parseInt(j[2]),j[3]);
				}
				h.add("\"����\", \"�ŵ���\", 9,\"2\"".replaceAll("\"", "").replaceAll(" ", "").split(","));

				r.addPoints("���ų�", "����", 17,"6");
				for(String[] j:h) {
					r.addPoints(j[0], j[1], Integer.parseInt(j[2]),j[3]);
				}
				h.add("\"���ų�\", \"����\", 17,\"6\"".replaceAll("\"", "").replaceAll(" ", "").split(","));

				r.addPoints("���ų�", "����3��", 24,"3");
				for(String[] j:h) {
					r.addPoints(j[0], j[1], Integer.parseInt(j[2]),j[3]);
				}
				h.add("\"���ų�\", \"����3��\", 24,\"3\"".replaceAll("\"", "").replaceAll(" ", "").split(","));

				r.addPoints("����3��", "����͹̳�", 19,"3");
				for(String[] j:h) {
					r.addPoints(j[0], j[1], Integer.parseInt(j[2]),j[3]);
				}
				h.add("\"����3��\", \"����͹̳�\", 19,\"3\"".replaceAll("\"", "").replaceAll(" ", "").split(","));

				r.addPoints("����3��", "���", 13,"1");
				for(String[] j:h) {
					r.addPoints(j[0], j[1], Integer.parseInt(j[2]),j[3]);
				}
				h.add("\"����3��\", \"���\", 13,\"1\"".replaceAll("\"", "").replaceAll(" ", "").split(","));

				r.addPoints("���", "�ŵ���", 12,"1");
				for(String[] j:h) {
					r.addPoints(j[0], j[1], Integer.parseInt(j[2]),j[3]);
				}
				h.add("\"���\", \"�ŵ���\", 12,\"1\"".replaceAll("\"", "").replaceAll(" ", "").split(","));

				r.addPoints("�ŵ���", "�¼�", 13,"1");
				for(String[] j:h) {
					r.addPoints(j[0], j[1], Integer.parseInt(j[2]),j[3]);
				}
				h.add("\"�ŵ���\", \"�¼�\", 13,\"1\"".replaceAll("\"", "").replaceAll(" ", "").split(","));

				r.addPoints("����͹̳�", "�¼�", 36,"7");
				for(String[] j:h) {
					r.addPoints(j[0], j[1], Integer.parseInt(j[2]),j[3]);
				}
				h.add("\"����͹̳�\", \"�¼�\", 36,\"7\"".replaceAll("\"", "").replaceAll(" ", "").split(","));

				for(String[] j:h) {
					r.addPoints(j[0], j[1], Integer.parseInt(j[2]),j[3]);
				}

				break;
			}

			r.setNumber("����", "3");
			r.setNumber("���", "3,����");
			r.setNumber("���ų�", "3,6");
			r.setNumber("����3��", "3,1,5");
			r.setNumber("����͹̳�", "3,7");
			r.setNumber("ȫ���Ա�", "����,2,����");
			r.setNumber("����", "2,6");
			r.setNumber("���", "����,1");
			r.setNumber("�ŵ���", "2,1");
			r.setNumber("�¼�", "1,7");

			r.printL(0,null,null,0,0);

			printR(0);

			r.printL(0,null,null,0,0);
			
			System.out.println("Min Time"+getMinT());
			System.out.println("Min Cross"+getMinC());

			Gui gui=new Gui();
		}

		public static String getMinT() {
			return resultSet.get(2);
		}
		
		public static String getMinC() {
			return resultSet.get(4);
		}
		
		public static void printR(int weight) {
			for(String s:resultSet) {
				System.out.println(s);
			}
			System.out.println();
		}

	}

	JButton button11=new JButton(),button22=new JButton();
	//JTextField text1,
	public Gui() {
		// TODO Auto-generated constructor stub
		setSize(1000,1000);
		setTitle("a�˰��� gui");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);      
		button11=new JButton("���� A*�˰���");
		button22=new JButton("���� A*�˰���");

		button11.setBounds(300,0,150,30);
		button22.setBounds(500,0,150,30);
		jpn1 jpn1=new jpn1();
		jpn2 jpn2=new jpn2();
		button11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==button11) {
					//jpn2.setVisible(false);
					jpn2.removeAll();
					jpn1.removeAll();
					jpn1.setBounds(10,50,1000,1000);
					jpn1.setGui();
					jpn1.setVisible(true);
					add(jpn1);
					jpn1.revalidate();
					jpn1.repaint();
				}   
			}
		});
		button22.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==button22) {
					jpn1.removeAll();
					jpn2.removeAll();
					jpn2.setVisible(true);
					jpn2.setBounds(10,50,1000,1000);
					jpn2.setGui();
					add(jpn2);
					jpn2.revalidate();
					jpn2.repaint();
				}

			}
		});
		add(button11);
		add(button22);

		setVisible(true);

	}

	class jpn1 extends JPanel {
		JButton b1,b2,button1,button2,button3;
		JLabel label1,label2,label3,label4,label5,label6,label7;
		JTextField text1,text2,text3;
		JLabel[] labelArray;
		JTextField[] textArray;

		public void setGui() {
			setLayout(null);
			m=new Main();
			label1=new JLabel("Input Route");
			text1=new JTextField(7);
			button1=new JButton("����");
			label1.setBounds(10,30,70,70);
			text1.setBounds(85,55,70,20);
			button1.setBounds(160,55,60,20);
			add(label1);
			add(text1);
			add(button1);
			label1.revalidate();
			label1.repaint();
			text1.repaint();
			text1.revalidate();
			button1.revalidate();
			button1.repaint();
			button1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getSource()==button1 || e.getSource()==text1) {
						input=text1.getText().split("-");
						if(input[0].equals("0")) {
							System.out.println(m.getRoot().toString());
							//m.getRoot().print(0);
							label3=new JLabel("������ �Է�");
							text3=new JTextField(10);
							button3=new JButton("����");
							label3.setBounds(10,60,70,70);
							text3.setBounds(85,85,70,20);
							button3.setBounds(160,85,60,20);
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
										label3.setVisible(false);
										text3.setVisible(false);
										button3.setVisible(false);
										label4=new JLabel("������� "+m.getStart());
										label5=new JLabel("�������� "+m.getEnd());
										label4.setBounds(10,60,70,70);
										label5.setBounds(10,85,70,70);
										add(label4);
										add(label5);
										label4.revalidate(); // �̺�Ʈ ������ �ٷ� ������Ʈ�� ���� �ȵ� �� 
										label4.repaint();
										label5.revalidate(); // �̺�Ʈ ������ �ٷ� ������Ʈ�� ���� �ȵ� �� 
										label5.repaint();
										m.setStartPoint(m.getStart());
										if(m.getStart().equals(m.getEnd())) {
											m.getRoot().searchAlgorithm1(0);
											m.getRoot().print(0);
											m.root.printNode(0);
										}
										else {
											m.getRoot().searchAlgorithm2(0);
											m.getRoot().print(0);
											m.root.printNode2(0,1);
										}

										label7=new JLabel(answer);
										label7.setBounds(10,150,250,20);
										add(label7);
										jpn3 jpn3=new jpn3();
										JScrollPane scroll = new JScrollPane(jpn3);
										jpn3.setGui();
										//jpn3.setBounds(250,50,650,650);
										jpn3.setPreferredSize(new Dimension(jpn3.getWidth(),jpn3.getHeight()));
										jpn3.setVisible(true);
										scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
										scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
										scroll.setBounds(250, 50, 700, 700);
										scroll.setViewportView(jpn3);//��ũ�� �� ���� �г��� �ø���.
										add(scroll);
										scroll.repaint();
										scroll.revalidate();
										jpn3.repaint();
										jpn3.revalidate();
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
						labelArray=new JLabel[points.length];
						textArray=new JTextField[points.length];
						ii=0;
						int k=0;
						for(k=0;k<points.length;k++) {
							labelArray[k]=new JLabel("Input "+input[0]+"-"+points[ii]+" Length");
							textArray[k]=new JTextField(7);
							labelArray[k].setBounds(10,30+30*(k+1),120,70);
							textArray[k].setBounds(108,55+30*(k+1),70,20);
							add(labelArray[k]);
							add(textArray[k]);
							labelArray[k].revalidate();
							labelArray[k].repaint();
							textArray[k].repaint();
							textArray[k].revalidate();
							ii++;
						}
						//                  label2=new JLabel("Input "+input[0]+"-"+points[0]+" Length");
						//                  text2=new JTextField(7);
						button2=new JButton("����");
						//                  label2.setBounds(10,60,120,70);
						//                  text2.setBounds(108,85,70,20);
						button2.setBounds(185,55+30*k,60,20);
						add(button2);
						//                  label2.revalidate(); // �̺�Ʈ ������ �ٷ� ������Ʈ�� ���� �ȵ� �� 
						//                  text2.revalidate();
						//                  label2.repaint();
						//                  text2.repaint();
						button2.revalidate();
						button2.repaint();
						//ii=0;
						button2.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								if(e.getSource()==button2 || e.getSource()==text2) {
									//                           lengths[ii]=Integer.parseInt(text2.getText());
									//                           if(ii<points.length-1) {
									//                              label2.setText("Input "+input[0]+"-"+points[ii+1]+" Length");
									//                              text2.setText("");
									//                              ii++;
									//                           }
									for(int i=0;i<points.length;i++) {
										lengths[i]=Integer.parseInt(textArray[i].getText());
									}
									m.histroy(points,lengths);
									ii=0;
									for(int i=0;i<points.length;i++) {
										labelArray[i].setVisible(false);
										textArray[i].setVisible(false);
									}
									button2.setVisible(false);
									text1.setText("");
									//                           else {
									//                              m.histroy(points,lengths);
									//                              ii=0;
									//                              label2.setVisible(false);
									//                              text2.setVisible(false);
									//                              button2.setVisible(false);
									//                              text1.setText("");
									//                           }
								}
							}
						});

					}
				}
			});
		}

	}
	class jpn2 extends JPanel {
		JLabel label1;
		JButton b1,imageButton,button1,button2,button3,button4;
		JTextField text1;
		private ImageIcon image;

		public void setGui() {
			setLayout(null);	
			b1=new JButton("����");
			b1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getSource()==b1) {
						image=new ImageIcon(Main.class.getResource("image/�뼱��1_"+imageNum+".png"));
						imageNum++;
						imageButton=new JButton(image);
						imageButton.setBounds(120,50,750,653);
						imageButton.setBorderPainted(false);
						imageButton.setContentAreaFilled(false);
						imageButton.setFocusPainted(false);
						add(imageButton);
						imageButton.revalidate();
						imageButton.repaint();
						label1=new JLabel("ȯ�� �ð� �Է�");
						text1=new JTextField(7);
						button1=new JButton("����");
						button1.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								if(e.getSource()==button1 || e.getSource()==text1) {
									weight=Integer.parseInt(text1.getText());
									//                           LNode r=new LNode("����","���",10,"3");
									//                           r.printL(0,null,null,0,0);
								}
							}
						});
						label1.setBounds(10,705,90,70);
						text1.setBounds(120,730,70,20);
						button1.setBounds(200,730,60,20);
						add(label1);
						add(text1);
						add(button1);
						label1.revalidate();
						label1.repaint();
						text1.repaint();
						text1.revalidate();
						button1.revalidate();
						button1.repaint();
						button3=new JButton("�ּ� �ð� Ž��");

						button4=new JButton("�ּ� ȯ�� Ž��");
						button3.setBounds(10,780,130,20);
						button4.setBounds(10,830,130,20);                  
						add(button3);
						add(button4);
						button3.revalidate();
						button3.repaint();
						button4.revalidate();
						button4.repaint();
					}
				}
			});
			b1.setBounds(10,50,60,20);
			add(b1);
			b1.revalidate();
			b1.repaint();
		}

	}
	class jpn3 extends JPanel {
		public void setGui() {
			setSize(5000,5000);
			setLayout(null);
			if(m.start.equals(m.end))
				m.getRoot().printNodeGui(0, this,76,30,true);
			else
				m.getRoot().printNodeGui2(0,1,this,76,30,true);
			System.out.println("width:"+this.getWidth()+" height:"+this.getHeight());
		}
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			for(Point p:p) {
				if(p!=null)
					g.drawLine(p.x1, p.y1, p.x2, p.y2);
			}
		}
		public void addSize() {
			this.setSize(this.getWidth(),this.getHeight());
		}
	}
}
class Point {
	int x1,y1,x2,y2;
}
