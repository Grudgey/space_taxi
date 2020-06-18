package stPackage;

import java.awt.Rectangle;
import java.awt.event.*;

public class Game {

	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////
	// /////////////////GAME VARIABLES///////////////////////////
	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////
	/**
	 * @param playerName
	 *            Name of player, for high scores purposes
	 */

	private String playerName = "";

	/**
	 * Number of frames before passenger appears on screen
	 */
	private int passengerWaitTime = 0;

	/**
	 * @param' crashFrame Frame number for taxi crashing graphics
	 */
	private int crashFrame = 0;

	/**
	 * @param pause
	 *            determines if the game is paused
	 */
	private boolean pause = false;

	/**
	 * @param left
	 *            determine if left key is pressed
	 */
	private boolean left;

	/**
	 * @param right
	 *            determine if right key is pressed
	 */
	private boolean right;

	/**
	 * @param up
	 *            determine if up key is pressed
	 */
	private boolean up;

	/**
	 * @param down
	 *            determine if down key is pressed
	 */
	private boolean down;

	/**
	 * @param idle
	 *            true if no key is pressed
	 */
	private boolean idle;

	/**
	 * @param win
	 *            true if player wins game
	 */
	private boolean win;

	/**
	 * @param game_over
	 *            true when all lives are gone
	 */
	private boolean game_over;

	/**
	 * @param gameLoaded
	 *            determines if the game has been loaded
	 */
	private boolean gameLoaded = false;

	/**
	 * @param nameEntered
	 *            determines if name has been entered yet
	 */
	private boolean nameEntered = false;

	/**
	 * @param startGame
	 *            determines if the game is running
	 */
	private boolean startGame;

	/**
	 * @param dead
	 *            determines if player is dead, i.e crashed
	 */
	private boolean dead;

	/**
	 * @param xBoundaryL
	 *            what does this do?
	 */
	private boolean xBoundaryL;

	/**
	 * @param level
	 *            instance of Level class
	 */
	private Level level;

	/**
	 * @param phy
	 *            instance of Physics class
	 */
	private Physics phy;

	/**
	 * @param taxi
	 *            instance of Taxi class
	 */
	private Taxi taxi;

	/**
	 * @param exitgate
	 *            instance of exitgate class
	 */
	private ExitGate exitgate;

	/**
	 * @param terrain
	 *            array of Terrain objects
	 */
	private Terrain[] terrain;

	/**
	 * @param platforms
	 *            array of platform objects
	 */
	private Platform[] platforms;

	/**
	 * @param passengers
	 *            array of passenger objects
	 */
	private Passenger[] passengers;

	/**
	 * @param nextPassenger
	 *            number of next passenger in line
	 */
	private int nextPassenger = 0;

	/**
	 * @param taxi_hitbox
	 *            rectangle representation of taxi, for collision detection
	 */
	private Rectangle taxi_hitbox;

	/**
	 * @param landinggear_hitbox
	 *            rectangle representation of taxi, for collision detection,
	 *            when landing gear is deployed
	 */
	private Rectangle landinggear_hitbox;

	/**
	 * @param exit_box
	 *            level finsihing collision box
	 */
	private Rectangle exit_box;

	/**
	 * @param passenger_hitbox
	 *            rectangle representation of passenger, for collision detection
	 */
	private Rectangle passenger_hitbox;

	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////
	// ////////////////////GAME LOOP/////////////////////////////
	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////

