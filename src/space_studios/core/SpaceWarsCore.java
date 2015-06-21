package space_studios.core;
//import list and arraylist
import java.util.ArrayList;
import java.util.List;







import space_studios.objects.BaseshipObject;
import space_studios.objects.BlockerShip;
import space_studios.objects.BlueBase;
import space_studios.objects.Constants;
import space_studios.objects.MusicPlayer;
import space_studios.objects.RedBase;
import space_studios.objects.RedBlockerShip;
import space_studios.objects.RedShooterShip;
import space_studios.objects.RedSuicideShip;
import space_studios.objects.ShipTypes;
import space_studios.objects.ShooterShip;
import space_studios.objects.SoundPlayer;
import space_studios.objects.Statistics;
import space_studios.objects.SuicideShip;

//import libgdx game stuff
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import baseShip class
//import bases
//import enum shipTypes
//import blue ships
//import red ships
//import Music
//import Statistics

public class SpaceWarsCore extends ApplicationAdapter{
		
		//camera
		private OrthographicCamera camera;
		
		//spritebatch for drawing the sprites on
		private SpriteBatch batch;
		
		//base
		public static RedBase mRedbase = new RedBase();
		public static BlueBase mBluebase = new BlueBase();
		
		//background
		private Texture tex_space;
		private Sprite spr_space;
		
		//AITest
		public static Boolean inSoloMode = true;
		public static Boolean inDuelMode = false;
		
		
		//menu + Win screen +credits
		private Texture tex_BlueWins;
		private Sprite spr_BlueWins;
		private Texture tex_RedWins;
		private Sprite spr_RedWins;
		private static Boolean inWinScreenSequence = false;
		private Texture tex_Credits;
		private Sprite spr_Credits;
		
		//Statistics Screen
		private Texture tex_StatisticsBLU;
		private Sprite spr_StatisticsBLU;
		private Texture tex_StatisticsRED;
		private Sprite spr_StatisticsRED;
		private static Boolean inStatisticsSequence = false;
		
		//title screen
		private Texture tex_title;
		private Sprite spr_title;
		private static Boolean inTitleSequence = true;

			// Start Button Duel
			private static Texture tex_duel;
			private static Sprite spr_duel;
		//lanes
		public static int lane1 = ((178-32)*2)+120;//y value
		public static int lane2 = (178*2)+120; //y value
		public static int lane3 = ((178+32)*2)+120;//y value
		public static int blueSelected;
		public static int redSelected;
		
		//keypushes
		private static Boolean Q = false;
		private static Boolean W = false;
		private static Boolean E = false;
		private static Boolean num1 = false;
		private static Boolean num2 = false;
		private static Boolean num3 = false;

		private static Boolean num8 = false;
		private static Boolean num9 = false;
		private static Boolean num0 = false;
		private static Boolean I = false;
		private static Boolean O = false;
		private static Boolean P = false;
		
		//basic ship list
		//arraylist of ships
		//TIP: dont look at the ships. They are complicated.
		//blue ship list
		private static List<BaseshipObject> allBShips = new ArrayList<BaseshipObject>();
		//red ship list
		private static List<BaseshipObject> allRShips = new ArrayList<BaseshipObject>();
		//all ships
		private static List<BaseshipObject> allShips = new ArrayList<BaseshipObject>();
		
		//ship cooldown. cool/30 = cooldown in seconds
		private static int cooldown = Constants.shipCool;
		private static int currentTick;
		private static int cooldown2 = Constants.shipCool;
		private static int currentTick2;
		
		//timer stuff for the lose screens/credits
		private static int waitTime;
		private static final int waitMax = Constants.waitBeforeEnd;
		private static int creditsWait;
		private static final int creditsWaitMax = Constants.waitBeforeCredits;
		private static Boolean creditsMoving;
		private static int creditsYPosition;
		private static int statisticsWait;
		private static final int statisticsWaitMax = Constants.waitBeforeStatistics;
		
		//resoucesCooldown. same time as the ship cooldown
		private static final int rcooldown = Constants.moneyCool;
		private static int rcurrentTick;
		
		//resouces variables
		public static int blueMoney;
		public static int redMoney;
		
