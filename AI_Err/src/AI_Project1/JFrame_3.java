package AI_Project1;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//4. ��ư, �ؽ�Ʈ�ʵ�
public class JFrame_3 extends JFrame_1 {
	JPanel jp = new JPanel(); // �г� �ʱ�ȭ
	JButton jb = new JButton("��ư"); // ��ư �ʱ�ȭ
	JLabel jl = new JLabel("���̺�"); // ���̺� �ʱ�ȭ
	JTextField tf = new JTextField(10); // �ؽ�Ʈ�ʵ� �ʱ�ȭ
	public JFrame_3() {
		super("Frame_3");
		jp.add(jb);
		jp.add(jl);
		jp.add(tf);
		add(jp);
		
		setSize(400,300);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
