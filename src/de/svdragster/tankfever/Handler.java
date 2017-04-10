package de.svdragster.tankfever;

import de.svdragster.tankfever.building.Building;
import de.svdragster.tankfever.entities.GameObject;
import de.svdragster.tankfever.entities.GameObjectType;
import de.svdragster.tankfever.entities.Unit;
import de.svdragster.tankfever.entities.polygons.TankPolygon;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Sven on 06.02.2017.
 */
public class Handler {

	private LinkedList<GameObject> objects = new LinkedList<>();
	private LinkedList<Building> buildings = new LinkedList<>();

	public Handler() {
		int max = 500;
		double amount = Math.sqrt(max);
		int x = 0;
		int y = 0;
		for (int i=0; i<max; i++) {
			addObject(new Unit(x * 6, y*6, 10, 10, GameObjectType.Unit)).setSelected(true);
			x++;
			if (x >= amount) {
				x = 0;
				y++;
			}
		}

	}

	public void tick(final double delta) {
		final boolean selection = Game.getInstance().mouseInput.getMouseHold() != 0;
		if (selection) {
			int x1 = (int) ((Game.getInstance().mouseInput.getMouseHoldPos().getX() + Game.camera.getX()) / Game.camera.getZoom());
			int y1 = (int) ((Game.getInstance().mouseInput.getMouseHoldPos().getY() + Game.camera.getY()) / Game.camera.getZoom());
			int x2 = (int) ((Game.getInstance().mouseInput.getMouseHoldStartPos().getX() + Game.camera.getX()) / Game.camera.getZoom());
			int y2 = (int) ((Game.getInstance().mouseInput.getMouseHoldStartPos().getY() + Game.camera.getY()) / Game.camera.getZoom());
			for (GameObject gameObject : objects) {
				gameObject.tick(delta);
				if (gameObject instanceof Unit) {
					gameObject.setSelected(isInAABB(gameObject, x1, y1, x2, y2));
				}
			}
		} else {
			for (GameObject gameObject : objects) {
				gameObject.tick(delta);
			}
		}
	}

	public synchronized void render(final Graphics2D g) {

		LinkedList<GameObject> tempObjects = new LinkedList<>();
		tempObjects.addAll(objects);

		for (GameObject gameObject : tempObjects) {
			gameObject.render(g);
		}
	}

	public synchronized GameObject addObject(final GameObject object) {
		this.objects.add(object);
		return object;
	}

	public synchronized void removeObject(final GameObject object) {
		this.objects.remove(object);
	}

	public synchronized LinkedList<GameObject> getObjects() {
		return objects;
	}

	public void setObjects(LinkedList<GameObject> objects) {
		this.objects = objects;
	}

	public GameObject getInAABB(int x, int y) {
		for (GameObject gameObject : getObjects()) {
			if (x >= gameObject.getX() && x <= gameObject.getX() + gameObject.getW()
					&& y >= gameObject.getY() && y <= gameObject.getY() + gameObject.getH()) {
				return gameObject;
			}
		}
		return null;
	}

	public boolean isInAABB(GameObject gameObject, int x1, int y1, int x2, int y2) {
		int maxX = Math.max(x1, x2);
		int minX = Math.min(x1, x2);
		int maxY = Math.max(y1, y2);
		int minY = Math.min(y1, y2);
		if (gameObject.getX() + gameObject.getW()/2 >= minX && gameObject.getX() + gameObject.getW()/2 <= maxX
				&& gameObject.getY() + gameObject.getH()/2 >= minY && gameObject.getY() + gameObject.getH()/2 <= maxY) {
			return true;
		}
		return false;
	}

	public TankPolygon pnpoly(int testx, int testy) {
		for (GameObject gameObject : objects) {
			if (gameObject.getType() == GameObjectType.Polygon) {
				final TankPolygon polygon = (TankPolygon) gameObject;
				final int nvert = polygon.getVertx().size();
				if (nvert == 0) {
					continue;
				}
				if (!polygon.isInAABB(testx, testy)) {
					continue;
				}
				final int vertx[] = polygon.getVertx().stream().mapToInt(i->i).toArray();
				final int verty[] = polygon.getVerty().stream().mapToInt(i->i).toArray();

				boolean inside = false;
				for (int i = 0, j = nvert - 1; i < nvert; j = i++) {
					if ((verty[i] > testy) != (verty[j] > testy) &&
							testx < (vertx[j] - vertx[i]) * (testy - verty[i]) / (verty[j] - verty[i]) + vertx[i]) {
						inside = !inside;
					}
				}
				if (inside) {
					return polygon;
				}
			}
		}
		return null;
	}


}
