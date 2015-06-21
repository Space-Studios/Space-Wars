package space_studios.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;

import space_studios.core.SpaceWarsCore;
import space_studios.objects.Constants;
import space_studios.objects.RedBase;

public class Robot { //Mecha-ship!
	private static SpaceWarsCore core;
	public StateMachine<Robot> stateMachine;
	
	public static boolean lane1 = false;
	public static boolean lane2 = false;
	public static boolean lane3 = false;
	public static boolean createAIShooterShip = false;
	public static boolean createAIBlockerShip = false;
	public static boolean createAISuicideShip = false;
	
	private static boolean hasPlayerBuiltShip = false;
	private static int rand;
	
	public Robot(SpaceWarsCore cor) {
		core = cor;
		stateMachine = new DefaultStateMachine<Robot>(this, RobotState.IDLE);
		System.out.println("Robot Initialized. Breakpoint 2");
	}
	
	public void update() {
		selectLane(core.redSelected);
		stateMachine.update();
	}
	
	public enum RobotState implements State<Robot> {
		IDLE() {
			@Override
			public void update(Robot robot) {
				if (core.getQ() || core.getW() || core.getE()) {
					robot.stateMachine.changeState(DEFENSIVE);
				}
			}
		},
		DEFENSIVE() {
			@Override
			public void update(Robot robot) {
				
				if (core.getBlueMoney() > Constants.maxmoney*0.2) {
					 robot.stateMachine.changeState(AGGRESSIVE);
				} else {
					if (hasPlayerBuiltShip) {
						 if (builtShipID == 1) {
							 if (core.redMoney >= 5) {
								 createAISuicideShip = true;
							 }
						 }
						 if (builtShipID == 2) {
							 if (core.redMoney >= 10) {
								 createAIShooterShip = true;
							 }
						 }
						 if (builtShipID == 3) {
							 if (core.redMoney >= 15) {
								 createAIBlockerShip = true;
							 }
						 }
					}
					selectLane(core.blueSelected);
				}
			}
		},
		AGGRESSIVE() {
			@Override
			public void update(Robot robot) {
				if (core.mRedbase.Life < Constants.baseLife*0.4) {
					robot.stateMachine.changeState(DEFENSIVE);
				} else {
					if (core.shipsInLane1 == 0) {
						rand = (int) (Math.random()*3 + 1);
						if (rand == 1) {
							createAISuicideShip = true;
							lane1 = true;
						}
						if (rand == 2) {
							createAIShooterShip = true;
							lane1 = true;
						}
						if (rand == 3) {
							createAIShooterShip = true;
							lane1 = true;
						}
					}
					if (core.shipsInLane2 == 0) {
						rand = (int) (Math.random()*3 + 1);
						if (rand == 1) {
							createAISuicideShip = true;
							lane2 = true;
						}
						if (rand == 2) {
							createAIShooterShip = true;
							lane2 = true;
						}
						if (rand == 3) {
							createAIShooterShip = true;
							lane2 = true;
						}
					}
					if (core.shipsInLane3 == 0) {
						rand = (int) (Math.random()*3 + 1);
						if (rand == 1) {
							createAISuicideShip = true;
							lane3 = true;
						}
						if (rand == 2) {
							createAIShooterShip = true;
							lane3 = true;
						}
						if (rand == 3) {
							createAIShooterShip = true;
							lane3 = true;
						}
					}
				}
			}
		};
		
		@Override public boolean onMessage(Robot robot, Telegram msg) {return false;}
		@Override public void enter(Robot robot) {}
		@Override public void exit(Robot robot) {}
	}
	public static int builtShipID;
	public void pushBuilt(int id) {
		hasPlayerBuiltShip = true;
		builtShipID = id;
	}
	public static void selectLane(int lane) {
		switch (lane) {
		case ((178-32)*2)+120:
			lane1 = true;
			break;
		case (178*2)+120:
			lane2 = true;
			break;
		case ((178+32)*2)+120:
			lane3 = true;
			break;
		}
	}
	public void deselectLane(int lane) {
		switch (lane) {
		case ((178-32)*2)+120:
			lane1 = false;
			break;
		case (178*2)+120:
			lane2 = false;
			break;
		case ((178+32)*2)+120:
			lane3 = false;
			break;
		}
	}
}