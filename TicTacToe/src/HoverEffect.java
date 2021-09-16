import java.awt.Color;
import java.awt.Graphics;

public class HoverEffect {
	private int x;
	private int y;
	public HoverEffect(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	public void render(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(x*Main.WIDTH/Main.ROWS, y*Main.WIDTH/Main.ROWS, Main.WIDTH/Main.ROWS,Main.WIDTH/Main.ROWS);
	}

}
