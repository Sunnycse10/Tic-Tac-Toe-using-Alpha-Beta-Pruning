import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener{
	//protected Graphics graphicsRender;
	private Timer timer;
	private int delay=50;
	public static int type=0;
	private static int placementCount=0;
	private boolean gameOver=false;
	public Placement[][] grid= new Placement[Main.ROWS][Main.ROWS];
	public int[][] array= new int[Main.ROWS][Main.ROWS];
	public int mx,my;
	public static String s;
	JButton play,help,quit;
	JLabel label1;
	MiniMax AI;
	public static enum State
	{
		Menu,
		Play,
		Quit
	}
	public static State state= State.Menu;
	public GamePanel(Color color)
	{
		this.setBackground(color);
		addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		setPreferredSize(new Dimension(Main.WIDTH,Main.HEIGHT));
		setFocusable(true);
		requestFocus();
		if(state==State.Menu)
		{
			this.setLayout(null);
			
			label1=new JLabel();
			label1.setBounds(100, 50, 500, 100);
			label1.setFont(new Font("Dialog",Font.BOLD,20));
			label1.setForeground(Color.WHITE);
			label1.setText("WELCOME TO TIC-TAC-TOE");
			
			
			play= new JButton();
			play.setBounds(Main.WIDTH/Main.ROWS+25,Main.WIDTH/Main.ROWS, 100, 50);
			play.setFont(new Font("Dialog",Font.BOLD,20));
			play.setText("PLAY");
			play.setFocusable(false);
			play.setBackground(Color.BLUE);
			play.setForeground(Color.WHITE);
			play.addActionListener(this);
			
			quit= new JButton();
			quit.setBounds(Main.WIDTH/Main.ROWS+25,Main.WIDTH/Main.ROWS+100, 100, 50);
			quit.setFont(new Font("Dialog",Font.BOLD,20));
			quit.setText("QUIT");
			quit.setFocusable(false);
			quit.setBackground(Color.BLUE);
			quit.setForeground(Color.WHITE);
			quit.addActionListener(this);
			
			this.add(label1);
			this.add(quit);
			this.add(play);
			
		}
		AI= new MiniMax();
		reset();
		resetHover();
		runGame();
	}
	
	private void resetHover() {
		// TODO Auto-generated method stub
		for(int i=0;i<Main.ROWS;i++)
		{
			for(int j=0;j<Main.ROWS;j++)
			{
				array[i][j]=0;
			}
		}
		
	}

	private void reset() {
		for(int i=0;i<Main.ROWS;i++)
		{
			for(int j=0;j<Main.ROWS;j++)
			{
				grid[i][j]=null;
			}
		}
		
	}

	public void runGame()
	{
		timer=new Timer(delay, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(state==State.Play)
		{
		
		for(int i=0;i<Main.ROWS;i++)
		{
			for(int j=0;j<Main.ROWS;j++)
			{
				g.setColor(Color.WHITE);
				g.drawRect(i*Main.WIDTH/Main.ROWS, j*Main.HEIGHT/Main.ROWS ,Main.WIDTH/Main.ROWS ,Main.HEIGHT/Main.ROWS );
				if(grid[i][j]==null)
				{
					continue;
				}
				else
				{
					grid[i][j].render(g);
				}
			}
		}
		if(mx>=0 && mx<Main.ROWS && my>=0 && my<Main.ROWS && array[mx][my]==1 &&  grid[mx][my]==null)
		{
			HoverEffect hover= new HoverEffect(mx,my);
			hover.render(g);
		}
		if(gameOver==true)
		{
			
			g.setColor(new Color(0,255,200,150));
			g.setFont(new Font("Arial", Font.BOLD, 50));
			//g.drawString("Match Over", 100, 200);
			if(s.equals("Tie"))
			{
				g.drawString("Match Draw!", 100, 200);
			}
			else
				g.drawString(s.equals("0")? "YOU WON!" : "Computer WON!" , 10, 200);
			g.setFont(new Font("Arial", Font.BOLD, 35));
			g.drawString("Press Enter to Restart", 20, 250);
		}
		}
		
		
	}

	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(gameOver==false && state==State.Play)
		{
		Checker check= new Checker();
		ArrayList<Placement> placement = check.checkWin(grid);
		if(placement!=null)
		{
			int type=placement.get(0).getType();
			s=Integer.toString(type);
			System.out.println(type+" won!" );
			gameOver=true;
		}
		else if(placementCount>=Main.SIZE)
		{
			s="Tie";
			System.out.println("Tie!");
			gameOver=true;
		}
		}
		if(e.getSource()==play)
		{
			state=State.Play;
			label1.setVisible(false);
			play.setVisible(false);
			quit.setVisible(false);
		}
		if(e.getSource()==quit)
		{
			System.exit(1);
		}
		
		Toolkit.getDefaultToolkit().sync();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(gameOver==false)
		{
		int x=e.getX(),y=e.getY();
		int mx= x/(Main.WIDTH/Main.ROWS);
		int my=y/(Main.HEIGHT/Main.ROWS);
		if(type%2==0 && grid[mx][my]==null)
		{
			grid[mx][my]=new Placement(mx,my,type);
			//System.out.println(mx+" "+my);
			placementCount++;
			type++;
		}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(gameOver==false && type%2!=0)
		{
			int player=type%2;
			int bestMove= AI.getBestMove(grid, player);
			int x=bestMove% Main.ROWS;
			int y=bestMove/ Main.ROWS;
			grid[x][y]=new Placement(x,y,player);
			type++;
			placementCount++;
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x=e.getX();
		int y=e.getY();
		mx=x/(Main.WIDTH/Main.ROWS);
		my=y/(Main.WIDTH/Main.ROWS);
		if(mx>=0 && mx<Main.ROWS && my>=0 && my<Main.ROWS && grid[mx][my]==null)
		{
			array[mx][my]=1;
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(gameOver==true && e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			//gameOver=false;
			//new GamePanel(Color.BLACK);
			//timer.restart();
			Placement[][] grid= new Placement[Main.ROWS][Main.ROWS];
			type=0;
			placementCount=0;
			gameOver=false;
			array= new int[Main.ROWS][Main.ROWS];
			mx=0;
			my=0;
			AI= new MiniMax();
			reset();
			resetHover();
			runGame();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
