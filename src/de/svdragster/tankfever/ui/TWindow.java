package de.svdragster.tankfever.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 15.02.2017.
 */
public class TWindow extends UIObject{

	Font font;
	private List<TButton> buttons = new ArrayList<>();
	private String title = "";

	public TWindow(int x, int y, int w, int h, boolean visible, String title) {
		super(x, y, w, h, visible);
		this.title = title;
		this.font = new Font("Impact", 1, 20);
	}


	@Override
	public void tick(final double delta) {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0xAA, 0xAA, 0xAA));
		g.fillRect(x, y, w, h);
		g.setColor(new Color(0x15, 0x15, 0x44));
		g.fillRect(x + 3, y + 3, w - 6, h - 6);

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
	public void updateButtons(int x) {
		int r, c;
		for (int i=0; i<getButtons().size(); i++) {
			final TButton button = getButtons().get(i);
			r = i/2;
			c = i%2;
			button.setX(12 + x + c*100);
			button.setY(50 + r*120);
		}
	}
}
