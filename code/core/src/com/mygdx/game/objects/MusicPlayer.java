package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class MusicPlayer {
	private static Sound music;
	//84.37
	public void init(){
		music = Gdx.audio.newSound(Gdx.files.internal("sounds/Music.wav"));
		music.play();
		music.setLooping(0, true);
	}
	public static void update(){

	}
}
