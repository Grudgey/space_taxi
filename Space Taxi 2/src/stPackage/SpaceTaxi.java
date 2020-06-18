package stPackage;
//
///**
class SpaceTaxi {
//	/**
//	 *  @param render
//	 *  Instance of render class
//	 */
	private static Render render;
//	/**
//	 *  @param status
//	 *  Current status of the game
//	 */
	private static int status;
	
//	/****CASE: status = 0 ===>  LOADING_SCREEN************/
//	/****CASE: status = 1 ===>  OPENING_SCREEN************/
//	/****CASE: status = 2 ===>  GAME_SCREEN***************/
//	/****CASE: status = 3 ===>  INSTRUCTION_SCREEN********/
//	/****CASE: status = 4 ===>  HIGHSCORE_SCREEN**********/
//	/****CASE: status = 5 ===>  GAME_OVER*****************/
//	
//	/**
//	 *  Main class for game.
//	 * 
//	 *  @param args main class arguments
//	 *  
//	 */
	public static void main(String[] args){
		status = 0;
//		// Load images and sound
		new ImageStore();
//		new Sound();
//		Sound.playMusic();
//		// Start rendering the game
		render = new Render();
	}
//
//	/**
//	 * @return Returns the status.
//	 */
	public static int getStatus() {
		return status;
	}
//
//	/**
//	 * @param status The status to set.
//	 */
	public static void setStatus(final int status) {
		SpaceTaxi.status = status;
	}
//
//	/**
//	 * @return Returns the render.
//	 */
	public static Render getRender() {
		return render;
	}
}
