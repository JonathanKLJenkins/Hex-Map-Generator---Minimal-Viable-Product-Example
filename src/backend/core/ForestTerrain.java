package backend.core;

import java.awt.Color;

public class ForestTerrain extends Terrain{

	public ForestTerrain(double fertility, double wetness, double elevation) {
		super(fertility, wetness, elevation);
		this.color = new Color(0,85,0,200);
	}

	@Override
	public boolean isValid() {
		return this.fertility > .25 && this.wetness > .5;
	}
}
