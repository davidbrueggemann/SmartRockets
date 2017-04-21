/**
 * 
 */
package de.gebit.brueggemann.smartrockets.rockets;

import java.awt.Color;
import java.util.List;

import de.gebit.brueggemann.smartrockets.space.obstacles.AbstractObstacle;
import de.gebit.brueggemann.smartrockets.space.planets.AbstractPlanet;
import de.gebit.brueggemann.smartrockets.util.Vector2D;

/**
 * @author DavidWork
 *
 */
public class Rocket {
	private RocketStatus status;
	private boolean hitTarget = false;
	private boolean hitObstangle = false;
	private boolean hitEdge = false;
	private Vector2D graphicalSize = new Vector2D(3, 8);
	private Smart smart;
	private int geneNumber = 0;

	public Rocket(Vector2D initialLocation, Smart smart) {
		status = new RocketStatus(initialLocation);
		this.smart = smart;
	}

	public void addForce(Vector2D force) {
		status.getAcceleration().add(force);
	}

	public Vector2D getLocalization() {
		return status.getLocation();
	}

	public void fly(AbstractPlanet targetPlanet, List<AbstractObstacle> obstacleList) {
		hittedObstacle(obstacleList);
		hittedSpaceEdge();
		reachedPlanet(targetPlanet);
		if (!hitTarget && !hitObstangle && !hitEdge) {
			addForce(smart.getGenes()[geneNumber++]);
			status.updateLocalization();
			geneNumber %= smart.getGenes().length;
		}
	}

	public double calculateFitness(AbstractPlanet targetPlanet, List<AbstractObstacle> obstacleList) {
		// Reward finishing faster and getting close
		double tempFitness = 1 / (status.getFinishTime());

		tempFitness = Math.pow(tempFitness, 4);

		if (hittedObstacle(obstacleList) || hittedSpaceEdge())
			tempFitness = 0; // lose of fitness hitting an obstacle
		else if (reachedPlanet(targetPlanet))
			tempFitness *= 2; // twice the fitness for finishing!

		tempFitness += 1 / getLocalization().distance(targetPlanet.getLocalization());

		return tempFitness;
	}

	public boolean hittedObstacle(List<AbstractObstacle> obstacleList) {
		if (!hitObstangle) {
			for (AbstractObstacle abstractObstacle : obstacleList) {
				hitObstangle = abstractObstacle.getDistanceToObstacle(getLocalization());
			}
		}
		return hitObstangle;
	}

	// TODO naming?
	public boolean hittedSpaceEdge() {
		if (!hitEdge) {
			// TODO CONSTANTS!!
			if (getLocalization().getX() < 2 || getLocalization().getX() > 845 || getLocalization().getY() < 2
					|| getLocalization().getY() > 845) {
				hitEdge = true;
			}
		}
		return hitEdge;
	}

	/**
	 * @return the hitTarget
	 */
	public boolean reachedPlanet(AbstractPlanet targetPlanet) {
		if (!hitTarget) {
			if (status.getLocation().distance(targetPlanet.getLocalization().add(
					new Vector2D(targetPlanet.getPlanetSize() / 2, targetPlanet.getPlanetSize() / 2))) < targetPlanet
							.getPlanetSize() / 2) {
				hitTarget = true;
				status.setFinishTime();
			}
		}

		return hitTarget;
	}

	/**
	 * @return the graphicalSize
	 */
	public Vector2D getGraphicalSize() {
		return graphicalSize;
	}

	public static Color getColor() {
		return Color.BLACK;
	}

	/**
	 * @return the smart
	 */
	public Smart getSmart() {
		return smart;
	}
}
