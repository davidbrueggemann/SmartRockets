/**
 * 
 */
package de.gebit.brueggemann.smartrockets.rockets;

import de.gebit.brueggemann.smartrockets.util.Vector2D;

/**
 * @author DavidWork
 *
 */
public class RocketStatus {
	private Vector2D location;
	private Vector2D velocity;
	private Vector2D acceleration;

	public RocketStatus(Vector2D initialLocation) {
		location = initialLocation;
		velocity = Vector2D.getNullVector();
		acceleration = Vector2D.getNullVector();
	}

	public void updateLocalization() {
		velocity.add(acceleration);
		location.add(velocity);
		acceleration.mult(0);
	}

	/**
	 * @return the location
	 */
	public Vector2D getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Vector2D location) {
		this.location = location;
	}

	/**
	 * @return the velocity
	 */
	public Vector2D getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity
	 *            the velocity to set
	 */
	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	/**
	 * @return the acceleration
	 */
	public Vector2D getAcceleration() {
		return acceleration;
	}

	/**
	 * @param acceleration
	 *            the acceleration to set
	 */
	public void setAcceleration(Vector2D acceleration) {
		this.acceleration = acceleration;
	}

}
