package de.svdragster.tankfever.gamestate.playstate;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.entities.polygons.TerrainType;
import de.svdragster.tankfever.gamestate.GameState;
import de.svdragster.tankfever.gamestate.GameStateType;
import de.svdragster.tankfever.ui.TButton;
import de.svdragster.tankfever.ui.TTextureButton;
import de.svdragster.tankfever.ui.UIObject;

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
		for (UIObject uiObject : getUiObjectList()) {
			uiObject.setVisible(false);
		}
	}

	@Override
	public void init() {
		if (uiObjectList.size() == 0) {
			final BuildingWindow window = new BuildingWindow(Game.WIDTH, 0, 300, Game.HEIGHT - 28, true);
			final TButton button = new TButton(window.getX() - 45, window.getY() + window.getH()/2 - 45, 50, 90, true, "<") {
				@Override
				public void onClick() {
					window.toggleExpansion();
				}
			};
			window.getButtons().add((TButton) addUiObject(new TTextureButton(window.getX(), window.getY() + window.getH()/2 - 45, 50, 50, true, "ABC", 18, Game.getTextureManager().getTxSandGrass()) {
				@Override
				public void onClick() {
					Game.getTextureManager().setSelectedTerrainType(TerrainType.GRASS_SAND);
				}
			}));
			window.getButtons().add((TButton) addUiObject(new TTextureButton(window.getX(), window.getY() + window.getH()/2 - 45, 50, 50, true, "DEF", 18, Game.getTextureManager().getTxWater()[0]) {
				@Override
				public void onClick() {
					Game.getTextureManager().setSelectedTerrainType(TerrainType.RIVER);
				}
			}));
			window.getButtons().add((TButton) addUiObject(new TTextureButton(window.getX(), window.getY() + window.getH()/2 - 45, 50, 50, true, "GHI", 18, Game.getTextureManager().getTxSandGrass()) {
				@Override
				public void onClick() {
					System.out.println("3");
				}
			}));
			window.getButtons().add((TButton) addUiObject(new TTextureButton(window.getX(), window.getY() + window.getH()/2 - 45, 50, 50, true, "JKL", 18, Game.getTextureManager().getTxSandGrass()) {
				@Override
				public void onClick() {
					System.out.println("4");
				}
			}));
			window.getButtons().add((TButton) addUiObject(new TTextureButton(window.getX(), window.getY() + window.getH()/2 - 45, 50, 50, true, "MNO", 18, Game.getTextureManager().getTxSandGrass()) {
				@Override
				public void onClick() {
					System.out.println("5");
				}
			}));
			window.updateButtons(window.getX());
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
