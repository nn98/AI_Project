package AI_Project1;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//4. 버튼, 텍스트필드
public class JFrame_3 extends JFrame_1 {
	JPanel jp = new JPanel(); // 패널 초기화
	JButton jb = new JButton("버튼"); // 버튼 초기화
	JLabel jl = new JLabel("레이블"); // 레이블 초기화
	JTextField tf = new JTextField(10); // 텍스트필드 초기화
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
