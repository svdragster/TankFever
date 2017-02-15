package de.svdragster.tankfever.gamestate;

import de.svdragster.tankfever.Handler;
import de.svdragster.tankfever.entities.DebugText;
import de.svdragster.tankfever.entities.GameObjectType;
import de.svdragster.tankfever.ui.UIHandler;

import java.awt.*;

/**
 * Created by Sven on 15.02.2017.
 */
public class MenuState extends GameState {

	public MenuState(GameStateType type, Handler handler, UIHandler uiHandler) {
		super(type, handler, uiHandler);
	}

	@Override
	public void init() {
		handler.addObject(new DebugText(0, 10, 0, 0, GameObjectType.Debug));
	}

	@Override
	public void tick() {
		handler.tick();
	}

	@Override
	public void render(Graphics g) {
		handler.render(g);
	}
}
