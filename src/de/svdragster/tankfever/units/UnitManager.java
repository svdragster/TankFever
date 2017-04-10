package de.svdragster.tankfever.units;

import de.svdragster.tankfever.Game;
import de.svdragster.tankfever.Location;
import de.svdragster.tankfever.entities.GameObject;
import de.svdragster.tankfever.entities.Unit;
import de.svdragster.tankfever.ui.clickanimations.MoveUnitAnimation;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Sven on 19.03.2017.
 */
public class UnitManager {

	private Game game;

	public UnitManager(Game game) {
		this.game = game;
	}

	public void moveSelectedUnits(int x, int y) {
		final Queue<Unit> selectedUnits = new LinkedList<>();
		for (GameObject gameObject : game.getHandler().getObjects()) {
			if (gameObject.isSelected()) {
				if (gameObject instanceof Unit) {
					selectedUnits.add((Unit) gameObject);
				}
			}
		}
		if (selectedUnits.size() == 0) {
			return;
		}
		game.getUiHandler().addObject(new MoveUnitAnimation(x, y, 30, 30, true));
		int amount = (int) Math.sqrt(selectedUnits.size()) + 1;
		int row = -amount/2, col = -amount/2;
		for (Unit unit : selectedUnits) {
			if (col >= amount/2) {
				col = -amount/2;
				row++;
			}
			unit.setDestination(new Location(x + col*10, y + row*10));
			col++;
		}
	}
}
