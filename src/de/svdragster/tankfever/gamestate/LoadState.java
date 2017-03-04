package de.svdragster.tankfever.gamestate;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.ui.TText;
import de.svdragster.tankfever.ui.TWindow;
import de.svdragster.tankfever.ui.UIObject;

import java.awt.*;

/**
 * Created by Sven on 15.02.2017.
 */
public class LoadState extends GameState {

	public LoadState(GameStateType type) {
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
			addUiObject(new TWindow(0, 0, Game.WIDTH - 3, Game.HEIGHT - 3, true));
			addUiObject(new TText(20, 20, 200, 100, true, "Loading"));
			/*addUiObject(new TButton(Game.WIDTH/2 - 100, Game.HEIGHT/2 + 100, 200, 60, true, "Quit") {

				@Override
				public void onClick() {
					System.exit(0);
				}

			});*/
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
		g.setColor(new Color(0xAA, 0xAA, 0xAA));
		int size = 0;
		if (Game.getInstance().getMaxLoadProgress() > 0) {
			size = Game.WIDTH / Game.getInstance().getMaxLoadProgress();
		}
		g.fillRect(0, Game.HEIGHT - 50, size * Game.getInstance().getLoadProgress(), 50);
	}
}
