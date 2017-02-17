package de.svdragster.tankfever.ui;

import java.awt.*;

/**
 * Created by Sven on 17.02.2017.
 */
public class Camera extends UIObject {

	private final double SPEED = 0.3;

	private double velX = 0, velY = 0, xx = 0, yy = 0;

	private float zoom = 1.0F, zoomSpeed = 0.0F;

	public Camera(int x, int y, int w, int h, boolean visible) {
		super(x, y, w, h, visible);
	}

	@Override
	public void tick() {
		if (zoomSpeed != 0.0) {
			if (zoom < 0.1F) {
				zoomSpeed = 0;
				zoom = 0.1F;
			}
			zoom += zoomSpeed;
			if (zoomSpeed > 0.0F) {
				zoomSpeed -= 0.001F;
			} else {
				zoomSpeed += 0.001F;
			}
			if (zoomSpeed <= 0.0001 && zoomSpeed >= -0.0001) {
				zoomSpeed = 0;
			}
		}
		if (velX != 0) {
			xx += velX;
			if (velX > 0) {
				velX -= SPEED;
			} else {
				velX += SPEED;
			}
			if (velX <= 0.0001 && velX >= -0.0001) {
				velX = 0;
			}
			x = (int) xx;
		}
		if (velY != 0) {
			yy += velY;
			if (velY > 0) {
				velY -= SPEED;
			} else {
				velY += SPEED;
			}
			if (velY <= 0.0001 && velY >= -0.0001) {
				velY = 0;
			}
			y = (int) yy;
		}
	}

	@Override
	public void render(Graphics g) {

	}

	public double getMotionX() {
		return velX;
	}

	public void setMotionX(double velX) {
		this.velX = velX;
	}

	public double getMotionY() {
		return velY;
	}

	public void setMotionY(double velY) {
		this.velY = velY;
	}

	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	public float getZoomSpeed() {
		return zoomSpeed;
	}

	public void setZoomSpeed(float zoomSpeed) {
		this.zoomSpeed = zoomSpeed;
	}
}
