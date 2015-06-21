package space_studios.objects;


import space_studios.objects.BaseshipObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class BulletBlue extends BaseshipObject {

	public ShipTypes getType() {
		return ShipTypes.Bullet; 
	}
	
	public BulletBlue() {
		super();
	}
	
	public void Init() {
		Life = 1;
		Damage = 1;
		Mask = new Rectangle (0.0f,0.0f,32.0f,16.0f);
		Texture = new Texture(Gdx.files.internal("assets/sprites/BlueBullet.png"));
		Sprite = new Sprite(Texture,0,0,32,16);
		this.setPlace(0, 0);
		Blue=true; 
		Speed=7;
	}
}