/**
 * 
 */
package de.gebit.brueggemann.smartrockets.space.planets;

import java.awt.Color;

import de.gebit.brueggemann.smartrockets.util.Vector2D;

/**
 * @author DavidWork
 *
 */
public abstract class AbstractPlanet {
	private int x_pos;
	private int y_pos;
	private int planetSize;
	private int size = 10;

	private Color planetColor = Color.BLACK;

	public AbstractPlanet(int initialLocalization_x, int initialLocalization_y, int size, Color initialPlanetColor) {
		x_pos = initialLocalization_x;
		y_pos = initialLocalization_y;
		setPlanetSize(size);
		setPlanetColor(initialPlanetColor);
	}

	public Vector2D getLocalization() {
		return new Vector2D(x_pos, y_pos);
	}

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
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the planetColor
	 */
	public Color getPlanetColor() {
		return planetColor;
	}

	/**
	 * @param planetColor
	 *            the planetColor to set
	 */
	public void setPlanetColor(Color planetColor) {
		this.planetColor = planetColor;
	}

	/**
	 * @return the planetSize
	 */
	public int getPlanetSize() {
		return planetSize;
	}

	/**
	 * @param planetSize
	 *            the planetSize to set
	 */
	public void setPlanetSize(int planetSize) {
		this.planetSize = planetSize;
	}

}
