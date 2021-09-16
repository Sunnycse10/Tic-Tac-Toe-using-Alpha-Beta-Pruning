import java.util.ArrayList;

public class Checker {
	public static ArrayList<Placement> checkWin(Placement [][]grid)
	{
		ArrayList<Placement> match = null;
		for(int i=0;i<Main.SIZE;i++)
		{
			int x=i%Main.ROWS;
			int y=i/Main.ROWS;
			//check Diagonal
			if(match==null)
			{
				match=checkMatch(x,y,1,1,grid);
			}
			
			if(match==null)
			{
				match=checkMatch(x,y,1,-1,grid);
			}
			//check rows
			if(match==null)
			{
				match=checkMatch(x, y, 1, 0, grid);
			
			}
			
			
			//check Column
			if(match==null)
			{
				match=checkMatch(x, y, 0, 1, grid);
			}
		}
		
		return match;
		
	}
	
	public static ArrayList<Placement> checkMatch(int x, int y, int dirX, int dirY, Placement[][] grid)
	{
		boolean found=false;
		ArrayList<Placement> match= new ArrayList<Placement>(Main.MATCH);
		int type=-1;
		while(x>=0 &&x<Main.MATCH && y>=0 && y<Main.MATCH) 
		{
			Placement placement = grid[x][y];
			if(placement!=null)
			{
				if(type==-1)
				{
				type=placement.getType();
				}
				if(type ==placement.getType())
				{
					match.add(placement);
				}
			}
			if(match.size()==Main.MATCH)
			{
				return match;
			}
			x+=dirX;
			y+=dirY;
		}
		
		return null;
		
	}

	public static int getWinType(Placement[][] placements) {
		// TODO Auto-generated method stub
		ArrayList<Placement> match=checkWin(placements);
		return match==null? -1 : match.get(0).getType(); 
	}

}
