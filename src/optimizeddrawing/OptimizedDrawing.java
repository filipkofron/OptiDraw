package optimizeddrawing;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author kofee
 */
public class OptimizedDrawing
{

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() ->
		{
			JFrame frame = new JFrame("OptimizedDrawing example");
			frame.setLayout(new BorderLayout());
			frame.add(new View(), BorderLayout.CENTER);
			frame.setSize(800, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});

	}

}
