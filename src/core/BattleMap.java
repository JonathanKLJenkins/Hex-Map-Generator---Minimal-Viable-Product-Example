package core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import backend.core.Terrain;
import backend.core.TerrainHexController;

public class BattleMap{
	
	private int boardRadius = 7;
	private HashMap<Integer, HashMap<Integer, Terrain>> board;
	private TerrainHexController controller = new TerrainHexController(this);
	
	public BattleMap(){
		board = new HashMap<Integer, HashMap<Integer, Terrain>>();
		for(int column = 0; column < boardRadius; column++) {
			HashMap<Integer, Terrain> columnContents = new HashMap<Integer, Terrain>();
			for(int row = 0; row < boardRadius + column; row++ ) {
				columnContents.put(row, controller.generateTerrain(column, row));
			}
			board.put(column, columnContents);
		}
		
		for(int column = boardRadius; column < boardRadius * 2 - 1; column++) {
			HashMap<Integer, Terrain> columnContents = new HashMap<Integer, Terrain>();
			for(int row = 0; row < (3*boardRadius -1) - column; row++ ) {
				columnContents.put(row, controller.generateTerrain(column, row));
			}
			board.put(column, columnContents);
		}
	}

	public int getHeight() {
		return boardRadius * 2 - 1;
	}

	public Terrain retrieve(int column, int row) {
		Terrain result = null;
		if(this.board.containsKey(column)) {
			if(this.board.get(column).containsKey(row)) {
				result = board.get(column).get(row);
			}
		}
		return result;
	}
	
	public void applyToAllHexes(Method method, Object[] parameters, Object object) {
		for(int column = 0; column < boardRadius; column++) {
			for(int row = 0; row < boardRadius + column; row++ ) {
				callMethod(method, parameters, object, column, row);
			}
		}
		
		for(int column = boardRadius; column < boardRadius * 2 - 1; column++) {
			for(int row = 0; row < (3*boardRadius - 2) - column; row++ ) {
				callMethod(method, parameters, object, column, row);
			}
		}
		
	}

	private void callMethod(Method method, Object[] parameters, Object instance, int column, int row) {
		try {
			parameters[0] = this.controller;
			parameters[1] = column;
			parameters[2] = row;

			method.invoke(instance, parameters);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("There is a problem with a method you are trying to apply to every hex in the map.");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public int getRadius() {
		return this.boardRadius;
	}

	public ArrayList<Terrain> getSurroundingHexes(int column, int row) {
		ArrayList<Terrain> surroundingHexes = new ArrayList<Terrain>();
		
		surroundingHexes.add(this.retrieve(column+1, row));
		surroundingHexes.add(this.retrieve(column+1, row+1));
		surroundingHexes.add(this.retrieve(column, row-1));
		surroundingHexes.add(this.retrieve(column, row+1));
		surroundingHexes.add(this.retrieve(column-1, row-1));
		surroundingHexes.add(this.retrieve(column-1, row));
		
		//Remove all nulls from list
		while(surroundingHexes.remove(null));
		
		return surroundingHexes;
	}
}