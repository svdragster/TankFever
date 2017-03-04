package de.svdragster.tankfever.gamestate.mapstate;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.ui.TButton;
import de.svdragster.tankfever.ui.TWindow;

import java.awt.*;

/**
 * Created by Sven on 04.03.2017.
 */
public class EditingWindow extends TWindow {

	private TButton hideButton;
	private boolean expanded = false;

	private double xx = 0;
	private double velX;

	public EditingWindow(int x, int y, int w, int h, boolean visible) {
		super(x, y, w, h, visible);
		this.xx = 0;
	}

	@Override
	public void tick() {
		if (expanded) {
			if (xx < w) {
				xx += 8;
				x = Game.WIDTH - (int) xx;
			} else {
				xx = w;
				x = Game.WIDTH - w;
			}
		} else {
			if (xx > 0) {
				xx -= 8;
				x = Game.WIDTH - (int) xx;
			} else {
				xx = 0.0;
				x = Game.WIDTH;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0xAA, 0xAA, 0xAA));
		g.fillRect(x - 50/* - (int) xx*/, y + h/2 - 50, 50, 100);
		g.fillRect(x/* - (int) xx*/, y, w, h);
		g.setColor(new Color(0x15, 0x15, 0x44));
		g.fillRect(x + 5/* - (int) xx*/, y + 5, w - 5, h - 5);
		hideButton.setX(x - 45/* - (int) xx*/);
	}

	public TButton getHideButton() {
		return hideButton;
	}

	public void setHideButton(TButton hideButton) {
		this.hideButton = hideButton;
	}

	public void toggleExpansion() {
		expanded = !expanded;
		if (expanded) {
			hideButton.setText(">");
		} else {
			hideButton.setText("<");
		}
	}

	public boolean isExpanded() {
		return expanded;
	}
}
