package AI_Project1;
import java.util.Arrays;
import java.util.Scanner;

class Node {
   String value;
   Node[] child;
   public Node(String a) {
      this.value=a;
   }
   public Node(String a,String b) {
      this.value=a;
      child=new Node[b.length()];
   }

   public void add(String b) {
      this.child=new Node[b.length()];
      String[] array=b.split("");
      for(int i=0;i<array.length;i++) {
         child[i]=new Node(array[i]);
      }
   }
   public Node find(String a) {
      if(this.value.equals(a)) 
         return this;
      for(int i=0;i<this.child.length;i++) {
         return this.child[i].find(a);
      }
      return null;
   }
   public void search() {
      System.out.println(this.value);
      if(this.child.length!=0) {
         for(int i=0;i<child.length;i++) {
            this.child[i].search();
            System.out.println(this.value);
         }
      }
   }
   public void printChild() {
	   
	   System.out.println(Arrays.toString(child));
	   
   }
   public void printAll() {
	   printChild();
	   for(int i=0;i<child.length;i++) child[i].printChild();
   }
}
public class Main0 {
   static Node root;
   public static void main(String[] args) {
      Scanner input=new Scanner(System.in);
      String s=input.next();	
      String[] array=s.split("-");
      root=new Node(array[0],array[1]);
      root.add(array[1]);

      while(true) {
         s=input.next();
         if(s.equals("0")) // 종료조건
            break;
         array=s.split("-");
         if(root.find(array[0]) != null) {
            System.out.println(array[0]+"은 null 아님");
            root.find(array[0]).add(array[1]);
         }
         //else
      }
      
      //root.print();
      
      //System.out.println(root.child[1].child[0].value);
      root.printAll();
      root.search();
   }
}