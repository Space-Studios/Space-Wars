package space_studios.screens;

import space_studios.objects.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Button {
	final Sprite sprite;
	final boolean selectable;
	boolean selected;
	Sprite select;
	float x = 0;
	float y = 0;
	final Polygon bounds;
	final Polygon mouse = new Polygon(new float[]{-10,-10,-10,10,10,10,10,-10});
	
	public boolean pressed;
	
	public Button (Sprite spr){
		sprite = spr;
		selectable = false;
		bounds = new Polygon (new float[] {0,0
				,sprite.getWidth(),0,
				0,sprite.getHeight(),
				sprite.getWidth(),sprite.getHeight()});
		bounds.setScale(sprite.getScaleX(), sprite.getScaleY());
	}
	public Button (Sprite spr,Sprite selected1){
		sprite = spr;
		select = selected1;
		select.setSize(sprite.getWidth(), sprite.getHeight()); //set to correct scale
		selectable = true;
		bounds = new Polygon (new float[] {
											0,0
											,sprite.getWidth(),0,
											sprite.getWidth(),sprite.getHeight(),
											0,sprite.getHeight()
											});
		
		bounds.setScale(sprite.getScaleX(), sprite.getScaleY());
	}
	
	public Boolean Update(float mouseX, float mouseY){
		//get mouse x and y
		//float mouseX = Gdx.input.getX();
		//float mouseY = Gdx.input.getY();
		
		//if it is selectable, check if it's selected
		mouse.setPosition(mouseX, mouseY);
		
		if(Intersector.overlaps(bounds.getBoundingRectangle(), mouse.getBoundingRectangle()) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			pressed = true;
		}
		else{
			pressed = false;
		}
		
		if (!selectable){
			return pressed;
		}
		
		if(Intersector.overlaps(bounds.getBoundingRectangle(), mouse.getBoundingRectangle())){
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
		if(select != null){
			select.setPosition(x1, y1);
		}
		
	}
	public void draw (SpriteBatch batch){
		sprite.draw(batch);
		if(selected){
			select.draw(batch);
		}
		/*
		ShapeRenderer ren = new ShapeRenderer();
		ren.begin(ShapeType.Line);
		ren.setColor(Color.WHITE);
		ren.polygon(bounds.getTransformedVertices());
		ren.polygon(mouse.getTransformedVertices());
		ren.end();
		*/
	}
	public void Dispose(){
		
	}
	
}
