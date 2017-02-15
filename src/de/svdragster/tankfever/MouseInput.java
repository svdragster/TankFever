package de.svdragster.tankfever;

import de.svdragster.tankfever.entities.GameObject;
import de.svdragster.tankfever.entities.GameObjectType;
import de.svdragster.tankfever.entities.Player;
import de.svdragster.tankfever.entities.polygons.TankPolygon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Sven on 10.02.2017.
 */
public class MouseInput extends MouseAdapter {

	private Handler handler;

	public MouseInput(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		final int click = e.getButton();
		final int x = e.getX(), y = e.getY();
		final GameObject clickedObject = handler.getInAABB(x, y);
		if (click == MouseEvent.BUTTON1) {
			if (clickedObject != null) {
				System.out.println("Clicked " + clickedObject.getType().name());
			} else {
				if (Game.selection == 1) {
					handler.addObject(new Player(x - 8, y - 8, 16, 16, GameObjectType.Player));
				} else if (Game.selection == 2) {
					GameObject mouse;
					//if (mouse == null) {
						if (Game.selectionObject == null) {
							mouse = handler.pnpoly(x, y);
							if (mouse == null) {
								mouse = new TankPolygon(x, y, 0, 0, GameObjectType.Polygon);
								handler.addObject(mouse);
								((TankPolygon) mouse).getVertx().add(x);
								((TankPolygon) mouse).getVerty().add(y);
							}
							mouse.setSelected(true);
							Game.selectionObject = mouse;

						} else {
							((TankPolygon) Game.selectionObject).getVertx().add(x);
							((TankPolygon) Game.selectionObject).getVerty().add(y);
						}
					/*} else {
						Game.selectionObject = mouse;
					}*/
				}
			}
		} else if (click == MouseEvent.BUTTON3) {
			if (clickedObject != null) {
				handler.getObjects().remove(clickedObject);
			}
		}
	}
}