		//font
		private static BitmapFont font;
		
		//music
		private static MusicPlayer music;
		private static Boolean playWinSound = true;
		
		//sounds
		protected SoundPlayer sounds;
		
		//hal-9000
		public static Robot robot;
		
		public static int shipsInLane1 = 0, shipsInLane2 = 0, shipsInLane3 = 0;
		
		// EPIC TIP: 0,0 is the lower left hand corner
		@Override
		public void create () {
			
			//initialize hal-9000
			robot = new Robot(this);
			//allows you to use constants
			Constants.setSize(Gdx.graphics.getDesktopDisplayMode().width,Gdx.graphics.getDesktopDisplayMode().height);
			//FULLSCREEN LINE, this enables fullscreen for your computer\\
			Gdx.graphics.setDisplayMode(Constants.display_width, Constants.display_height, true);
			//setting the lanes, it only works here and not higher
			lane1 = Constants.display_height/2+64; 
			lane2 =Constants.display_height/2;
			lane3 = Constants.display_height/2-64;
				
			//music\\
			music=new MusicPlayer();
			music.init();
			
			//sounds\\
			sounds= new SoundPlayer();
			sounds.init();
			
			//--font stuff--\\
			
			//make font
			font = new BitmapFont();
			
			//sets font color
			font.setColor(Color.WHITE);
			
			//--Timer stuff--\\
			
			//sets currentTick to 0
			currentTick=0;
			currentTick2=0;
			waitTime=0;
			
			//--debug stuff--\\
			blueSelected=lane2;
			redSelected=lane2;
			
			//--Resources stuff--\\
			
			//set resources to 20
			blueMoney=20;
			redMoney=20;
			
			//--Ship Stuff--\\
			
			// Create all ships in the game
			//red first
			//ten suicide ships
			for(int i = 0; i<Constants.sShips; i++) {
				allRShips.add(new RedSuicideShip());
			}
			
			//five shooter ships
			for(int i = 0; i<Constants.shShips; i++) {
				allRShips.add(new RedShooterShip());
			}
			
			//three blocker ships
			for(int i = 0; i<Constants.bShips; i++) {
				allRShips.add(new RedBlockerShip());
			}
			//then blue
			//ten suicide ships
			for(int i = 0; i<Constants.sShips; i++) {
				allBShips.add(new SuicideShip());
			}
			
			//five shooter ships
			for(int i = 0; i<Constants.shShips; i++) {
				allBShips.add(new ShooterShip());
			}
			
			//three blocker ships
			for(int i = 0; i<Constants.bShips; i++) {
				allBShips.add(new BlockerShip());
			}
			//add stuff to the allships list
			allShips.addAll(allRShips);
			allShips.addAll(allBShips);
			// init the ships
			for(int len = allBShips.size(), i = 0; i < len; i++) {
				BaseshipObject ship = allBShips.get(i);
				ship.Init();
			}
			for(int len = allRShips.size(), i = 0; i < len; i++) {
				BaseshipObject ship = allRShips.get(i);
				ship.Init();
			}
			// init the bases
			mRedbase.Init();
			mBluebase.Init();
			
			//set camera
			camera=new OrthographicCamera();
			camera.setToOrtho(false,1024,480);
			batch=new SpriteBatch();
	
			//background for the game
			tex_space = new Texture(Gdx.files.internal("assets/sprites/SPACE!!!!!.png"));
			spr_space = new Sprite(tex_space,0,0,1024*2,1080);
			
			//sets credits
			tex_Credits = new Texture(Gdx.files.internal("assets/sprites/Credits.png"));
			spr_Credits = new Sprite(tex_Credits,0,0,1920,3420);
			creditsWait = 0;
			creditsMoving = false;
			creditsYPosition = -2160;
			
			//red lose and blue lose screens
			tex_BlueWins = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Win Screen/Player 1 Blue Wins Screen.png"));
			spr_BlueWins = new Sprite(tex_BlueWins,0,0,1920,1080);
			tex_RedWins = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Win Screen/Player 2 Red Wins Screen.png"));
			spr_RedWins = new Sprite(tex_RedWins,0,0,1920,1080);
			
			
			//title
			tex_title = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/Title Screen Image .png"));
			spr_title = new Sprite(tex_title,0,0,1920,1080);
			
			//Start Button Dual
			tex_duel = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartA.png"));
			spr_duel = new Sprite(tex_duel);
			spr_duel.setPosition(Constants.display_width/5,Constants.display_height/4);
			
			//statistics
			tex_StatisticsBLU = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Statistics Screen/Statistics Screen BLUEWON.png"));
			spr_StatisticsBLU = new Sprite(tex_StatisticsBLU,0,0,1920,1080);
			tex_StatisticsRED = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Statistics Screen/Statistics Screen REDWON.png"));
			spr_StatisticsRED = new Sprite(tex_StatisticsRED,0,0,1920,1080);
			
			//resizes all of the screens to your screen size\\
			//yup, it is just the same command over and over again!!!
			spr_title.setSize(Constants.display_width, Constants.display_height);
			spr_RedWins.setSize(Constants.display_width, Constants.display_height);
			spr_BlueWins.setSize(Constants.display_width, Constants.display_height);
			spr_StatisticsBLU.setSize(Constants.display_width, Constants.display_height);
			spr_StatisticsRED.setSize(Constants.display_width, Constants.display_height);

			//except for those credits...
			spr_Credits.setSize(Constants.display_width, Constants.display_height*4);
			
			//now the credits have to be put in the correct position
			creditsYPosition = Constants.display_height*(-3);
			
			//sets position for stationary things
			spr_space.setPosition(0, 0);
			//red stuff
			mRedbase.setPlace((Constants.display_width/4)*3, (Constants.display_height/2)-48);
			//blue stuff
			mBluebase.setPlace(Constants.display_width/4, (Constants.display_height/2)-48);
		}
		
