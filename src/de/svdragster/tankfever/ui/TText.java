package de.svdragster.tankfever.ui;

import java.awt.*;

/**
 * Created by Sven on 15.02.2017.
 */
public class TText extends UIObject {

	private Font font = new Font("Impact", 1, 30);
	private String text = "null";
	private boolean shadowFont = true;

	public TText(int x, int y, int w, int h, boolean visible, String text) {
		super(x, y, w, h, visible);
		this.text = text;
	}

	@Override
	public void tick(final double delta) {

	}

	@Override
	public void render(Graphics g) {
		g.setFont(font);
		Graphics2D g2d = (Graphics2D) g;
		FontMetrics fm = g2d.getFontMetrics();
		int x = ((w - fm.stringWidth(text)) / 2) + this.x;
		int y = ((h - fm.getHeight()) / 2) + fm.getAscent() + this.y;
		if (shadowFont) {
			g.setColor(new Color(0x11, 0x11, 0x11));
			g2d.drawString(text, x + 3, y + 3);
		}
		g.setColor(new Color(0xBB, 0xBB, 0xBB));
		g2d.drawString(text, x, y);
	}

	public void setText(final String string) {
		this.text = string;
	}

	public boolean isShadowFont() {
		return shadowFont;
	}

	public void setShadowFont(boolean shadowFont) {
		this.shadowFont = shadowFont;
	}
}
