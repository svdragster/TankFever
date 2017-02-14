package de.svdragster.tankfever.entities.polygons;

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
			vertx[i] = getVertx().get(i);
			verty[i] = getVerty().get(i);
		}*/

		Graphics2D g2d = (Graphics2D) g;
		//g2d.setPaint();
		g.setColor(new Color(0x22, 0x0, 0x0));
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, getVertx().size());
		path.moveTo(getVertx().get(0), getVerty().get(0));
		for (int i=1; i<getVertx().size(); i++) {
			//path.curveTo(getVertx().get(i - 1), getVerty().get(i - 1), getVertx().get(i), getVerty().get(i), getVertx().get(i) + 10, getVerty().get(i) + 10);
			path.quadTo(getVertx().get(i - 1), getVerty().get(i - 1), getVertx().get(i), getVerty().get(i));
		}
		path.closePath();
		g2d.fill(path);
		g.setColor(new Color(0xFF, 0xFF, 0xFF));
		g.fillOval((int) x, (int) y, 4, 4);
	}
}