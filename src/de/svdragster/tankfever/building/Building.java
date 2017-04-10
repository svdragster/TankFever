package de.svdragster.tankfever.building;

import de.svdragster.tankfever.entities.GameObject;
import de.svdragster.tankfever.entities.GameObjectType;
import de.svdragster.tankfever.tribes.Tribe;

/**
 * Created by Sven on 06.03.2017.
 */
public abstract class Building extends GameObject {

	private Tribe tribeOwner;
	private BuildingType buildingType;
	private Shadow shadow = null;

	public Building(double x, double y, int w, int h, GameObjectType type, Tribe tribeOwner, BuildingType buildingType) {
		super(x, y, w, h, type);
		this.tribeOwner = tribeOwner;
		this.buildingType = buildingType;
	}

	public Tribe getTribeOwner() {
		return tribeOwner;
	}

	public void setTribeOwner(Tribe tribeOwner) {
		this.tribeOwner = tribeOwner;
	}

	public BuildingType getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}
	
	public Shadow getShadow() {
		return shadow;
	}
	
	public void setShadow(Shadow shadow) {
		this.shadow = shadow;
	}
}
