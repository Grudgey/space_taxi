package stPackage;

public class Passenger{

	/**@param posX 
	 *                x co-ordinate of passengers position
	 */
	private int posX;
	/**@param posY 
	 *                y co-ordinate of passengers position  
	 */
	private int posY;
	/**@param finposX 
	 *                x co-ordinate of passengers final position  
	 */
	private int finposX;
	/**@param finposY
	 *                y co-ordinate of passengers final position  
	 */
	private int finposY;
	/**@param fare 
	 *                current taxi fare bonus
	 */
	private int fare;
	/**@param startFare 
	 *                starting taxi fare bonus
	 */
	private int startFare;
	/**@param visible 
	 *                determines if passenger is visible
	 */
	private boolean visible;
	/**@param alive 
	 *                determines if passenger is alive
	 */
	private boolean alive;
	/**@param onboard 
	 *                determines if passenger is onboard taxi
	 */
	private boolean onboard;
	/**@param walking 
	 *                determines if passenger is walking
	 */
	private boolean walking;
	/**@param pickedUp 
	 *                determines if passenger has been pickedUp
	 */
	private boolean pickedUp;
	/**@param hit 
	 *                determines if passenger is hit
	 */
	private boolean hit;
	/**@param enter 
	 *                Platform passenger on which the passenger enters
	 */
	private final Platform enter;
	/**@param exit 
	 *                Platform passenger on which the passenger exits
	 */
	private final Platform exit;

	/**
	 *  Default Construtor
	 */
	
	public Passenger(){
		alive = false;
		enter = new Platform();
		exit = new Platform();
		visible = false;
		onboard = false;
		pickedUp = false;
		walking = false;
		hit = false;
	}
	
	/**
	 *  Makes a passenger with starting fare, enterance and exit platforms
	 *  
	 *  @param _fare starting fare
	 *  @param _enter entering platform
	 *  @param _exit exitting platform
	 */
	public Passenger(int _fare, Platform _enter, Platform _exit){
		enter = _enter;
		exit = _exit;
		fare = _fare;
		startFare = _fare;
		posX = _enter.getPassengerPositionX();
		posY = _enter.getPassengerPositionY();
		finposX = _exit.getPassengerPositionX();
		finposY = _exit.getPassengerPositionY();
		walking = false;
		visible = false;
		onboard = false;
		pickedUp=false;
		hit = false;
	}
	
	/** 
	 *  resets passenger properties to default
	 */
	public void reset(){
		posX = enter.getPassengerPositionX();
		posY = enter.getPassengerPositionY();
		finposX = exit.getPassengerPositionX();
		finposY = exit.getPassengerPositionY();
		walking = false;
		visible = false;
		onboard = false;
		pickedUp=false;
		hit = false;
		fare = startFare;
		
	}

	/**
	 * @return Returns the fare.
	 */
	public int getFare() {
		return fare;
	}

	/**
	 * @param fare The fare to set.
	 */
	public void setFare(int fare) {
		if(fare <0){
			fare = 0;
		}
		this.fare = fare;
	}

	/**
	 * @return Returns the finposX.
	 */
	public int getFinposX() {
		return finposX;
	}

	/**
	 * @return Returns the finposY.
	 */
	public int getFinposY() {
		return finposY;
	}

	/**
	 * @return Returns the posX.
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX The posX to set.
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
	 * @param posY The posY to set.
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	/**
	 * @return visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible The visible to set.
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	/**
	 * @return enter 
	 */
	public Platform getEnter() {
		return enter;
	}
	/**
	 * @return exit 
	 * 	 */
	public Platform getExit() {
		return exit;
	}

	/**
	 * @return Returns the alive.
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive The alive to set.
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @return Returns the onboard.
	 */
	public boolean isOnboard() {
		return onboard;
	}

	/**
	 * @param onboard The onboard to set.
	 */
	public void setOnboard(boolean onboard) {
		this.onboard = onboard;
	}

	/**
	 * @return Returns the walking.
	 */
	public boolean isWalking() {
		return walking;
	}

	/**
	 * @param walking The walking to set.
	 */
	public void setWalking(boolean walking) {
		this.walking = walking;
	}

	/**
	 * @return Returns the pickedUp.
	 */
	public boolean isPickedUp() {
		return pickedUp;
	}

	/**
	 * @param pickedUp The pickedUp to set.
	 */
	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	/**
	 * @return Returns the hit.
	 */
	public boolean isHit() {
		return hit;
	}

	/**
	 * @param hit The hit to set.
	 */
	public void setHit(boolean hit) {
		this.hit = hit;
	}
}
