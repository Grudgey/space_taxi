package stPackage;

import java.io.*;
import java.net.URL;
import java.awt.Rectangle;
import java.util.StringTokenizer;

public class Level {

	/**
	 * Default Constructor
	 * 
	 * Installs level information
	 */
	
	public Level() {     
		install();
	}

	/**
	 * @param level File that holds level info
	 */
//	private File level = new File(
//			"level.txt");
	
	private final String levelPath = "level.txt";
	
	URL url = this.getClass().getResource(levelPath);
	
	private File level = new File(levelPath);

	// Level Elements
	/** @see #readin()*/
	private Terrain[] terrain = new Terrain[50];

	/** @see #readin()*/
	private Passenger[] passengers = new Passenger[10];

	/** @see #readin() */
	private Platform[] platforms = new Platform[10];

	/** @see #readin() */
	private ExitGate exitgate = new ExitGate();

	/** @see #readin() */
	private Rectangle exit_box;

	/** @see #readin()*/
	private Taxi taxi = new Taxi();

	// Current position in arrays
	/** @see #readin() */
	private int terrainNum = 0;

	// Starting position is 1 because platform0 will be used as exit platform
	/** @see #readin() */
	private int platformNum = 1;

	/** @see #readin() */
	private int passengerNum = 0;

	/**
	 *   Reads information from text file into the arrays
	 */
	final public void install() {
		readin();
		updateArrays();
	}

	
	/**
	 * Reads information from text file
	 */
	final public void readin() {
		// Read in level info from file;
		BufferedReader input = null;
		platforms[0] = new Platform();
		try {
			input = new BufferedReader(new FileReader(level));
			String line = input.readLine();
			StringTokenizer st;
			int elementType = -1;

			while (line != null) {
				st = new StringTokenizer(line);
				try {
					elementType = Integer.parseInt(st.nextToken());
				} catch (NumberFormatException e) {
					// Ignores non integers
					System.out.println("");
				}
				// Terrain
				if (elementType == 0) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					int h = Integer.parseInt(st.nextToken());
					int l = Integer.parseInt(st.nextToken());
					terrain[terrainNum] = new Terrain(x, y, h, l);
					terrainNum++;
				}
				// Platfrom
				else if (elementType == 1) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					int l = Integer.parseInt(st.nextToken());
					platforms[platformNum] = new Platform(platformNum, x, y, l);
					platformNum++;
				}
				// Passenger
				else if (elementType == 2) {
					int fare = Integer.parseInt(st.nextToken());
					int startPlatform = Integer.parseInt(st.nextToken());
					int finishPlatform = Integer.parseInt(st.nextToken());
					passengers[passengerNum] = new Passenger(fare,
							platforms[startPlatform], platforms[finishPlatform]);
					passengerNum++;
				}
				// Exit Gate
				else if (elementType == 3) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					int l = Integer.parseInt(st.nextToken());
					exitgate = new ExitGate(x, y, l);
				 }
				// exit box
				else if (elementType == 4) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					int w = Integer.parseInt(st.nextToken());
					int h = Integer.parseInt(st.nextToken());
					exit_box = new Rectangle(x, y, w, h);
				}
				// Taxi start position
				else if (elementType == 5) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					taxi = new Taxi(x, y);
				}
				line = input.readLine();
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					// flush and close both "input" and its underlying
					// FileReader
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 *  Decreases size of arrays to match the information
	 *
	 */
	final public void updateArrays() {
		Terrain[] newTerrain = new Terrain[terrainNum];
		Platform[] newPlatform = new Platform[platformNum];
		Passenger[] newPassenger = new Passenger[passengerNum];
		for (int i = 0; i < newTerrain.length; i++) {
			newTerrain[i] = terrain[i];
		}
		for (int i = 0; i < newPlatform.length; i++) {
			newPlatform[i] = platforms[i];
		}
		for (int i = 0; i < newPassenger.length; i++) {
			newPassenger[i] = passengers[i];
		}
		terrain = newTerrain;
		platforms = newPlatform;
		passengers = newPassenger;
	}

	/**
	 * @return the exit_box
	 */
	public Rectangle getExit_box() {
		return exit_box;
	}

	/**
	 * @param exit_box the exit_box to set
	 */
	public void setExit_box(Rectangle exit_box) {
		this.exit_box = exit_box;
	}

	/**
	 * @return the exitgate
	 */
	public ExitGate getExitgate() {
		return exitgate;
	}

	/**
	 * @param exitgate the exitgate to set
	 */
	public void setExitgate(ExitGate exitgate) {
		this.exitgate = exitgate;
	}

	/**
	 * @return the level
	 */
	public File getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(File level) {
		this.level = level;
	}

	/**
	 * @return the passengerNum
	 */
	public int getPassengerNum() {
		return passengerNum;
	}

	/**
	 * @param passengerNum the passengerNum to set
	 */
	public void setPassengerNum(int passengerNum) {
		this.passengerNum = passengerNum;
	}

	/**
	 * @return the passengers
	 */
	public Passenger[] getPassengers() {
		return passengers;
	}

	/**
	 * @param passengers the passengers to set
	 */
	public void setPassengers(Passenger[] passengers) {
		this.passengers = passengers;
	}

	/**
	 * 	 * @return the platformNum
	 */
	public int getPlatformNum() {
		return platformNum;
	}

	/**
	 * @param platformNum the platformNum to set
	 */
	public void setPlatformNum(int platformNum) {
		this.platformNum = platformNum;
	}

	/**
	 * @return the platforms
	 */
	public Platform[] getPlatforms() {
		return this.platforms;
	}

	/**
	 * @param platforms the platforms to set
	 */
	public void setPlatforms(Platform[] platforms) {
		this.platforms = platforms;
	}

	/**
	 * @return the taxi
	 */
	public Taxi getTaxi() {
		return taxi;
	}

	/**
	 * @param taxi the taxi to set
	 */
	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}

	/**
	 * @return the terrain
	 */
	public Terrain[] getTerrain() {
		return terrain;
	}

	/**
	 * @param terrain the terrain to set
	 */
	public void setTerrain(Terrain[] terrain) {
		this.terrain = terrain;
	}

	/**
	 * @return the terrainNum
	 */
	public int getTerrainNum() {
		return terrainNum;
	}

	/**
	 * @param terrainNum the terrainNum to set
	 */
	public void setTerrainNum(int terrainNum) {
		this.terrainNum = terrainNum;
	}
}
