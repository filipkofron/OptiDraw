package optimizeddrawing;

/**
 *
 * @author Filip Kofron
 */
public class DirtyMap implements SpaceMarker
{

	private final static int SIDE = 128;
	private final static int SIZE = (SIDE * SIDE) / 32;
	private final int[] map = new int[SIZE];

	private final int width;
	private final int height;

	private final float ptX;
	private final float ptY;

	public DirtyMap(int width, int height)
	{
		this.width = width;
		this.height = height;

		for (int i = 0; i < SIZE; i++)
		{
			map[i] = ~0;
		}

		ptX = (float) width / SIDE;
		ptY = (float) height / SIDE;
	}

	public void clear()
	{
		for (int i = 0; i < SIZE; i++)
		{
			map[i] = 0;
		}
	}

	private final static int BITS[] =
	{
		1 << 31, 1 << 30, 1 << 29, 1 << 28,
		1 << 27, 1 << 26, 1 << 25, 1 << 24,
		1 << 23, 1 << 22, 1 << 21, 1 << 20,
		1 << 19, 1 << 18, 1 << 17, 65536,
		32768, 16384, 8192, 4096,
		2048, 1024, 512, 256,
		128, 64, 32, 16,
		8, 4, 2, 1
	};

	@Override
	public void addRect(int x1, int y1, int x2, int y2)
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
				map[(y << 2) + (x >> 5)] |= BITS[x & 0x1F];
			}
		}
	}

	public void onSelectDirty(SpaceMarker spaceMarker)
	{
		for (int x = 0; x < SIDE; x++)
		{
			for (int y = 0; y < SIDE; y++)
			{
				int r = map[(y << 2) + (x >> 5)];
				if (r != 0)
				{
					if ((r & BITS[x & 0x1F]) != 0)
					{
						spaceMarker.addRect((int) (x * ptX), (int) (y * ptY),
								(int) ((x + 1) * ptX), (int) ((y + 1) * ptY));
					}
				}
			}
		}
	}
}
