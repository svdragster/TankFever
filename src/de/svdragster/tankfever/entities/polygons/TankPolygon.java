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

	public TankPolygon(double x, double y, int w, int h, GameObjectType type) {
		super(x, y, w, h, type);
	}

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

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {

		//list.stream().mapToInt(i->i).toArray();
		/*final int vertx[] = new int[getVertx().size()];
		final int verty[] = new int[getVerty().size()];
		for (int i=0; i<getVertx().size(); i++) {
			vertx[i] = getVertx() .get(i);
			verty[i] = getVerty().get(i);
		}*/
		if (getVertx().size() == 0 || getVerty().size() == 0) {
			return;
		}
		for (int i=0; i<getVertx().size(); i++) {
			final int x = getVertx().get(i);
			final int y = getVerty().get(i);
			if (x >= Game.camera.getX() && x <= Game.camera.getX() + Game.camera.getW()
					&& y >= Game.camera.getY() && y <= Game.camera.getY() + Game.camera.getH()) {
				break;
			}
			if (i == getVertx().size() - 1) {
				return;
			}
		}
		final Graphics2D g2d = (Graphics2D) g;
		//g2d.setPaint();
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, getVertx().size());
		path.moveTo(getVertx().get(0) * Game.camera.getZoom() - Game.camera.getX(), getVerty().get(0) * Game.camera.getZoom() - Game.camera.getY());
		for (int i=1; i<getVertx().size(); i++) {
			//path.curveTo(getVertx().get(i - 1), getVerty().get(i - 1), getVertx().get(i), getVerty().get(i), getVertx().get(i) + 10, getVerty().get(i) + 10);
			path.quadTo(getVertx().get(i - 1) * Game.camera.getZoom() - Game.camera.getX(), getVerty().get(i - 1) * Game.camera.getZoom() - Game.camera.getY(), getVertx().get(i) * Game.camera.getZoom() - Game.camera.getX(), getVerty().get(i) * Game.camera.getZoom() - Game.camera.getY());
		}
		path.closePath();
		if (isSelected()) {
			for (int i=0; i<getVertx().size(); i++) {
				if (isSelected()) {
					g.setColor(new Color(0xFF, 0xFF, 0xFF));
					g.fillOval((int) ((getVertx().get(i) - 2) * Game.camera.getZoom()) - Game.camera.getX(), (int) ((getVerty().get(i) - 2) * Game.camera.getZoom()) - Game.camera.getY(), (int) (4 * Game.camera.getZoom()), (int) (4 * Game.camera.getZoom()));
					//g.fillOval(getVertx().get(i) - 2, getVerty().get(i) - 2, 4, 4);
				}
			}
			g.setColor(new Color(0x52, 0x12, 0x12));
		} else {
			g.setColor(new Color(0x22, 0x0, 0x0));
		}
		g2d.fill(path);
	}
}
