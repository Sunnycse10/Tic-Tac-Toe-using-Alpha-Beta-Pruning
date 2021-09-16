import java.awt.Color;

import javax.swing.JFrame;

public class Main {
	public static int WIDTH=500;
	public static int HEIGHT=500;
	public static int ROWS=3;
	public static int MATCH=3;
	public static int SIZE=ROWS*ROWS;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame= new JFrame();
		GamePanel game= new GamePanel(Color.BLACK);
		frame.add(game);
		//frame.addMouseMotionListener(game);
		//frame.addMouseListener(game);
		frame.setTitle("Tic-Tac-Toe");
		frame.setSize(WIDTH, HEIGHT+30);
		frame.setVisible(true);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
