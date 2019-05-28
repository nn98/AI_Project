package AI_Project1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main3 {
	static boolean f=true;
	static ArrayList<String> confirm;
	static ArrayList<String> vHistory=new ArrayList<String>();
	static ArrayList<Integer> lHistory=new ArrayList<Integer>();
	static String start,end; // 시작노드와 도착노드 각각 저장
	static Node root; // 루트 노드 저장
	static int recursionNum=0; // searchAlgorithm 재귀 횟수
	static String nodeVertex=""; // 모든 정점들을 문자열로 저장

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

		//경로 추가 메소드
		private void add(String points,int length) {
			if(!f) {
				if(confirm.contains(points)) {
//					System.out.printf("point %s is exist at %s\n", points,confirm.toString());
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
		public void print(int depth) {
			for(int i=0;i<this.getSize();i++) {
				for(int j=0;j<depth;j++) System.out.print("\t");
				System.out.println(this.vertex+"-"+points.get(i).vertex+" : "+this.lengths.get(i));
				points.get(i).print(depth+1);
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

		public int findRoot() { // 현재정점과 루트노드와 연결된 g값 찾기
			for(int i=0;i<root.points.size();i++) {
				if(this.vertex.equals(root.points.get(i).vertex)) {
					System.out.println(this.vertex+"->"+root.vertex+"의 g값:"+root.lengths.get(i));
					return root.lengths.get(i);
				}
			}
			return 0; // 루트노드와 현재 정점과 연결된 것이 없음
		}
		public void searchAlgorithm2(int gLength) { // 시작노드와 도착노드가 다른 알고리즘 탐색
			recursionNum++;
			for(int i=0;i<this.points.size();i++) { // 자식 노드 수만큼 반복
				if(end.equals(this.points.get(i).vertex) && recursionNum!=nodeVertex.length()-1) {
					System.out.println("end와 값이 같음");
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
		System.out.print("도착지 입력:");
		end=s.next();
		start=root.vertex;
		System.out.println("출발지는 "+start);
		System.out.println("도착지는 "+end);
		if(start.equals(end)) root.searchAlgorithm1(0); // 시작노드와 도착노드가 같을때
		else root.searchAlgorithm2(0); // 시작노드와 도착노드가 다를때
		System.out.println(nodeVertex);
	}
}