/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimizeddrawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JPanel;

/**
 *
 * @author Filip Kofron
 */
public class View extends JPanel implements ComponentListener, RepaintListener, SpaceMarker
{
	private Canvas canvas;
	private ToolBox toolBox;
	
	public View()
	{
		addComponentListener(this);
	}
	
	@Override
	public void componentResized(ComponentEvent e)
	{
		canvas = new Canvas(getWidth(), getHeight());
		if(toolBox != null)
		{
			removeMouseListener(toolBox);
			removeMouseMotionListener(toolBox);
		}
		toolBox = new ToolBox(this, canvas);
		addMouseListener(toolBox);
		addMouseMotionListener(toolBox);
		repaint();
	}

	@Override
	public void componentMoved(ComponentEvent e)
	{
	}

	@Override
	public void componentShown(ComponentEvent e)
	{
	}

	@Override
	public void componentHidden(ComponentEvent e)
	{
	}
	
	private Graphics graphics;
	
	@Override
	protected void paintComponent(Graphics g)
	{
		graphics = g;
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		if(canvas != null)
		{
			g.drawImage(canvas.getImage(), 0, 0, this);
			//canvas.getDirtyMap().onSelectDirty(this);
			canvas.getDrawableMap().draw(g);
		}
		graphics = null;
	}

	@Override
	public void onRepaint()
	{
		canvas.draw();
		repaint();
	}

	@Override
	public void addRect(int x1, int y1, int x2, int y2)
	{
		graphics.setColor(new Color(100, 100, 100, 100));
		graphics.fillRect(x1, y1, x2, y2);
	}
}