	/**
	 * @param ActionListener
	 *            main loop of game
	 */
	private ActionListener action = new ActionListener() {
		/**
		 * 
		 * @param actionPerformed
		 *            Main loop of game
		 * 
		 * @param evt
		 *            ActionEvent evt
		 */

		public void actionPerformed(final ActionEvent evt) {
			// Opening Screen
			if (SpaceTaxi.getStatus() == 1) {
				SpaceTaxi.getRender().repaint();
			}

			// Game Screen

			if (SpaceTaxi.getStatus() == 2) {
				if (!gameLoaded) {
					loadGame();
				} else {
					// Case: Game Over
					if (game_over) {
//						Sound.stopThrusters();
						SpaceTaxi.getRender().repaint();
						if (nameEntered) {
							new HighScores(playerName, taxi.getScore());
							gameLoaded = false;
							SpaceTaxi.setStatus(4);
						}
					} // Case: Game Won
					else if (win) {
//						Sound.stopThrusters();
						SpaceTaxi.getRender().repaint();
						if (nameEntered) {
							new HighScores(playerName, taxi.getScore());
							gameLoaded = false;
							SpaceTaxi.setStatus(4);
						}
					} else if (startGame && !pause) {
						// If taxi is dead, run crash frames
						if (dead) {
//							Sound.stopThrusters();
//							Sound.playTaxiCrash();
							if (crashFrame == 16) {
								resetGame();
							} else {
								SpaceTaxi.getRender().repaint();
								crashFrame++;
							}
						}
						// Pickup, Dropoff passengers
						else if (taxi.isLanded()) {
							if (!taxi.getPassenger().isHit()) {
								if (taxi.getPlatformNum() == taxi
										.getPassenger().getEnter().getNumber()
										&& !taxi.getPassenger().isOnboard()) {
									taxi.pickup();
								} else if (taxi.getPlatformNum() == taxi
										.getPassenger().getExit().getNumber()
										&& taxi.getPassenger().isAlive()
										&& taxi.getPassenger().isPickedUp()) {
//									Sound.stopConversation0();
//									Sound.playConversation1();
									taxi.dropoff();
								}
							}
							// Update passenger fare
							if (taxi.getPassenger().isOnboard()) {
								if (taxi.getPassenger().getExit().getNumber() == 3) {
//									Sound.playConversation0();
								}
								SpaceTaxi.getRender().repaint(400, 90, 160, 15);
								taxi.getPassenger().setFare(
										taxi.getPassenger().getFare() - 1);
							}
						}
						// Update Level
						else {
							if (taxi.getFuel() < 200) {
//								Sound.playFuelAlarm();
							} else {
//								Sound.stopFuelAlarm();
							}
							// test for collisions
							terrainCollisions();
							platformCollisions();
							gateCollisions();
							exitboxCollision();
							passengerCollision();
							// Update passenger fare
							if (taxi.getPassenger().isOnboard()) {
								taxi.getPassenger().setFare(
										taxi.getPassenger().getFare() - 1);
							}
							// move taxi
							yMovement();
							xMovement();
							// play movement sounds
							if (right || left || up || down) {
//								Sound.playThrusters();
							} else {
//								Sound.stopThrusters();
							}
							// Update passenger fare
							// move hitboxs
							taxi_hitbox.setLocation(taxi.getPosX(), taxi
									.getPosY());
							landinggear_hitbox.setLocation(taxi.getPosX(), taxi
									.getPosY());
							passenger_hitbox.setLocation(taxi.getPassenger()
									.getPosX(), taxi.getPassenger().getPosY());

						}
						SpaceTaxi.getRender().repaint(taxi_hitbox.x - 30,
								taxi_hitbox.y - 40, taxi_hitbox.width + 60,
								taxi_hitbox.height + 80);
						SpaceTaxi.getRender().repaint(passenger_hitbox.x,
								passenger_hitbox.y, passenger_hitbox.width,
								passenger_hitbox.height);
						SpaceTaxi.getRender().repaint(50, 640, 130, 40);
						SpaceTaxi.getRender().repaint(650, 640, 70, 40);
						// Passenger

						// Making new passenger visible
						if (!taxi.getPassenger().isAlive()) {
							if (passengerWaitTime == 100) {
								passengerWaitTime = 0;
//								Sound.playPassengerAppearing();
								// Avoid array out of bounds exception
								if (nextPassenger < passengers.length) {
									taxi
											.setPassenger(passengers[nextPassenger]);
									taxi.getPassenger().setAlive(true);
									taxi.getPassenger().setVisible(true);
									nextPassenger++;
								}
							} else {
								passengerWaitTime++;
							}
						}
						// last passenger onboard -> open gate
						if (taxi.getPassenger().getExit().getNumber() == 0
								&& taxi.getPassenger().isOnboard()) {
//							Sound.playConversation2();
							exitgate.setVisible(false);
						}
					} else {
						 SpaceTaxi.getRender().repaint();
					}
				}
			}
			// Instruction Screen
			if (SpaceTaxi.getStatus() == 3) {
				SpaceTaxi.getRender().repaint();
			}
			// HighScores Screen
			if (SpaceTaxi.getStatus() == 4) {
				SpaceTaxi.getRender().repaint();
			}
			// Exit Game
			if (SpaceTaxi.getStatus() == 5) {
				SpaceTaxi.getRender().repaint();
			}
		}
	};

	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////
	// /////////////////KEY LISTENER/////////////////////////////
	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////
	/**
	 * @param move
	 *            KeyListener
	 */
	private KeyListener move = new KeyAdapter() {
		/**
		 * 
		 * @param keyPressed
		 *            determines actions when keys are pressed
		 * 
		 * @param e
		 *            KeyEvent e
		 */
		public void keyPressed(final KeyEvent e) {
			int code = e.getKeyCode();
			System.out.print(code);
			// Main Screen

			if (SpaceTaxi.getStatus() == 1) {
				if (code == 38) {
					//up pressed
					SpaceTaxi.setStatus(4);
				}
				if (code == 40) {
					//down pressed
					SpaceTaxi.setStatus(3);
				}
				if (code == 10) {
					//enter pressed
					SpaceTaxi.setStatus(2);
					System.out.print("Enter pressed!");
					
				}
				if (code == 27) {
					//escape pressed
					SpaceTaxi.setStatus(5);
				}
			}

			// Game Screen
			else if (SpaceTaxi.getStatus() == 2) {
				// if game running
				if (!game_over && !win) {
					if (code == 87) {
						// Cannot move while passenger is walking to/from taxi
						if (!taxi.getPassenger().isWalking()) {
							if (taxi.isLanded()) {
								taxi.setLanded(false);
//								Sound.playLandingGear();
								taxi.setLandingGear(false);
								taxi.setPlatformNum(-1);
							}
							if (taxi.getFuel() > 0) {
								up = true;
							}
						}
					}
					if (code == 83) {
						if (taxi.getFuel() > 0) {
							down = true;
						}
					}
					if (code == 65) {
						if (!taxi.isLandingGear()) {
							if (taxi.getFuel() > 0) {
								left = true;
							}
						}
					}
					if (code == 68) {
						if (!taxi.isLandingGear()) {
							if (taxi.getFuel() > 0) {
								right = true;
							}
						}
					}
					if (code == 10) {
						// <---- THIS CHECKSTYLE MEANS SOMETHING
						if (!startGame) {
							SpaceTaxi.getRender().repaint();
							startGame = true;
						}
					}
					if (code == 27) {
						gameLoaded = false;
						SpaceTaxi.setStatus(1);
					}
					if (KeyEvent.getKeyText(code).equals("P")) {
						if (pause) {
							pause = false;
						} else {
							pause = true;
						}
					}
				}
			}
			// Instruction Screen
			else if (SpaceTaxi.getStatus() == 3) {
				if (code == 10) {
					SpaceTaxi.setStatus(1);
				}
			}
			// HighScores Screen
			else if (SpaceTaxi.getStatus() == 4) {
				if (code == 10) {
					SpaceTaxi.setStatus(1);
				}
			}
		}

		/**
		 * 
		 * 
		 * @param keyReleased
		 *            controls what happens when keys are released
		 * @param e
		 * 
		 */
		public void keyReleased(final KeyEvent e) {
			int code = e.getKeyCode();
			if (game_over || win) {
				if (code == 10) {
					if (playerName.equals("")) {
						playerName = "null";
					}
					nameEntered = true;
				} else {
					if (KeyEvent.getKeyText(code).equals("Backspace")) {
						if (playerName.length() != 0) {
							if (playerName.length() == 1) {
								playerName = "";
							} else {
								playerName = playerName.substring(0, playerName
										.length() - 1);
							}
						}
					} else {
						playerName = playerName.concat(KeyEvent
								.getKeyText(code));
					}
				}
			} else {
				if (code == 87) {
					up = false;
				}
				if (code == 83) {
					down = false;
				}
				if (code == 65) {
					left = false;
				}
				if (code == 68) {
					right = false;
				}
				if (KeyEvent.getKeyText(code).equals("L")) {
					if (!taxi.isLanded()) {
						if (taxi.isLandingGear()) {
//							Sound.playLandingGear();
							taxi.setLandingGear(false);
						} else {
//							Sound.playLandingGear();
							taxi.setLandingGear(true);
							right = false;
							left = false;
						}
					}
				}
			}
		}

		public void keyTyped(final KeyEvent e) {
		}
	};

	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////
	// /////////////////TAXI MOVEMENT////////////////////////////
	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////

