package de.svdragster.tankfever;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sven on 06.02.2017.
 */
public class Window extends Canvas {

	private Game game;
	private JFrame frame;

	public Window(int w, int h, String title, Game game) {
		this.game = game;
		frame = new JFrame(title);

		final Dimension dimension = new Dimension(w, h);
		frame.setPreferredSize(dimension);
		frame.setMaximumSize(dimension);
		frame.setMinimumSize(dimension);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}
}
