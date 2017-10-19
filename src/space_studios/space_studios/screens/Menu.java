package space_studios.screens;

import java.util.ArrayList;
import java.util.Arrays;

import space_studios.core.SpaceWarsCore;
import space_studios.core.SpaceWarsDuel;
import space_studios.core.SpaceWarsSolo;
import space_studios.objects.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Menu implements Screen{
	private Texture tex_title;
	private Sprite spr_title;
	
	private Texture tex_duel;
	private Sprite spr_duel;
	
	private Texture tex_solo;
	private Sprite spr_solo;
	
	private Button duel;
	private Button solo;
	
	private ButtonGroup home; //1 is play, 2 is settings, 3 is quit
	private ButtonGroup play; //1 is duel, 2 is solo, 3 is customize, 4 is back
	
	private OrthographicCamera cam;
	final SpaceWarsCore core;
	
	public Menu(final SpaceWarsCore coreInput) {
		core = coreInput;
		cam = new OrthographicCamera();
		cam.setToOrtho(false,1024,480);
		
		//title
		tex_title = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/Title Screen Image .png"));
		spr_title = new Sprite(tex_title,0,0,1920,1080);
		
		
		//duel start screen
		tex_duel = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartDuelA.png"));
		spr_duel = new Sprite(tex_duel);
		spr_duel.setPosition(Constants.display_width/2-256,Constants.display_height/8);

		
		//solo start screen
		tex_solo = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartSoloA.png"));
		spr_solo = new Sprite(tex_solo);
		spr_solo.setPosition(Constants.display_width/2,Constants.display_height/8);
		
		//extra textures
		Texture sett = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/Settings.png"));
		Texture back = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/Back.png"));
		Texture quit = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/Quit.png"));
		Texture custom = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/Customize.png"));
		Texture pplay = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/Play.png"));
		
		//for buttons, we need a selector sprite
		Texture seltex = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/selector.png"));
		Sprite selspr = new Sprite(seltex,128,128);
		Sprite selspr2 = new Sprite(selspr);
		
		//play submenu buttons
		solo = new Button(spr_solo,selspr);
		duel = new Button(spr_duel,selspr2);
		Button hcus = new Button(new Sprite(custom),new Sprite (selspr));
		Button sdBack = new Button(new Sprite (back),new Sprite (selspr));
		solo.SetPosition(Constants.display_width/2-634, Constants.display_height/8);
		duel.SetPosition(Constants.display_width/2-358,Constants.display_height/8);
		hcus.SetPosition(Constants.display_width/2-100, Constants.display_height/8);
		sdBack.SetPosition(Constants.display_width/2+420,Constants.display_height/8);
		
		//buttongroup!
		play = new ButtonGroup(new Button[] {duel,solo,hcus,sdBack});
		
		
		//home submenu buttons
		Button hplay = new Button(new Sprite(pplay),new Sprite (selspr));
		Button hsettings = new Button(new Sprite(sett),new Sprite (selspr));
		Button hquit = new Button(new Sprite(quit),new Sprite (selspr));
		hplay.SetPosition(Constants.display_width/2-512, Constants.display_height/8);
		hsettings.SetPosition(Constants.display_width/2-180,Constants.display_height/8);
		hquit.SetPosition(Constants.display_width/2+460, Constants.display_height/8);
		
		//buttongroup!
		home = new ButtonGroup(new Button[] {hplay,hsettings,hquit});
		
		
		//solo.SetPosition(Constants.display_width/2+20, Constants.display_height/8);
		//duel.SetPosition(Constants.display_width/2-260,Constants.display_height/8);
		
		spr_title.setSize(Constants.display_width, Constants.display_height);
		
		//buttongroup hiding
		play.active = false;
		home.active = true;
	}
	
	@Override
	public void render(float delta) {
		core.batch.begin();
		cam.unproject(new Vector3().set(Gdx.input.getX(), Gdx.input.getY(), 0));
		spr_title.draw(core.batch);
		//spr_solo.draw(core.batch);
		//spr_duel.draw(core.batch);
		
		//updateButtons();
		//solo.draw(core.batch);
		//duel.draw(core.batch);
		
		//buttongroups
		updateGroups();
		
		play.draw(core.batch);
		home.draw(core.batch);
		
		//highlightButton();
		core.batch.end();
	}
	public void updateGroups(){
		Object[] pid = play.update(Gdx.input.getX(),Constants.display_height - Gdx.input.getY());
		Object[] hid = home.update(Gdx.input.getX(),Constants.display_height - Gdx.input.getY());
		
		//home buttons
		if(contains(hid,0)){
			home.active = false;
			play.active = true;
			return;
		}
		if(contains(hid,1)){
			//TODO: go to settings
			return;
		}
		if(contains(hid,2)){ //quit mah dudes
			dispose();
			System.exit(0);
			return;
		}
		
		//play buttons
		if(contains(pid,0)){ //duel
			core.setScreen(new SpaceWarsDuel(core));
			dispose();
			return;
		}
		if(contains(pid,1)){ //solo
			core.setScreen(new SpaceWarsSolo(core));
			SpaceWarsCore.inSoloMode = true;
			dispose();
			return;
		}
		if(contains(pid,2)){
			//TODO: ship selection screen
			return;
		}
		if(contains(pid,3)){
			home.active = true;
			play.active = false;
			return;
		}
		
	}
	public boolean contains(Object[] o, Object t){
		if(o == null){
			return false;
		}
		for(int i = 0; i < o.length;i++){
			if(o[i].equals(t)){
				return true;
			}
		}
		return false;
	}
	/*
	public void updateButtons(){ //10/10 vry simple
		solo.Update(Gdx.input.getX(),Constants.display_height - Gdx.input.getY());
		duel.Update(Gdx.input.getX(),Constants.display_height - Gdx.input.getY());
		
		if(solo.pressed){
			core.setScreen(new SpaceWarsSolo(core));
			SpaceWarsCore.inSoloMode = true;
			dispose();
		}
		if(duel.pressed){
			core.setScreen(new SpaceWarsDuel(core));
			dispose();
		}
	}
	
	/*
	public void highlightButton() {
		if (Gdx.input.getX() > Constants.display_width/2 
			&& Gdx.input.getX() < Constants.display_width/2+256
			&& Gdx.input.getY() > 6*Constants.display_height/8 
			&& Gdx.input.getY() < 6*Constants.display_height/8+128) {
			tex_solo = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartSoloB.png"));
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				core.setScreen(new SpaceWarsSolo(core));
				SpaceWarsCore.inSoloMode = true;
				dispose();
			}
		} else {
			tex_solo = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartSoloA.png"));
		}
		if (Gdx.input.getX() > Constants.display_width/2-256 
			&& Gdx.input.getX() < Constants.display_width/2
			&& Gdx.input.getY() > 6*Constants.display_height/8 
			&& Gdx.input.getY() < 6*Constants.display_height/8+128) {
			tex_duel = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartDuelB.png"));
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				core.setScreen(new SpaceWarsDuel(core));
				dispose();
			}
		} else {
			tex_duel = new Texture(Gdx.files.internal("assets/sprites/Menu & Title Screens/Title Screen/StartDuelA.png"));
		}
		spr_solo.setTexture(tex_solo);
		spr_duel.setTexture(tex_duel);
	}
	*/
	
	@Override
	public void dispose() {
		tex_title.dispose();
		tex_solo.dispose();
		tex_duel.dispose();
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
class ButtonGroup{
	public java.util.ArrayList<Button> butts; //reeeeaaall mature
	public boolean active = true;
	
	public ButtonGroup(Button[] buttons){
		butts = new ArrayList<Button> (Arrays.asList(buttons));
	}
	public ButtonGroup(String[] buttonPaths){
		for(int i = 0; i < buttonPaths.length; i++){
			Texture t = new Texture(Gdx.files.internal(buttonPaths[i]));
			Sprite bspr = new Sprite(t);
			Texture s = new Texture(Gdx.files.internal(Constants.selector));
			Sprite sspr = new Sprite(s);
			butts.add(new Button(bspr,sspr));
		}
	}
	public ButtonGroup(String[] buttonPaths,int[][] loc){
		for(int i = 0; i < buttonPaths.length; i++){
			Texture t = new Texture(Gdx.files.internal(buttonPaths[i]));
			Sprite bspr = new Sprite(t);
			Texture s = new Texture(Gdx.files.internal(Constants.selector));
			Sprite sspr = new Sprite(s);
			butts.add(new Button(bspr,sspr));
			butts.get(i).SetPosition(loc[0][i], loc[1][i]); //probably glitchy
		}
	}
	public void add(Button b){
		butts.add(b);
	}
	/**
	 * Returns an array containing the IDs of all pushed buttons. If not active, returns null
	 * @param mouseX - X of mouse
	 * @param mouseY - Y of mouse
	 * @return Array containing IDs of all pushed buttons, in order of creation. Button left out if not selected. If not active, returns null
	 */
	public Object[] update(int mouseX, int mouseY){
		if(!active){
			//Object[] o = {0};
			//return o;
			return null;
		}
		ArrayList<Integer> outs = new ArrayList<Integer>();
		
		for(int i = 0; i < butts.size(); i++){
			boolean sel = butts.get(i).Update(mouseX, mouseY);
			if(sel){
				outs.add(i);
			}
		}
		
		return outs.toArray();
	}
	/**
	 * Draws all buttons in the group 
	 * @param batch - the spritebatch to draw on
	 */
	public void draw(SpriteBatch batch){
		if(!active){
			return;
		}
		for(int i = 0; i < butts.size(); i++){
			butts.get(i).draw(batch);
		}
	}
}

