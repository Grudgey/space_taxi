package stPackage;

import java.awt.Rectangle;

// TO DO.
public class ExitGate {
	
	
	/**@param height 
	 *                height  of exitgate
	 *             
	 */
	private int height = 15;
	/**@param visible 
	 * 				  determines if exitgate is open/closed 
	 */
	private boolean visible;
	
	/**@param gaterect 
	 * makes a rectangle from the speficications of the exitgate
	 * so it can be drawn on the screen					
	 */
	private Rectangle gaterect = new Rectangle();
	
	/**
	 *  Default Constructor.
	 */
	
	public ExitGate(){		
	}
	
	/**
	 * 
	 *  Makes an exit gate at position (x,y) with width w.
	 *  
	 * @param x              x co-ordinate for location of exitgate
	 * @param y              y co-ordinate for location of exitgate
	 * @param w              width of exit gate

	 */
	public ExitGate(final int x,final int y,final int w){
		gaterect.x = x;
		gaterect.y = y;
		gaterect.width = w;
		gaterect.height = height;
		visible = true;
	}
	/**
	 * @return Returns whether exit gate is visible
	 * 
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * @param visible sets whether exit gate is visible.
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * 
	 * @return true if taxi collides with exit gate, else false
	 * 			
	 * 
	 * @param test taxi rectangle
	 *
	 */
	public boolean collisionTest(Rectangle test){
		if(test.intersects(this.gaterect)){
			// only intersects if exitgate is visible	
			return visible;
			}
		else {
			return false;
		}
	}

	/**
	 * @return the exit gate rectangle
	 */
	public Rectangle getGaterect() {
		return gaterect;
	}
}
