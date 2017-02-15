package de.svdragster.tankfever.gamestate;

import de.svdragster.tankfever.Handler;
import de.svdragster.tankfever.ui.UIHandler;

import java.awt.*;

/**
 * Created by Sven on 15.02.2017.
 */
public abstract class GameState {

	private GameStateType type;
	Handler handler;
	private UIHandler uiHandler;

	public abstract void init();
	public abstract void tick();
	public abstract void render(Graphics g);

	public GameState(GameStateType type, Handler handler, UIHandler uiHandler) {
		this.type = type;
		this.handler = handler;
		this.uiHandler = uiHandler;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public UIHandler getUiHandler() {
		return uiHandler;
	}

	public void setUiHandler(UIHandler uiHandler) {
		this.uiHandler = uiHandler;
	}
}
