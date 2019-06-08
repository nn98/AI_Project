package AI_Project1;

import java.awt.FlowLayout;

import javax.swing.JButton;

// 3. 프레임 객체 상속, 버튼 추가
public class JFrame_2 extends JFrame_1 {
	public JFrame_2() {
		super();
		setLayout(new FlowLayout());
		JButton b=new JButton("Btn");
		this.add(b);
	}
}
