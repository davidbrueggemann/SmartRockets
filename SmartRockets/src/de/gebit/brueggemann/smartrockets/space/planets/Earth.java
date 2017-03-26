/**
 * 
 */
package de.gebit.brueggemann.smartrockets.space.planets;

import java.awt.Color;

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
}
