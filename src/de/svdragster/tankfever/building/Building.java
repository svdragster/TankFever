package de.svdragster.tankfever.building;

import de.svdragster.tankfever.Location;
import de.svdragster.tankfever.tribes.Tribe;

import java.awt.*;

/**
 * Created by Sven on 06.03.2017.
 */
public abstract class Building {

	private Tribe tribeOwner;
	private Location location;

	public Building(Tribe tribeOwner, Location location) {
		this.tribeOwner = tribeOwner;
		this.location = location;
	}

	public abstract void tick();
	public abstract void render(Graphics g);

}
