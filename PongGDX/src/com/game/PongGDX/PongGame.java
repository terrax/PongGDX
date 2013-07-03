package com.game.PongGDX;

import com.badlogic.gdx.Game;
import com.game.PongGDX.Screens.GameScreen;

public class PongGame extends Game {
	
	@Override
	public void create() {		
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
	
}
