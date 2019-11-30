package backend.core;

import java.awt.Color;

public class BarrenTerrain extends Terrain{

	public BarrenTerrain(double fertility, double wetness, double elevation) {
		super(fertility, wetness, elevation);
		this.color = new Color(150,100,100,200);
	}
	
	@Override
	public boolean isValid() {
		return this.fertility < -.25;
	}

}
