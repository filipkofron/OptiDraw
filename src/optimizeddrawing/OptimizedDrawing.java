package optimizeddrawing;

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
		TestDrawer testDrawer = new TestDrawer();
		DirtyMap dirtyMap = new DirtyMap(128, 128);
		
		dirtyMap.clear();
		
		dirtyMap.addRect(1, 1, 2, 2);
		dirtyMap.addRect(2, 2, 3, 3);
		
		dirtyMap.onSelectDirty(testDrawer);
	}

}
