package de.svdragster.tankfever;

import de.svdragster.tankfever.entities.GameObject;
import de.svdragster.tankfever.entities.GameObjectType;
import de.svdragster.tankfever.entities.Player;
import de.svdragster.tankfever.entities.polygons.TankPolygon;
import de.svdragster.tankfever.ui.TButton;
import de.svdragster.tankfever.ui.UIHandler;
import de.svdragster.tankfever.ui.UIObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Created by Sven on 10.02.2017.
 */
public class MouseInput extends MouseAdapter {

	private Handler handler;
	private UIHandler uiHandler;

	public MouseInput(Handler handler, UIHandler uiHandler) {
		this.handler = handler;
		this.uiHandler = uiHandler;
		new MouseWheel();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		final int click = e.getButton();
		if (click == MouseEvent.BUTTON1) {
			final UIObject uiObject = uiHandler.getInAABB(e.getX(), e.getY());
			if (uiObject != null) {
				if (uiObject instanceof TButton) {
					((TButton) uiObject).onClick();
				}
				return;
			}
		}
		final int x = (int) ((e.getX() + Game.camera.getX()) / Game.camera.getZoom()), y = (int) ((e.getY() + Game.camera.getY()) / Game.camera.getZoom());
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
						if (Game.getSelectionObject() == null) {
							mouse = handler.pnpoly(x, y);
							if (mouse == null) {
								mouse = new TankPolygon(x, y, 0, 0, GameObjectType.Polygon);
								handler.addObject(mouse);
								((TankPolygon) mouse).getVertx().add(x);
								((TankPolygon) mouse).getVerty().add(y);
							}
							mouse.setSelected(true);
							Game.setSelectionObject(mouse);
						} else {
							((TankPolygon) Game.getSelectionObject()).getVertx().add(x);
							((TankPolygon) Game.getSelectionObject()).getVerty().add(y);
						}
					/*} else {
						Game.selectionObject = mouse;
					}*/
				}
			}
		} else if (click == MouseEvent.BUTTON3) {
			Game.setSelectionObject(null);
			for (GameObject gameObject : handler.getObjects()) {
				if (gameObject.isSelected()) {
					gameObject.setSelected(false);
				}
			}
		}
	}
}

class MouseWheel implements MouseWheelListener {
	public MouseWheel() {
		Game.getInstance().addMouseWheelListener(this);
	}

	public void mouseWheelMoved(final MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		if (notches < 0) {
			Game.camera.setZoomSpeed(0.015F);
		} else {
			Game.camera.setZoomSpeed(-0.015F);
		}
	}
}