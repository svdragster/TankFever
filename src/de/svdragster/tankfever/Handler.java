package de.svdragster.tankfever;

import de.svdragster.tankfever.entities.GameObject;
import de.svdragster.tankfever.entities.GameObjectType;
import de.svdragster.tankfever.entities.polygons.TankPolygon;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Sven on 06.02.2017.
 */
public class Handler {

	private LinkedList<GameObject> objects = new LinkedList<>();

	public void tick() {
		objects.forEach(GameObject::tick);
	}

	public synchronized void render(final Graphics g) {
		LinkedList<GameObject> tempObjects = new LinkedList<>();
		tempObjects.addAll(objects);
		for (GameObject gameObject : tempObjects) {
			gameObject.render(g);
		}
	}

	public synchronized void addObject(final GameObject object) {
		this.objects.add(object);
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
