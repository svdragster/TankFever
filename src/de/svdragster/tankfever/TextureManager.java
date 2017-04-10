package de.svdragster.tankfever;

import de.svdragster.tankfever.entities.polygons.TerrainType;
import de.svdragster.tankfever.gamestate.GameStateType;
import de.svdragster.tankfever.ui.TText;

import javax.imageio.ImageIO;
import java.awt.*;
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
	private BufferedImage[] animUnitMove;

	public void loadTextures() {
		Game game = Game.getInstance();

		final String[] paths = new String[]{
				"resources/sand_grass_dark2.jpg",
				"resources/sand_grass_mosaik.jpg",
				"resources/water1.jpg",
				"resources/water2.jpg",
				"resources/water3.jpg",
				"resources/water4.jpg",
				"resources/water_mosaik.jpg",
				"resources/unit.png",
				"resources/anim_unit1.png",
				"resources/anim_unit2.png",
				"resources/anim_unit3.png",
				"resources/anim_unit4.png",
				"resources/anim_unit5.png",
				"resources/anim_unit6.png",
				"resources/anim_unit7.png",
				"resources/anim_unit8.png",
		};

		try {
			game.setMaxLoadProgress(9);
			game.setLoadProgress(0);
			txSandGrass = toCompatibleImage(ImageIO.read(new File("resources/sand_grass_dark2.jpg")));
			game.setLoadProgress(game.getLoadProgress() + 1);
			txSandGrassMosaik = toCompatibleImage(ImageIO.read(new File("resources/sand_grass_mosaik.jpg")));
			game.setLoadProgress(game.getLoadProgress() + 1);
			txWater = new BufferedImage[12];
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

			animUnitMove = new BufferedImage[8];
			game.setLoadProgress(game.getLoadProgress() + 1);
			animUnitMove[0] = toCompatibleImage(ImageIO.read(new File("resources/anim_unit1.png")));
			game.setLoadProgress(game.getLoadProgress() + 1);
			animUnitMove[1] = toCompatibleImage(ImageIO.read(new File("resources/anim_unit2.png")));
			game.setLoadProgress(game.getLoadProgress() + 1);
			animUnitMove[2] = toCompatibleImage(ImageIO.read(new File("resources/anim_unit3.png")));
			game.setLoadProgress(game.getLoadProgress() + 1);
			animUnitMove[3] = toCompatibleImage(ImageIO.read(new File("resources/anim_unit4.png")));
			game.setLoadProgress(game.getLoadProgress() + 1);
			animUnitMove[4] = toCompatibleImage(ImageIO.read(new File("resources/anim_unit5.png")));
			game.setLoadProgress(game.getLoadProgress() + 1);
			animUnitMove[5] = toCompatibleImage(ImageIO.read(new File("resources/anim_unit6.png")));
			game.setLoadProgress(game.getLoadProgress() + 1);
			animUnitMove[6] = toCompatibleImage(ImageIO.read(new File("resources/anim_unit7.png")));
			game.setLoadProgress(game.getLoadProgress() + 1);
			animUnitMove[7] = toCompatibleImage(ImageIO.read(new File("resources/anim_unit8.png")));

			game.setLoadProgress(game.getMaxLoadProgress());
		} catch (IOException e) {
			System.err.println("Could not load all textures: " + e.getMessage());
			e.printStackTrace();

			game.getUiHandler().addObject(new TText(Game.WIDTH/2 - 600/2, Game.HEIGHT/2 - 200/2, 600, 200, true, "Error: " + e.getMessage() + " (" + game.getLoadProgress() + ")"));

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.exit(1);
		}
		if (Game.getInstance().getGameState().getType() == GameStateType.Load) {
			Game.getInstance().changeState(GameStateType.Play);
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

	public BufferedImage[] getAnimUnitMove() {
		return animUnitMove;
	}

	public BufferedImage createMap(final Handler handler) {
		final BufferedImage bufferedImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
		return bufferedImage;
	}

	private BufferedImage load(final String path) {
		try {
			return toCompatibleImage(ImageIO.read(new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private BufferedImage toCompatibleImage(BufferedImage image)
	{
		// obtain the current system graphical settings
		GraphicsConfiguration gfx_config = GraphicsEnvironment.
				getLocalGraphicsEnvironment().getDefaultScreenDevice().
				getDefaultConfiguration();

	    /*
	     * if image is already compatible and optimized for current system
	     * settings, simply return it
	     */
		if (image.getColorModel().equals(gfx_config.getColorModel()))
			return image;

		// image is not optimized, so create a new image that is
		BufferedImage new_image = gfx_config.createCompatibleImage(
				image.getWidth(), image.getHeight(), image.getTransparency());

		// get the graphics context of the new image to draw the old image on
		Graphics2D g2d = (Graphics2D) new_image.getGraphics();

		// actually draw the image and dispose of context no longer needed
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();

		// return the new optimized image
		return new_image;
	}
}
