package de.svdragster.tankfever.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Sven on 05.03.2017.
 */
public class TTextureButton extends TButton {

	private BufferedImage bufferedImage;
	private TexturePaint tp;

	public TTextureButton(int x, int y, int w, int h, boolean visible, String text, BufferedImage image) {
		super(x, y, w, h, visible, text);
		this.bufferedImage = image;
		this.tp = new TexturePaint(bufferedImage, new Rectangle(0, 0, w, h));
	}

	public TTextureButton(int x, int y, int w, int h, boolean visible, String text, int fontSize, BufferedImage image) {
		super(x, y, w, h, visible, text, fontSize);
		this.bufferedImage = image;
		this.tp = new TexturePaint(bufferedImage, new Rectangle(0, 0, w, h));
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
		g2d.setPaint(tp);
		g.fillRect(x+2, y+2, w-4, h-4);
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
}
