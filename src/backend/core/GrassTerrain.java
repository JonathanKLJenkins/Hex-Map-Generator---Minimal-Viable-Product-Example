package backend.core;

import java.awt.Color;

public class GrassTerrain extends Terrain {

	public GrassTerrain(double fertility, double wetness, double elevation) {
		super(fertility, wetness, elevation);
		this.color = new Color(50, 150, 50,200);
	}
	
	@Override
	public boolean isValid() {
		return this.fertility > -.3;
	}
}
