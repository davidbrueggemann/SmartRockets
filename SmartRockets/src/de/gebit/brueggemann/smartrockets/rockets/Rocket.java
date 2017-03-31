/**
 * 
 */
package de.gebit.brueggemann.smartrockets.rockets;

import java.awt.Color;

import de.gebit.brueggemann.smartrockets.space.planets.AbstractPlanet;
import de.gebit.brueggemann.smartrockets.util.Vector2D;

/**
 * @author DavidWork
 *
 */
public class Rocket {
	private RocketStatus status;
	private boolean hitTarget = false;
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

	public void fly(AbstractPlanet targetPlanet) {
		reachedPlanet(targetPlanet);
		if (!hitTarget) {
			addForce(smart.getGenes()[geneNumber++]);
			status.updateLocalization();
			geneNumber %= smart.getGenes().length;
		}
	}

	public double calculateFitness(AbstractPlanet targetPlanet) {
		return 1 / Vector2D.distance(getLocalization(), targetPlanet.getLocalization());
	}

	/**
	 * @return the hitTarget
	 */
	public boolean reachedPlanet(AbstractPlanet targetPlanet) {
		if (!hitTarget) {
			if (status.getLocation().distance(targetPlanet.getLocalization().add(
					new Vector2D(targetPlanet.getPlanetSize() / 2, targetPlanet.getPlanetSize() / 2))) < targetPlanet
							.getPlanetSize() / 2)
				hitTarget = true;
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
