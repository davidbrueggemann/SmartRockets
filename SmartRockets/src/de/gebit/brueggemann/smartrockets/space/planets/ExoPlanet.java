/**
 * 
 */
package de.gebit.brueggemann.smartrockets.space.planets;

import java.awt.Color;

/**
 * @author DavidWork
 *
 */
public class ExoPlanet extends AbstractPlanet {
	private final static Color PLANET_COLOR = Color.RED;
	private final static int EXO_PLANET_SIZE = 60;

	public ExoPlanet(int initialLocalization_x, int initialLocalization_y) {
		super(initialLocalization_x, initialLocalization_y, EXO_PLANET_SIZE, PLANET_COLOR);
	}

	@Override
	public int getSize() {
		return EXO_PLANET_SIZE;
	}
}
