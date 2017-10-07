package space_studios.core;

import java.util.ArrayList;
import java.util.List;

import space_studios.objects.BaseshipObject;
import space_studios.objects.BlockerShip;
import space_studios.objects.BlueBase;
import space_studios.objects.Constants;
import space_studios.objects.RedBase;
import space_studios.objects.RedBlockerShip;
import space_studios.objects.RedShooterShip;
import space_studios.objects.RedSuicideShip;
import space_studios.objects.ShipTypes;
import space_studios.objects.ShooterShip;
import space_studios.objects.Statistics;
import space_studios.objects.SuicideShip;
import space_studios.screens.ScreenManager;
import space_studios.screens.StatisticScreen;
import space_studios.screens.Win;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceWarsSolo implements Screen{
	final SpaceWarsCore core;
	
	private static Robot robot;
	private OrthographicCamera cam;
	
	public static int lane1;
	public static int lane2;
	public static int lane3;
	
	public static int blueSelected;
	public static int redSelected;
	
	public static int blueMoney;
	public static int redMoney;
	
	private static Boolean Q = false;
	private static Boolean W = false;
	private static Boolean E = false;
	private static Boolean num1 = false;
	private static Boolean num2 = false;
	private static Boolean num3 = false;
	
	private static int cooldown = Constants.shipCool;
	private static int currentTick;
	private static int cooldown2 = Constants.shipCool;
	private static int currentTick2;
	
	private static int waitTime;
	private static final int waitMax = Constants.waitBeforeEnd;
	
	private static final int rcooldown = Constants.moneyCool;
	private static int rcurrentTick;
	
	//base
	public static RedBase mRedbase = new RedBase();
	public static BlueBase mBluebase = new BlueBase();
	
	//blue ship list
	private static List<BaseshipObject> allBShips = new ArrayList<BaseshipObject>();
	//red ship list
	private static List<BaseshipObject> allRShips = new ArrayList<BaseshipObject>();
	//all ships
	private static List<BaseshipObject> allShips = new ArrayList<BaseshipObject>();
	
	private Texture tex_space;
	private Sprite spr_space;
	
	private static Boolean playWinSound = true;
	
	public static int shipsInLane1 = 0, shipsInLane2 = 0, shipsInLane3 = 0;
	
	public final static boolean debug = false;
	
	public SpaceWarsSolo(final SpaceWarsCore coreInput) {
		core = coreInput;
		robot = new Robot(this);
		
		//sets lanes
		lane1 = Constants.display_height/2+64;
		lane2 =Constants.display_height/2;
		lane3 = Constants.display_height/2-64;
		
		blueSelected=lane2;
		redSelected=lane2;
		
		blueMoney = 20;
		redMoney = 0;
		
		currentTick=0;
		currentTick2=0;
		waitTime=0;
		
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
		
		cam=new OrthographicCamera();
		cam.setToOrtho(false,1024,480);
		
		tex_space = new Texture(Gdx.files.internal("assets/sprites/SPACE!!!!!.png"));
		spr_space = new Sprite(tex_space,0,0,1024*2,1080);
		spr_space.setPosition(0, 0);
		spr_space.setScale(Constants.ScreenScaleX());
		
		mRedbase.setPlace((Constants.display_width/4)*3, (Constants.display_height/2)-48);
		mBluebase.setPlace(Constants.display_width/4, (Constants.display_height/2)-48);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		core.batch.begin();
		if (mBluebase.isDead() || mRedbase.isDead() && waitTime < waitMax){
			waitTime++;
			//update ships
			updateShips(allShips);
			//draw the background
			spr_space.draw(core.batch);
			//draw the bases
			mRedbase.show(core.batch); 
			mBluebase.show(core.batch);
			//update the bases
			mRedbase.update(allShips); 
			mBluebase.update(allShips);
			//draw the ships
			drawShips(allBShips,core.batch);
			core.batch.end();
			return;
		}
		if ((mBluebase.isDead() || mRedbase.isDead()) && waitTime >= waitMax){
			if (mRedbase.isDead()) {
				if (ScreenManager.win == null) {
					ScreenManager.win = new Win(core, "blue");
					core.setScreen(ScreenManager.win);
				} else {
					core.setScreen(ScreenManager.win);
				}
			}
			if (mBluebase.isDead()) {
				if (ScreenManager.win == null) {
					ScreenManager.win = new Win(core, "red");
					core.setScreen(ScreenManager.win);
				} else {
					core.setScreen(ScreenManager.win);
				}
			}
			if (playWinSound){
				core.sounds.playWin();
				playWinSound = false;
			}
			Win.statisticsWait++;
		}
		
		//draw the background
		spr_space.draw(core.batch);
		//draw the bases
		mRedbase.show(core.batch); 
		mBluebase.show(core.batch);
		//draw the ships
		drawShips(allBShips,core.batch);
		
		//draw Money
		if (blueMoney==Constants.maxmoney) {
			core.font.draw(core.batch, "Money:$"+blueMoney+"   MONEY CAP REACHED", (Constants.display_width/4)-32, (Constants.display_height/2)-128);
		}
		else{
			core.font.draw(core.batch, "Money:$"+blueMoney, (Constants.display_width/4), (Constants.display_height/2)-128);
		}
		if (redMoney==Constants.maxmoney) {
			core.font.draw(core.batch, "Money:$"+redMoney+"   MONEY CAP REACHED", ((Constants.display_width/4)*3)-32, (Constants.display_height/2)-128);
		}
		else{
			core.font.draw(core.batch, "Money:$"+redMoney, (Constants.display_width/4)*3, (Constants.display_height/2)-128);
		}
		
		
		//end the drawing
		core.batch.end();
		
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
		redSelected = robot.lane;
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
	}
	
	public static void shipCreateR(){
		//cooldown management
		if (currentTick2 < cooldown2){
			currentTick2++;
			return;
		}
		currentTick2 = 0;
		//----RED----\\
		//suicide ship
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
							if (ship.create(robot.lane,0)==true){
								Statistics.redSuicideShipCreation += 1;
								cooldown2=Constants.shipCool;
								redMoney-=5;
								robot.createAISuicideShip = false;
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
								if (ship.create(robot.lane,0)==true){
									Statistics.redShooterShipCreation += 1;
									cooldown2=Constants.shipCool+10;
									redMoney-=10;
									robot.createAIShooterShip = false;
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
								if (ship.create(robot.lane,0)==true){
									Statistics.redBlockerShipCreation += 1;
									cooldown2 = Constants.shipCool * 2;
									redMoney-=15;
									robot.createAIBlockerShip = false;
									break;
								}
							}
						}
					}
				}
			}
	
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
		if (add) {
			if (lane == lane1) {
				shipsInLane1 ++;
				if (debug) System.out.println("Ship added - lane 1");
			} else if (lane == lane2) {
				shipsInLane2 ++;
				if (debug) System.out.println("Ship added - lane 2");
			} else if (lane == lane3) {
				shipsInLane3 ++;
				if (debug) System.out.println("Ship added - lane 3");
			} else {
				System.out.println("Error - invalid lane");
			}
		} else {
			if (lane == lane1) {
				shipsInLane1 --;
				if (debug) System.out.println("Ship removed - lane 1");
			} else if (lane == lane2) {
				shipsInLane2 --;
				if (debug) System.out.println("Ship removed - lane 2");
			} else if (lane == lane3) {
				shipsInLane3 --;
				if (debug) System.out.println("Ship removed - lane 3");
			} else {
				if (debug) System.out.println("Error - invalid lane");
			}
		}
	}
	@Override
	public void dispose() {
		tex_space.dispose();
	}
	@Override
	public void hide() {}
	@Override
	public void pause() {}
	@Override
	public void resize(int width, int height) {}
	@Override
	public void resume() {}
	@Override
	public void show() {}
}
