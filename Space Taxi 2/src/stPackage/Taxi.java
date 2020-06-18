package stPackage;


public class Taxi {
	/**
	 * @param fuel
	 * amount of fuel taxi has
	 */

	private int fuel;

	/**
	 * @param landed
	 * determines if taxi is landed
	 */

	private boolean landed;
	/**
	 * @param landingGear
	 * determines if taxi has landing gear equipped
	 */

	private boolean landingGear = false;

	/**
	 * @param leftthruster
	 * determines if taxi has leftthruster going
	 */


	private boolean leftthruster = false;

	/**
	 * @param rightthruster
	 * determines if taxi has rightthruster going	 
	 */
	private boolean rightthruster = false;

	/**
	 * @param topthruster
	 * determines if taxi has topthruster going	 
	 */
	private boolean topthruster = false;

	/**
	 * @param bottomthruster
	 * determines if taxi has bottomthruster going	 
	 */
	private boolean bottomthruster = false;

	/**
	 * @param alive
	 * determines if taxi is alive
	 */
	private boolean alive = true;

	/**
	 * @param moving_left
	 * deteremines if taxi is moving left
	 */
	private boolean moving_left = false;

	/**
	 * @param accX
	 * horizontal acceleration
	 */
	private double accX;

	/**
	 * @param accY
	 * vertical acceleration
	 */
	private double accY;

	/**
	 * @param accX
	 * horizontal velocity
	 */

	private double velX;

	/**
	 * @param velY
	 * vertical velocity
	 */
	private double velY;

	/**
	 * @param posX
	 * horizontal position
	 */
	private int posX;

	/**
	 * @param posY
	 * vertical position
	 */
	private int posY;

	/**
	 * @param start_x
	 * horizontal starting position
	 */
	private int start_x;

	/**
	 * @param start_y
	 * vertical starting position
	 */
	private int start_y;

	/**
	 * @param passenger
	 * taxi's passenger
	 */
	private Passenger passenger;

	/**
	 * @param platformNum
	 * number of platform taxi is currently on
	 */
	private int platformNum;


	/**
	 * @param lives
	 * number of lives left
	 */
	private int lives;

	/**
	 * @param score
	 * current score 
	 */
	private int score;

	/** 
	 * Default Constructor
	 */ 
	public Taxi() {
		accX = 0;
		accY = 0;
		velX = 0;
		velY = 0;
		posX = 0;
		posY = 0;
		start_x = 0;
		start_y = 0;
		passenger = new Passenger();
		passenger.reset();
		lives = 3;
		score = 0;
		fuel = 10000;
		landed = false;
		platformNum = -1;;
	}
	/**
	 * Creates a taxi at position x,y
	 * @param x x co-ordinate
	 * @param y y co-ordinate
	 */
	public Taxi(int x, int y) {
		accX = 0;
		accY = 0;
		velX = 0;
		velY = 0;
		posX = x;
		posY = y;
		start_x = x;
		start_y = y;
		landingGear = false;
		leftthruster = false;
		rightthruster = false;
		topthruster = false;
		bottomthruster = false;
		moving_left = false;
		alive = true;
		passenger = new Passenger();
		lives = 3;
		score = 0;
		fuel = 1000;
		landed = false;
		platformNum = -1;
	}
	/**
	 * Resets taxi to default values
	 *
	 */
	public void reset() {
		accX = 0;
		accY = 0;
		velX = 0;
		velY = 0;
		posX = start_x;
		posY = start_y;
		landingGear = false;
		leftthruster = false;
		rightthruster = false;
		topthruster = false;
		bottomthruster = false;
		alive = true;
		passenger = new Passenger();
		lives--;
		platformNum = -1;
		setScore(score - 50);
		fuel = 1000;
		landed = false;
	}

	/**
	 * 
	 * Pick up passenger
	 */
	public void pickup() {
		// pick up passenger

		// walks passenger to taxi
		if (passenger.getPosX() != posX + 30) {
			passenger.setWalking(true);
			// ***Insert guard to make sure the passenger stop when it reaches
			// the taxi****
			if (passenger.getPosX() - posX + 30 < 1
					&& passenger.getPosX() - posX + 30 > -1) {
				passenger.setPosX(posX + 30);
				// passenger gets in taxi
				passenger.setWalking(false);
				passenger.setVisible(false);
				passenger.setOnboard(true);
				passenger.setPickedUp(true);
			}
			if (passenger.getPosX() > posX + 30) {
				passenger.setPosX(passenger.getPosX() - 1);
			} else {
				passenger.setPosX(passenger.getPosX() + 1);
			}
		}else{
				// passenger gets in taxi
				passenger.setWalking(false);
				passenger.setVisible(false);
				passenger.setOnboard(true);
				passenger.setPickedUp(true);
			}
		}

