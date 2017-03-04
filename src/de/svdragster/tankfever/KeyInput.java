package de.svdragster.tankfever;

import de.svdragster.tankfever.entities.GameObject;
import de.svdragster.tankfever.entities.GameObjectType;
import de.svdragster.tankfever.entities.Player;
import de.svdragster.tankfever.entities.polygons.TankPolygon;
import de.svdragster.tankfever.gamestate.GameStateType;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Sven on 10.02.2017.
 */
public class KeyInput extends KeyAdapter {

	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		final int key = e.getKeyCode();

		for (GameObject gameObject : handler.getObjects()) {
			if (gameObject.getType() == GameObjectType.Player) {
				final Player player = (Player) gameObject;
				if (key == KeyEvent.VK_W) {
					player.setaY(-1);
				} else if (key == KeyEvent.VK_S) {
					player.setaY(1);
				}
				if (key == KeyEvent.VK_A) {
					player.setaX(-1);
				} else if (key == KeyEvent.VK_D) {
					player.setaX(1);
				}
			}
		}
		if (key == KeyEvent.VK_X) {
			Game.camera.setZoomSpeed(0.01F);
		} else if (key == KeyEvent.VK_Y) {
			Game.camera.setZoomSpeed(-0.01F);
		} else if (key == KeyEvent.VK_W) {
			Game.camera.setDirectionY(-1);
			//moveCamera(0, -6);
		} else if (key == KeyEvent.VK_S) {
			//moveCamera(0, 6);
			Game.camera.setDirectionY(1);
		} else if (key == KeyEvent.VK_A) {
			//moveCamera(-6, 0);
			Game.camera.setDirectionX(-1);
		} else if (key == KeyEvent.VK_D) {
			//moveCamera(6, 0);
			Game.camera.setDirectionX(1);
		}
		if (key == KeyEvent.VK_1) {
			Game.selection = 1;
		} else if (key == KeyEvent.VK_2) {
			Game.selection = 2;
		} else if (key == KeyEvent.VK_3) {
			Game.selection = 3;
		} else if (key == KeyEvent.VK_4) {
			Game.selection = 4;
		} else if (key == KeyEvent.VK_5) {
			Game.selection = 5;
		} else if (key == KeyEvent.VK_6) {
			Game.selection = 6;
		} else if (key == KeyEvent.VK_7) {
			Game.selection = 7;
		} else if (key == KeyEvent.VK_8) {
			Game.selection = 8;
		} else if (key == KeyEvent.VK_9) {
			Game.selection = 9;
		} else if (key == KeyEvent.VK_0) {
			Game.selection = 0;
		} else if (key == KeyEvent.VK_ESCAPE) {
			if (Game.getInstance().getGameState().getType() == GameStateType.Menu) {
				Game.getInstance().changeState(Game.getInstance().getLastGameState());
			} else {
				Game.getInstance().changeState(GameStateType.Menu);
			}
		} else if (key == KeyEvent.VK_BACK_SPACE) {
			if (Game.getSelectionObject() != null && Game.getSelectionObject() instanceof TankPolygon) {
				final TankPolygon polygon = (TankPolygon) Game.getSelectionObject();
				if (polygon.getVertx().size() == 0) {
					handler.removeObject(polygon);
					Game.setSelectionObject(null);
				} else {
					polygon.getVertx().removeLast();
					polygon.getVerty().removeLast();
				}
			}
		} else if (key == KeyEvent.VK_DELETE) {
			if (Game.getSelectionObject() != null && Game.getSelectionObject() instanceof TankPolygon) {
				final TankPolygon polygon = (TankPolygon) Game.getSelectionObject();
				handler.removeObject(polygon);
				Game.setSelectionObject(null);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		final int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			Game.camera.setDirectionY(0);
			//moveCamera(0, -6);
		} else if (key == KeyEvent.VK_S) {
			//moveCamera(0, 6);
			Game.camera.setDirectionY(0);
		} else if (key == KeyEvent.VK_A) {
			//moveCamera(-6, 0);
			Game.camera.setDirectionX(0);
		} else if (key == KeyEvent.VK_D) {
			//moveCamera(6, 0);
			Game.camera.setDirectionX(0);
		}
		for (GameObject gameObject : handler.getObjects()) {
			if (gameObject.getType() == GameObjectType.Player) {
				final Player player = (Player) gameObject;
				if (key == KeyEvent.VK_W) {
					player.setaY(0);
				} else if (key == KeyEvent.VK_S) {
					player.setaY(0);
				}
				if (key == KeyEvent.VK_A) {
					player.setaX(0);
				} else if (key == KeyEvent.VK_D) {
					player.setaX(0);
				}
			}
		}
	}



	private void move(final GameObject gameObject, final int x, final int y) {

	}

	private void moveAll(final int x, final int y) {
		for (GameObject gameObject : handler.getObjects()) {
			move(gameObject, x, y);
		}
	}

	private void moveCamera(final int x, final int y) {
		Game.camera.setMotionX(x);
		Game.camera.setMotionY(y);
	}
}
