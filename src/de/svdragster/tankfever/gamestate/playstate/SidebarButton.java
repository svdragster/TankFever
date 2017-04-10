package de.svdragster.tankfever.gamestate.playstate;

import de.svdragster.tankfever.ui.TButton;

import java.awt.*;

/**
 * Created by Sven on 09.04.2017.
 */
public class SidebarButton extends TButton {
	
	private MenuType menuType;
	
	public SidebarButton(int x, int y, int w, int h, boolean visible, String text, MenuType menuType) {
		super(x, y, w, h, visible, text);
		this.menuType = menuType;
	}
	
	@Override
	public void tick(double delta) {
		super.tick(delta);
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
	}
	
	public MenuType getMenuType() {
		return menuType;
	}
	
	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}
}
