package stPackage;


public class Physics {
	/**
	 * Minimum horizontal position for taxi
	 */
	private final double xMin = 4;
	/**
	 * Maximum horizontal position for taxi
	 */
	private final double xMax = 935;
	/**
	 *  constant of acceleration due to gravity
	 */  
	private final double grav = 0.0000000000000098;
	/**
	 * 
	 * constant of acceleration caused by thrusters
	*/
	private final double acc = .2;
	/**
	 *  Landing Speed Limit. Great than this value and the taxi will crash
	 * 
	 */ 
	private static final double crashSpeed = 6;
	/**
	 * 	 max velocity of taxi
	 */ 
	private final double maxVel = 10;
	
	
	/**
	 * Calculate horizontal acceleration of taxi
	 * 
	 * @param dirx direction taxi is moving
	 * @param accx current acceleration 
	 * @return newAccX
	 */
	public double AccX(int dirx, double accx){
		double newAccx;
		if(dirx == 1){
			newAccx = - acc;
		}else if(dirx == -1){
			newAccx = acc;
		}else{
			System.out.println("Error in Physics/AccX");
			newAccx = accx;
		}
//		System.out.println("Accx = " + newAccx);
		return newAccx;
	}
	/**
	 * Calculate veritcal acceleration of taxi
	 * 
	 * @param diry direction taxi is moving
	 * @param accy current acceleration 
	 * @return newAccY
	 */
	public double AccY(int diry, double accy){
		double newAccy;
		if(diry == -1){
			newAccy = - acc + grav;
		}else if(diry == 1){
			newAccy = + acc + grav;
		}else{
			System.out.println("Error in Physics/AccY");
			newAccy = accy;
		}
		return newAccy;
	}
	/**
	 * Calculate horizontal velocity of taxi
	 * 
	 * @param velx current velocity of taxi
	 * @param accx current acceleration 
	 * @return newAccX
	 */
	public double velX(double velx, double accx){
		double newVelx = velx + accx;
		if(newVelx > maxVel){
			newVelx = maxVel;
		}
		if(newVelx < -maxVel){
			newVelx = -maxVel;
		}
		return newVelx;
	}
	/**
	 * Calculates vertical velocity of taxi
	 * @param vely current velocity of taxi
	 * @param accy acceleration
	 * @return newVelY
	 */
	public double velY(double vely, double accy){
		double newVely = vely + accy;
		if(newVely > maxVel){
			newVely = maxVel;
		}
		if(newVely < -maxVel){
			newVely = -maxVel;
		}
		return newVely;
	}
	/**
	 * Calculates new horizontal position of taxi
	 * 
	 * @param posx current position	
	 * @param velx velocity
	 * @param accx acceleration
	 * @return posX
	 */
	public double posX(double posx, double velx, double accx){
		double newPosx = posx + velx + (accx/2);
		if(newPosx>xMax){
			return xMax;
		}
		else if(newPosx<xMin){
			return xMin;
		}
		else{
		return newPosx;
		}
	}
	/**
	 * Calculates new vertical position of taxi
	 * 
	 * @param posy current position	
	 * @param vely velocity
	 * @param accy acceleration
	 * @return posY
	 */
	public double posY(double posy, double vely, double accy){
		double newPosy = posy + vely + (accy/2);

		return newPosy;

	}
		

	/**
	 * @return Returns the xMax.
	 */
	public final double getXMax() {
		return xMax;
	}
	/**
	 * @return Returns the xMin.
	 */
	public final double getXMin() {
		return xMin;
	}
	/**
	 * @return Returns the crashSpeed.
	 */
	public static final double getCrashSpeed() {
		return crashSpeed;
	}

}