	/**
	 * Calculates the horizontal movement of the taxi
	 */
	public final void xMovement() {
		// now working out x movement
		// first make sure object is not out of bounds
		// check if out of bounds
		if (taxi.getPosX() == phy.getXMax() || taxi.getPosX() == phy.getXMin()) {
			xBoundaryL = true;
			taxi.setAccX(0);
			taxi.setVelX(0);
		}
		// if left key is being pressed move left
		if (left) {
			taxi.setFuel(taxi.getFuel() - 1);
			taxi.setLeftthruster(true);
			taxi.setRightthruster(false);
			taxi.setMoving_left(true);
			if (taxi.getVelX() < 0) {
				taxi.setAccX(phy.AccX(1, taxi.getAccX()));
				taxi.setVelX(phy.velX(taxi.getVelX(), taxi.getAccX()));
				taxi.setPosX((int) phy.posX(taxi.getPosX(), taxi.getVelX(),
						taxi.getAccX()));
			} else {
				taxi.setAccX(phy.AccX(1, taxi.getAccX() * 5));
				taxi.setVelX(phy.velX(taxi.getVelX(), taxi.getAccX()));
				taxi.setPosX((int) phy.posX(taxi.getPosX(), taxi.getVelX(),
						taxi.getAccX()));
			}
		}
		// if right key is being pressed move right
		else if (right) {
			taxi.setFuel(taxi.getFuel() - 1);
		if (xBoundaryL && taxi.getPosX() != phy.getXMax()) {
				taxi.setPosX((int) phy.getXMin() + 1);
		}
			xBoundaryL = false;
			taxi.setLeftthruster(false);
			taxi.setRightthruster(true);
			taxi.setMoving_left(false);
			if (taxi.getVelX() > 0) {

				taxi.setAccX(phy.AccX(-1, taxi.getAccX()));
				taxi.setVelX(phy.velX(taxi.getVelX(), taxi.getAccX()));
				taxi.setPosX((int) phy.posX(taxi.getPosX(), taxi.getVelX(),
						taxi.getAccX()));
			} else {
				taxi.setAccX(phy.AccX(-1, taxi.getAccX() * 5));
				taxi.setVelX(phy.velX(taxi.getVelX(), taxi.getAccX()));
				taxi.setPosX((int) phy.posX(taxi.getPosX(), taxi.getVelX(),
						taxi.getAccX()));
			}
		} else {
			// else if idle
			if (taxi.getVelX() <= 0.05 && taxi.getVelX() > -0.05) {
				taxi.setRightthruster(false);
				taxi.setLeftthruster(false);
			}
			// drifting left
			// THIS CHECKSTYLE MEANS SOMETHING!!!
			else if (taxi.getVelX() < 0 && !left) {
				taxi.setRightthruster(false);
				taxi.setLeftthruster(false);
				taxi.setAccX(phy.AccX(-1, taxi.getAccX()) * .1);
				taxi.setVelX(phy.velX(taxi.getVelX(), taxi.getAccX()));
				taxi.setPosX((int) phy.posX(taxi.getPosX(), taxi.getVelX(),
						taxi.getAccX()));
			}
			// if currently drifting right, add restriction from left
			// THIS CHECKSTYLE MEANS SOMETHING!!!
			else if (taxi.getVelX() > 0 && !right) {
				taxi.setRightthruster(false);
				taxi.setLeftthruster(false);
				taxi.setAccX(phy.AccX(1, taxi.getAccX()) * .1);
				taxi.setAccX(phy.AccX(1, taxi.getAccX()) * 0.1);
				taxi.setVelX(phy.velX(taxi.getVelX(), taxi.getAccX()));
				taxi.setPosX((int) phy.posX(taxi.getPosX(), taxi.getVelX(),
						taxi.getAccX()));
			}
		}
	}

