package de.svdragster.tankfever.gamestate;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.Handler;
import de.svdragster.tankfever.entities.GameObject;
import de.svdragster.tankfever.ui.UIHandler;
import de.svdragster.tankfever.ui.UIObject;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Sven on 15.02.2017.
 */
public abstract class GameState {

	public java.util.List<UIObject> uiObjectList = new ArrayList<>();
	public java.util.List<GameObject> gameObjectList = new ArrayList<>();

	GameStateType type;

	public void kill() {
		for (UIObject uiObject : uiObjectList) {
			getUiHandler().removeObject(uiObject);
		}

		for (GameObject gameObject : gameObjectList) {
			getHandler().removeObject(gameObject);
		}
		uiObjectList.clear();
		gameObjectList.clear();
	}

	public abstract void vanish();
	public abstract void init();
	public abstract void tick(final double delta);
	public abstract void render(Graphics2D g);

	public GameState(GameStateType type) {
		this.type = type;
	}

	public GameStateType getType() {
		return type;
	}

	public Handler getHandler() {
		return Game.getInstance().getHandler();
	}

	public UIHandler getUiHandler() {
		return Game.getInstance().getUiHandler();
	}

	public UIObject addUiObject(final UIObject object) {
		uiObjectList.add(object);
		getUiHandler().addObject(object);
		return object;
	}

	public void addGameObject(final GameObject object) {
		gameObjectList.add(object);
		getHandler().addObject(object);
	}

	public List<UIObject> getUiObjectList() {
		return uiObjectList;
	}

	public List<GameObject> getGameObjectList() {
		return gameObjectList;
	}
}
