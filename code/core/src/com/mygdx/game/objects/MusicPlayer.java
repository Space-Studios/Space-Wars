package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class MusicPlayer {
	private static Sound music;
	//84.37
	public void init(){
		long musicID = 0L;
		music = Gdx.audio.newSound(Gdx.files.internal("sounds/Music.wav"));
		musicID = music.play();
		music.setLooping(musicID, true);
		music.setVolume(musicID, 0.2f);
	}
	public static void update(){

	}
}
