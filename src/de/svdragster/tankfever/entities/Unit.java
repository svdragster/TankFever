package de.svdragster.tankfever.entities;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.Location;

import java.awt.*;


/**
 * Created by Sven on 19.03.2017.
 */
public class Unit extends GameObject {

	private Location destination;
	private final double SPEED = 1;
	private double lastVectorX = 0;
	private double lastVectorY = 0;
	private double rotation = 0;

	public Unit(double x, double y, int w, int h, GameObjectType type) {
		super(x, y, w, h, type);
		destination = new Location(500, 800);
	}

	@Override
	public void tick() {
		if (destination == null) {
			return;
		}
		if (lastVectorX == 0 && lastVectorY == 0) {
			int toX = (int) destination.getX(), toY = (int) destination.getY();
			double vectorX = toX - x;
			double vectorY = toY - y;
			double len = Math.sqrt((vectorX * vectorX) + (vectorY * vectorY));
			if (len <= 0.1) {
				destination = null;
				return;
			}
			//System.out.println(vectorX + ", " + vectorY + ", " + len);
			vectorX /= len;
			vectorY /= len;
			vectorX *= SPEED;
			vectorY *= SPEED;
			lastVectorX = vectorX;
			lastVectorY = vectorY;
		}
		if (Math.abs(x - destination.getX()) <= 1 && Math.abs(y - destination.getY()) <= 1) {
			destination = null;
			return;
		}
		x += lastVectorX;
		y += lastVectorY;
	}

	@Override
	public void render(Graphics g) {
		int x = (int) ((getX()) * Game.camera.getZoom() - Game.camera.getX());
		int y = (int) ((getY()) * Game.camera.getZoom() - Game.camera.getY());
		if (x < -w || y < -h) {
			return;
		} else if (x + w > Game.WIDTH || y + h > Game.HEIGHT) {
			return;
		}
		if (Game.camera.getZoom() <= 0.85) {
			g.setColor(new Color(0xA0, 0, 0));
			g.fillRect((x + 5), (y + 5), (int) ((w-5) * Game.camera.getZoom()), (int) ((h-5) * Game.camera.getZoom()));
			//System.out.println(">>>>>" + (int) (x * Game.camera.getZoom() - Game.camera.getX()) + "\t\t" + (int) (y * Game.camera.getZoom() - Game.camera.getY()));
		} else {
			if (Game.getTextureManager().getTxUnit() == null) {
				return;
			}
			g.setColor(new Color(0x0, 0, 0, 0x40));
			g.fillRect((int) ((getX() + 1) * Game.camera.getZoom() - Game.camera.getX()), (int) ((getY() + 4) * Game.camera.getZoom() - Game.camera.getY()), (int) ((w-5) * Game.camera.getZoom()), (int) ((h-5) * Game.camera.getZoom()));
			//AffineTransform affineTransform = new AffineTransform();
			//affineTransform.translate(x, y);
			final Graphics2D g2d = (Graphics2D) g;
			//g2d.drawImage(Game.getTextureManager().getTxUnit(), affineTransform, null);
			//g.fillOval((int) (x), (int) (y), (int) (w * Game.camera.getZoom()), (int) (h * Game.camera.getZoom()));
			final TexturePaint tp = new TexturePaint(Game.getTextureManager().getTxUnit(), new Rectangle(x, y, (int) (w * Game.camera.getZoom()), (int) (h * Game.camera.getZoom())));
			g2d.setPaint(tp);
			g.fillRect((x), (y), (int) (w * Game.camera.getZoom()), (int) (h * Game.camera.getZoom()));
		}
		if (isSelected()) {
			g.setColor(new Color(0x0, 0x40, 0xA0));
			g.drawRect(x + 2, y + 2, (int) ((w - 2) * Game.camera.getZoom()), (int) ((h - 2) * Game.camera.getZoom()));
		}
		//g.setColor(new Color(0xFF, 0, 0));
		//g.drawLine((int) ((x + w/2) * Game.camera.getZoom() - Game.camera.getX()), (int) ((y + h/2) * Game.camera.getZoom() - Game.camera.getY()), (int) ((destination.getX() + w/2) * Game.camera.getZoom() - Game.camera.getX()), (int) ((destination.getY() + h/2) * Game.camera.getZoom() - Game.camera.getY()));
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.lastVectorX = 0;
		this.lastVectorY = 0;
		this.destination = destination;
	}
}
