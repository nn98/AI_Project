package AI_Project1;

import java.util.Arrays;
import java.util.Scanner;

public class Main1 {

	static class Node{

		private String vertex;	//����
		private Node[] points;	//����� ������

		//������
		public Node(String vertex,String points) {
			//���� ����
			this.vertex=vertex;			
			//����� ���� ����
			if(points!=null) this.add(points);	
		}
		
		//���� �߰� �޼ҵ�
		private void add(String points) {
			//������ ����
			String[] p=points.split("");	
			this.points=new Node[p.length];
			//�����Ҷ� ����� ���� null��
			for(int i=0;i<p.length;i++) this.points[i]=new Node(p[i],null);
		}

		//���� ����
		public void addPoints(String vertex,String points) {
			//ã���� �߰�.
			if(this.vertex.equals(vertex)) this.add(points);
			//������ ����� ������ Ž��
			else if(!this.isEmpty()) for(Node i:this.points) i.addPoints(vertex, points);					
		}

		public boolean isEmpty() {
			return this.points==null;
		}

		@Override 
		public String toString() {
			return "|Vertex: "+vertex+" - Points: "+Arrays.toString(points)+"|";
		}
	}
	public static void main(String[] args) {
		
		Scanner s=new Scanner(System.in);
		Node root = null;
		while(true) {
			String[] input=s.next().split("-");
			if(input[0].equals("0")) break;
			if(root==null) root=new Node(input[0],input[1]);
			else root.addPoints(input[0],input[1]);
		}
		System.out.println(root.points[1].points[0].vertex);
		System.out.println(root.toString());
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