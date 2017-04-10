package de.svdragster.tankfever.building;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.entities.GameObjectType;
import de.svdragster.tankfever.tribes.Tribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 09.04.2017.
 */
public class BuildingManager {
	
	public static BuildingType currentlyBuilding = BuildingType.None;
	
	private List<Building> buildings = new ArrayList<>();
	
	public List<Building> getBuildings() {
		return buildings;
	}
	
	public static void build(int x, int y, int w, int h, Tribe tribeOwner, BuildingType buildingType) {
		Building building = null;
		switch (buildingType) {
			case Headquarter:
				building = new Headquarter(x, y, w, h, GameObjectType.Building, tribeOwner, buildingType);
				break;
			case Barracks:
				building = new Barracks(x, y, w, h, GameObjectType.Building, tribeOwner, buildingType);
				break;
		}
		if (building != null) {
			Game.getInstance().getHandler().addObject(building);
		}
	}
}