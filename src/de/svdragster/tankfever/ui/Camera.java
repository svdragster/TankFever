package de.svdragster.tankfever.ui;

import java.awt.*;

/**
 * Created by Sven on 17.02.2017.
 */
public class Camera extends UIObject {

	private final double SPEED = 0.3;
	private final double MAXSPEED = 6;

	private double velX = 0, velY = 0, xx = 0, yy = 0, directionX = 0, directionY = 0;

	private float zoom = 1.0F, zoomSpeed = 0.0F;

	public Camera(int x, int y, int w, int h, boolean visible) {
		super(x, y, w, h, visible);
	}

	@Override
	public void tick() {
		if (directionX == -1) {
			velX += -SPEED;
			if (velX < -MAXSPEED) {
				velX = -MAXSPEED;
			}
		} else if (directionX == 1) {
			velX += SPEED;
			if (velX > MAXSPEED) {
				velX = MAXSPEED;
			}
		} else {
			if (velX > 0) {
				velX -= SPEED;
			} else if (velX < 0){
				velX += SPEED;
			}
			if (velX <= 0.0001 && velX >= -0.0001) {
				velX = 0;
			}
		}
		if (directionX != 0 || velX != 0) {
			if (velX != 0) {
				xx += velX;
				x = (int) xx;
			}
		}
		if (directionY == -1) {
			velY += -SPEED;
			if (velY < -MAXSPEED) {
				velY = -MAXSPEED;
			}
		} else if (directionY == 1) {
			velY += SPEED;
			if (velY > MAXSPEED) {
				velY = MAXSPEED;
			}
		} else {
			if (velY > 0) {
				velY -= SPEED;
			} else if (velY < 0) {
				velY += SPEED;
			}
			if (velY <= 0.0001 && velY >= -0.0001) {
				velY = 0;
			}
		}
		if (directionY != 0 || velY != 0) {
			if (velY != 0) {
				yy += velY;
				y = (int) yy;
			}
		}
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

	public double getDirectionX() {
		return directionX;
	}

	public void setDirectionX(double directionX) {
		this.directionX = directionX;
	}

	public double getDirectionY() {
		return directionY;
	}

	public void setDirectionY(double directionY) {
		this.directionY = directionY;
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
