package backend.core;

import java.awt.Color;

public class MountainTerrain extends Terrain {
	public MountainTerrain(double fertility, double wetness, double elevation) {
		super(fertility, wetness, elevation);
		this.color = new Color(88,88,88,200);
	}
	
	@Override
	public boolean isValid() {
		return this.elevation > .5;
	}
}
