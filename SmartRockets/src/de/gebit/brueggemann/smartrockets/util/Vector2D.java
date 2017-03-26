package de.gebit.brueggemann.smartrockets.util;

public class Vector2D {
	private double x;
	private double y;

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D add(Vector2D vector) {
		this.x += vector.getX();
		this.y += vector.getY();
		return this;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Vector2D mult(double n) {
		this.x *= n;
		this.y *= n;
		return this;
	}

	public double distance(Vector2D anotherVector) {
		double dx = this.x - anotherVector.x;
		double dy = this.y - anotherVector.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	static public double distance(Vector2D vector1, Vector2D vector2) {
		double dx = vector1.x - vector2.x;
		double dy = vector1.y - vector2.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public static Vector2D getNullVector() {
		return new Vector2D(0, 0);
	}
}
