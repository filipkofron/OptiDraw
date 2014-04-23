package optimizeddrawing;

/**
 *
 * @author kofee
 */
public interface Drawable
{
	public void onUpdateDirty(SpaceMarker spaceMarker);
	public void onUpdateLocation(SpaceMarker spaceMarker);
	
	public long getZ();

	public int drawPixel(int prev, float x, float y);
	
	public void resize(int ax, int ay, int bx, int by);
}
