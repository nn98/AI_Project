package AI_Project1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {

   static boolean f=true;
   static ArrayList<String> confirm;
   static ArrayList<String> vHistory=new ArrayList<String>();
   static ArrayList<Integer> lHistory=new ArrayList<Integer>();

   static class Node{

      private String vertex;                  //���� ����
      private ArrayList<String> vertexs=null;
      private ArrayList<Node> points=null;       //���� ����
      private ArrayList<Integer> lengths=null;   //���� ����
      //����-���� ���ļ� ��� Route

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
               System.out.printf("���� ����-%s, ��� ���� %s confirm %s �� ����. \n",this.vertex, points,confirm.toString());
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
   }
   public static void main(String[] args) {

      Scanner s=new Scanner(System.in);
      Node root = null;
      while(true) {
         System.out.print("Input Route:");
         String[] input=s.next().split("-");
         if(input[0].equals("0")) break;
         String[] points=input[1].split("");
         int[] lengths=new int[points.length];
         for(int i=0;i<points.length;i++) {
            System.out.print("Input "+input[0]+"-"+points[i]+" Length:");
            lengths[i]=s.nextInt();
         }
         for(int i=0;i<points.length;i++) {
            if(root==null) {
                confirm=new ArrayList<String>();
                confirm.add(input[0]);
               root=new Node(input[0],points[i],lengths[i]);
            }
            else {
               if(!input[0].equals("a")) {
                  vHistory.add(""+input[0]+"-"+points[i]);
                  lHistory.add(lengths[i]);
               }
               confirm=new ArrayList<String>();
               confirm.add(input[0]);
               root.addPoints(input[0],points[i],lengths[i]);
               if(!f) {
                  vHistory.add(""+points[i]+"-"+input[0]);
                  lHistory.add(lengths[i]);
                  confirm=new ArrayList<String>();
                  confirm.add(points[i]);
                  root.addPoints(points[i],input[0],lengths[i]);
               }
            }
         }
         for(int i=0;i<points.length;i++) {
            if(!f) {
                 for(int h=vHistory.size()-1;h>=0;h--) {
                    confirm=new ArrayList<String>();
                    confirm.add(points[i]);
                    String[] his=vHistory.get(h).split("-");
                    if(confirm.contains(his[1])) continue;
                    confirm.add(his[0]);
                    root.addPoints(his[0], his[1], lHistory.get(h));
                 }
              }
            if(!f) {
                 for(int h=vHistory.size()-1;h>=0;h--) {
                    confirm=new ArrayList<String>();
                    confirm.add(points[i]);
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
      root.print(0);
//      root.points.get(1).points.get(1).points.get(0).add("e", 7);
//      System.out.println(root.points.get(1).points.get(1).points.get(0).points.get(0).toString());
      
      /*
      a-bcde
      1
      2
      3
      4
      b-cde
      5
      6
      7
      c-de
      8
      9
      d-e
      10
      
       */
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
//      System.out.printf("���ο��� �߰� : %s - %s\n",input[0],points[i]);
//      System.out.printf("   ���ο��� ���߰� : %s - %s\n------------------\n",points[i],input[0]);
//      System.out.println("�����丮 Ž�� : "+vHistory.get(h));
//      System.out.println("      confirm�� "+his[1]+" ����, ���߰�.");
   }
}