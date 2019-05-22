
public class NodeTest {

	static class Node{
		String[] value;
		int[] line;
		int i=0;
		Node link;
		public Node(String value) {
			this.value=value.split("");
			line=new int[(value.length()-1)*2];
		}
		public void setLine(int value) {
			line[i++]=value;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node n=new Node("ABCD");
	}

}
