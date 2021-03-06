import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static boolean f=true;
	static ArrayList<String> confirm;
	static ArrayList<String> vHistory=new ArrayList<String>();
	static ArrayList<Integer> lHistory=new ArrayList<Integer>();

	static class Node{

		private String vertex;                  //현재 정점
		private ArrayList<String> vertexs=null;
		private ArrayList<Node> points=null;       //연결 정점
		private ArrayList<Integer> lengths=null;   //연결 길이
		//정점-길이 합쳐서 경로 Route

		//생성자
		public Node(String vertex,String point,int length) {
			//현재 정점 설정
			this.vertex=vertex;   
			//연결 경로 설정
			if(point!=null) this.add(point,length);   
			//point가 null이면 말단 경로
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
			System.out.println("V "+this.vertex+", add "+points+", Vs "+this.vertexs.toString());
			//정점 저장-연결 정점 null로 설정해 말단 경로 생성
			this.points.add(new Node(points,null,length));
			//길이 저장
			this.lengths.add(length);
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
	}
	public static void main(String[] args) {

		Scanner s=new Scanner(System.in);
		Node root = null;
		while(true) {
			System.out.print("Input Route:");
			String[] input=s.next().split("-");
			if(input[0].equals("0")) break;
			//confirm.add(input[0]);
			String[] points=input[1].split("");
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
					
					for(int h=0;h<vHistory.size();h++) {
						confirm=new ArrayList<String>();
						confirm.add(points[i]);
						System.out.println("add "+vHistory.get(h));
						String[] his=vHistory.get(h).split("-");
						if(confirm.contains(his[1])) continue;
						confirm.add(his[0]);
						root.addPoints(his[0], his[1], lHistory.get(h));
					}
				}
			}
			f=false;
		}
		System.out.println(root.toString());
		//      for(Node n:root.points) {
		//    	  System.out.println(root.vertex+"-"+n.vertex);
		//      }
		for(int i=0;i<root.getSize();i++) {
			System.out.println(root.vertex+"-"+root.points.get(i).vertex+" : "+root.lengths.get(i));
			for(int j=0;j<root.points.get(i).getSize();j++) {
				System.out.println("	"+root.points.get(i).vertex+"-"+root.points.get(i).points.get(j).vertex+" : "+root.points.get(i).lengths.get(j));
				for(int k=0;k<root.points.get(i).points.get(j).getSize();k++)
					System.out.println("		"+root.points.get(i).points.get(j).vertex+"-"+root.points.get(i).points.get(j).points.get(k).vertex+" : "+root.points.get(i).points.get(j).lengths.get(k));
			}
		}
		System.out.println(vHistory.toString()+" , "+lHistory.toString());
		System.out.println(root.vertexs.toString());
		for(Node n:root.points) System.out.println(n.vertexs.toString());
	}
}