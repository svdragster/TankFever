package de.svdragster.tankfever.entities;

import de.svdragster.tankfever.Game;

import java.awt.*;

/**
 * Created by Sven on 06.02.2017.
 */
public class DebugText extends GameObject {

	public DebugText(int x, int y, int w, int h, GameObjectType type) {
		super(x, y, w, h, type);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(143, 137, 144));
		g.drawString(Game.lastFrames + " FPS ||| " + Game.selection + " " + (Game.selectionObject == null ? "none" : Game.selectionObject.getType().name()), (int) x, (int) y);
	}
}
