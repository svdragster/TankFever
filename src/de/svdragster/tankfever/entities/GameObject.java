package de.svdragster.tankfever.entities;

import java.awt.*;

/**
 * Created by Sven on 06.02.2017.
 */
public abstract class GameObject {

	protected double x, y;
	protected int w, h;
	protected GameObjectType type;
	protected double velX, velY;
	protected boolean selected = false;

	public GameObject(double x, double y, int w, int h, GameObjectType type) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.type = type;
	}

	public abstract void tick();
	public abstract void render(Graphics g);

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public void setX(int x) {
		this.x = x;
	}

	public GameObjectType getType() {
		return type;
	}

	public void setType(GameObjectType type) {
		this.type = type;
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

	public boolean isSelected() {

		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