	/**
	 * Calculates the veritcal movement of the taxi
	 */

	public final void yMovement() {
		// working out y movement
		// if up key is being pressed move up
		if (up) {
			taxi.setFuel(taxi.getFuel() - 1);
			taxi.setBottomthruster(true);
			taxi.setTopthruster(false);
			taxi.setAccY(phy.AccY(-1, taxi.getAccY()));
			taxi.setVelY(phy.velY(taxi.getVelY(), taxi.getAccY()));
			taxi.setPosY((int) phy.posY(taxi.getPosY(), taxi.getVelY(), taxi
					.getAccY()));
		}
		// if down key is being pressed move down
		else if (down) {
			taxi.setFuel(taxi.getFuel() - 1);
			taxi.setBottomthruster(false);
			taxi.setTopthruster(true);
			taxi.setAccY(phy.AccY(1, taxi.getAccY()));
			taxi.setVelY(phy.velY(taxi.getVelY(), taxi.getAccY()));
			taxi.setPosY((int) phy.posY(taxi.getPosY(), taxi.getVelY(), taxi
					.getAccY()));
		}
		// else just work out movement when thrusters are idle
		else {
			taxi.setBottomthruster(false);
			taxi.setTopthruster(false);
			taxi.setAccY(phy.AccY(1, taxi.getAccY()));
			taxi.setVelY(phy.velY(taxi.getVelY(), taxi.getAccY()));
			taxi.setPosY((int) phy.posY(taxi.getPosY(), taxi.getVelY(), taxi
					.getAccY()));
		}

	}

	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////
	// ////////////////LOAD / RESET LEVEL////////////////////////
	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////
	/**
	 * loadGame sets all game variables to default for start of level
	 * 
	 */
	final public void loadGame() {
		left = false;

		right = false;

		up = false;

		down = false;

		idle = false;

		win = false;

		game_over = false;

		nameEntered = false;

		playerName = "";

		gameLoaded = true;

		startGame = false;

		dead = false;

		xBoundaryL = false;

		level = new Level();

		phy = new Physics();

		taxi = level.getTaxi();

		exitgate = level.getExitgate();

		terrain = level.getTerrain();

		platforms = level.getPlatforms();

		passengers = level.getPassengers();

		nextPassenger = 0;

		taxi_hitbox = new Rectangle(taxi.getPosX(), taxi.getPosY(), 60, 18);

		landinggear_hitbox = new Rectangle(taxi.getPosX(), taxi.getPosY(), 60,
				28);

		exit_box = level.getExit_box();

		passenger_hitbox = new Rectangle(taxi.getPassenger().getPosX(), taxi
				.getPassenger().getPosY(), 11, 19);
	}

