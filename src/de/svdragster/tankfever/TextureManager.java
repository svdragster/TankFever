package de.svdragster.tankfever;

import de.svdragster.tankfever.entities.polygons.TerrainType;
import de.svdragster.tankfever.gamestate.GameStateType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sven on 04.03.2017.
 */
public class TextureManager {

	private TerrainType selectedTerrainType = TerrainType.GRASS_SAND;

	private BufferedImage txSandGrass;
	private BufferedImage txSandGrassMosaik;
	private BufferedImage[] txWater;
	private BufferedImage txWaterMosaik;
	private BufferedImage txUnit;

	public void loadTextures() {
		Game game = Game.getInstance();
		try {
			game.setMaxLoadProgress(9);
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
			game.setLoadProgress(game.getLoadProgress() + 1);
			txWaterMosaik = ImageIO.read(new File("resources/water_mosaik.jpg"));
			game.setLoadProgress(game.getLoadProgress() + 1);
			txUnit = ImageIO.read(new File("resources/unit.png"));
			game.setLoadProgress(game.getMaxLoadProgress());
		} catch (IOException e) {
			System.err.println("Could not load all textures: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
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

	public TerrainType getSelectedTerrainType() {
		return selectedTerrainType;
	}

	public BufferedImage getTxWaterMosaik() {
		return txWaterMosaik;
	}

	public BufferedImage getTxUnit() {
		return txUnit;
	}

	public void setSelectedTerrainType(TerrainType selectedTerrainType) {
		this.selectedTerrainType = selectedTerrainType;
	}

	public BufferedImage createMap(final Handler handler) {
		final BufferedImage bufferedImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
		return bufferedImage;
	}
}
