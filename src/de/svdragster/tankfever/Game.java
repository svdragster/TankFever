package de.svdragster.tankfever;

import de.svdragster.tankfever.building.BuildingManager;
import de.svdragster.tankfever.building.BuildingType;
import de.svdragster.tankfever.entities.DebugText;
import de.svdragster.tankfever.entities.GameObject;
import de.svdragster.tankfever.gamestate.GameState;
import de.svdragster.tankfever.gamestate.GameStateType;
import de.svdragster.tankfever.gamestate.LoadState;
import de.svdragster.tankfever.gamestate.MenuState;
import de.svdragster.tankfever.gamestate.mapstate.MapState;
import de.svdragster.tankfever.gamestate.playstate.PlayState;
import de.svdragster.tankfever.ui.Camera;
import de.svdragster.tankfever.ui.UIHandler;
import de.svdragster.tankfever.units.UnitManager;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Sven on 06.02.2017.
 */
public class Game extends Canvas implements Runnable {

	private static Game instance;

	public static final int WIDTH = 1200;
	public static final int HEIGHT = WIDTH / 16 * 9;

	public static int lastFrames = 0;
	//public static float zoom = 1.5F;
	public static Camera camera;

	private Thread thread;
	public boolean running = false;

	private Renderer renderer;

	private Random random;
	private Handler handler;
	private UIHandler uiHandler;
	private UnitManager unitManager;
	private static TextureManager textureManager = new TextureManager();
	private SoundManager soundManager;
	
	private BuildingManager buildingManager;

	public static int selection = 3;
	private static GameObject selectionObject = null;

	private GameState gameState;
	private GameStateType lastGameState = GameStateType.Menu;
	private int loadProgress = 0;
	private int maxLoadProgress = 0;

	private boolean finishedStartup = false;

	public MouseInput mouseInput;

	private Window window;

	public Game() {
		instance = this;

		this.window = new Window(WIDTH, HEIGHT, "", this);

		unitManager = new UnitManager(this);

		handler = new Handler();
		uiHandler = new UIHandler();


		uiHandler.addObject(new DebugText(0, 10, 0, 0, true));

		changeState(GameStateType.Load);
		camera = new Camera(0, 0, WIDTH, HEIGHT, false);
		this.renderer = new Renderer(this);
		start();
		uiHandler.addObject(camera);
		this.mouseInput = new MouseInput(this.handler, this.uiHandler);
		this.addKeyListener(new KeyInput(this.handler));
		this.addMouseListener(this.mouseInput);
		this.addMouseMotionListener(new MouseMotion(this.mouseInput));
		this.finishedStartup = true;
		//this.soundManager = new SoundManager();
		textureManager.loadTextures();



		random = new Random();

		getUnitManager().moveSelectedUnits(500, 500);

		for (GameObject gameObject : handler.getObjects()) {
			gameObject.setSelected(false);
		}
		
		buildingManager = new BuildingManager();
		
		//final Headquarter headquarter = new Headquarter(200, 200, 70, 50, GameObjectType.Building, null, BuildingType.Headquarter);
		//getHandler().addObject(headquarter);
		BuildingManager.build(200, 200, 70, 50, null, BuildingType.Headquarter);

		//handler.addObject(new Player(0, 10, 32, 32, GameObjectType.Player));
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (!finishedStartup) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long lastTime = System.nanoTime();
		final double amountOfTicks = 80.0;
		final double ns = 1_000_000_000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		//////////////////
		// Game Loop Start
		//////////////////

		while (running) {
			long now = System.nanoTime();
			delta += ((double) now - (double) lastTime) / ns;
			lastTime = now;
			while (delta >= 0.3) {
				tick(0.3);
				delta -= 0.3;
			}
			render();

			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS --> " + frames);
				lastFrames = frames;
				frames = 0;
			}
		}

		//////////////////
		// de.svdragster.tankfever.Game Loop End
		//////////////////