	/**
	 * reset the appropriate variabled of the game when taxi dies
	 */
	public final void resetGame() {
//		Sound.stopConversation0();
//		Sound.stopConversation1();
//		Sound.stopConversation2();
//		Sound.stopFuelAlarm();
//		Sound.setTaxicrash_looping(false);
		taxi.reset();
		if (taxi.getLives() < 0) {
			game_over = true;
		}
		left = false;
		right = false;
		up = false;
		down = false;
		startGame = false;
		crashFrame = 0;
		if(nextPassenger>0){
		nextPassenger--;
		}
		passengers[nextPassenger].reset();
		dead = false;
		taxi_hitbox.x = 10;
		taxi_hitbox.y = 80;
		landinggear_hitbox.x = 10;
		landinggear_hitbox.y = 98;
		exitgate.isVisible();
		SpaceTaxi.getRender().repaint();
	}

	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////
	// /////////////////COLLISION DETECTION//////////////////////
	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////

	/**
	 * terrainCollisions what happens when taxi collides with terrain
	 */
	final void terrainCollisions() {
		for (int i = 0; i < terrain.length; i++) {
			if (terrain[i].collisionTest(taxi_hitbox)) {
				dead = true;
				taxi.getPassenger().setOnboard(false);
				exitgate.setVisible(true);
			}
			if (taxi.isLandingGear()
					&& terrain[i].collisionTest(landinggear_hitbox)) {
				dead = true;
				exitgate.setVisible(true);
				taxi.getPassenger().setOnboard(false);
			}
		}
	}

	/**
	 * platformCollisions for when taxi collides with platform
	 */

	public void platformCollisions() {
		for (int i = 0; i < platforms.length; i++) {
			if (!taxi.isLanded()) {
				// Landing Gear
				if (taxi.isLandingGear()) {
					if (platforms[i].collisionTest(landinggear_hitbox, taxi
							.getVelY()) == 0) {
//						Sound.playTaxiLanding();
						taxi.setVelX(0);
						taxi.setVelY(0);
						taxi.setAccX(0);
						taxi.setAccY(0);
						taxi.setPosY(taxi.getPosY() - 4);
						up = false;
						down = false;
						left = false;
						right = false;
						taxi.land(i);
					} else if (platforms[i].collisionTest(landinggear_hitbox,
							taxi.getVelY()) == 1) {
						dead = true;
						exitgate.setVisible(true);
						taxi.getPassenger().setOnboard(false);
					}

				} else {
					if (platforms[i].collisionTest(taxi_hitbox, taxi.getVelY()) == 0) {
						dead = true;
						exitgate.setVisible(true);
						taxi.getPassenger().setOnboard(false);
					} else if (platforms[i].collisionTest(taxi_hitbox, taxi
							.getVelY()) == 1) {
						dead = true;
						exitgate.setVisible(true);
						taxi.getPassenger().setOnboard(false);
					}
				}
			}
		}
	}

