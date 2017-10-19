package space_studios.objects;


public class Constants {
	//----------define constants----------\\
	//room stuff
	public static String versionNumber = "v1.0.1";
	
	public static final String title = "Space Wars" + " " + versionNumber;
	public static final boolean fullscreen = false; //this must stay false
	
	public static int room_width = 1920;
	public static int room_height = 1080;
	
	public static int display_width;
	public static int display_height;
	
	//animation stuff
	public static final int animationSpeed = 1; //this divided by 30 is animation speed
	public static final float boomSize = 1.5f;
	//minor gameplay stuff
	public static final int shipCool = 14; //if s ship, this value, if sh ship, this value + 10, if b ship, this value *2
	public static final int moneyCool = 15;
	public static final int income = 1;
	//gameplay stuff
	public static final int sShips = 10;
	public static final int shShips = 5;
	public static final int bShips = 3;
	//major gameplay stuff
	//maximum money
	public static final int maxmoney = 100;
	//ship lives
	public static final int sShipLife = 1;
	public static final int shShipLife = 3;
	public static final int bShipLife = 24;
	//ship damage
	public static final int sShipDam = 4;
	public static final int shShipDam = 2; //this includes bullet damage
	public static final int bShipDam = 2;
	//base stuff
	public static final int baseLife = 50;
	//shooter ship shooting cooldown
	public static final int shotCool = 100;
	//how long to wait before showing the lose screen
	public static final int waitBeforeEnd = 200;
	//how long you sit and stare at the lose screen before the credits pop up and you can stare at something else
	public static final int waitBeforeCredits = 500;
	//Win screen splash screen wait time
	public static final int waitBeforeStatistics = 200;
	
	//button selector texture path
	public static final String selector = "assets/sprites/Menu & Title Screens/selector.png";
	
	//Ship Texture Arrays
	private static final String p = "assets/sprites/Ships & Bases/Ships/";
	public static final String[] suicideShipPaths = {p+"Basic Suicide Drone.png",p+"Red Basic Suicide Drone.png"};
	public static final String[] shooterShipPaths = {p+"Basic Blocker.png",p+"Red Basic Blocker.png"};
	public static final String[] blockerShipPaths = {p+"Basic Shooter Ship.png",p+"Red Basic Shooter Ship.png"};
	
	//sets display_width
	public static void setSize(int width, int height){
		display_width = width;
		display_height = height;
	}
	
	//scaling stuff
	public static final float scaleFactor = 1.5f;
	public static float ScreenScaleX(){
		return (display_width/1920)*scaleFactor;
	}
	public static float ScreenScaleY(){
		return (display_height/1080)*scaleFactor;
	}
	public static float FontScale(){
		return (display_width/1920)*1.3f;
	}

	
}
