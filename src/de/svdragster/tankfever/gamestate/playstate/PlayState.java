package de.svdragster.tankfever.gamestate.playstate;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.entities.polygons.TerrainType;
import de.svdragster.tankfever.gamestate.GameState;
import de.svdragster.tankfever.gamestate.GameStateType;
import de.svdragster.tankfever.ui.TButton;
import de.svdragster.tankfever.ui.TTextureButton;
import de.svdragster.tankfever.ui.TWindow;
import de.svdragster.tankfever.ui.UIObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 15.02.2017.
 */
public class PlayState extends GameState {
	
	private List<SidebarButton> sidebarButtons = new ArrayList<>();
	private List<SidebarWindow> sidebarWindows = new ArrayList<>();

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
			final TWindow sidebarWindow = new TWindow(Game.WIDTH - 70, 0, 67, Game.HEIGHT - 27, true, "");
			addUiObject(sidebarWindow);
			final UnitWindow windowUnits = new UnitWindow(Game.WIDTH, 0, 300, Game.HEIGHT - 28, true, "Units", MenuType.UNITS);
			final BuildingWindow windowBuild = new BuildingWindow(Game.WIDTH, 0, 300, Game.HEIGHT - 28, true, "Building", MenuType.BUILD);
			final SidebarButton buttonBuild = new SidebarButton(windowBuild.getX() - 45, windowBuild.getY() + windowBuild.getH()/2 - 45, 50, 90, true, "B ", MenuType.BUILD) {
				@Override
				public void onClick() {
					hideAllWindowsExcept(MenuType.BUILD);
					windowBuild.toggleExpansion();
				}
			};
			final SidebarButton buttonUnits = new SidebarButton(windowUnits.getX() - 45, windowUnits.getY() + windowUnits.getH()/2 - 155, 50, 90, true, "U ", MenuType.UNITS) {
				@Override
				public void onClick() {
					hideAllWindowsExcept(MenuType.UNITS);
					windowUnits.toggleExpansion();
				}
			};
			windowBuild.getButtons().add((TButton) addUiObject(new TTextureButton(windowBuild.getX(), windowBuild.getY() + windowBuild.getH()/2 - 45, 50, 50, true, "ABC", 18, Game.getTextureManager().getTxSandGrass()) {
				@Override
				public void onClick() {
					Game.getTextureManager().setSelectedTerrainType(TerrainType.GRASS_SAND);
				}
			}));
			windowBuild.getButtons().add((TButton) addUiObject(new TTextureButton(windowBuild.getX(), windowBuild.getY() + windowBuild.getH()/2 - 45, 50, 50, true, "DEF", 18, Game.getTextureManager().getTxWater()[0]) {
				@Override
				public void onClick() {
					Game.getTextureManager().setSelectedTerrainType(TerrainType.RIVER);
				}
			}));

			windowBuild.updateButtons(windowBuild.getX());
			windowUnits.updateButtons(windowUnits.getX());

			buttonUnits.setShadowRect(false);
			buttonBuild.setShadowRect(false);
			
			
			windowBuild.setHideButton(buttonBuild);
			windowUnits.setHideButton(buttonUnits);

			addUiObject(windowBuild);
			addUiObject(windowUnits);

			addUiObject(buttonBuild);
			addUiObject(buttonUnits);
			
			sidebarButtons.add(buttonBuild);
			sidebarButtons.add(buttonUnits);
			sidebarWindows.add(windowBuild);
			sidebarWindows.add(windowUnits);
		} else {
			for (UIObject uiObject : getUiObjectList()) {
				uiObject.setVisible(true);
			}
		}
	}

	@Override
	public void tick(final double delta) {
		getHandler().tick(delta);
		getUiHandler().tick(delta);
	}

	@Override
	public void render(Graphics2D g) {
		getHandler().render(g);
		getUiHandler().render(g);
	}
	
	public void toggleAllButtonsExcept(MenuType menuType) {
		for (SidebarButton sidebarButton: sidebarButtons) {
			if (sidebarButton.getMenuType() != menuType) {
				sidebarButton.setVisible(!sidebarButton.isVisible());
			}
		}
	}
	
	public void hideAllWindowsExcept(MenuType menuType) {
		for (SidebarWindow window : sidebarWindows) {
			if (window.getMenuType() != menuType) {
				//window.setVisible(!window.isVisible());
				if (window.isExpanded()) {
					window.toggleExpansion();
				}
			}
		}
	}
}