	/**
	 * gateCollisions check to see if taxi collides with exit gate
	 */
	public void gateCollisions() {
		if (exitgate.collisionTest(taxi_hitbox)) {
			dead = true;
		}
		if (taxi.isLandingGear() && exitgate.collisionTest(landinggear_hitbox)) {
			dead = true;
		}
	}

	/**
	 * exitboxCollision allows for the taxi to exit level
	 */

	final public void exitboxCollision() {
		if (exit_box.intersects(taxi_hitbox)) {
			if (!dead) {
				taxi.setScore(taxi.getScore() + taxi.getPassenger().getFare());
				win = true;
//				Sound.playCrowdCheering();
			}
		} else {
			win = false;
		}
	}

	/**
	 * passengerCollision what happens if taxi collides with passenger
	 */

	final public void passengerCollision() {
		if (!taxi.getPassenger().isOnboard()) {
			if (passenger_hitbox.intersects(taxi_hitbox)) {
				if (!taxi.getPassenger().isHit()) {
//					Sound.playPassengerDeath();
					taxi.getPassenger().setHit(true);
					taxi.setScore(taxi.getScore() - 10);
				}
			} else if (passenger_hitbox.intersects(landinggear_hitbox)) {
				if (!taxi.getPassenger().isHit()) {
//					Sound.playPassengerDeath();
					taxi.getPassenger().setHit(true);
					taxi.setScore(taxi.getScore() - 10);
				}
			} else {
				taxi.getPassenger().setHit(false);
			}
		}
	}

	/**
	 * @return the action
	 */
	final public ActionListener getAction() {
		return action;
	}

	/**
	 * @return the crashFrame
	 */
	final public int getCrashFrame() {
		return crashFrame;
	}

	/**
	 * @param crashFrame
	 *            the crashFrame to set
	 */
	final public void setCrashFrame(final int crashFrame) {
		this.crashFrame = crashFrame;
	}

	/**
	 * @return the dead
	 */
	final public boolean isDead() {
		return dead;
	}

	/**
	 * @return the exit_box
	 */
	final public Rectangle getExit_box() {
		return exit_box;
	}

	/**
	 * @return the exitgate
	 */
	final public ExitGate getExitgate() {
		return exitgate;
	}

	/**
	 * @return the game_over
	 */
	final public boolean isGame_over() {
		return game_over;
	}

	/**
	 * @return the idle
	 */
	final public boolean isIdle() {
		return idle;
	}

	/**
	 * @return the landinggear_hitbox
	 */
	final public Rectangle getLandinggear_hitbox() {
		return landinggear_hitbox;
	}

	/**
	 * @return the left
	 */
	final public boolean isLeft() {
		return left;
	}

	/**
	 * @return the move
	 */
	final public KeyListener getMove() {
		return move;
	}

	/**
	 * @return the passenger_hitbox
	 */
	final public Rectangle getPassenger_hitbox() {
		return passenger_hitbox;
	}

	/**
	 * @return the passengers
	 */
	final public Passenger[] getPassengers() {
		return passengers;
	}

	/**
	 * @return the platforms
	 */
	final public Platform[] getPlatforms() {
		return platforms;
	}

	/**
	 * @return the right
	 */
	final public boolean isRight() {
		return right;
	}

	/**
	 * @return the startGame
	 */
	final public boolean isStartGame() {
		return startGame;
	}

	/**
	 * @return the taxi
	 */
	final public Taxi getTaxi() {
		return taxi;
	}

	/**
	 * @return the taxi_hitbox
	 */
	final public Rectangle getTaxi_hitbox() {
		return taxi_hitbox;
	}

	/**
	 * @return the terrain
	 */
	final public Terrain[] getTerrain() {
		return terrain;
	}

	/**
	 * @return the up
	 */
	final public boolean isUp() {
		return up;
	}

	/**
	 * @return the win
	 */
	final public boolean isWin() {
		return win;
	}

	/**
	 * @return the xBoundaryL
	 */
	final public boolean isXBoundaryL() {
		return xBoundaryL;
	}

	/**
	 * @return Returns the gameLoaded.
	 */
	final public boolean isGameLoaded() {
		return gameLoaded;
	}

	/**
	 * @return Returns the playerName.
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @return Returns the pause.
	 */
	public boolean isPause() {
		return pause;
	}
}
