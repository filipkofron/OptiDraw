package optimizeddrawing;

/**
 *
 * @author Filip Kofron
 */
public abstract class Rectangle implements Drawable
{
	protected long z;
	protected float x1;
	protected float y1;
	protected float x2;
	protected float y2;
	
	protected float lastX1;
	protected float lastY1;
	protected float lastX2;
	protected float lastY2;

	public Rectangle(long z, float x1, float y1, float x2, float y2)
	{
		this.z = z;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public Rectangle()
	{
	}
	
	@Override
	public void onUpdateDirty(SpaceMarker spaceMarker)
	{
		int a = (int) Math.min(x1, lastX1);
        int b = (int) Math.max(x1, lastX1);
        int c = (int) Math.min(x2, lastX2);
        int d = (int) Math.max(x2, lastX2);

        int e = (int) Math.min(y1, lastY1);
        int f = (int) Math.max(y1, lastY1);
        int g = (int) Math.min(y2, lastX2);
        int h = (int) Math.max(y2, lastY2);

        spaceMarker.addRect(b, c, e, f);
        spaceMarker.addRect(a, b, f, g);
        spaceMarker.addRect(c, d, f, g);
        spaceMarker.addRect(b, c, g, h);

        if(x1 == a && y1 == e || lastX1 == a && lastY1 == e)
        {
            spaceMarker.addRect(a, b, e, f);
            spaceMarker.addRect(c, d, g, h);
        }
        else
        {
            spaceMarker.addRect(c, d, e, f);
            spaceMarker.addRect(a, b, g, h);
        }
	}

	@Override
	public void onUpdateLocation(SpaceMarker spaceMarker)
	{
		spaceMarker.addRect((int) x1 - 1, (int) y1 - 1, (int) x2 + 1, (int) y2 + 1);
	}

	@Override
	public long getZ()
	{
		return z;
	}
}
