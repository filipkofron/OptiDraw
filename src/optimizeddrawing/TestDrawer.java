/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimizeddrawing;

/**
 *
 * @author Filip Kofron
 */
public class TestDrawer implements SpaceMarker
{

	@Override
	public void addRect(int x1, int y1, int x2, int y2)
	{
		System.out.println("[" + x1 + ", " + y1 + "]:[" + x2 + ", " + y2 + "]");
	}

}
