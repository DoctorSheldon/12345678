import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class maingui extends JFrame {
	private cellworld cells;
	
	public maingui(int rows, int columns,int size){
		cells= new cellworld(rows, columns,size);
		new Thread(cells).start();
		add(cells);
		
		this.setTitle("lifegame");
		this.setVisible(true);
		this.setSize((rows+1)*size, (columns+1)*size);
	}
	
	 public static void main(String[] args) 
	    {
		 new maingui(40, 40,20);
	    }
	 }
