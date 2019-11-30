package gui.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JComponent;

import backend.core.TerrainHexController;

//Note: The code in this file is taken from a GIT project: https://gist.github.com/salamander2/4329783

@SuppressWarnings("serial")
public class Hex extends JComponent {
	
	private final double square_root_3 = 1.73205;
	Color internalColor;
	final private int BORDERS = 15;
	private TerrainHexController terHexCont;
	Polygon poly;
	int column;
	int row;
	private int triangleSide;
	private int sideLength;
	private int height;


	public Hex(int column, int row, TerrainHexController terHexCont) {
		this.terHexCont = terHexCont;
		this.column = column;
		this.row = row;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.triangleSide = (int) ((height/2) / square_root_3);
		this.sideLength = (int) (height / square_root_3);
		internalColor = terHexCont.getColor(column, row);
		int x = column * (sideLength+triangleSide);
		int y = (row+Math.abs(terHexCont.getBoardRadius()-1 - column)/2) * height + (column + (terHexCont.getBoardRadius() + 1)%2)%2*height/2;
		poly = hex(x,y);
		
		g.setColor(internalColor);
		g.fillPolygon(poly);
		g.setColor(Color.BLACK);
		g.drawPolygon(poly);
		//this.setBounds(x-1, y-1, triangleSide*2 + sideLength + 1, height + 1);
	}
	
	public Polygon hex (int x0, int y0) {

		int y = y0 + BORDERS;
		int x = x0 + BORDERS; 
		
		int[] xCoordinates,yCoordinates;

		
		xCoordinates = new int[] {x+triangleSide,x+sideLength+triangleSide,x+sideLength+triangleSide+triangleSide,x+sideLength+triangleSide,x+triangleSide,x};
		
		int radius = height/2;
		yCoordinates = new int[] {y,y,y+radius,y+height,y+height,y+radius};
		return new Polygon(xCoordinates, yCoordinates, 6);
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
