///*
//
///*is this import needed?? */
////import  sun.audio.*;    //import the sun.audio package
//
//import java.applet.*;
//import java.net.URL;
//
//	/**
//	 * Audio clip of crowd cheering
//	 */
//	private static AudioClip crowd_cheering;
//	/**
//	 * Audio clip of passenger getting hit
//	 */
//	private static AudioClip passenger_death;
//	/**
//	 * Audio clip of conversation1
//	 */
//	private static AudioClip[] conversation = new AudioClip[3];
//	/**
//	 * Audio clip of passenger appearing
//	 */
//	private static AudioClip[] passenger_appear = new AudioClip[2];
//	/**
//	 * Audio clip of landing gear
//	 */
//	private static AudioClip[] landing_gear = new AudioClip[2];
//	/**
//	 * Audio clip of taxi crashing
//	 */
//	private static AudioClip[] taxi_crash = new AudioClip[3];
//	/**
//	 * Audio clip of taxi_landing
//	 */
//	private static AudioClip taxi_landing;
//	/**
//	 * Audio clip of thrusters
//	 */
//	private static AudioClip thrusters;
//	/**
//	 * Audio clip of fuel_alarm
//	 */
//	private static AudioClip fuel_alarm;
//	/**
//	 * Audio clip of music
//	 */
//	private static AudioClip music;
//	/**
//	 * determines if thruster sound is looping
//	 */
//	private static boolean thrusters_looping = false;
//	/**
//	 * determines if taxi crash sound is looping
//	 */
//	private static boolean taxicrash_looping = false;
//	/**
//	 * changes between landing gear sounds
//	 */
//	private static boolean landing_gear_open = true;
//	/**
//	 * determines if fuel_alarm is looping
//	 */
//	private static boolean fuel_alarm_looping = false;
//	/**
//	 * determines if conversation is looping
//	 */
//	private static boolean conversation_looping0 = false;
//	/**
//	 * determines if conversation is looping
//	 */
//	private static boolean conversation_looping1 = false;
//	/**
//	 * determines if conversation is looping
//	 */
//	private static boolean conversation_looping2 = false;
//	/**
//	 *  Location of sound files
//	 */
//	private final String Path = "../sounds/";
////	AudioClip v = Applet.newAudioClip();
//
//
//	/**
//	 * Default Constructor
//	 */
//	public Sound(){
//		//load sounds into arrays etc
//		
//		//loading explosion sounds
//		for(int i = 0; i< taxi_crash.length; i++){
//				URL url = this.getClass().getResource(Path + "explo"+i+".wav");
//				taxi_crash[i] = Applet.newAudioClip(url);
//		}
//		
//		//loading passenger appear sounds
//		for(int i = 0; i< passenger_appear.length; i++){
//				URL u = this.getClass().getResource(Path + "ManCallingTaxi"+i+".wav");
//				System.out.println("Loading "+u.toString());
//				passenger_appear[i] = Applet.newAudioClip(u);
//		}
//		
//		for(int i = 0; i< conversation.length; i++){
//			URL u = this.getClass().getResource(Path + "TaxiConversation"+i+".wav");
//				System.out.println("Loading "+u.toString());
//				conversation[i] = Applet.newAudioClip(u);
//		}
//		
//		//loading passenger_death and thrusters
//			URL url1 = this.getClass().getResource(Path + "thrusters.wav");
//		URL url2 = this.getClass().getResource(Path + "passenger_death.wav");
//		URL url3 = this.getClass().getResource(Path + "fuel_alarm.wav");
//		URL url4 = this.getClass().getResource(Path + "landing_gear_open.wav");
//		URL url5 = this.getClass().getResource(Path + "landing_gear_close.wav");
//		URL url6 = this.getClass().getResource(Path + "music.wav");
//		URL url7 = this.getClass().getResource(Path + "taxi_landing.wav");
//		URL url8 = this.getClass().getResource(Path + "crowd_cheering.wav");
//		System.out.println("Loading "+url1.toString());
//		System.out.println("Loading "+url2.toString());
//		System.out.println("Loading "+url3.toString());
//		System.out.println("Loading "+url4.toString());
//		System.out.println("Loading "+url5.toString());
//		System.out.println("Loading "+url6.toString());
//		System.out.println("Loading "+url7.toString());
//		System.out.println("Loading "+url8.toString());
//		thrusters = Applet.newAudioClip(url1);
//		passenger_death = Applet.newAudioClip(url2);
//		fuel_alarm = Applet.newAudioClip(url3);
//		landing_gear[0] = Applet.newAudioClip(url4);
//		landing_gear[1] = Applet.newAudioClip(url5);
//		music = Applet.newAudioClip(url6);
//		taxi_landing = Applet.newAudioClip(url7);
//		crowd_cheering = Applet.newAudioClip(url8);
//	}
//	/**
//	 * Play Sound for passenger appearing
//	 *
//	 */
//	static public void playPassengerAppearing(){
//		passenger_appear[(int)(Math.random()*2)].play();
//	}
//	/**
//	 * Play Sound for conversation
//	 *
//	 */
//	static public void playConversation0(){
//		if(!conversation_looping0){
//		conversation[0].play();
//		conversation_looping0 = true;
//		}
//	}	/**
//	 * stop Sound for conversation0
//	 *
//	 */
//	static public void stopConversation0(){
//		conversation[0].stop();
//		conversation_looping0 = false;
//	}
//	/**
//	 * Play Sound for conversation1
//	 *
//	 */
//	static public void playConversation1(){
//		if(!conversation_looping1){
//		conversation[1].play();
//		conversation_looping1 = true;
//		}
//	}
//	/**
//	 * stop Sound for conversation1
//	 *
//	 */
//	static public void stopConversation1(){
//		conversation[1].stop();
//		conversation_looping0 = false;
//	}
//	/**
//	 * Play Sound for conversation2
//	 *
//	 */
//	static public void playConversation2(){
//		if(!conversation_looping2){
//		conversation[2].play();
//		conversation_looping2 = true;
//		}
//	}
//	/**
//	 * stop Sound for conversation2
//	 *
//	 */
//	static public void stopConversation2(){
//		conversation[2].stop();
//		conversation_looping2 = false;
//	}
//	/**
//	 * Play Sound for taxi landing
//	 *
//	 */
//	static public void playTaxiLanding(){
//		taxi_landing.play();
//	}
//	/**
//	 * Play Sound for low fuel
//	 *
//	 */
//	static public void playFuelAlarm(){
//		if(!fuel_alarm_looping){
//		fuel_alarm.loop();
//		fuel_alarm_looping = true;
//		}
//	}
//	/**
//	 * Stop Sound for low fuel
//	 *
//	 */
//	static public void stopFuelAlarm(){
//		fuel_alarm_looping = false;
//		fuel_alarm.stop();
//	}
//	/**
//	 * Play Sound for passenger dying
//	 *
//	 */
//	static public void playPassengerDeath(){
//		passenger_death.play();
////		p.start(taxi_crash[(int)Math.random()*taxi_crash.length-1]);
//	}
//	/**
//	 * Play Sound for crowd cheering
//	 *
//	 */
//	static public void playCrowdCheering(){
//		crowd_cheering.play();
////		p.start(taxi_crash[(int)Math.random()*taxi_crash.length-1]);
//	}
//	/**
//	 * Play music
//	 *
//	 */
//	static public void playMusic(){
//		music.loop();
//	}
//	/**
//	 * Stop music
//	 *
//	 */
//	static public void stopMusic(){
//		music.stop();
//	}
//	/**
//	 * Play Sound for taxi crashing
//	 *
//	 */
//	static public void playTaxiCrash(){
//		if(!taxicrash_looping){
//		taxi_crash[(int)((Math.random()*3))].play();
//		taxicrash_looping = true;
//		}
//	}
//	/**
//	 * Stop Sound for passenger dying
//	 *
//	 */
//	static public void stopTaxiCrash(){
//		taxicrash_looping = false;
//		taxi_crash[1].stop();
//	}
//	
//	/**
//	 * Play Sound for thrusters
//	 *
//	 */
//	static public void playThrusters(){
//		if(!thrusters_looping){
//		thrusters.loop();
//		thrusters_looping = true;
//		}
//	}
//	/**
//	 * Play Sound for landing gear
//	 */
//	static public void playLandingGear(){
//		if(landing_gear_open){
//			landing_gear[0].play();
//			landing_gear_open = false;
//		}
//		else{
//			landing_gear[1].play();
//			landing_gear_open = true;
//		}
//	}
//	/**
//	 * Stop sound for thrusters
//	 *
//	 */
//	static public void stopThrusters(){
//		thrusters.stop();
//		thrusters_looping = false;
//	}
//	/**
//	 * @return Returns the music.
//	 */
//	public static AudioClip getMusic() {
//		return music;
//	}
//	/**
//	 * @return Returns the taxicrash_looping.
//	 */
//	public static boolean isTaxicrash_looping() {
//		return taxicrash_looping;
//	}
//	/**
//	 * @param taxicrash_looping The taxicrash_looping to set.
//	 */
//	public static void setTaxicrash_looping(boolean taxicrash_looping) {
//		Sound.taxicrash_looping = taxicrash_looping;
//	}
//}
//*/