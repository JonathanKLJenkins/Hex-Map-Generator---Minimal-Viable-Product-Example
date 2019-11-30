package backend.core;

import java.awt.Color;

public abstract class Terrain {
	protected double fertility;
	protected double wetness;
	protected double elevation;
	protected Color color;
	
	Terrain(double fertility, double wetness, double elevation){
		this.fertility = fertility;
		this.wetness = wetness;
		this.elevation = elevation;
	}

	public Color getColor() {
		int red = color.getRed();
		int blue = (int) Math.min(255, (color.getBlue() * (1 + this.wetness/25)));
		int green = (int) Math.min(255, color.getGreen()*(1+fertility/8));
		int alpha = (int) Math.min(255, color.getAlpha()*(1+elevation/8));
		return new Color(red, green, blue, alpha);
	}

	protected boolean isValid() {
		return true;
	}
}
