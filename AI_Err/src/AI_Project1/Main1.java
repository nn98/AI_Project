package AI_Project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main1 {

   static class Node{

      private String vertex;                  //현재 정점
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
         //경로 미존시 경로 초기화
         if(this.isEmpty()) this.setRoute();
         //정점 저장-연결 정점 null로 설정해 말단 경로 생성
         this.points.add(new Node(points,null,length));
         //길이 저장
         this.lengths.add(length);
      }

      //경로 연결 메소드
      public void addPoints(String vertex,String point,int length) {
         //현재 정점이면 추가
         if(this.vertex.equals(vertex)) this.add(point,length);
         //없으면 연결된 정점들 탐색
         else if(!this.isEmpty()) for(Node i:this.points) i.addPoints(vertex, point,length);               
         //존재하지 않는 정점이면 생략
      }

      //경로 존재 확인 메소드
      public boolean isEmpty() {
         return this.points==null&&this.lengths==null;
      }

      //경로 초기화 메소드
      public void setRoute() {
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
         System.out.print("Input Length:");
         int length=s.nextInt();
         if(root==null) root=new Node(input[0],input[1],length);
         else root.addPoints(input[0],input[1],length);
      }
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
//System.out.println("Node create : "+vertex);
/*
      //System.out.println("toString call at "+vertex);
      //System.out.println(root.points.get(1).points.get(0).vertex);
      //private Node[] points;   
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
*/