	/**
	 * 
	 * Drop off passenger
	 */
	public void dropoff() {
		// drop off passenger
		// gets out of taxi
		
		if (passenger.isOnboard()) {
			passenger.setPosX(posX + 30);
			passenger.setPosY(passenger.getFinposY());
			passenger.setVisible(true);
			passenger.setOnboard(false);
		}

		// walks passenger closer to exit point
		if (passenger.getPosX() != passenger.getFinposX()) {
			passenger.setWalking(true);
			// ***guard to make sure the passenger stop when it reaches the
			// finish point****
			if (passenger.getPosX() - passenger.getFinposX() < 1
					&& passenger.getPosX() - passenger.getFinposX() > -1) {
				passenger.setPosX(passenger.getFinposX());
			} else if (passenger.getPosX() > passenger.getFinposX()) {
				passenger.setPosX(passenger.getPosX() - 1);
			} else {
				passenger.setPosX(passenger.getPosX() + 1);
			}
		} else {
			passenger.setWalking(false);
			passenger.setAlive(false);
			passenger.setVisible(false);
			score = score + 50 + passenger.getFare();
		}
	}

	/**
	 * Land taxi
	 * 
	 * @param platformNum platform number
	 */
	public void land(int platformNum) {
		velX = 0;
		velY = 0;
		accX = 0;
		accY = 0;
		this.platformNum = platformNum;
		landed = true;
	}

	
	/**
	 * @return Returns the landingGear.
	 */
	public boolean isLandingGear() {
		return landingGear;
	}

	/**
	 * @param landingGear
	 *            The landingGear to set.
	 */
	public void setLandingGear(boolean landingGear) {
		this.landingGear = landingGear;
	}

	/**
	 * @return Returns the bottomthruster.
	 */
	public boolean isBottomthruster() {
		return bottomthruster;
	}

	/**
	 * @return Returns the leftthruster.
	 */
	public boolean isLeftthruster() {
		return leftthruster;
	}

	/**
	 * @return Returns the moving_left.
	 */
	public boolean isMoving_left() {
		return moving_left;
	}

	/**
	 * @return Returns the rightthruster.
	 */
	public boolean isRightthruster() {
		return rightthruster;
	}

	/**
	 * @return Returns the topthruster.
	 */
	public boolean isTopthruster() {
		return topthruster;
	}

	/**
	 * @param bottomthruster
	 *            The bottomthruster to set.
	 */
	public void setBottomthruster(boolean bottomthruster) {
		this.bottomthruster = bottomthruster;
	}

	/**
	 * @param leftthruster
	 *            The leftthruster to set.
	 */
	public void setLeftthruster(boolean leftthruster) {
		this.leftthruster = leftthruster;
	}

	/**
	 * @param moving_left
	 *            The moving_left to set.
	 */
	public void setMoving_left(boolean moving_left) {
		this.moving_left = moving_left;
	}

	/**
	 * @param rightthruster
	 *            The rightthruster to set.
	 */
	public void setRightthruster(boolean rightthruster) {
		this.rightthruster = rightthruster;
	}

	/**
	 * @param topthruster
	 *            The topthruster to set.
	 */
	public void setTopthruster(boolean topthruster) {
		this.topthruster = topthruster;
	}

	/**
	 * @return Returns the landed.
	 */
	public boolean isLanded() {
		return landed;
	}

	/**
	 * @param landed
	 *            The landed to set.
	 */
	public void setLanded(boolean landed) {
		this.landed = landed;
	}

	/**
	 * @return Returns the lives.
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * @param lives
	 *            The lives to set.
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
	/**
	 * 
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            The score to set.
	 */
	public void setScore(int score) {
		if(score<0){
			score = 0;
		}
		this.score = score;
	}

	/**
	 * @return Returns the passenger.
	 */
	public Passenger getPassenger() {
		return passenger;
	}

	/**
	 * @param passenger
	 *            The passenger to set.
	 */
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	/**
	 * @return Returns the accX.
	 */
	public double getAccX() {
		return accX;
	}

	/**
	 * @param accX
	 *            The accX to set.
	 */
	public void setAccX(double accX) {
		this.accX = accX;
	}

	/**
	 * @return Returns the accY.
	 */
	public double getAccY() {
		return accY;
	}

	/**
	 * @param accY
	 *            The accY to set.
	 */
	public void setAccY(double accY) {
		this.accY = accY;
	}

	/**
	 * @return Returns the alive.
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive
	 *            The alive to set.
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @return Returns the fuel.
	 */
	public int getFuel() {
		return fuel;
	}

	/**
	 * @param fuel
	 *            The fuel to set.
	 */
	public void setFuel(int fuel) {
		if(fuel<0){
			fuel = 0;
		}
		this.fuel = fuel;
	}

	/**
	 * @return Returns the platformNum.
	 */
	public int getPlatformNum() {
		return platformNum;
	}

	/**
	 * @param platformNum
	 *            The platformNum to set.
	 */
	public void setPlatformNum(int platformNum) {
		this.platformNum = platformNum;
	}

	/**
	 * @return Returns the posX.
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX
	 *            The posX to set.
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return Returns the posY.
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param posY
	 *            The posY to set.
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * @return Returns the velX.
	 */
	public double getVelX() {
		return velX;
	}

	/**
	 * @param velX
	 *            The velX to set.
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}

	/**
	 * @return Returns the velY.
	 */
	public double getVelY() {
		return velY;
	}

	/**
	 * @param velY
	 *            The velY to set.
	 */
	public void setVelY(double velY) {
		this.velY = velY;
	}
}
