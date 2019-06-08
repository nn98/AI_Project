package AI_Project1;

import javax.swing.JFrame;

// 2. 프레임 객체화
public class JFrame_1 extends JFrame {
	public JFrame_1() {
		setTitle("Title");
		setSize(300,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	// 실행 객체화
	public void run() {
		setVisible(true);
	}
}
