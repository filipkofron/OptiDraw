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
}
