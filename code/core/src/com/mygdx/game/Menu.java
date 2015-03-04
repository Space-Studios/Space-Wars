package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu extends ApplicationAdapter{
	
	public Boolean Created;
	public static Texture texture;
	public static Sprite sprite;
	public static final SpriteBatch batch = new SpriteBatch();
	
	public Menu(){};
	public void init(){
		Created = true;
		texture = new Texture(Gdx.files.internal("sprites/Menu.png"));
		sprite = new Sprite(texture,0,0,1600,960);
	}
	public void render(){
		draw();
		keycheck();
	}
	public  void draw(){
		if (!Created){
			return;
		}
		
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}
	public Boolean keycheck(){
		if (!Created){
			return false;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
			 Created=false;
			 return true;
		 }
		 return false;
	}
}
