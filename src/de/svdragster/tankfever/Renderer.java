package de.svdragster.tankfever;

/**
 * Created by Sven on 19.03.2017.
 */
public class Renderer extends Thread {

	private Game game;
	private long milli = System.currentTimeMillis();
	public static boolean startRendering = true;


	public Renderer(Game game) {
		this.game = game;
		System.out.println("starting");
		//System.setProperty("sun.java2d.opengl","True");
		//this.start();
	}

	@Override
	public void run() {
		int frames = 0;
		long timer = System.currentTimeMillis();
		while (!this.game.running) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while (this.game.running) {
			long now = System.currentTimeMillis();
			if (now - this.milli >= 17) {
				this.game.render();
				this.milli = now;
				frames++;

				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					System.out.println("FPS --> " + frames);
					Game.lastFrames = frames;
					frames = 0;
				}
			}
		}
	}


}
