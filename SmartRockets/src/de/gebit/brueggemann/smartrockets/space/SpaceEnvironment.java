/**
 * 
 */
package de.gebit.brueggemann.smartrockets.space;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;

import de.gebit.brueggemann.smartrockets.rockets.Rocket;
import de.gebit.brueggemann.smartrockets.rockets.Smart;
import de.gebit.brueggemann.smartrockets.space.obstacles.AbstractObstacle;
import de.gebit.brueggemann.smartrockets.space.obstacles.StarDestroyer;
import de.gebit.brueggemann.smartrockets.space.planets.Earth;
import de.gebit.brueggemann.smartrockets.space.planets.ExoPlanet;

/**
 * @author DavidWork
 *
 */
public class SpaceEnvironment extends javax.swing.JPanel {

	private int spaceSize = 100;
	private Earth earth;
	private ExoPlanet exoPlanet;

	private StarDestroyer starDestroyer;
	private List<AbstractObstacle> obstacleList = new ArrayList<>();
	private Thread timeThread;

	private int cycleNumber = 0;
	private static final int LIFETIME = 500;
	// See Mutation Rate Wikipedia ==> 0.5 * Math.pow(10, -9)
	private static final double MUTATION_RATE = 0.5 * Math.pow(10, -2);

	private int rocketPoolSize = 10;
	private List<Rocket> rocketPool;
	private int generation = 1;

	public SpaceEnvironment(int aSpaceSize, int amountOfRockets) {
		// Initital Space
		spaceSize = aSpaceSize;
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.WHITE);

		// initial Planets
		earth = new Earth(aSpaceSize / 2, (int) (aSpaceSize - aSpaceSize * 0.1));
		exoPlanet = new ExoPlanet(aSpaceSize / 2, (int) (aSpaceSize * 0.1));

		// Star Destroyer as Obstangle
		starDestroyer = new StarDestroyer((int) (aSpaceSize * 0.25), (int) (aSpaceSize * 0.75));
		obstacleList.add(starDestroyer);

		// initial Rockets
		rocketPoolSize = amountOfRockets;
		rocketPool = new ArrayList<Rocket>();

		for (int i = 0; i < rocketPoolSize; i++) {
			// generate a random localization on earth (starting point)

			rocketPool.add(new Rocket(earth.getRandomLocalizationOnPlanet(), new Smart()));
		}
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		// Draw Text information
		g.drawString("Generation: " + generation, 10, 18);
		g.drawString("Cycles to next gen: " + (LIFETIME - cycleNumber), 10, 36);

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

		// Draw Obstangle
		g.setColor(starDestroyer.getObstacleColor());
		g.fillPolygon(starDestroyer.getGraphics());

		// Fly action
		if (cycleNumber < LIFETIME) {
			for (Rocket rocket : rocketPool) {
				rocket.fly(exoPlanet, obstacleList);
			}
			cycleNumber++;
		} else {
			// TODO extract to methods

			// reset cycle number
			cycleNumber = 0;

			// FITNESS
			// calculate the average Fitness for the Selection
			ArrayList<Rocket> tempSelectionPool = new ArrayList<>();
			List<Double> tempFitnessValues = new ArrayList<>();
			double tempAverageFitness = 0;
			double tempSumOfFitness = 0;
			for (Rocket rocket : rocketPool) {
				double tempCurrentFitness = rocket.calculateFitness(exoPlanet, obstacleList);
				tempFitnessValues.add(tempCurrentFitness);
				tempSumOfFitness += tempCurrentFitness;
			}
			tempAverageFitness = tempSumOfFitness / tempFitnessValues.size();

			// SELECTION
			for (Rocket rocket : rocketPool) {
				double tempCurrentFitness = rocket.calculateFitness(exoPlanet, obstacleList);
				if (tempCurrentFitness >= tempAverageFitness) {
					tempSelectionPool.add(rocket);
				}
			}

			// REPRODUCTION
			for (int i = 0; i < rocketPoolSize; i++) {
				Rocket tempMumRocket = tempSelectionPool
						.get((int) (Math.random() * rocketPoolSize) % tempSelectionPool.size());
				Rocket tempDadRocket;
				do {
					tempDadRocket = tempSelectionPool
							.get((int) (Math.random() * rocketPoolSize) % tempSelectionPool.size());
				} while (tempDadRocket.equals(tempMumRocket));
				Smart tempChildSmartness = tempMumRocket.getSmart().crossover(tempDadRocket.getSmart());
				tempChildSmartness.mutate(MUTATION_RATE);
				rocketPool.set(i, new Rocket(earth.getRandomLocalizationOnPlanet(), tempChildSmartness));
			}
			generation++;
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
