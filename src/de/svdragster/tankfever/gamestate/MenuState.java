package de.svdragster.tankfever.gamestate;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.ui.TButton;
import de.svdragster.tankfever.ui.UIObject;

import java.awt.*;

/**
 * Created by Sven on 15.02.2017.
 */
public class MenuState extends GameState {

	public MenuState(GameStateType type) {
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
			addUiObject(new TButton(Game.WIDTH/2 - 100, Game.HEIGHT/2 - 100, 200, 60, true, "Continue") {

				@Override
				public void onClick() {
					Game.getInstance().changeState(Game.getInstance().getLastGameState());
				}

			});
			addUiObject(new TButton(Game.WIDTH/2 - 100, Game.HEIGHT/2 + 100, 200, 60, true, "Quit") {

				@Override
				public void onClick() {
					System.exit(0);
				}

			});
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
	public void render(Graphics2D g) {
		getHandler().render(g);
		getUiHandler().render(g);
	}
}
