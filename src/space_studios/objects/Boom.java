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
		texture.add(new Texture(Gdx.files.internal("assets/sprites/Fiery Explotion/frame 1.png")));
		sprite.add(new Sprite(texture.get(0),0,0,128,128));
		texture.add(new Texture(Gdx.files.internal("assets/sprites/Fiery Explotion/frame 2.png")));
		sprite.add(new Sprite(texture.get(1),0,0,128,128));
		texture.add(new Texture(Gdx.files.internal("assets/sprites/Fiery Explotion/frame 3.png")));
		sprite.add(new Sprite(texture.get(2),0,0,128,128));
		texture.add(new Texture(Gdx.files.internal("assets/sprites/Fiery Explotion/frame 4.png")));
		sprite.add(new Sprite(texture.get(3),0,0,128,128));
		texture.add(new Texture(Gdx.files.internal("assets/sprites/Fiery Explotion/frame 5.png")));
		sprite.add(new Sprite(texture.get(4),0,0,128,128));
		texture.add(new Texture(Gdx.files.internal("assets/sprites/Fiery Explotion/frame 6.png")));
		sprite.add(new Sprite(texture.get(5),0,0,128,128));
		texture.add(new Texture(Gdx.files.internal("assets/sprites/Fiery Explotion/frame 7.png")));
		sprite.add(new Sprite(texture.get(6),0,0,128,128));
		texture.add(new Texture(Gdx.files.internal("assets/sprites/Fiery Explotion/frame 8.png")));
		sprite.add(new Sprite(texture.get(7),0,0,128,128));
		texture.add(new Texture(Gdx.files.internal("assets/sprites/Fiery Explotion/frame 9.png")));
		sprite.add(new Sprite(texture.get(8),0,0,128,128));
		texture.add(new Texture(Gdx.files.internal("assets/sprites/Fiery Explotion/frame 10.png")));
		sprite.add(new Sprite(texture.get(9),0,0,128,128));
		texture.add(new Texture(Gdx.files.internal("assets/sprites/Fiery Explotion/frame 11.png")));
		sprite.add(new Sprite(texture.get(10),0,0,128,128));
		texture.add(new Texture(Gdx.files.internal("assets/sprites/Fiery Explotion/frame 12 (not necessary).png")));
		sprite.add(new Sprite(texture.get(11),0,0,128,128));
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
		//add one to frame and if twelve, destroy
		frame++;
		if (frame>=12){
			frame=0;
			Show = false;
		}
		
		//sets current sprite to current place
		this.setPlace(X,Y);
		
	}
	
	//sets position of the object
	public void setPlace(float xPosition,float yPosition){
		if (frame>=12){
			return;
		}
		sprite.get(frame).setPosition(xPosition, yPosition);
		sprite.get(frame).setScale(Size);
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
