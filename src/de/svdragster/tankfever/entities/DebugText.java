package de.svdragster.tankfever.entities;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.ui.UIObject;

import java.awt.*;

/**
 * Created by Sven on 06.02.2017.
 */
public class DebugText extends UIObject {


	private Font font = new Font("Arial", 0, 12);

	public DebugText(int x, int y, int w, int h, boolean visible) {
		super(x, y, w, h, visible);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(new Color(143, 137, 144));
		g.drawString(Game.lastFrames + " FPS ||| " + Game.selection + " " + (Game.getSelectionObject() == null ? "none" : Game.getSelectionObject().getType().name()) + " ||| " + Game.camera.getZoom() + ", " + Game.camera.getZoomSpeed(), x, y);
	}
}
/*if svelia.love==true
    set svelia.relationship==true
end*/