		stop();
	}

	private void tick(final double delta) {
		gameState.tick(delta);
	}

	public void render() {
		if (!Game.getInstance().isFinishedStartup()) {
			return;
		}
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		final Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		g.setColor(new Color(23, 22, 46));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		if (getTextureManager().getTxSandGrass() != null) {
			g.setPaint(new TexturePaint(getTextureManager().getTxSandGrass(), new Rectangle(-Game.camera.getX(), -Game.camera.getY(), (int) (512 * Game.camera.getZoom()), (int) (512 * Game.camera.getZoom()))));
			g.fillRect(-Game.camera.getX(), -Game.camera.getY(), (int) (1000 * Game.camera.getZoom()), (int) (1000 * Game.camera.getZoom()));
		}
		g.setColor(new Color(0xAA, 0, 0));
		g.drawRect(- Game.camera.getX(), - Game.camera.getY(), (int) (1000 * Game.camera.getZoom()), (int) (1000 * Game.camera.getZoom()));
		gameState.render(g);

		if (this.mouseInput.getMouseHold() != 0) {
			if (mouseInput.getMouseHoldPos() != null) {
				g.setColor(new Color(0xFF, 0xFF, 0xFF));
				//final int x = (int) ((mouseInput.getMouseHoldPos().getX() + Game.camera.getX()) / Game.camera.getZoom()), y = (int) ((mouseInput.getMouseHoldPos().getY() + Game.camera.getY()) / Game.camera.getZoom());
				//g.drawRect(mouseInput.getMouseHoldPos().getX(), mouseInput.getMouseHoldPos().getY(), mouseInput.getMouseHoldStartPos().getX() - mouseInput.getMouseHoldPos().getX(), mouseInput.getMouseHoldStartPos().getY() - mouseInput.getMouseHoldPos().getY());
				int maxX = Math.max(mouseInput.getMouseHoldPos().getX(), mouseInput.getMouseHoldStartPos().getX());
				int maxY = Math.max(mouseInput.getMouseHoldPos().getY(), mouseInput.getMouseHoldStartPos().getY());
				int minX = Math.min(mouseInput.getMouseHoldPos().getX(), mouseInput.getMouseHoldStartPos().getX());
				int minY = Math.min(mouseInput.getMouseHoldPos().getY(), mouseInput.getMouseHoldStartPos().getY());
				g.drawRect(minX, minY, maxX - minX, maxY - minY);
			}
		}

		g.dispose();
		bs.show();
	}

	private Map<GameStateType, GameState> gameStates = new HashMap<>();
	
	public void changeState(final GameStateType type) {
		if (gameState != null) {
			lastGameState = gameState.getType();
			gameState.vanish();
		}
		if (gameStates.containsKey(type)) {
			gameState = gameStates.get(type);
		} else {
			switch (type) {
				case Menu:
					gameState = new MenuState(type);
					break;
				case Play:
					gameState = new PlayState(type);
					break;
				case Map:
					gameState = new MapState(type);
					break;
				case Load:
					gameState = new LoadState(type);
					break;
			}
			gameStates.put(type, gameState);
		}
		gameState.init();
	}

	public GameState getGameState() {
		return gameState;
	}

	public static void main(String args[]) {
		new Game();
	}

	public static Game getInstance() {
		return instance;
	}

	public static void setInstance(Game instance) {
		Game.instance = instance;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public UIHandler getUiHandler() {
		return uiHandler;
	}

	public void setUiHandler(UIHandler uiHandler) {
		this.uiHandler = uiHandler;
	}

	public static GameObject getSelectionObject() {
		return selectionObject;
	}

	public static void setSelectionObject(GameObject selectionObject) {
		if (selectionObject != null) {
			selectionObject.setChanged(true);
		} else if (Game.selectionObject != null) {
			Game.selectionObject.setChanged(true);
		}
		Game.selectionObject = selectionObject;
	}

	public GameStateType getLastGameState() {
		return lastGameState;
	}

	public static TextureManager getTextureManager() {
		return textureManager;
	}

	public int getLoadProgress() {
		return loadProgress;
	}

	public void setLoadProgress(int loadProgress) {
		this.loadProgress = loadProgress;
	}

	public int getMaxLoadProgress() {
		return maxLoadProgress;
	}

	public void setMaxLoadProgress(int maxLoadProgress) {
		this.maxLoadProgress = maxLoadProgress;
	}

	public UnitManager getUnitManager() {
		return unitManager;
	}

	public boolean isFinishedStartup() {
		return finishedStartup;
	}

	public SoundManager getSoundManager() {
		return soundManager;
	}
	
	public BuildingManager getBuildingManager() {
		return buildingManager;
	}
}
