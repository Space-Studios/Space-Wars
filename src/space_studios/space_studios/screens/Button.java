package space_studios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
	final Sprite sprite;
	final boolean selectable;
	boolean selected;
	Sprite select;
	float x = 0;
	float y = 0;
	final Rectangle bounds;
	
	public boolean pressed;
	
	public Button (Sprite spr){
		sprite = spr;
		selectable = false;
		bounds = new Rectangle (0,0,sprite.getWidth(), sprite.getHeight());
	}
	public Button (Sprite spr,Sprite selected1){
		sprite = spr;
		select = selected1;
		select.setSize(sprite.getWidth(), sprite.getHeight()); //set to correct scale
		selectable = true;
		bounds = new Rectangle (0,0,sprite.getWidth(), sprite.getHeight());
	}
	
	public Boolean Update(){
		//get mouse x and y
		float mouseX = Gdx.input.getX();
		float mouseY = Gdx.input.getY();
		
		//if it is selectable, check if it's selected
		Rectangle mouse = new Rectangle(mouseX-10,mouseY-10,20,20);
		if(bounds.overlaps(mouse) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			pressed = true;
		}
		else{
			pressed = false;
		}
		
		if (!selectable){
			return pressed;
		}
		
		if(bounds.overlaps(mouse)){
			selected = true;
		}
		else{
			selected = false;
		}
		
		//return true if the button was pressed
		return pressed;
	}
	public void SetPosition (float x1, float y1){
		x = x1;
		y = y1;
		sprite.setPosition(x1, y1);
		bounds.setPosition(x1, y1);
	}
	public void draw (SpriteBatch batch){
		sprite.draw(batch);
		if(selected){
			select.draw(batch);
		}
	}
	public void Dispose(){
		
	}
	
}
