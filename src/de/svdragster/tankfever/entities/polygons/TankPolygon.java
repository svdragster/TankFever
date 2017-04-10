package de.svdragster.tankfever.entities.polygons;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.entities.GameObject;
import de.svdragster.tankfever.entities.GameObjectType;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;

/**
 * Created by Sven on 10.02.2017.
 */
public class TankPolygon extends GameObject {

	private LinkedList<Integer> vertx = new LinkedList<>();
	private LinkedList<Integer> verty = new LinkedList<>();

	public LinkedList<Integer> getVertx() {
		return vertx;
	}

	public void setVertx(LinkedList<Integer> vertx) {
		this.vertx = vertx;
	}

	public LinkedList<Integer> getVerty() {
		return verty;
	}

	public void setVerty(LinkedList<Integer> verty) {
		this.verty = verty;
	}

	private int maxX = 0;
	private int minX = 0;
	private int maxY = 0;
	private int minY = 0;

	private TerrainType terrainType = TerrainType.RIVER;
	private TexturePaint tp;
	private int animation = 0;
	private int animationDirection = 1;
	private long animationTime = 0;

	public TankPolygon(double x, double y, int w, int h, GameObjectType type) {
		super(x, y, w, h, type);
		this.terrainType = Game.getTextureManager().getSelectedTerrainType();
	}

	public TankPolygon(double x, double y, int w, int h, GameObjectType type, TerrainType terrainType) {
		super(x, y, w, h, type);
		this.terrainType = terrainType;
	}

	@Override
	public void tick(final double delta) {
		if (System.currentTimeMillis() - animationTime >= 300) {
			if (animationDirection > 0) {
				animation++;
			} else {
				animation--;
			}
			animationTime = System.currentTimeMillis();
		}
		if (animation >= 3) {
			animationDirection = -1;
		} else if (animation <= 0) {
			animationDirection = 1;
		}
	}

	@Override
	public boolean render(Graphics2D g) {

		//list.stream().mapToInt(i->i).toArray();
		/*final int vertx[] = new int[getVertx().size()];
		final int verty[] = new int[getVerty().size()];
		for (int i=0; i<getVertx().size(); i++) {
			vertx[i] = getVertx() .get(i);
			verty[i] = getVerty().get(i);
		}*/
		if (getVertx().size() == 0 || getVerty().size() == 0) {
			return false;
		}
		/*for (int i=0; i<getVertx().size(); i++) {
			final int x = getVertx().get(i);
			final int y = getVerty().get(i);
			if (x >= Game.camera.getX() && x <= Game.camera.getX() + Game.camera.getW()
					&& y >= Game.camera.getY() && y <= Game.camera.getY() + Game.camera.getH()) {
				break;
			}
			if (i == getVertx().size() - 1) {
				return;
			}
		}*/

		//final Graphics2D g2d = (Graphics2D) g;
		//g2d.setPaint();
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, getVertx().size());
		path.moveTo(getVertx().get(0) * Game.camera.getZoom() - Game.camera.getX(), getVerty().get(0) * Game.camera.getZoom() - Game.camera.getY());
		for (int i=1; i<getVertx().size(); i++) {
			//path.curveTo(getVertx().get(i - 1), getVerty().get(i - 1), getVertx().get(i), getVerty().get(i), getVertx().get(i) + 10, getVerty().get(i) + 10);
			path.quadTo(getVertx().get(i - 1) * Game.camera.getZoom() - Game.camera.getX(), getVerty().get(i - 1) * Game.camera.getZoom() - Game.camera.getY(), getVertx().get(i) * Game.camera.getZoom() - Game.camera.getX(), getVerty().get(i) * Game.camera.getZoom() - Game.camera.getY());
		}
		path.closePath();
		if (isSelected()) {
			if (Game.camera.getZoom() > 0.6) {
				for (int i = 0; i < getVertx().size(); i++) {
					if (isSelected()) {
						g.setColor(new Color(0xFF, 0xFF, 0xFF));
						g.fillOval((int) ((getVertx().get(i) - 2) * Game.camera.getZoom()) - Game.camera.getX(), (int) ((getVerty().get(i) - 2) * Game.camera.getZoom()) - Game.camera.getY(), (int) (4 * Game.camera.getZoom()), (int) (4 * Game.camera.getZoom()));
						//g.fillOval(getVertx().get(i) - 2, getVerty().get(i) - 2, 4, 4);
					}
				}
			}
			if (terrainType == TerrainType.RIVER) {
				tp = new TexturePaint(Game.getTextureManager().getTxWaterMosaik(), new Rectangle(-Game.camera.getX(), -Game.camera.getY(), (int) (512 * Game.camera.getZoom()), (int) (512 * Game.camera.getZoom())));
			} else {
				tp = new TexturePaint(Game.getTextureManager().getTxSandGrassMosaik(), new Rectangle(-Game.camera.getX(), -Game.camera.getY(), (int) (512 * Game.camera.getZoom()), (int) (512 * Game.camera.getZoom())));
			}
			g.setPaint(tp);
		} else {
			//g.setColor(new Color(0x22, 0x0, 0x0));
			if (terrainType == TerrainType.RIVER) {
				tp = new TexturePaint(Game.getTextureManager().getTxWater()[animation], new Rectangle(-Game.camera.getX(), -Game.camera.getY(), (int) (256 * Game.camera.getZoom()), (int) (256 * Game.camera.getZoom())));
			} else {
				tp = new TexturePaint(Game.getTextureManager().getTxSandGrass(), new Rectangle(-Game.camera.getX(), -Game.camera.getY(), (int) (512 * Game.camera.getZoom()), (int) (512 * Game.camera.getZoom())));
			}
			g.setPaint(tp);
		}
		g.fill(path);
		return true;
	}

	public boolean isInAABB(int x, int y) {
		if (!isChanged()) {
			if (x < minX || x > maxX || y < minY || y > maxY) {
				return false;
			}
			return true;
		}
		final int nvert = getVertx().size();
		if (nvert == 0) {
			return false;
		}
		final int vertx[] = getVertx().stream().mapToInt(i->i).toArray();
		final int verty[] = getVerty().stream().mapToInt(i->i).toArray();
		int minX = vertx[0];
		int maxX = vertx[0];
		int minY = verty[0];
		int maxY = verty[0];
		for (int i = 1; i < nvert; i++) {
			if (vertx[i] < minX) {
				minX = vertx[i];
			}
			if (vertx[i] > maxX) {
				maxX = vertx[i];
			}
			if (verty[i] < minY) {
				minY = verty[i];
			}
			if (verty[i] > maxY) {
				maxY = verty[i];
			}
		}
		this.maxX = maxX;
		this.minX = minX;
		this.maxY = maxY;
		this.minY = minY;
		setChanged(false);
		//printf("(%d, %d) %d, %d   %d, %d\n", testx, testy, minX, minY, maxX, maxY);
		if (x < minX || x > maxX || y < minY || y > maxY) {
			return false;
		}
		return true;
	}
}