		@Override
		public void dispose(){
			//disposes all the game textures and objects
			batch.dispose();
			tex_space.dispose();
		}

		@Override
		public void render () {
			
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			///drawing. The ones drawn first are behind the others
			batch.begin();
			
			
			//if escape if pushed, exit
			if (end()){
				batch.end();
				dispose();
				System.exit(0);
			}
			//if the user hasn't pressed enter, render the title screen
			if (!endTitle() && inTitleSequence) {
				spr_title.draw(batch);
				spr_duel.draw(batch);
				
				batch.end();
				return;
			}
			//---if any group lost, wait for 20/30 of a second and then draw the appropriate lose screen and freeze the game until escape is pressed---\\
			//waiting
			if (mBluebase.isDead() || mRedbase.isDead() && waitTime < waitMax){
				waitTime++;
				//update ships
				updateShips(allShips);
				//draw the background
				spr_space.draw(batch);
				//draw the bases
				mRedbase.show(batch); 
				mBluebase.show(batch);
				//update the bases
				mRedbase.update(allShips); 
				mBluebase.update(allShips);
				//draw the ships
				drawShips(allBShips,batch);
				batch.end();
				return;
			}
			//lose screen drawing

			if ((mBluebase.isDead() || mRedbase.isDead()) && waitTime >= waitMax){
				inWinScreenSequence = true;
				if (playWinSound){
					sounds.playWin();
					playWinSound = false;
				}
				statisticsWait++;
			}
			if (statisticsWait >= statisticsWaitMax) {
				inWinScreenSequence = false;

				inStatisticsSequence = true;
				creditsWait++;
			}
			if (inWinScreenSequence) {
				if (mRedbase.isDead()) {
					spr_BlueWins.setPosition(0, 0);
					spr_BlueWins.draw(batch);
					batch.end();
					return;
				}
				if(mBluebase.isDead()){
					spr_RedWins.setPosition(0, 0);
					spr_RedWins.draw(batch);
					batch.end();
					return;
				}
				batch.end();
			}		
			//this checks if the timer for the credits is ready
			if (creditsWait >= creditsWaitMax){
				//this sets it to the correct position, and then draws the credits
				spr_Credits.setPosition(0, creditsYPosition);
				spr_Credits.draw(batch);
				//if moving has started, always move the credits down until they are over, and then the game stops
				if (creditsMoving){

				if (creditsYPosition < 0){
						creditsYPosition ++;
					}
					else {
						//if credits are done, make player wait until they push escape
						creditsMoving = false;
					}
				}
			// if the credits have not yet been set to their initial position, the credits will start moving next step
			else {
				creditsMoving = true;
				}
			//classic end
			batch.end();
			return;
		}
					if (beginStatistics() || inStatisticsSequence) {
						spr_StatisticsBLU.setPosition(0,0);
						spr_StatisticsRED.setPosition(0,0);
						if (mBluebase.isDead()) {
							spr_StatisticsRED.draw(batch);
						}
						else if (mRedbase.isDead()){
							spr_StatisticsBLU.draw(batch);
						}
						font.draw(batch, "Total Money Earned:$"+Statistics.totalInGameMoneyEarned, (Constants.room_width * 860)/1920, (Constants.room_height * (1080-565))/1080);
						font.draw(batch, "Suicide Ships Created: "+Statistics.blueSuicideShipCreation, (Constants.room_width * 325)/1920, (Constants.room_height * (1080-425))/1080);
						font.draw(batch, "Shooter Ships Created: "+Statistics.blueShooterShipCreation, (Constants.room_width * 325)/1920, (Constants.room_height * (1080-510))/1080);
						font.draw(batch, "Blocker Ships Created: "+Statistics.blueBlockerShipCreation, (Constants.room_width * 325)/1920, (Constants.room_height * (1080-602))/1080);
						font.draw(batch, "Red Ships Destroyed: "+Statistics.blueKills, (Constants.room_width * 325)/1920, (Constants.room_height * (1080-700))/1080);
						font.draw(batch, "Suicide Ship Creation: "+Statistics.redSuicideShipCreation, (Constants.room_width * 1267)/1920, (Constants.room_height * (1080-413))/1080);
						font.draw(batch, "Shooter Ship Creation: "+Statistics.redShooterShipCreation, (Constants.room_width * 1267)/1920, (Constants.room_height * (1080-510))/1080);
						font.draw(batch, "Blocker Ship Creation: "+Statistics.redBlockerShipCreation, (Constants.room_width * 1267)/1920, (Constants.room_height * (1080-602))/1080);
						font.draw(batch, "Blue Ships Destroyed: "+Statistics.redKills, (Constants.room_width * 1267)/1920, (Constants.room_height * (1080-710))/1080);						
						inStatisticsSequence = true;
						inWinScreenSequence = false;
						batch.end();
						return;
					}
								

			//draw the background
			spr_space.draw(batch);
			//draw the bases
			mRedbase.show(batch); 
			mBluebase.show(batch);
			//draw the ships
			drawShips(allBShips,batch);
			
			//draw Money
			if (blueMoney==Constants.maxmoney) {
				font.draw(batch, "Money:$"+blueMoney+"   MONEY CAP REACHED", (Constants.display_width/4)-32, (Constants.display_height/2)-128);
			}
			else{
				font.draw(batch, "Money:$"+blueMoney, (Constants.display_width/4), (Constants.display_height/2)-128);
			}
			if (redMoney==Constants.maxmoney) {
				font.draw(batch, "Money:$"+redMoney+"   MONEY CAP REACHED", ((Constants.display_width/4)*3)-32, (Constants.display_height/2)-128);
			}
			else{
				font.draw(batch, "Money:$"+redMoney, (Constants.display_width/4)*3, (Constants.display_height/2)-128);
			}
			
			
			//end the drawing
			batch.end();
			
			//Updates
			updateShips(allShips);
			mRedbase.update(allShips);
			mBluebase.update(allShips);
			
			//update keys
			updateKeys();
			
			//update hal
			robot.update();
			
			//get right selected variable
			getLane();
			
			//create ships
			createShips();
			shipCreateR();
			
			//update money
			giveMoney();
			}
		 

		
		//-------FUNCTIONS-------\\
		private static void updateShips(List<BaseshipObject> allShips){
			for(int len = allBShips.size(), i = 0; i < len; i++) {
				BaseshipObject ship = allBShips.get(i);
				ship.update(Gdx.graphics.getRawDeltaTime(), allShips);
				ship.bulletTest(mRedbase, mBluebase);
			}
			for(int len = allRShips.size(), i = 0; i < len; i++) {
				BaseshipObject ship = allRShips.get(i);
				ship.update(Gdx.graphics.getRawDeltaTime(), allShips);
				ship.bulletTest(mRedbase, mBluebase);
			}
			
		}
		
