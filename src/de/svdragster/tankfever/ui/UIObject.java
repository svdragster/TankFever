package de.svdragster.tankfever.ui;

import java.awt.*;

/**
 * Created by Sven on 15.02.2017.
 */
public abstract class UIObject {

	protected int x, y, velX, velY, w, h;
	protected boolean visible = false;

	public UIObject(int x, int y, int w, int h, boolean visible) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.visible = visible;
	}

	public abstract void tick();
	public abstract void render(Graphics g);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
}
