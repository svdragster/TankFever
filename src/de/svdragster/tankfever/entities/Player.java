package de.svdragster.tankfever.entities;

import java.awt.*;

/**
 * Created by Sven on 06.02.2017.
 */
public class Player extends GameObject {

	private final double maxSpeed = 2.5;

	private int aX = 0, aY = 0;

	public Player(double x, double y, int w, int h, GameObjectType type) {
		super(x, y, w, h, type);
	}

	@Override
	public void tick() {
		if (aX != 0) {
			if (aX == 1) {
				if (velX == 0) {
					velX = 0.1;
				} else if (velX < 0) {
					velX *= -1;
				}
			} else if (aX == -1){
				if (velX == 0) {
					velX = -0.1;
				} else if (velX > 0) {
					velX *= -1;
				}
			}
			velX *= 1.1;
			if (velX > maxSpeed) {
				velX = maxSpeed;
			} else if (velX < -maxSpeed) {
				velX = -maxSpeed;
			}
		} else {
			velX *= 0.95;
		}
		if (aY != 0) {
			if (aY == -1) {
				if (velY == 0) {
					velY = -0.1;
				} else if (velY > 0) {
					velY *= -1;
				}
			} else if (aY == 1){
				if (velY == 0) {
					velY = 0.1;
				} else if (velY < 0) {
					velY *= -1;
				}
			}
			velY *= 1.1;
			if (velY > maxSpeed) {
				velY = maxSpeed;
			} else if (velY < -maxSpeed) {
				velY = -maxSpeed;
			}
		} else {
			velY *= 0.95;
		}
		x += velX;
		y += velY;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(143, 137, 144));
		g.drawRect((int) x, (int) y, w, h);
	}

	public int getaX() {
		return aX;
	}

	public void setaX(int aX) {
		this.aX = aX;
	}

	public int getaY() {
		return aY;
	}

	public void setaY(int aY) {
		this.aY = aY;
	}
}
