package stPackage;

import java.awt.Rectangle;

// TO DO
public class Terrain {
	/**
	 * @param terrain  Makes a new rectangle to represent the terrain 
	 */
	private Rectangle terrain = new Rectangle();
	
	/**
	 *  Default Constructor
	 */
	public Terrain(){
		
	}
	
	/**
	 * Makes terrain
	 * 
	 * @param x  x co-ordinate of piece of terrain
	 * @param y  y co-ordinate of piece of terrain
	 * @param l  length of piece of terrain
	 * @param h  leight of piece of terrain
	 */
	public Terrain(int x, int y, int l, int h){
		terrain.x = x;
		terrain.y = y;
		terrain.height = h;
		terrain.width = l;
	}
	
	/**
	 * 
	 * @param test taxi rectangle
	 * @return true if taxi collides with terrain
	 */
	public boolean collisionTest(Rectangle test){
		return test.intersects(this.terrain);
	}

	/**
	 * @return Returns the terrain.
	 */
	public Rectangle getTerrain() {
		return terrain;
	}
}
