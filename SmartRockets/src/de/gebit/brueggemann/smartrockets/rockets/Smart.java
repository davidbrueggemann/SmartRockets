/**
 * 
 */
package de.gebit.brueggemann.smartrockets.rockets;

import de.gebit.brueggemann.smartrockets.util.Vector2D;

/**
 * @author DavidWork
 *
 */
public class Smart {
	/**
	 * @return the genes
	 */
	public Vector2D[] getGenes() {
		return genes;
	}

	private Vector2D[] genes;
	private double maxforce = 0.1;
	private final static int GENE_POOL = 100;

	public Smart() {
		genes = new Vector2D[GENE_POOL];
		for (int i = 0; i < genes.length; i++) {
			double angle = Math.random() * 2 * Math.PI;
			// choose a vector from 360 degrees range in which the object will
			// move
			genes[i] = new Vector2D(Math.cos(angle), Math.sin(angle));
			genes[i].mult(Math.random() * maxforce);
		}
	}

	public Smart(Vector2D[] newgenes) {
		genes = newgenes;
	}

	public Smart crossover(Smart partner) {
		Vector2D[] child = new Vector2D[genes.length];
		// randomly pick a midpoint
		int crossover = (int) (Math.random() * genes.length);
		// take half from one and half from the other based on the midpoint
		for (int i = 0; i < genes.length; i++) {
			if (i > crossover) {
				child[i] = genes[i];
			} else {
				child[i] = partner.genes[i];
			}
		}
		// return new children's Smart
		Smart newgenes = new Smart(child);
		return newgenes;
	}

	public void mutate(double m) { // m is mutationRate given to the population
									// object while instantiating
		for (int i = 0; i < genes.length; i++) {
			if (Math.random() < m) {
				double angle = Math.random() * 2 * Math.PI;
				genes[i] = new Vector2D(Math.cos(angle), Math.sin(angle));
				genes[i].mult(Math.random() * maxforce);
			}
		}
	}

}
