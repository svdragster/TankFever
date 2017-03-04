package de.svdragster.tankfever.gamestate;

import java.awt.*;

/**
 * Created by Sven on 15.02.2017.
 */
public class PlayState extends GameState {

	public PlayState(GameStateType type) {
		super(type);
	}

	@Override
	public void vanish() {

	}

	@Override
	public void init() {

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
