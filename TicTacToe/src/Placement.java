import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Placement {
	private int type;
	private int x,y;
	
	private BufferedImage image;
	public Placement(int x,int y, int type)
	{
		this.x=x;
		this.y=y;
		this.type=type%2;
		String imageType= type%2==0? "x": "o";
		try {
			image=ImageIO.read(getClass().getResource(imageType+"1.png"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public Placement(int type)
	{
		this.type=type%2;
	}
	
	
	public void render(Graphics g)
	{
		g.drawImage(image,x*Main.WIDTH/Main.ROWS+5,y*Main.WIDTH/Main.ROWS+5,Main.WIDTH/Main.ROWS-10,Main.WIDTH/Main.ROWS-10,null);
					
		
	}
	public void setX(int x)
	{
		this.x=x;
	}
	public void setY(int y)
	{
		this.y=y;
	}
	public void setType(int type)
	{
		this.type=type;
	}
	
	public int getType()
	{
		return type;
	}
	
	
	

}
