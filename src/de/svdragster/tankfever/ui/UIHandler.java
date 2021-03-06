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
		if (object instanceof TButton) {
			this.objects.add(object);
		} else if (object instanceof TWindow) {
			this.objects.add(0, object);
		} else {
			this.objects.add(object);
		}
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

	public synchronized void tick(final double delta) {
		LinkedList<UIObject> tempObjects = new LinkedList<>();
		tempObjects.addAll(getObjects());
		for (UIObject uiObject : tempObjects) {
			uiObject.tick(delta);
		}
		//tempObjects.forEach(UIObject::tick);
	}

	public UIObject getInAABB(int x, int y) {
		//for (UIObject object : getObjects()) {
		for (int i=getObjects().size()-1; i>=0; i--) {
			final UIObject object = getObjects().get(i);
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
