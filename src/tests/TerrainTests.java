package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import backend.core.Terrain;
import backend.core.TerrainHexController;

public class TerrainTests {
	
	// There must be at least one valid terrain type for every possible combination
	// of factors. Check on every .025 interval as that is the smallest interval we
	// use as a condition in our terrains.
	@Test
	public void checkAlwaysValidTerrain() {
		ArrayList<Float> testValues = new ArrayList<Float>();
		for (float value = -1; value <= 1; value+=.025) {
			testValues.add(value);
		}
		TerrainHexController controller = new TerrainHexController();
		
		//We are gonna run a lot of test cases here.
		for(float wetness : testValues) {
			for(float fertility: testValues) {
				for(float elevation: testValues) {
					ArrayList<Terrain> result = controller.generatePotentialTerrains(elevation, fertility, wetness);
					assertTrue(result.size() > 0);
				}
			}
		}
	}

}
