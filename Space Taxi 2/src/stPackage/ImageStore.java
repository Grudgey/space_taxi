package stPackage;
//
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.IOException;
import java.util.HashMap;
//
import javax.imageio.ImageIO;

public class ImageStore {
//	/**
//	 *  @ param imageFiles array for storing images
//	 */
	private static String[] imageFiles =  {"taxi0.GIF", "taxi1.GIF", "taxi_left.GIF","taxi_right.GIF", 
			"landing_gear.gif",
			"thruster_left.GIF","thruster_right.GIF", 
			"thruster_up.GIF","thruster_down.GIF",
			"thruster_left_alt.GIF","thruster_right_alt.GIF",
			"thruster_up_alt.GIF", "thruster_down_alt.GIF" };
//	/**
//	 * Image offScreenBuffer
//	 */
	private static Image offScreenBuffer;
//	/**
//	 * Image for game background
//	 */
	private static BufferedImage BackGround;
//	/**
//	 * Image for LoadingScreen
//	 */
	private static BufferedImage LoadingScreen;
//	/**
//	 * Image for MainScreen
//	 */
	private static BufferedImage MainScreen;
//	/**
//	 * Image for InstructionScreen
//	 */
	private static BufferedImage InstructionScreen;
//	/**
//	 * Image for HighScoreScreen
//	 */
	private static BufferedImage HighScoreScreen;
//	/**
//	 * Image for GameLoadingScreen
//	 */
	private static BufferedImage GameLoadingScreen;
//	/**
//	 * hashmap to hold images
//	 */
	private static HashMap images = new HashMap(imageFiles.length);
//	/**
//	 * 
//	 */
	private static Image[] explosionFrames = new Image[17];
//	/**
//	 * 
//	 */
	private static Image[] passengerFrames = new Image[7];
//	/**
//	 *  Location of images
//	 */
	private final String imagePath = "../images/";
//	/**
//	 *  Default constructor
//	 */
	public ImageStore(){
		loadImages();
	}
//	
//	/**
//	 * Loads Images and stores for future use 
//	 *
//	 */
	public void loadImages(){
		try {
			URL url = this.getClass().getResource(imagePath + "LoadingScreen.png");
			LoadingScreen = ImageIO.read(url);
	    	System.out.println("loading LoadingScreen.png");
	    } catch (IOException e) {
	    	System.out.println("Cannot read LoadingScreen.png");
	    }
		try {
			URL url = this.getClass().getResource(imagePath + "MainScreen.gif");
			MainScreen = ImageIO.read(url);
		    System.out.println("loading MainScreen.gif");
	    } catch (IOException e) {
	    	System.out.println("Cannot read MainScreen.GIF");
	    }
	    try {
	    	URL url = this.getClass().getResource(imagePath + "GameLoadingScreen.png");
			GameLoadingScreen = ImageIO.read(url);
		    System.out.println("loading GameLoadingScreen.png");
	    } catch (IOException e) {
	    	System.out.println("Cannot read GameLoadingScreen.png");
	    }
	    try {
	    	URL url = this.getClass().getResource(imagePath + "InstructionsScreen.gif");
			InstructionScreen = ImageIO.read(url);
		    System.out.println("loading InstructionsScreen.gif");
	    } catch (IOException e) {
	    	System.out.println("Cannot read InstructionsScreen.GIF");
	    }
	    try {
	    	URL url = this.getClass().getResource(imagePath + "HighScoresScreen.gif");
			HighScoreScreen = ImageIO.read(url);
	    	System.out.println("loading HighScoresScreen.gif");
	    } catch (IOException e) {
	    	System.out.println("Cannot read HighScoresScreen.GIF");
	    }
	    try {
	    	URL url = this.getClass().getResource(imagePath + "Bg.gif");
			BackGround = ImageIO.read(url);
			System.out.println("loading Bg.gif");
	    } catch (IOException e) {
	    	System.out.println("Cannot read Bg.GIF");
	    }
	    
	    //loading images into container(hashmap)
	    for(int i = 0; i < imageFiles.length; i++){
	    	System.out.println("loading "+imageFiles[i]);
		    try {
		    	URL url = this.getClass().getResource(imagePath + imageFiles[i]);
		    	Image cur = ImageIO.read(url);
			    cur = makeColorTransparent(cur, Color.blue);
			    images.put(imageFiles[i],cur);
		    } catch (IOException e) {
		    	System.out.println("Cannot read "+imageFiles[i]);
		    }
	    }
	    
	    for(int i = 0; i < 17; i++){
	    	System.out.println("loading "+i+".GIF");
	    	try {
	    		Image cur = ImageIO.read(this.getClass().getResource(imagePath + i +".gif"));
	    		cur = makeColorTransparent(cur, Color.blue);
	    		explosionFrames[i] = cur;
	    	}
	    	catch (IOException e){
	    		System.out.println("Could not read "+i+".gif");
	    	}
	    }
	    
	    for(int i = 0; i < 7; i++){
	    	System.out.println("loading p"+i+".GIF");
	    	try {
	    		Image cur = ImageIO.read(this.getClass().getResource(imagePath + "p"+ i +".gif"));
	    		cur = makeColorTransparent(cur, Color.blue);
	    		passengerFrames[i] = cur;
	    	}
	    	catch (IOException e){
	    		System.out.println("Could not read p"+i+".gif");
	    	}
	    }
	}
//	/**
//	 * 
//	 * @param im image
//	 * @param color  colour we wish to make transparent
//	 * @return Image
//	 */
	public static Image makeColorTransparent
	  (Image im, final Color color) {
	  ImageFilter filter = new RGBImageFilter() {
	    private int markerRGB = color.getRGB() | 0xFF000000;

	    public final int filterRGB(int x, int y, int rgb) {
	      if ( ( rgb | 0xFF000000 ) == markerRGB ) {
	        // Mark the alpha bits as zero - transparent
	        return 0x00FFFFFF & rgb;
	        }
	      else {
	        // nothing to do
	   	   System.out.println();
	        return rgb;
	        }
	      }
	    }; 

	  ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
	  return Toolkit.getDefaultToolkit().createImage(ip);
	}
//
//	/**
//	 * @return Returns the backGround.
//	 */
	public static Image getBackGround() {
		return BackGround;
	}
//
//	/**
//	 * @return Returns the explosionFrames.
//	 */
	public static Image[] getExplosionFrames() {
		return explosionFrames;
	}
//
//	/**
//	 * @return Returns the gameLoadingScreen.
//	 */
	public static Image getGameLoadingScreen() {
		return GameLoadingScreen;
	}
//
//	/**
//	 * @return Returns the highScoreScreen.
//	 */
	public static Image getHighScoreScreen() {
		return HighScoreScreen;
	}
//
//	/**
//	 * @return Returns the imageFiles.
//	 */
	public static String[] getImageFiles() {
		return imageFiles;
	}
//
//	/**
//	 * @return Returns the images.
//	 */
	public static HashMap getImages() {
		return images;
	}
//
//	/**
//	 * @return Returns the instructionScreen.
//	 */
	public static Image getInstructionScreen() {
		return InstructionScreen;
	}
//
//	/**
//	 * @return Returns the loadingScreen.
//	 */
	public static Image getLoadingScreen() {
		return LoadingScreen;
	}
//
//	/**
//	 * @return Returns the mainScreen.
//	 */
	public static Image getMainScreen() {
		return MainScreen;
	}
//
//	/**
//	 * @return Returns the offScreenBuffer.
//	 */
	public static Image getOffScreenBuffer() {
		return offScreenBuffer;
	}
//
//	/**
//	 * @return Returns the passengerFrames.
//	 */
	public static Image[] getPassengerFrames() {
		return passengerFrames;
	}
//
//	/**
//	 * @param offScreenBuffer The offScreenBuffer to set.
//	 */
	public static void setOffScreenBuffer(Image offScreenBuffer) {
		ImageStore.offScreenBuffer = offScreenBuffer;
	}
//
}
