package space_studios.objects;

//this program is the boom and has NO BUGS!!!

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Boom {
	private List <Sprite> sprite =  new ArrayList<Sprite> ();
	private List <Texture> texture = new ArrayList<Texture> ();
	private static int frame;
	private static int counter;
	private static final int animationSpeed = Constants.animationSpeed;
	private static int X;
	private static int Y;
	private static float Size;
	public Boolean Show;
	
	//constructor
	public Boom(float Xpo,float Ypo,float howBig){
		frame = 0;
		counter=0;
		X = (int) Xpo-4;
		Y = (int) Ypo-80;
		Show = true;
		Size=howBig;
	}
	
	public void Init(){
		//texture and sprite adding
		for (int i = 0; i < 11; i++) {
			texture.add(new Texture(Gdx.files.internal("assets/sprites/Fiery Explotion/frame "+(i+1)+".png")));
			sprite.add(new Sprite(texture.get(i),0,0,128,128));
		}
	}
	
	//update
	public void update(){
		if (!Show){
			return;
		}
		
		//add one to counter
		counter ++;
		//if next frame
		if (counter!=animationSpeed){
			return;
		}
		//reset counter
		counter=0;
		//add one to frame and if eleven, destroy
		frame++;
		if (frame>=11){
			frame=0;
			Show = false;
		}
		
		//sets current sprite to current place
		this.setPlace(X,Y);
		
	}
	
	//sets position of the object
	public void setPlace(float xPosition,float yPosition){
		if (frame>=11){
			return;
		}
		sprite.get(frame).setPosition(xPosition, yPosition);
		sprite.get(frame).setScale(Size*Constants.ScreenScaleX());
		X = (int) xPosition;
		Y = (int) yPosition;
	}

	public void show(SpriteBatch batch){
		if (!Show){
			return;
		}
		this.setPlace(X, Y);
		sprite.get(frame).draw(batch);
	}	
}
