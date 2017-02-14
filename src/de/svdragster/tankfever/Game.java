package de.svdragster.tankfever;

import de.svdragster.tankfever.entities.DebugText;
import de.svdragster.tankfever.entities.GameObject;
import de.svdragster.tankfever.entities.GameObjectType;
import de.svdragster.tankfever.entities.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * Created by Sven on 06.02.2017.
 */
public class Game extends Canvas implements Runnable {

	private static Game instance;

	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;

	public static int lastFrames = 0;

	private Thread thread;
	private boolean running = false;

	private Random random;
	private Handler handler;

	public static int selection = 0;
	public static GameObject selectionObject = null;

	public Game() {
		instance = this;

		new Window(WIDTH, HEIGHT, "Tank Game", this);

		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler));


		random = new Random();

		handler.addObject(new DebugText(0, 10, 0, 0, GameObjectType.Debug));
		handler.addObject(new Player(0, 10, 32, 32, GameObjectType.Player));
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
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		final double ns = 1_000_000_000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		//////////////////
		// de.svdragster.tankfever.Game Loop Start
		//////////////////

		while (running) {

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}

			if (running) {
				render();
			}

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

	private void tick() {
		handler.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(new Color(23, 22, 46));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		g.dispose();
		bs.show();
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
}
