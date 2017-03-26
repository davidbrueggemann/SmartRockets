/**
 * 
 */
package de.gebit.brueggemann.smartrockets.space;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;

import de.gebit.brueggemann.smartrockets.rockets.Rocket;
import de.gebit.brueggemann.smartrockets.space.planets.Earth;
import de.gebit.brueggemann.smartrockets.space.planets.ExoPlanet;
import de.gebit.brueggemann.smartrockets.util.Vector2D;

/**
 * @author DavidWork
 *
 */
public class SpaceEnvironment extends javax.swing.JPanel {

	private int spaceSize = 100;
	private Earth earth;
	private ExoPlanet exoPlanet;

	private Thread timeThread;

	private int rocketPoolSize = 10;
	private ArrayList<Rocket> rocketPool;

	public SpaceEnvironment(int aSpaceSize, int amountOfRockets) {
		// Initital Space
		spaceSize = aSpaceSize;
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.WHITE);

		// initial Planets
		earth = new Earth(aSpaceSize / 2, (int) (aSpaceSize - aSpaceSize * 0.1));
		exoPlanet = new ExoPlanet(aSpaceSize / 2, (int) (aSpaceSize * 0.1));

		// initial Rockets
		rocketPoolSize = amountOfRockets;
		rocketPool = new ArrayList<Rocket>();
		Random random = new Random();

		for (int i = 0; i < rocketPoolSize; i++) {
			// generate a random localization on earth (starting point)
			// TODO Rockets should start >>ON<< the planet
			int max_x = earth.getX_pos() + earth.getPlanetSize();
			int min_x = earth.getX_pos();
			int max_y = earth.getY_pos() + earth.getPlanetSize();
			int min_y = earth.getY_pos();

			int x = random.nextInt(max_x - min_x + 1) + min_x;
			int y = random.nextInt(max_y - min_y + 1) + min_y;

			rocketPool.add(new Rocket(new Vector2D(x, y)));
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw Earth (starting point)
		g.setColor(earth.getPlanetColor());
		g.fillOval(earth.getX_pos(), earth.getY_pos(), earth.getSize(), earth.getSize());
		// Draw ExoPlanet (end point)
		g.setColor(exoPlanet.getPlanetColor());
		g.fillOval(exoPlanet.getX_pos(), exoPlanet.getY_pos(), exoPlanet.getSize(), exoPlanet.getSize());

		// Draw Rockets with current localization
		g.setColor(Rocket.getColor());
		for (Rocket rocket : rocketPool) {
			g.fillRect((int) rocket.getLocalization().getX(), (int) rocket.getLocalization().getY(),
					(int) rocket.getGraphicalSize().getX(), (int) rocket.getGraphicalSize().getY());
		}

		for (Rocket rocket : rocketPool) {
			rocket.fly(exoPlanet);
		}

	}

	public void startTime() {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// ignore
					}
				}
			}
		};
		timeThread = new Thread(runnable);
		timeThread.start();
	}

	public void stopTime() {
		timeThread.interrupt();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(spaceSize, spaceSize);
	}

}
