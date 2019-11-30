package backend.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import core.BattleMap;

public class TerrainHexController {

	private Random random = new Random();
	private double averageWet = .3;
	private double averageElevation = .2;
	private double averageFertility = 0;
	private BattleMap board;
	private double maxWetVariation = .25;
	private double maxElevationVariation = .35;
	private double maxFertilityVariation = .5;
	
	public BattleMap getBoard() {
		return board;
	}

	public TerrainHexController(BattleMap battleMap) {
		this.board = battleMap;
	}

	public TerrainHexController() {
		this.board = new BattleMap();
	}

	public Terrain generateTerrain(int column, int row) {
		double elevation = generateElevation(column, row);
		double wetness = generateWetness(column, row);
		double fertility = generateFertility(column, row, elevation, wetness);
		ArrayList<Terrain> potentialTerrains = generatePotentialTerrains(elevation, fertility, wetness);
		return potentialTerrains.get(random.nextInt(potentialTerrains.size()));
	}
	
	public ArrayList<Terrain> generatePotentialTerrains(double elevation, double fertility, double wetness){
		Terrain mountain = new MountainTerrain(fertility, wetness, elevation);
		Terrain barren = new BarrenTerrain(fertility, wetness, elevation);
		Terrain forest = new ForestTerrain(fertility, wetness, elevation);
		Terrain grass = new GrassTerrain(fertility, wetness, elevation);
		ArrayList<Terrain> potentialTerrains = new ArrayList<Terrain>();
		if(mountain.isValid()) {
			potentialTerrains.add(mountain);
		}
		if(barren.isValid()) {
			potentialTerrains.add(barren);
		}
		if(grass.isValid()) {
			potentialTerrains.add(grass);
		}
		if(forest.isValid()) {
			potentialTerrains.add(forest);
		}
		return potentialTerrains;
	}

	private double generateFertility(int column, int row,
			double elevation, double wetness) { 
		ArrayList<Terrain> surroundingHexes = this.board.getSurroundingHexes(column, row);
		double fertility = averageFertility;
		for(Terrain t : surroundingHexes) {
			fertility += t.fertility;
		}
		// Calculate the average fertility value for surrounding hexes
		if(surroundingHexes.size() > 0) {
			fertility = fertility/surroundingHexes.size();
		}
		// Only vary by the configured amount
		double randDouble = random.nextDouble();
		fertility += (randDouble*2-1) * this.maxFertilityVariation ;
		return fertility;
	}

	private double generateWetness(int column, int row) {
		ArrayList<Terrain> surroundingHexes = this.board.getSurroundingHexes(column, row);
		double wetness = averageWet;
		for(Terrain t : surroundingHexes) {
			wetness += t.wetness;
		}
		// Calculate the average wetness value for surrounding hexes
		if(surroundingHexes.size() > 0) {
			wetness = wetness/surroundingHexes.size();
		}
		// Only vary by the configured amount
		wetness += (random.nextDouble()*2-1) * this.maxWetVariation ;
		return wetness;
	}

	private double generateElevation(int column, int row) {
		ArrayList<Terrain> surroundingHexes = this.board.getSurroundingHexes(column, row);
		double elevation = averageElevation;
		for(Terrain t : surroundingHexes) {
			elevation += t.elevation;
		}
		// Calculate the average elevation value for surrounding hexes
		if(surroundingHexes.size() > 0) {
			elevation = elevation/surroundingHexes.size();
		}
		// Only vary by the configured amount
		elevation += (random.nextDouble()*2-1) * this.maxElevationVariation;
		return elevation;
	}

	public int getBoardRadius() {
		return this.board.getRadius();
	}

	public Color getColor(int column, int row) {
		return this.board.retrieve(column, row).getColor();
	}
}
