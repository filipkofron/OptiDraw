/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimizeddrawing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Filip Kofron
 */
public class ToolBox implements MouseMotionListener, MouseListener
{
	private RepaintListener repaintListener;
	private Drawable currentDrawable;
	private Canvas canvas;
	private int startX;
	private int startY;
	private int lastX;
	private int lastY;

	private class DrawableLocator implements SpaceMarker
	{
		@Override
		public void addRect(int x1, int y1, int x2, int y2)
		{
			canvas.getDrawableMap().addDrawable(currentDrawable, x1, y1, x2, y2);
		}
	}
	
	private DrawableLocator drawableLocator = new DrawableLocator();
	
	public ToolBox(RepaintListener repaintListener, Canvas canvas)
	{
		this.repaintListener = repaintListener;
		this.canvas = canvas;
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		lastX = e.getX();
		lastY = e.getY();
		
		Drawable drawable = currentDrawable;
		
		if(drawable != null)
		{
			drawable.resize(startX, startY, lastX, lastY);
			canvas.getDrawableMap().clearDrawable(drawable);
			currentDrawable.onUpdateLocation(drawableLocator);
			currentDrawable.onUpdateDirty(canvas.getDirtyMap());
			repaintListener.onRepaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		startX = e.getX();
		startY = e.getY();
		lastX = startX + 1;
		lastY = startY + 1;
		currentDrawable = new FilledRectangle(System.currentTimeMillis(), startX, startY, lastX, lastY);
		
		currentDrawable.onUpdateLocation(drawableLocator);
		currentDrawable.onUpdateDirty(canvas.getDirtyMap());
		repaintListener.onRepaint();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		currentDrawable = null;
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}
}
