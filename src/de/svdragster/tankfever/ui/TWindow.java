package de.svdragster.tankfever.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 15.02.2017.
 */
public class TWindow extends UIObject{

	private List<TButton> buttons = new ArrayList<>();

	public TWindow(int x, int y, int w, int h, boolean visible) {
		super(x, y, w, h, visible);
	}


	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0xAA, 0xAA, 0xAA));
		g.fillRect(x, y, w, h);
		g.setColor(new Color(0x15, 0x15, 0x44));
		g.fillRect(x + 3, y + 3, w - 3, h - 3);
	}

	@Override
	public void setVisible(boolean visible) {
		for (TButton button : buttons) {
			button.setVisible(false);
		}
		super.setVisible(visible);
	}

	public List<TButton> getButtons() {
		return buttons;
	}
}
