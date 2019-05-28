package AI_Project1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main3 {
	static boolean f=true;
	static ArrayList<String> confirm;
	static ArrayList<String> vHistory=new ArrayList<String>();
	static ArrayList<Integer> lHistory=new ArrayList<Integer>();
	static String start,end; // ���۳��� ������� ���� ����
	static Node root; // ��Ʈ ��� ����
	static int recursionNum=0; // searchAlgorithm ��� Ƚ��
	static String nodeVertex=""; // ��� �������� ���ڿ��� ����

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

		//��� �߰� �޼ҵ�
		private void add(String points,int length) {
			if(!f) {
				if(confirm.contains(points)) {
//					System.out.printf("point %s is exist at %s\n", points,confirm.toString());
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
		public void print(int depth) {
			for(int i=0;i<this.getSize();i++) {
				for(int j=0;j<depth;j++) System.out.print("\t");
				System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i));
				points.get(i).print(depth+1);
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

		public int findRoot() { // ���������� ��Ʈ���� ����� g�� ã��
			for(int i=0;i<root.points.size();i++) {
				if(this.vertex.equals(root.points.get(i).vertex)) {
					System.out.println(this.vertex+"->"+root.vertex+"�� g��:"+root.lengths.get(i));
					return root.lengths.get(i);
				}
			}
			return 0; // ��Ʈ���� ���� ������ ����� ���� ����
		}
		public void searchAlgorithm2(int gLength) { // ���۳��� ������尡 �ٸ� �˰��� Ž��
			recursionNum++;
			for(int i=0;i<this.points.size();i++) { // �ڽ� ��� ����ŭ �ݺ�
				if(end.equals(this.points.get(i).vertex) && recursionNum!=nodeVertex.length()-1) {
					System.out.println("end�� ���� ����");
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
	public static void main(String[] args) {

		Scanner s=new Scanner(System.in);
		root = null;
		while(true) {
			System.out.print("Input Route:");
			String[] input=s.next().split("-");
			if(input[0].equals("0")) break;
			//confirm.add(input[0]);
			String[] points=input[1].split("");
			findNodeNum(input[0],points);
			int[] lengths=new int[points.length];
			for(int i=0;i<points.length;i++) {
				System.out.print("Input "+input[0]+"-"+points[i]+" Length:");
				lengths[i]=s.nextInt();
			}
			for(int i=0;i<points.length;i++) {
				if(root==null) root=new Node(input[0],points[i],lengths[i]);
				else {
					if(!input[0].equals("a")) {
						vHistory.add(""+input[0]+"-"+points[i]);
						lHistory.add(lengths[i]);
					}
//					System.out.printf("vertex %s - add %s\n",input[0],points[i]);
					confirm=new ArrayList<String>();
					confirm.add(input[0]);
					root.addPoints(input[0],points[i],lengths[i]);
					if(!f) {
						vHistory.add(""+points[i]+"-"+input[0]);
						lHistory.add(lengths[i]);
//						System.out.printf("vertex %s - add %s\n------------------\n",points[i],input[0]);
						confirm=new ArrayList<String>();
						confirm.add(points[i]);
						root.addPoints(points[i],input[0],lengths[i]);
					}
				}
				if(!f) {
					for(int h=vHistory.size()-1;h>=0;h--) {
						confirm=new ArrayList<String>();
						confirm.add(points[i]);
//						System.out.println("add "+vHistory.get(h));
						String[] his=vHistory.get(h).split("-");
						if(confirm.contains(his[1])) continue;
						confirm.add(his[0]);
//						System.out.println();
						root.addPoints(his[0], his[1], lHistory.get(h));
					}
					for(int h=vHistory.size()-1;h>=0;h--) {
						confirm=new ArrayList<String>();
						confirm.add(points[i]);
//						System.out.println("add "+vHistory.get(h));
						String[] his=vHistory.get(h).split("-");
						if(confirm.contains(his[1])) continue;
						confirm.add(his[0]);
//						System.out.println();
						root.addPoints(his[0], his[1], lHistory.get(h));
					}
				}
			}
			f=false;
		}
		System.out.println(root.toString());
		root.print(0);
		//      for(Node n:root.points) {
		//         System.out.println(root.vertex+"-"+n.vertex);
		//      }
		//      for(int i=0;i<root.getSize();i++) {
		//         System.out.println(root.vertex+"-"+root.points.get(i).vertex+" : "+root.lengths.get(i));
		//         for(int j=0;j<root.points.get(i).getSize();j++) {
		//            System.out.println("   "+root.points.get(i).vertex+"-"+root.points.get(i).points.get(j).vertex+" : "+root.points.get(i).lengths.get(j));
		//            for(int k=0;k<root.points.get(i).points.get(j).getSize();k++) {
		//               System.out.println("      "+root.points.get(i).points.get(j).vertex+"-"+root.points.get(i).points.get(j).points.get(k).vertex+" : "+root.points.get(i).points.get(j).lengths.get(k));
		//               for(int l=0;l<root.points.get(i).points.get(j).points.get(l).getSize();l++)
		//                  System.out.println("         "+root.points.get(i).points.get(j).points.get(k).vertex+"-"+root.points.get(i).points.get(j).points.get(k).points.get(l).vertex+" : "+root.points.get(i).points.get(j).points.get(k).lengths.get(l));
		//            }
		//         }
		//      }
		//      System.out.println(vHistory.toString()+" , "+lHistory.toString());
		//      System.out.println(root.vertexs.toString());
		//      for(Node n:root.points) System.out.println(n.vertexs.toString());
		System.out.print("������ �Է�:");
		end=s.next();
		start=root.vertex;
		System.out.println("������� "+start);
		System.out.println("�������� "+end);
		if(start.equals(end)) root.searchAlgorithm1(0); // ���۳��� ������尡 ������
		else root.searchAlgorithm2(0); // ���۳��� ������尡 �ٸ���
		System.out.println(nodeVertex);
	}
}