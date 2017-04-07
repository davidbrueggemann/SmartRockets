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

	private long startTime;
	private float finishTime = 0;

	private float recordDistance = 0;

	public RocketStatus(Vector2D initialLocation) {
		location = initialLocation;
		velocity = Vector2D.getNullVector();
		acceleration = Vector2D.getNullVector();

		startTime = System.currentTimeMillis();
	}

	public void updateLocalization() {
		velocity.add(acceleration);
		recordDistance += Vector2D.distance(velocity, location);

		location.add(velocity);

		// TODO Constants!!
		// TODO Extract to Method!!
		if (location.getX() <= 2)
			location = new Vector2D(2.0, location.getY());
		if (location.getX() >= 848)
			location = new Vector2D(848.0, location.getY());
		if (location.getY() <= 2)
			location = new Vector2D(location.getX(), 2.0);
		if (location.getY() >= 848)
			location = new Vector2D(location.getX(), 848.0);

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

	/**
	 * @return the finishTime
	 */
	public float getFinishTime() {
		if (finishTime == 0)
			this.finishTime = (System.currentTimeMillis() - startTime) / 1000;
		return finishTime;
	}

	/**
	 * @param finishTime
	 *            Will be used to set the finish time as seconds(!) in floating
	 *            number based on known start time and current timestamp.
	 */
	public void setFinishTime() {
		this.finishTime = (System.currentTimeMillis() - startTime) / 1000;
	}

	/**
	 * @return the distance
	 */
	public float getRecordDistance() {
		return recordDistance;
	}

}
