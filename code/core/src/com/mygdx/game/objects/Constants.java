package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;


public class Constants {
	//----------define constants----------\\
	//room stuff
	public static final int room_width = 1920;
	public static final int room_height = 1080;
	
	public static final int display_width = Gdx.graphics.getDesktopDisplayMode().width;
	public static final int display_height = Gdx.graphics.getDesktopDisplayMode().height;
	
	public static final String title = "Space Wars";
	public static final boolean fullscreen = false;
	//animation stuff
	public static final int animationSpeed = 3; //this divided by 30 is the frames per second
	public static final float boomSize = 1;
	//minor gameplay stuff
	public static final int shipCool = 30; //if s ship, this value, if sh ship, this value + 10, if b ship, this value *2
	public static final int moneyCool = 15;
	public static final int income = 1;
	//gameplay stuff
	public static final int sShips = 10;
	public static final int shShips = 5;
	public static final int bShips = 3;
	//major gameplay stuff
	//ship lives
	public static final int sShipLife = 1;
	public static final int shShipLife = 3;
	public static final int bShipLife = 6;
	//ship damage
	public static final int sShipDam = 5;
	public static final int shShipDam = 2; //this includes bullet damage
	public static final int bShipDam = 2;
	//base stuff
	public static final int baseLife = 50;
	//shooter ship shooting cooldown
	public static final int shotCool = 120;
	//how long to wait before showing the lose screen
	public static final int waitBeforeEnd = 300;
	
}
