package stPackage;
//
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
//

public class Render extends Frame {
//	/**
//	 * 
//	 */
	private static final long serialVersionUID = 1L;
//
//	/**
//	 *            instance of game class
//	 */
	private Game game = new Game();
//
//	/**
//	 *            alternates between horizontal thruster images
//	 */
//
	private boolean alt_horizontal = false;
//
//	/**
//	 *            alterantes between vertical thruster images
//	 */
//
	private boolean alt_vertical = false;
//
//	/**
//	 * current number of passenger image
//	 *            
//	 */
//
	private int passengerFrame = 0;
//
//	/*************************** Constructor *******************************/
// 
	public Render() {
		setBackground(Color.white);
		setSize(1000, 700);
		// We need to turn on the visibility of our frame
		// by setting the Visible parameter to true.
		setVisible(true);
		addKeyListener(game.getMove());

		// Now, we want to be sure we properly dispose of resources
		// this frame is using when the window is closed. We use
		// an anonymous inner class adapter for this.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(final WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
		Timer timer = new Timer(30, game.getAction());
		timer.start();
	}
//
//	/**
//	 * @param g
//	 *            Paints everything to the screen
//	 *            
//	 */
//	
	public final void paint(final Graphics g) {
		// CASE : LOADING SCREEN
		if (SpaceTaxi.getStatus() == 0) {
			g.setColor(Color.DARK_GRAY);
			g.drawImage(ImageStore.getLoadingScreen(), 0, 0, null);
			SpaceTaxi.setStatus(1);
		}
		// CASE : OPENING SCREEN
		else if (SpaceTaxi.getStatus() == 1) {
			g.drawImage(ImageStore.getMainScreen(), 0, 0, null);
		}
		// CASE : GAME SCREEN
		else if (SpaceTaxi.getStatus() == 2) {
			g.setColor(Color.DARK_GRAY);
			g.drawImage(ImageStore.getBackGround(), 0, 0, null);
			drawTerrain(g);
			drawPlatforms(g);
			g.setColor(Color.GREEN);
			g.drawRect(game.getExit_box().x, game.getExit_box().y, game
					.getExit_box().width, game.getExit_box().height);

			// SCORE
			g.drawString("SCORE : " + game.getTaxi().getScore(), 650, 650);
			// LIVES
			g.drawString("LIVES : " + game.getTaxi().getLives(), 50, 650);
			// FUEL
			if(game.getTaxi().getFuel() < 200){
				g.setColor(Color.RED);
				g.drawString("Fuel Low!!", 120, 675);
				g.setColor(Color.GREEN);
			}
			g.drawString("FUEL : " + game.getTaxi().getFuel(), 50, 675);
			// FARE
			g.drawString("FARE : " + game.getTaxi().getPassenger().getFare(),
					650, 675);
			// Case : Game Finished
			if (game.isGame_over()) {
				g.setColor(Color.GREEN);
				g.drawString("GAME OVER", 400, 100);
				g.drawString("You Scored : " + game.getTaxi().getScore(), 400,
						120);
				g.drawString("Enter Name: " + game.getPlayerName(), 400, 140);

			} else if (game.isWin()) {
				g.setColor(Color.GREEN);
				g.drawString("YOU WIN", 400, 100);
				g.drawString("You Scored : " + game.getTaxi().getScore(), 400,
						120);
				g.drawString("Enter Name: " + game.getPlayerName(), 400, 140);
			}
			// Case : Game Running
			else{
				if (game.getExitgate().isVisible()) {
					g.setColor(Color.RED);
					g.drawRect(game.getExitgate().getGaterect().x, game
							.getExitgate().getGaterect().y, game.getExitgate()
							.getGaterect().width, game.getExitgate()
							.getGaterect().height);
					g.fillRect(game.getExitgate().getGaterect().x, game
							.getExitgate().getGaterect().y, game.getExitgate()
							.getGaterect().width, game.getExitgate()
							.getGaterect().height);
				}
				if (game.getTaxi().getPassenger().isVisible()
						&& !game.getTaxi().getPassenger().isHit()) {
					drawPassenger(g);
				}

				if (game.getTaxi().getPassenger().isOnboard()) {
					g.setColor(Color.WHITE);
					if (game.getTaxi().getPassenger().getExit().getNumber() == 0) {
						g.drawString("TAKE ME OUT OF HERE", 400, 100);
					} else {
						g.drawString("TAKE ME TO PLATFORM "
								+ game.getTaxi().getPassenger().getExit()
										.getNumber(), 400, 100);
					}
				}
				if (game.isDead()) {
					g.drawImage(ImageStore.getExplosionFrames()[game
							.getCrashFrame()], game.getTaxi().getPosX() - 15,
							game.getTaxi().getPosY() - 30, this);
				} else {
					drawTaxi(g);
					drawThrusters(g);
				}
				if (!game.isStartGame()) {
					g.setColor(Color.RED);
					g.drawString("Hit Enter To Go!", 450, 300);
				}
				if (game.isPause()) {
					g.setColor(Color.RED);
					g.drawString("PAUSED", 450, 300);
				}
			}
		}// CASE : INSTRUCTION SCREEN
		else if (SpaceTaxi.getStatus() == 3) {
			g.drawImage(ImageStore.getInstructionScreen(), 0, 0, null);
		}
		// CASE : HIGHSCORE SCREEN
		else if (SpaceTaxi.getStatus() == 4) {
			g.drawImage(ImageStore.getHighScoreScreen(), 0, 0, null);
			HighScores highScores = new HighScores();
			int ypos = 155;
			for(int i=0; i<10 ; i++){
				g.setColor(Color.GREEN);
				if(highScores.getNames()[i]!=null && !highScores.getNames()[i].equals("null")){
					g.drawString(highScores.getNames()[i], 250, ypos);
				}
				if(highScores.getScores()[i]!=0){
					g.drawString(String.valueOf(highScores.getScores()[i]), 450, ypos);
				}
				ypos += 50;
			}
		}
		// CASE : EXIT GAME
		else if (SpaceTaxi.getStatus() == 5) {
			
			SpaceTaxi.getRender().dispose();
//			Sound.getMusic().stop();
			System.exit(0);
		}
		g.drawRect(110, 665 , 70, 15);
	}
//
//	/**
//	 * @param g
//	 *            Repaints everything to the screen
//	 *            
//	 */
	public final void update(final Graphics g) {
		Graphics gr;
		if (ImageStore.getOffScreenBuffer() == null
				|| (!(ImageStore.getOffScreenBuffer().getWidth(this) == this
						.getWidth() && ImageStore.getOffScreenBuffer()
						.getHeight(this) == this.getWidth()))) {
			ImageStore.setOffScreenBuffer(this.createImage(getWidth(),
					getWidth()));
		}
		gr = ImageStore.getOffScreenBuffer().getGraphics();
		paint(gr);
		g.drawImage(ImageStore.getOffScreenBuffer(), 0, 0, this);
	}
//
//	/**
//	 * @param  g
//	 *            renders the graphics for the taxi
//	 *           
//	 *            
//	 */
	public final void drawTaxi(final Graphics g) {
		if (game.getTaxi().isMoving_left()) {
			g.drawImage((Image) ImageStore.getImages().get("taxi_left.GIF"), game
					.getTaxi().getPosX(), game.getTaxi().getPosY(), null);
		} else {
			g.drawImage((Image) ImageStore.getImages().get("taxi_right.GIF"), game
					.getTaxi().getPosX(), game.getTaxi().getPosY(), null);
		}
		if (game.getTaxi().isLandingGear()) {
			g.drawImage((Image) ImageStore.getImages().get("landing_gear.gif"),
					4 + game.getTaxi().getPosX(), 17 + game.getTaxi()
							.getPosY(), null);
		}
	}
//
//	/**
//	 * @param  g
//	 *            renders the graphics for the thrusters
//	 */
	public final void drawThrusters(final Graphics g) {
		if (game.getTaxi().isRightthruster()) {
			if (alt_horizontal) {
				alt_horizontal = false;
				g.drawImage((Image) ImageStore.getImages()
						.get("thruster_right_alt.GIF"), game.getTaxi()
						.getPosX() - 20, 10 + game.getTaxi().getPosY(), null);
			} else {
				alt_horizontal = true;
				g.drawImage(
						(Image) ImageStore.getImages().get("thruster_right.GIF"),
						game.getTaxi().getPosX() - 20, 10 + game.getTaxi()
								.getPosY(), null);
			}
		}
		if (game.getTaxi().isLeftthruster()) {
			if (alt_horizontal) {
				alt_horizontal = false;
				g.drawImage((Image) ImageStore.getImages()
						.get("thruster_left_alt.GIF"), 60 + game.getTaxi()
						.getPosX(), 10 + game.getTaxi().getPosY(), null);
			} else {
				alt_horizontal = true;
				g.drawImage((Image) ImageStore.getImages().get("thruster_left.GIF"),
						60 + game.getTaxi().getPosX(), 10 + game.getTaxi()
								.getPosY(), null);
			}
		}
		if (game.getTaxi().isBottomthruster()) {
			if (alt_vertical) {
				alt_vertical = false;
				g.drawImage((Image) ImageStore.getImages()
						.get("thruster_down_alt.GIF"), 25 + game.getTaxi()
						.getPosX(), 18 + game.getTaxi().getPosY(), null);
			} else {
				alt_vertical = true;
				g.drawImage((Image) ImageStore.getImages().get("thruster_down.GIF"),
						25 + game.getTaxi().getPosX(), 18 + game.getTaxi()
								.getPosY(), null);
			}
		}
		if (game.getTaxi().isTopthruster()) {
			if (alt_vertical) {
				alt_vertical = false;
				g.drawImage((Image) ImageStore.getImages()
						.get("thruster_up_alt.GIF"), +25
						+ game.getTaxi().getPosX(),
						game.getTaxi().getPosY() - 20, null);
			} else {
				alt_vertical = true;
				g.drawImage((Image) ImageStore.getImages().get("thruster_up.GIF"),
						25 + game.getTaxi().getPosX(), game.getTaxi()
								.getPosY() - 20, null);
			}
		}
	}
//
//	/**
//	 * @param  g renders the graphics for the platforms
//	 */
	public final void drawPlatforms(final Graphics g) {
		for (int i = 0; i < game.getPlatforms().length; i++) {
			g.setColor(Color.YELLOW);
			g.drawRect(game.getPlatforms()[i].getPlatform().x,
					game.getPlatforms()[i].getPlatform().y,
					game.getPlatforms()[i].getPlatform().width, game
							.getPlatforms()[i].getPlatform().height);
			g.fillRect(game.getPlatforms()[i].getPlatform().x,
					game.getPlatforms()[i].getPlatform().y,
					game.getPlatforms()[i].getPlatform().width, game
							.getPlatforms()[i].getPlatform().height);
			g.setColor(Color.BLACK);
			g.drawString(String.valueOf(i), game.getPlatforms()[i].getPlatform().x
					+ (game.getPlatforms()[i].getPlatform().width / 2), game
					.getPlatforms()[i].getPlatform().y
					+ (game.getPlatforms()[i].getPlatform().height));
		}
	}
//
//	/**
//	 * @param  g renders the graphics for the terrain
//	 */
	public final void drawTerrain(final Graphics g) {
		for (int i = 0; i < game.getTerrain().length; i++) {
			g.drawRect(game.getTerrain()[i].getTerrain().x,
					game.getTerrain()[i].getTerrain().y,
					game.getTerrain()[i].getTerrain().width,
					game.getTerrain()[i].getTerrain().height);
			g.fillRect(game.getTerrain()[i].getTerrain().x,
					game.getTerrain()[i].getTerrain().y,
					game.getTerrain()[i].getTerrain().width,
					game.getTerrain()[i].getTerrain().height);
		}
	}
//
//	/**
//	 * @param  g renders the graphics for the passenger
//	 */
	public final void drawPassenger(final Graphics g) {

		if (game.getTaxi().getPassenger().isWalking()) {
			if (passengerFrame == 6) {
				passengerFrame = 0;
			} else {
				passengerFrame++;
			}
		}
		g.drawImage((Image) ImageStore.getPassengerFrames()[passengerFrame], game
				.getTaxi().getPassenger().getPosX(), game.getTaxi()
				.getPassenger().getPosY(), null);
	}
//
}
