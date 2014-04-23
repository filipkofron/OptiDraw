/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimizeddrawing;

import java.util.ArrayList;

/**
 *
 * @author Filip Kofron
 */
public class DrawableMap
{
	private final static int SIDE = 32;

	private final int width;
	private final int height;

	private final float ptX;
	private final float ptY;
	private final ArrayList [][] map = new ArrayList[SIDE][SIDE];

	public DrawableMap(int width, int height)
	{
		this.width = width;
		this.height = height;
	
		for(int x = 0; x < SIDE; x++)
		{
			for(int y = 0; y < SIDE; y++)
			{
				map[x][y] = new ArrayList<>();
			}
		}
		
		ptX = (float) width / SIDE;
		ptY = (float) height / SIDE;
	}
	
	public void clear()
	{
		for(int x = 0; x < SIDE; x++)
		{
			for(int y = 0; y < SIDE; y++)
			{
				map[x][y].clear();
			}
		}
	}
	
	public void clearDrawable(Drawable drawable)
	{
		for(int x = 0; x < SIDE; x++)
		{
			for(int y = 0; y < SIDE; y++)
			{
				map[x][y].remove(drawable);
			}
		}
	}

	public void addDrawable(Drawable drawable, int x1, int y1, int x2, int y2)
	{
		int sx = (int) (Math.floor(x1 / ptX));
		int sy = (int) (Math.floor(y1 / ptY));
		int ex = (int) (Math.ceil(x2 / ptX));
		int ey = (int) (Math.ceil(y2 / ptY));

		if (sx > ex)
		{
			int tmp = ex;
			ex = sx;
			sx = tmp;
		}

		if (sy > ey)
		{
			int tmp = ey;
			ey = sy;
			sy = tmp;
		}

		for (int x = sx; x < ex; x++)
		{
			for (int y = sy; y < ey; y++)
			{
				map[x][y].add(drawable);
			}
		}
	}
	
	public ArrayList objectsAt(int x1, int y1, int x2, int y2)
	{
		int sx = (int) (Math.floor(x1 / ptX));
		int sy = (int) (Math.floor(y1 / ptY));
		int ex = (int) (Math.ceil(x2 / ptX));
		int ey = (int) (Math.ceil(y2 / ptY));

		ArrayList<Drawable> objects = new ArrayList<>();
		
		if (sx > ex)
		{
			int tmp = ex;
			ex = sx;
			sx = tmp;
		}

		if (sy > ey)
		{
			int tmp = ey;
			ey = sy;
			sy = tmp;
		}

		for (int x = sx; x < ex; x++)
		{
			for (int y = sy; y < ey; y++)
			{
				for(Object object : map[x][y])
				{
					if(!objects.contains((Drawable) object))
					{
						objects.add((Drawable) object);
					}
				}
			}
		}

		return objects;
	}
}
