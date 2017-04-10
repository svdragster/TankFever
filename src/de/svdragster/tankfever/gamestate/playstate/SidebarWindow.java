package de.svdragster.tankfever.gamestate.playstate;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.ui.TButton;
import de.svdragster.tankfever.ui.TWindow;

/**
 * Created by Sven on 09.04.2017.
 */
public class SidebarWindow extends TWindow {
	
	private double smooth = 1;
	private double xx = 0;
	
	TButton hideButton;
	private MenuType menuType;
	boolean expanded = false;
	
	public SidebarWindow(int x, int y, int w, int h, boolean visible, String title, MenuType menuType) {
		super(x, y, w, h, visible, title);
		this.menuType = menuType;
		this.xx = 0;
	}
	
	@Override
	public void tick(double delta) {
		if (expanded) {
			if (xx < w) {
				if (xx > w*0.6) {
					if (smooth < 7.0) {
						smooth *= 1.03;
						if (smooth > 7.0) {
							smooth = 7;
						}
					}
				}
				xx += (9 - smooth) * delta;
				x = Game.WIDTH - (int) xx;
				hideButton.setX(x - 45);
				updateButtons(x);
			} else {
				smooth = 1;
				xx = w;
				x = Game.WIDTH - w;
				hideButton.setX(x - 45);
				updateButtons(x);
			}
		} else {
			if (xx > 0) {
				if (xx < w*0.5) {
					if (smooth < 7.0) {
						smooth *= 1.025;
						if (smooth > 7.0) {
							smooth = 7;
						}
					}
				}
				xx -= (9 - smooth) * delta;
				x = Game.WIDTH - (int) xx;
				hideButton.setX(x - 45);
				updateButtons(x);
			} else {
				smooth = 1;
				xx = 0.0;
				x = Game.WIDTH;
				hideButton.setX(x - 45);
				updateButtons(x);
			}
		}
	}
	
	public MenuType getMenuType() {
		return menuType;
	}
	
	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}
	
	public void toggleExpansion() {
		smooth = 1;
		expanded = !expanded;
		if (expanded) {
			hideButton.setText(">");
		} else {
			hideButton.setText(hideButton.getOriginalText() + " ");
		}
	}
	
	
	public boolean isExpanded() {
		return expanded;
	}
	
	
	public TButton getHideButton() {
		return hideButton;
	}
	
	public void setHideButton(TButton hideButton) {
		this.hideButton = hideButton;
	}
}
