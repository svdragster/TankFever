package de.svdragster.tankfever.gamestate.playstate;

import java.awt.*;

/**
 * Created by Sven on 04.03.2017.
 */
public class BuildingWindow extends SidebarWindow {

	
	private double smooth = 1;
	private double velX;

	public BuildingWindow(int x, int y, int w, int h, boolean visible, String title, MenuType menuType) {
		super(x, y, w, h, visible, title, menuType);
		setFont(new Font("Impact", Font.BOLD, 20));
	}

	/*@Override
	public void tick(final double delta) {
	
	}*/

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0xAA, 0xAA, 0xAA));
		//g.fillRect(x - 50/* - (int) xx*/, y + h/2 - 160, 50, 100);
		g.fillRect(x - 50/* - (int) xx*/, y + h/2 - 50, 50, 100);
		g.fillRect(x/* - (int) xx*/, y, w, h);
		g.setColor(new Color(0x15, 0x15, 0x44));
		g.fillRect(x + 5/* - (int) xx*/, y + 5, w - 10, h - 10);
		Graphics2D g2d = (Graphics2D) g;
		g.setFont(getFont());
		FontMetrics fm = g2d.getFontMetrics();
		int x = this.x + 10;
		int y = this.y + fm.getHeight();
		g.setColor(new Color(0x11, 0x11, 0x11));
		g2d.drawString(getTitle(), x + 3, y + 3);
		g.setColor(new Color(0xBB, 0xBB, 0xBB));
		g2d.drawString(getTitle(), x, y);
	}

	/*public void updateButtons(int x) {
		int r, c;
		for (int i=0; i<getButtons().size(); i++) {
			final TButton button = getButtons().get(i);
			r = i/4;
			c = i%4;
			button.setX(12 + x + c*60);
			button.setY(50 + r*60);
		}
	}*/
}
