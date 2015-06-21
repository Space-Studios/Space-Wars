package space_studios.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundPlayer {
	
	private static Sound boom;
	private static Sound shootshipLaunch;
	private static Sound win;
	
	public void init(){
		
		boom = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/explosion.wav"));
		shootshipLaunch = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/ship_launch_2.wav"));
		win = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/win.wav"));
		
	}
	public void playBoom(){
		long boomid = 0L;
		boomid = boom.play();
		boom.setPitch(boomid,0.9f);
		boom.setVolume(boomid, 0.3f);
	}
	
	public void playShoot(){
		long shootid = 0L;
		shootid = shootshipLaunch.play();
		shootshipLaunch.setPitch(shootid, 0.7f);
	}
	public void playShipLaunch(){
		long launchid = 0L;
		launchid = shootshipLaunch.play();
		shootshipLaunch.setPitch(launchid, 0.5f);
	}
	
	public void playWin(){
		long winid = 0L;
		winid = win.play();
		win.setPitch(winid,0.5f);
	}
}
