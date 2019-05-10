package AI_Project1;

import java.util.Arrays;
import java.util.Scanner;

public class Main1 {

	static class Node{

		private String vertex;	//정점
		private Node[] points;	//연결된 정점들

		//생성자
		public Node(String vertex,String points) {
			//정점 설정
			this.vertex=vertex;			
			//연결된 정점 설정
			if(points!=null) this.add(points);	
		}
		
		//정점 추가 메소드
		private void add(String points) {
			//나눠서 저장
			String[] p=points.split("");	
			this.points=new Node[p.length];
			//저장할때 연결된 정점 null로
			for(int i=0;i<p.length;i++) this.points[i]=new Node(p[i],null);
		}

		//간선 연결
		public void addPoints(String vertex,String points) {
			//찾으면 추가.
			if(this.vertex.equals(vertex)) this.add(points);
			//없으면 연결된 정점들 탐색
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