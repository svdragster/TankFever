package de.svdragster.tankfever.ui.clickanimations;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.ui.UIObject;

import java.awt.*;

/**
 * Created by Sven on 20.03.2017.
 */
public class MoveUnitAnimation extends UIObject {

	private int currentAnim = 0;

	public MoveUnitAnimation(int x, int y, int w, int h, boolean visible) {
		super(x, y, w, h, visible);
	}

	@Override
	public void tick(final double delta) {
		currentAnim++;
		if (currentAnim >= Game.getTextureManager().getAnimUnitMove().length * 2) {
			Game.getInstance().getUiHandler().removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		int x = (int) ((getX() - w/2) * Game.camera.getZoom() - Game.camera.getX());
		int y = (int) ((getY() - h/2) * Game.camera.getZoom() - Game.camera.getY());
		final Graphics2D g2d = (Graphics2D) g;
		final TexturePaint tp = new TexturePaint(Game.getTextureManager().getAnimUnitMove()[currentAnim/2], new Rectangle(x, y, (int) (w * Game.camera.getZoom()), (int) (h * Game.camera.getZoom())));
		g2d.setPaint(tp);
		g.fillRect((x), (y), (int) (w * Game.camera.getZoom()), (int) (h * Game.camera.getZoom()));
	}
}
