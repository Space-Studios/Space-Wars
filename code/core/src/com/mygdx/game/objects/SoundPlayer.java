package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundPlayer {
	
	private static Sound boom;
	private static Sound shoot;
	private static Sound bigBoom;
	
	public void init(){
		/*
		boom = Gdx.audio.newSound(Gdx.files.internal("sounds/Music.wav"));
		shoot = Gdx.audio.newSound(Gdx.files.internal("sounds/Music.wav"));
		bigBoom = Gdx.audio.newSound(Gdx.files.internal("sounds/Music.wav"));
		*/
	}
	
	public void playBoom(){
		boom.play();
	}
	
	public void playShoot(){
		shoot.play();
	}
	
	public void playBigBoom(){
		bigBoom.play();
	}
}
