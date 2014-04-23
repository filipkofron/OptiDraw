/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimizeddrawing;

import com.sun.prism.paint.Color;

/**
 *
 * @author Filip Kofron
 */
public class FilledRectangle extends Rectangle
{

	public FilledRectangle(long z, float x1, float y1, float x2, float y2)
	{
		super(z, x1, y1, x2, y2);
	}

	public FilledRectangle()
	{
	}
	
	@Override
	public int drawPixel(int prev, float x, float y)
	{
		if(x >= x1 && x <= x2 && y >= y1 && y <= y2)
		{
			if(x == x1 || x == x2 || y == y1 || y == y2)
			{
				return Color.RED.getIntArgbPre();
			}
			return Color.GREEN.getIntArgbPre();
		}
		return prev;
	}
}
