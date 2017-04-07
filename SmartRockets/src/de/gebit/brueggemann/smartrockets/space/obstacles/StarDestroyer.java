/**
 * 
 */
package de.gebit.brueggemann.smartrockets.space.obstacles;

import java.awt.Color;
import java.awt.Polygon;

/**
 * https://www.google.de/search?q=sternenzerst%C3%B6rer&ie=utf-8&oe=utf-8&client
 * =firefox-b-ab&gfe_rd=cr&ei=B57nWP2VG4-g8wfe8oH4Dg
 * 
 * @author DavidWork
 *
 */
public class StarDestroyer extends AbstractObstacle {

	private static final int DEFAULT_HEIGHT = 200;
	private static final int DEFAULT_WIDTH = 500;
	private static Color color = Color.darkGray;

	/**
	 * @param initialLocalization_x
	 * @param initialLocalization_y
	 * @param height
	 * @param width
	 * @param initialObstacleColor
	 */
	public StarDestroyer(int initialLocalization_x, int initialLocalization_y, int height, int width,
			Color initialObstacleColor) {
		super(initialLocalization_x, initialLocalization_y, height, width, initialObstacleColor);

	}

	public StarDestroyer(int initialLocalization_x, int initialLocalization_y) {
		super(initialLocalization_x, initialLocalization_y, DEFAULT_HEIGHT, DEFAULT_WIDTH, color);
	}

	public Polygon getGraphics() {
		Polygon tempGraphix = new Polygon();
		tempGraphix.addPoint(getX_pos(), getY_pos());
		tempGraphix.addPoint(getX_pos() + getWidth(), getY_pos() + getHeight() / 2);
		tempGraphix.addPoint(getX_pos() + getWidth(), getY_pos() - getHeight() / 2);
		return tempGraphix;
	}
}
