/**
 * 
 */
package de.gebit.brueggemann.smartrockets.space.planets;

import java.awt.Color;
import java.util.Random;

import de.gebit.brueggemann.smartrockets.util.Vector2D;

/**
 * @author DavidWork
 */
public class Earth extends AbstractPlanet {
	private final static Color PLANET_COLOR = Color.BLUE;
	private final static int EARTH_SIZE = 50;

	public Earth(int initialLocalization_x, int initialLocalization_y) {
		super(initialLocalization_x, initialLocalization_y, EARTH_SIZE, PLANET_COLOR);
	}

	@Override
	public int getSize() {
		return EARTH_SIZE;
	}

	// TODO Rockets should start really >>ON<< the planet
	// generate a random localization on earth (starting point)
	public Vector2D getRandomLocalizationOnPlanet() {
		Random random = new Random();

		int max_x = getX_pos() + getPlanetSize();
		int min_x = getX_pos();
		int max_y = getY_pos() + getPlanetSize();
		int min_y = getY_pos();

		int x = random.nextInt(max_x - min_x + 1) + min_x;
		int y = random.nextInt(max_y - min_y + 1) + min_y;

		return new Vector2D(x, y);
	}
}