		private static void drawShips(List<BaseshipObject> allShips, SpriteBatch batch){
			for(int i = allRShips.size()-1; i != 0; i--) {
				BaseshipObject ship = allRShips.get(i);
				ship.draw(batch);
			}
			for(int i = allBShips.size()-1; i != 0; i--) {
				BaseshipObject ship = allBShips.get(i);
				ship.draw(batch);
			}
		}
		
		private static void getLane(){
			if (num1){
				blueSelected = lane1;
			}
			if (num2){
				blueSelected = lane2;
			}
			if (num3){
				blueSelected = lane3;
			}
			if (inSoloMode) {
				if (robot.lane1) {
					redSelected = lane1;
				}
				if (robot.lane2) {
					redSelected = lane2;
				}
				if (robot.lane3) {
					redSelected = lane3;
				}
			} else if (inDuelMode) {
				if (num8){
					redSelected = lane1;
				}
				if (num9){
					redSelected = lane2;
				}
				if (num0){
					redSelected = lane3;
				}
			}
		}
		
		private static void updateKeys(){
			//updates ship keys 
			if(Gdx.input.isKeyPressed(Input.Keys.Q)){
				 Q = true;
			 }
			 else{
				 Q = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.W)){
				 W = true;
			 }
			 else{
				 W = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.E)){
				 E = true;
			 }
			 else{
				 E = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.I)){
				 I = true;
			 }
			 else{
				 I = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.O)){
				 O = true;
			 }
			 else{
				 O = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.P)){
				 P = true;
			 }
			 else{
				 P = false;
			 }
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
				 num1 = true;
			 }
			 else{
				 num1 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
				 num2 = true;
			 }
			 else{
				 num2 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
				 num3 = true;
			 }
			 else{
				 num3 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_8)){
				 num8 = true;
			 }
			 
			 else{
				 num8 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_9)){
				 num9 = true;
			 }
			 else{
				 num9 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_0)){
				 num0 = true;
			 }
			 else{
				 num0 = false;
			 }	 
		}
		
		public static Boolean end(){
			 if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
				return true; 
			 }
			return false;
		}
		//If the user presses enter, it changes the status of inTitleSequence to false
		//This will cause the game to begin rendering
		
		// This is the button function that tracks whether the buttons are being highlighted or not.
		//It will return a boolean (highlightedButton()) which will be used to exit the start screen
		//Apologies for all the math if someone wants to change them to variables go for it but this in its entirety does track correctly -- TFlexSoom
		public static Boolean highlightedButton(){
			if (Gdx.input.getX() > (Constants.display_width/2)-128 
					&& Gdx.input.getX() < (Constants.display_width/2)+128)
			{
				if (Constants.display_height - Gdx.input.getY() 
						> (Constants.display_height/5)-64 
						&& 
						Constants.display_height - Gdx.input.getY() 
						< (Constants.display_height/5)+64)
				{
					tex_duel = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartB.png"));
					spr_duel = new Sprite(tex_duel);
					spr_duel.setPosition(Constants.display_width/2 -128,Constants.display_height/5 - 64);
					return true;
				}
				tex_duel = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartA.png"));
				spr_duel = new Sprite(tex_duel);
				spr_duel.setPosition(Constants.display_width/2-128,Constants.display_height/5-64);
				return false;
			}
			tex_duel = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartA.png"));
			spr_duel = new Sprite(tex_duel);
			spr_duel.setPosition(Constants.display_width/2 - 128,Constants.display_height/5 - 64);
			return false;
		}

		
		
		public static Boolean endTitle(){
			if(/*Gdx.input.isKeyPressed(Input.Keys.ENTER)*/ highlightedButton() 
					&& Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				inTitleSequence = false;
				inDuelMode = true;
				return true;
			}
			return false;
		}
		public static Boolean endWinScreen(){
			if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
				inWinScreenSequence = false;
				return true;
			}
			return false;
		}
		//If the user presses enter while on the win screen, 
		//it will bring the statistics screen
		public static Boolean beginStatistics(){
			if(mBluebase.isDead() || mRedbase.isDead()) {
				if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
					inWinScreenSequence = false;
					inStatisticsSequence = true;
					return true;
				}
				
			}
			return false;
		}
		public static void createShips(){
			//cooldown management
			if (currentTick < cooldown){
				currentTick++;
				return;
			}
			currentTick = 0;	
			
			//actually make ships:
			//----BLUE----\\
			//suicide ship
			if (Q==true){
				//goes through the list of ships
				for(int len = allBShips.size(), i = 0; i < len; i++) {
					//gets current ship
					BaseshipObject ship = allBShips.get(i);
					//if it is a suicide ship
					if (ship.getType().equals(ShipTypes.SuicideShip)){
						//if you have enough money for it
						if (blueMoney>=5){	
							//if its create function returns true, break
							if (ship.create(blueSelected,0)==true){
								Q=false;
								Statistics.blueSuicideShipCreation +=1;
								blueMoney-=5;
								cooldown = Constants.shipCool;
								robot.pushBuilt(1);
								setLaneQuant(blueSelected, true);
								break;
							}
							
						}
					}
				}
			}
			//shooter ship
			//same as suicide ship
			if (W==true){
				for(int len = allBShips.size(), i = 0; i < len; i++) {
					BaseshipObject ship = allBShips.get(i);
					if (ship.getType().equals(ShipTypes.ShooterShip)){
						//if you have enough money for it
						if (blueMoney>=10){	
							//if its create function returns true, break
							if (ship.create(blueSelected,0)==true){
								W=false;
								Statistics.blueShooterShipCreation += 1;
								blueMoney-=10;
								cooldown = Constants.shipCool+10;
								robot.pushBuilt(2);
								setLaneQuant(blueSelected, true);
								break;
							}
							
						}
					}
				}
			}
			//blocker ship
			if (E==true){
				for(int len = allBShips.size(), i = 0; i < len; i++) {
					BaseshipObject ship = allBShips.get(i);
					if (ship.getType().equals(ShipTypes.BlockerShip)){
						//if you have enough money for it
						if (blueMoney>=15){	
							//if its create function returns true, break
							if (ship.create(blueSelected,0)==true){
								E=false;
								Statistics.blueBlockerShipCreation += 1;
								blueMoney-=15;
								cooldown = Constants.shipCool * 2;
								robot.pushBuilt(3);
								setLaneQuant(blueSelected, true);
								break;
							}
							
						}
					}
				}
			}
			
		}//end of shipCreate
		public static void shipCreateR(){
			//cooldown management
			if (currentTick2 < cooldown2){
				currentTick2++;
				return;
			}
			currentTick2 = 0;
			//----RED----\\
			//suicide ship
			if (inSoloMode) {
				if (robot.createAISuicideShip==true){
					//goes through the list of ships
					for(int len = allRShips.size(), i = 0; i < len; i++) {
						//gets current ship
						BaseshipObject ship = allRShips.get(i);
						//if it is a suicide ship
						if (ship.getType().equals(ShipTypes.SuicideShip)){
							//if you have enough money for it
							if (redMoney>=5){	
								//if its create function returns true, break
								if (ship.create(redSelected,0)==true){
									I=false;
									Statistics.redSuicideShipCreation += 1;
									cooldown2=Constants.shipCool;
									redMoney-=5;
									robot.createAISuicideShip = false;
									robot.deselectLane(redSelected);
									break;
									}
								
								}
							}
						}
					}
				//shooter ship
				//same as suicide ship
					if (robot.createAIShooterShip==true){
						for(int len = allRShips.size(), i = 0; i < len; i++) {
							BaseshipObject ship = allRShips.get(i);
							if (ship.getType().equals(ShipTypes.ShooterShip)){
								//if you have enough money for it
								if (redMoney>=10){	
									//if its create function returns true, break
									if (ship.create(redSelected,0)==true){
										O=false;
										Statistics.redShooterShipCreation += 1;
										cooldown2=Constants.shipCool+10;
										redMoney-=10;
										robot.createAIShooterShip = false;
										robot.deselectLane(redSelected);
										break;
									}
								
								}
							}
						}
					}
				//blocker ship
					if (robot.createAIBlockerShip==true){
						for(int len = allRShips.size(), i = 0; i < len; i++) {
							BaseshipObject ship = allRShips.get(i);
							if (ship.getType().equals(ShipTypes.BlockerShip)){
								//if you have enough money for it
								if (redMoney>=15){	
									//if its create function returns true, break
									if (ship.create(redSelected,0)==true){
										P=false;
										Statistics.redBlockerShipCreation += 1;
										cooldown2 = Constants.shipCool * 2;
										redMoney-=15;
										robot.createAIBlockerShip = false;
										robot.deselectLane(redSelected);
										break;
									}
								
								}
							}
						}
					}
				} else if (inDuelMode) {
				if (I==true){
				//goes through the list of ships
				for(int len = allRShips.size(), i = 0; i < len; i++) {
					//gets current ship
					BaseshipObject ship = allRShips.get(i);
					//if it is a suicide ship
					if (ship.getType().equals(ShipTypes.SuicideShip)){
						//if you have enough money for it
						if (redMoney>=5){	
							//if its create function returns true, break
							if (ship.create(redSelected,0)==true){
								I=false;
								Statistics.redSuicideShipCreation += 1;
								cooldown2=Constants.shipCool;
								redMoney-=5;
								break;
								}
							
							}
						}
					}
				}
			}
			//shooter ship
			//same as suicide ship
				if (O==true){
					for(int len = allRShips.size(), i = 0; i < len; i++) {
						BaseshipObject ship = allRShips.get(i);
						if (ship.getType().equals(ShipTypes.ShooterShip)){
							//if you have enough money for it
							if (redMoney>=10){	
								//if its create function returns true, break
								if (ship.create(redSelected,0)==true){
									O=false;
									Statistics.redShooterShipCreation += 1;
									cooldown2=Constants.shipCool+10;
									redMoney-=10;
									break;
								}
							
							}
						}
					}
				}
			//blocker ship
				if (P==true){
					for(int len = allRShips.size(), i = 0; i < len; i++) {
						BaseshipObject ship = allRShips.get(i);
						if (ship.getType().equals(ShipTypes.BlockerShip)){
							//if you have enough money for it
							if (redMoney>=15){	
								//if its create function returns true, break
								if (ship.create(redSelected,0)==true){
									P=false;
									Statistics.redBlockerShipCreation += 1;
									cooldown2 = Constants.shipCool * 2;
									redMoney-=15;
									break;
								}
							
							}
						}
					}
				}
			}
		public static void giveMoney(){
			if (rcurrentTick < rcooldown){
				rcurrentTick++;
				return;
			}
			rcurrentTick = 0;
			
			blueMoney+=Constants.income;
			redMoney+=Constants.income;
			Statistics.totalInGameMoneyEarned += Constants.income;
			//money cap
			if (blueMoney>Constants.maxmoney) {
				blueMoney=Constants.maxmoney;
			}
			if (redMoney>Constants.maxmoney) {
				redMoney=Constants.maxmoney;
			}
		}
		//stuff to make ai easier
		public int getBlueSelectedLane() {
			return blueSelected;
		}
		public boolean getQ() {
			return Q;
		}
		public boolean getW() {
			return W;
		}
		public boolean getE() {
			return E;
		}
		public int getBlueMoney() {
			return blueMoney;
		}
		public static void setLaneQuant(int lane, boolean add) {
			switch (lane) {
			case ((178-32)*2)+120:
				if (add) {
					shipsInLane1 ++;
				} else {
					shipsInLane1 --;
				}
				break;
			case (178*2)+120:
				if (add) {
					shipsInLane2 ++;
				} else {
					shipsInLane2 --;
				}
				break;
			case ((178+32)*2)+120:
				if (add) {
					shipsInLane3 ++;
				} else {
					shipsInLane3 --;
				}
				break;
			}
		}
}
