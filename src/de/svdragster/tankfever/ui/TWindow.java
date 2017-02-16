package de.svdragster.tankfever.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 15.02.2017.
 */
public class TWindow extends UIObject{

	private List<TButton> buttons = new ArrayList<>();

	public TWindow(int x, int y, int w, int h, boolean visible) {
		super(x, y, w, h, visible);
	}


	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {

	}
}
