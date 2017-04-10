package de.svdragster.tankfever.ui;

import java.awt.*;

/**
 * Created by Sven on 15.02.2017.
 */
public class TButton extends UIObject {

	Font font;
	String text = "null";
	String originalText = "null";
	boolean shadowRect = true;
	boolean shadowFont = true;

	public TButton(int x, int y, int w, int h, boolean visible, String text) {
		super(x, y, w, h, visible);
		this.text = text;
		this.originalText = text;
		this.font = new Font("Impact", 1, 30);
	}

	public TButton(int x, int y, int w, int h, boolean visible, String text, int fontSize) {
		super(x, y, w, h, visible);
		this.text = text;
		this.originalText = text;
		this.font = new Font("Impact", 1, fontSize);
	}

	@Override
	public void tick(final double delta) {

	}

	@Override
	public void render(Graphics g) {
		if (shadowRect) {
			g.setColor(new Color(0x10, 0x10, 0x10));
			g.fillRect(x + 5, y + 5, w, h);
		}
		g.setColor(new Color(0x10, 0x60, 0xA0));
		g.fillRect(x, y, w, h);
		g.setFont(font);
		Graphics2D g2d = (Graphics2D) g;
		//FontMetrics fm = g2d.getFontMetrics();
		//int x = w - fm.stringWidth(text) - 5;
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


	public void onClick() {

	}

	public void setText(final String string) {
		this.text = string;
	}

	public boolean isShadowRect() {
		return shadowRect;
	}

	public void setShadowRect(boolean shadowRect) {
		this.shadowRect = shadowRect;
	}

	public boolean isShadowFont() {
		return shadowFont;
	}

	public void setShadowFont(boolean shadowFont) {
		this.shadowFont = shadowFont;
	}

	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}
}
