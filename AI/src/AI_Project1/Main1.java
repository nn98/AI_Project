package AI_Project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main1 {

	static class Node{

		private String vertex;	//����
		private ArrayList<Node> points=null; //����� ������
		private ArrayList<Integer> lengths=null;
		//private Node[] points;	

		//������
		public Node(String vertex,String point,int length) {
			//���� ����
			this.vertex=vertex;	
			//����� ���� ����
			if(point!=null) this.add(point,length);	
		}

		//���� �߰� �޼ҵ�
		private void add(String points,int length) {
			//������ ����
			if(this.isEmpty()) this.setRoute();
			//�����Ҷ� ����� ���� null��
			this.points.add(new Node(points,null,length));
			this.lengths.add(length);
		}

		//���� ����
		public void addPoints(String vertex,String point,int length) {
			//ã���� �߰�.
			if(this.vertex.equals(vertex)) this.add(point,length);
			//������ ����� ������ Ž��
			else if(!this.isEmpty()) for(Node i:this.points) i.addPoints(vertex, point,length);					
		}

		public boolean isEmpty() {
			return this.points==null&&this.lengths==null;
		}

		public void setRoute() {
			this.points=new ArrayList<Node>();
			this.lengths=new ArrayList<Integer>();
		}

		public String getRoute() {
			String result="";
			for(int i=0;i<this.points.size();i++) result+=this.points.get(i).toString()+" - "+this.lengths.get(i)+" ";
			return result;
		}
		
		public String getLengths() {
			if(isEmpty()) return "Empty";
			else return this.lengths.toString();
		}
		
		public String getPoints() {
			if(isEmpty()) return "Empty";
			else return this.points.toString();
		}

		@Override 
		public String toString() {
			//System.out.println("toString call at "+vertex);
			if(this.isEmpty()) return "[To "+this.vertex+"]";
			else return "| Vertex "+this.vertex+" - "+this.getRoute()+"|";
			
		}
	}
	public static void main(String[] args) {

		Scanner s=new Scanner(System.in);
		Node root = null;
		while(true) {
			System.out.print("Input Route:");
			String[] input=s.next().split("-");
			if(input[0].equals("0")) break;
			System.out.print("Input Length:");
			int length=s.nextInt();
			if(root==null) root=new Node(input[0],input[1],length);
			else root.addPoints(input[0],input[1],length);
		}
		System.out.println(root.toString());
		//System.out.println(root.points.get(1).points.get(0).vertex);
	}
}
//Test
//System.out.println(points);
//System.out.println(Arrays.toString(p));
//Test
//String[] arr;
//System.out.println("1: "+"".length()+"2: "+Arrays.toString("".split("")));
//Node root=new Node("a","bcd");
//root.addPoints("b", "cd");
//root.addPoints("c", "d");
//root.addPoints("e", "d");
//System.out.println("Node create : "+vertex);
/*
a-b
1
a-c
2
a-d
3
b-c
4
b-d
5
c-d
6
0
0


*/
