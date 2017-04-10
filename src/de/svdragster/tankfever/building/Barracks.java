package de.svdragster.tankfever.building;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.entities.GameObjectType;
import de.svdragster.tankfever.tribes.Tribe;

import java.awt.*;

/**
 * Created by Sven on 01.04.2017.
 */
public class Barracks extends Building {


	public Barracks(double x, double y, int w, int h, GameObjectType type, Tribe tribeOwner, BuildingType buildingType) {
		super(x, y, w, h, type, tribeOwner, buildingType);
		setShadow(new Shadow(new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0}));
	}

	@Override
	public void tick(double delta) {

	}
	
	@Override
	public void onSelect() {
	
	}
	
	@Override
	public void onDeselect() {
	
	}
	
	@Override
	public boolean render(Graphics2D g) {
		int x = (int) ((getX()) * Game.camera.getZoom() - Game.camera.getX());
		int y = (int) ((getY()) * Game.camera.getZoom() - Game.camera.getY());
		/*int shadowX = 0, shadowY = 0;
		if (Game.camera.getZoom() > 1.5) {
			shadowX = (int) ((getX() - 3) * Game.camera.getZoom() - Game.camera.getX());
			shadowY = (int) ((getY() + 3) * Game.camera.getZoom() - Game.camera.getY());
		}*/
		switch (getBuildingType()) {
			case Barracks:
				//int x = (int) gx;
				//int y = (int) gy;
				if (x < -w || y < -h) {
					return false;
				} else if (x + w > Game.WIDTH || y + h > Game.HEIGHT) {
					return false;
				}
				if (Game.camera.getZoom() <= 1.5) {
					g.setColor(new Color(0xA0, 0, 0));

					g.fillRect((x), (y), (int) ((w) * Game.camera.getZoom()), (int) ((h) * Game.camera.getZoom()));
				} else {
					if (Game.getTextureManager().getTxUnit() == null) {
						return false;
					}
					if (getShadow() != null) {
						if (g.getColor().getRed() != 0x0 || g.getColor().getGreen() != 0x0 || g.getColor().getBlue() != 0x0 || g.getColor().getAlpha() != 0x50) {
							g.setColor(new Color(0x0, 0, 0, 0x50));
						}
						getShadow().getX()[0] = (int) ((getX() + 2) * Game.camera.getZoom() - Game.camera.getX());
						getShadow().getY()[0] = (int) ((getY()) * Game.camera.getZoom() - Game.camera.getY());
						getShadow().getX()[1] = (int) ((getX() - 5) * Game.camera.getZoom() - Game.camera.getX());
						getShadow().getY()[1] = (int) ((getY() + 5) * Game.camera.getZoom() - Game.camera.getY());
						getShadow().getX()[2] = (int) ((getX() - 5) * Game.camera.getZoom() - Game.camera.getX());
						getShadow().getY()[2] = (int) ((getY() + h + 5) * Game.camera.getZoom() - Game.camera.getY());
						getShadow().getX()[3] = (int) ((getX() + w - 5) * Game.camera.getZoom() - Game.camera.getX());
						getShadow().getY()[3] = (int) ((getY() + h + 5) * Game.camera.getZoom() - Game.camera.getY());
						getShadow().getX()[4] = (int) ((getX() + w - 1) * Game.camera.getZoom() - Game.camera.getX());
						getShadow().getY()[4] = (int) ((getY() + h - 2) * Game.camera.getZoom() - Game.camera.getY());
						g.fillPolygon(getShadow().getX(), getShadow().getY(), getShadow().getX().length);
					}
					//g.fillRoundRect(shadowX, shadowY, (int) ((w) * Game.camera.getZoom()), (int) ((h) * Game.camera.getZoom()), 15, 15);
					g.setColor(new Color(0x90, 0, 0, 0xFF));
					g.fillRoundRect((x), (y), (int) ((w) * Game.camera.getZoom()), (int) ((h) * Game.camera.getZoom()), 15, 15);
					//g.fillRect((int) ((getX() + 1) * Game.camera.getZoom() - Game.camera.getX()), (int) ((getY() + 4) * Game.camera.getZoom() - Game.camera.getY()), (int) ((w-5) * Game.camera.getZoom()), (int) ((h-5) * Game.camera.getZoom()));
					//g.drawImage(Game.getTextureManager().getTxUnit(), x, y, (int) (w * Game.camera.getZoom()), (int) (h * Game.camera.getZoom()), null);
				}
				if (isSelected()) {
					g.setColor(new Color(0x0, 0x40, 0xA0));
					g.drawRect(x, y, (int) ((w) * Game.camera.getZoom()), (int) ((h) * Game.camera.getZoom()));
				}
				break;
		}
		return true;
	}
}
