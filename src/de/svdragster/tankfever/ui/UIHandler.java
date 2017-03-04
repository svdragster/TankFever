package de.svdragster.tankfever.ui;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Sven on 15.02.2017.
 */
public class UIHandler {

	private LinkedList<UIObject> objects = new LinkedList<>();

	public LinkedList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(LinkedList<UIObject> objects) {
		this.objects = objects;
	}

	public void addObject(final UIObject object) {
		this.objects.add(object);
	}

	public synchronized void removeObject(final UIObject object) {
		this.objects.remove(object);
	}

	public synchronized void render(final Graphics g) {
		LinkedList<UIObject> tempObjects = new LinkedList<>();
		tempObjects.addAll(getObjects());
		for (UIObject object : tempObjects) {
			if (object.isVisible()) {
				object.render(g);
			}
		}
	}

	public synchronized void tick() {
		LinkedList<UIObject> tempObjects = new LinkedList<>();
		tempObjects.addAll(getObjects());
		tempObjects.forEach(UIObject::tick);
	}

	public UIObject getInAABB(int x, int y) {
		for (UIObject object : getObjects()) {
			if (object.isVisible()) {
				if (x >= object.getX() && x <= object.getX() + object.getW()
						&& y >= object.getY() && y <= object.getY() + object.getH()) {
					return object;
				}
			}
		}
		return null;
	}



}
