package stPackage;

import java.awt.Rectangle;

public class Platform {
	
	/**
	 * @param number
	 * number of platform
	 */
	private int number;
	/**
	 * @param posX
	 * x co-ordinate of platform
	 */
	private int posx;
	/**
	 * @param posY
	 * y co-ordinate of platform
	 */
	private int posy;
	/**
	 @param length
	 * length of platform
	 * */
	private int length;
	/**
	 * @param platform
	 * rectangle that represents platform
	 */
	private Rectangle platform = new Rectangle();
	/**
	 * @param passengerPositionX
	 * position on platform where passenger enters/exits.
	 */
	private int passengerPositionX;
	/**
	 * @param passengerPositionY
	 * position on platform where passenger enters/exits.
	 */
	private int passengerPositionY;
	/**
	 * Default Constructor.
	 */
	public Platform(){
		passengerPositionX = -1;
		passengerPositionY = -1;
	}
	/**
	 * Creates Platform with number num, position (x,y) and length len.
	 * 
	 * @param num number
	 * @param x x co-ordinate
	 * @param y y co-ordinate
	 * @param len length
	 */
	public Platform(final int num, final int x, final int y, final int len){
		System.out.println("constructing platform with "+x+" "+y+" "+len);
		number = num;
		platform.x = x;
		platform.y = y;
		platform.width = len;
		platform.height = 10;
		// TEMPORARY VALUES
		passengerPositionX = x;
		passengerPositionY = y-19;
	}
	
	/**
	 * 
	 * @param rect taxi rectangle
	 * @param vely taxi vertical velocity
	 * @return int 0-2 depending on whether the collision is valid, invalid or does not occur
	 */
	
	public final int collisionTest(final Rectangle rect,final double vely){
		if(rect.intersects(this.platform)){	
			// Collides directly with top of platform and doesnn't exceed the landing speed limit
			if(rect.x>platform.x && rect.x+rect.width<platform.x+platform.width&&vely>0&&vely<Physics.getCrashSpeed()){
				return 0;
			}
			// Collides elsewhere
			else {
				return 1;
			}
		}else{
			// No collision
			return 2;
		}
	}
	/**
	 * @return the length
	 */
	final public int getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	final public void setLength(final int length) {
		this.length = length;
	}
	/**
	 * @return the number
	 */
	final public int getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	final public void setNumber(final int number) {
		this.number = number;
	}
	/**
	 * @return the passengerPositionX
	 */
	final public int getPassengerPositionX() {
		return passengerPositionX;
	}
	/**
	 * @param passengerPositionX the passengerPositionX to set
	 */
	final public void setPassengerPositionX(final int passengerPositionX) {
		this.passengerPositionX = passengerPositionX;
	}
	/**
	 * @return the passengerPositionY
	 */
	final public int getPassengerPositionY() {
		return passengerPositionY;
	}
	/**
	 * @param passengerPositionY the passengerPositionY to set
	 */
	final public void setPassengerPositionY(final int passengerPositionY) {
		this.passengerPositionY = passengerPositionY;
	}
	/**
	 * @return the posx
	 */
	final public int getPosx() {
		return posx;
	}
	/**
	 * @param posx the posx to set
	 */
	final public void setPosx(final int posx) {
		this.posx = posx;
	}
	/**
	 * @return the posy
	 */
	final public int getPosy() {
		return posy;
	}
	/**
	 * @param posy the posy to set
	 */
	final public void setPosy(final int posy) {
		this.posy = posy;
	}
	/**
	 * @return Returns the platform.
	 */
	final public Rectangle getPlatform() {
		return platform;
	}
}
