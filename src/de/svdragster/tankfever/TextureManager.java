package de.svdragster.tankfever;

import de.svdragster.tankfever.gamestate.GameStateType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sven on 04.03.2017.
 */
public class TextureManager {

	private BufferedImage txSandGrass;
	private BufferedImage txSandGrassMosaik;
	private BufferedImage[] txWater;

	public void loadTextures() {
		Game game = Game.getInstance();
		try {
			System.out.println("start");
			game.setMaxLoadProgress(6);
			game.setLoadProgress(0);
			txSandGrass = ImageIO.read(new File("resources/sand_grass_dark2.jpg"));
			game.setLoadProgress(game.getLoadProgress() + 1);
			txSandGrassMosaik = ImageIO.read(new File("resources/sand_grass_mosaik.jpg"));
			game.setLoadProgress(game.getLoadProgress() + 1);
			txWater = new BufferedImage[4];
			txWater[0] = ImageIO.read(new File("resources/water1.jpg"));
			game.setLoadProgress(game.getLoadProgress() + 1);
			txWater[1] = ImageIO.read(new File("resources/water2.jpg"));
			game.setLoadProgress(game.getLoadProgress() + 1);
			txWater[2] = ImageIO.read(new File("resources/water3.jpg"));
			game.setLoadProgress(game.getLoadProgress() + 1);
			txWater[3] = ImageIO.read(new File("resources/water4.jpg"));
			game.setLoadProgress(game.getMaxLoadProgress());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (Game.getInstance().getGameState().getType() == GameStateType.Load) {
			Game.getInstance().changeState(GameStateType.Map);
		}
	}

	public BufferedImage getTxSandGrass() {
		return txSandGrass;
	}

	public BufferedImage getTxSandGrassMosaik() {
		return txSandGrassMosaik;
	}

	public BufferedImage[] getTxWater() {
		return txWater;
	}
}
