package AI_Project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//5. GUI ���� ����.
public class TargetFrame extends JFrame {
	JPanel jp = new JPanel(); // �г� �ʱ�ȭ
	JButton jb = new JButton("��ư"); // ��ư �ʱ�ȭ
	
	JLabel jl = new JLabel("���̺�"); // ���̺� �ʱ�ȭ
	JTextField tf = new JTextField(10); // �ؽ�Ʈ�ʵ� �ʱ�ȭ
	JTextField[] tfs=new JTextField[10];
	JTextArea ta = new JTextArea(); // �ؽ�Ʈ�ʵ� �ʱ�ȭ
	public TargetFrame() {
		super("Frame_3");
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] vertexs=tf.getText().split("\n");
				ta.setText(tf.getText());
				jp.add(new JButton("add"));
//				removeAll();
//				setVisible(false);
//				setVisible(true);
			}
		});
		jp.add(jb);
		jp.add(jl);
		jp.add(tf);
		jp.add(ta);
		add(jp);
		
		setSize(400,300);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
