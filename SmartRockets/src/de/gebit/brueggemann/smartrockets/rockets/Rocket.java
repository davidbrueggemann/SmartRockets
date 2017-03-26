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

	public Rocket(Vector2D initialLocation /* add SMART */) {
		status = new RocketStatus(initialLocation);
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
			// TODO implement SMART
			addForce(new Vector2D(Math.random() - Math.random(), Math.random() - Math.random()));
			status.updateLocalization();
		}
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
}
