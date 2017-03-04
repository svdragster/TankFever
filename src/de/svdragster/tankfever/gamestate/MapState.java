package de.svdragster.tankfever.gamestate;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.gamestate.mapstate.EditingWindow;
import de.svdragster.tankfever.ui.TButton;
import de.svdragster.tankfever.ui.UIObject;

import java.awt.*;

/**
 * Created by Sven on 15.02.2017.
 */
public class MapState extends GameState {

	public MapState(GameStateType type) {
		super(type);
	}

	@Override
	public void vanish() {
		for (UIObject uiObject : getUiObjectList()) {
			uiObject.setVisible(false);
		}
	}

	@Override
	public void init() {
		if (uiObjectList.size() == 0) {
			final EditingWindow window = new EditingWindow(Game.WIDTH, 0, 200, Game.HEIGHT, true);
			final TButton button = new TButton(window.getX() - 45, window.getY() + window.getH()/2 - 45, 50, 90, true, "<") {
				@Override
				public void onClick() {
					window.toggleExpansion();
				}
			};
			button.setShadowRect(false);
			window.setHideButton(button);
			addUiObject(window);
			addUiObject(button);
		} else {
			for (UIObject uiObject : getUiObjectList()) {
				uiObject.setVisible(true);
			}
		}
	}

	@Override
	public void tick() {
		getHandler().tick();
		getUiHandler().tick();
	}

	@Override
	public void render(Graphics g) {
		getHandler().render(g);
		getUiHandler().render(g);
	}
}
