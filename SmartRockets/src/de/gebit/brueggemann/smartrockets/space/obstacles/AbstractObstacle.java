/**
 * 
 */
package de.gebit.brueggemann.smartrockets.space.obstacles;

import java.awt.Color;

import de.gebit.brueggemann.smartrockets.util.Vector2D;

/**
 * @author DavidWork
 *
 */
public abstract class AbstractObstacle {
	private int x_pos;
	private int y_pos;
	private int height;
	private int width;

	private Color obstacleColor;

	public AbstractObstacle(int initialLocalization_x, int initialLocalization_y, int height, int width,
			Color initialObstacleColor) {
		x_pos = initialLocalization_x;
		y_pos = initialLocalization_y;
		setHeight(height);
		setWidth(width);
		setObstacleColor(initialObstacleColor);
	}

	public Vector2D getLocalization() {
		return new Vector2D(x_pos, y_pos);
	}
	
	public abstract boolean getDistanceToObstacle(Vector2D point);
	
	/**
	 * @return the x_pos
	 */
	public int getX_pos() {
		return x_pos;
	}

	/**
	 * @param x_pos
	 *            the x_pos to set
	 */
	public void setX_pos(int x_pos) {
		this.x_pos = x_pos;
	}

	/**
	 * @return the y_pos
	 */
	public int getY_pos() {
		return y_pos;
	}

	/**
	 * @param y_pos
	 *            the y_pos to set
	 */
	public void setY_pos(int y_pos) {
		this.y_pos = y_pos;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the obstacleColor
	 */
	public Color getObstacleColor() {
		return obstacleColor;
	}

	/**
	 * @param obstacleColor
	 *            the obstacleColor to set
	 */
	public void setObstacleColor(Color obstacleColor) {
		this.obstacleColor = obstacleColor;
	}


}
