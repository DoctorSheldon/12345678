import java.awt.Graphics;
import javax.swing.JPanel;

public class cellworld extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rows;
	private int cols;
	private cell[][] cells1;
	private cell[][] cells2;
	private int size;
	
	public cellworld(int rows,int cols,int size){
		this.size=size;
		this.rows=rows;
		this.cols=cols;
		cells1=new cell[rows][cols];
		cells2=new cell[rows][cols];
		for(int i=0;i<rows;i++){
			for (int j=0;j<cols;j++){
				if(Math.random()>0.5){
					cells1[i][j]=cell.live;
				}
				else {
					cells1[i][j]=cell.death;
				}	
			}
		}
	}
	
	private void evolve(int x, int y)
	{
		int activeSurroundingCell = 0;
		
		if(isValidCell(x - 1, y - 1) && (cells1[x - 1][y - 1] == cell.live))
		{
			activeSurroundingCell++;
		}

		if(isValidCell(x, y - 1) && (cells1[x][y - 1] == cell.live))
		{
			activeSurroundingCell++;
		}	
		
		if(isValidCell(x + 1, y - 1) && (cells1[x + 1][y - 1] == cell.live))
		{
			activeSurroundingCell++;
		}

		if(isValidCell(x + 1, y) && (cells1[x + 1][y] == cell.live))
		{
			activeSurroundingCell++;
		}
		
		if(isValidCell(x + 1, y + 1) && (cells1[x + 1][y + 1] == cell.live))
		{
			activeSurroundingCell++;
		}

		if(isValidCell(x, y + 1) && (cells1[x][y + 1] == cell.live))
		{
			activeSurroundingCell++;
		}
		
		if(isValidCell(x - 1, y + 1) && (cells1[x - 1][y + 1] == cell.live))
		{
			activeSurroundingCell++;
		}

		if(isValidCell(x - 1, y) && (cells1[x - 1][y] == cell.live))
		{
			activeSurroundingCell++;
		}	
		
		if(activeSurroundingCell == 3)
		{
			cells2[x][y] = cell.live;
		}
		else if(activeSurroundingCell == 2)
		{
			cells2[x][y] = cells1[x][y];
		}
		else
		{
			cells2[x][y] = cell.death;
		}
	}
	
	private boolean isValidCell(int x, int y)
	{
		if((x >= 0) && (x < rows) && (y >= 0) && (y < cols))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
		
	public void change(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				evolve(i, j);
				}
			}
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			synchronized(this)
			{
				
				repaint();
				sleep(0.3);
				change();
				cells1 = cells2;			
			}
		}
	}
	
	public void paintComponent(Graphics g) 
		{
	        super.paintComponent(g);
	        
	        for (int i = 0; i < rows; i++) 
	        {
	            for (int j = 0; j < cols; j++) 
	            {
	            	if(cells1[i][j] == cell.live)
	            	{
	            		g.fillRect(j * size, i * size, size, size);
	            	}
	            	else
	            	{
	                    g.drawRect(j *size, i *size, size, size);            		
	            	}
	            }
	        }
	    }	
	 
	private void sleep(double d)
		{
			try 
			{
				Thread.sleep((long) (1000 * d));
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
}
