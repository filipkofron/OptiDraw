/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimizeddrawing;

import com.sun.prism.paint.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Filip Kofron
 */
public class Canvas
{

	private final BufferedImage image;
	private final int side;

	private DrawableMap drawableMap;
	private DirtyMap dirtyMap;

	private class Drawer implements SpaceMarker
	{
		@Override
		public void addRect(int x1, int y1, int x2, int y2)
		{
			ArrayList<Drawable> objects = drawableMap.objectsAt(x1, y1, x2, y2);

			objects.sort(new Comparator<Drawable>()
			{
				@Override
				public int compare(Drawable o1, Drawable o2)
				{
					long r = o1.getZ() - o2.getZ();
					if(r < 0)
					{
						return -1;
					}
					if(r > 0)
					{
						return 1;
					}
					return 0;
				}
			});
			
			for (int x = x1; x < x2; x++)
			{
				for (int y = y1; y < y2; y++)
				{
					int pix = image.getRGB(x, y);
					for (Drawable object : objects)
					{
						pix = object.drawPixel(pix, x, y);
					}
					image.setRGB(x, y, pix);
				}
			}
		}
	}

	private Drawer drawer = new Drawer();

	private int nextComplement(int max)
	{
		return (int) Math.pow(2.0, Math.ceil(Math.log(max) / Math.log(2.0) - 0.1));
	}

	public Canvas(int width, int height)
	{
		this.side = nextComplement(Math.max(width, height));

		image = new BufferedImage(side, side, BufferedImage.TYPE_4BYTE_ABGR);
		for (int x = side / 4; x < side * 3 / 4; x++)
		{
			for (int y = side / 4; y < side * 3 / 4; y++)
			{
				image.setRGB(x, y, Color.RED.getIntArgbPre());
			}
		}

		drawableMap = new DrawableMap(width, height);
		dirtyMap = new DirtyMap(width, height);
	}

	public void draw()
	{
		dirtyMap.onSelectDirty(drawer);
		dirtyMap.clear();
	}

	public Image getImage()
	{
		return image;
	}

	public DrawableMap getDrawableMap()
	{
		return drawableMap;
	}

	public DirtyMap getDirtyMap()
	{
		return dirtyMap;
	}
}
