package com.game.PongGDX.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.PongGDX.PongGame;

public class GameScreen implements Screen {

	PongGame game;
	BitmapFont font;
	SpriteBatch spriteBatch;
	Texture texBall, texPaddle;
	Sprite sprLeftPaddle, sprRightPaddle, sprBall;
	
	Rectangle recLeftPaddle, recRightPaddle, recBall;
	Vector2 vecBallVelocity;
	
	int pointsLeft, pointsRight;
	
	public GameScreen(PongGame myGdxGame) {
		this.game = myGdxGame;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.isKeyPressed(Keys.UP) && recLeftPaddle.y < Gdx.graphics.getHeight() - recLeftPaddle.height) {
			recLeftPaddle.y += 3.33f;
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN) && recLeftPaddle.y > 0) {
			recLeftPaddle.y -= 3.33f;
		}
		
		recBall.x += vecBallVelocity.x;
		recBall.y += vecBallVelocity.y;
		
		if (recBall.overlaps(recLeftPaddle) || recBall.overlaps(recRightPaddle)) {
			vecBallVelocity.x *= -1;
			vecBallVelocity.y *= -1;
		}
		
		if(recBall.y <= 0 || recBall.y >= Gdx.graphics.getHeight() - recBall.height) {
			vecBallVelocity.y *= -1;
		}
		
		if(recBall.x < 0 - recBall.width) {
			recBall.x += 100;
			vecBallVelocity.x = 2;
			pointsRight++;
		}
		
		if (recBall.y + recBall.height / 2 < recRightPaddle.y + recRightPaddle.height / 2) {
			recRightPaddle.y -= 3.33f;
		}
		if (recBall.y + recBall.height / 2 > recRightPaddle.y + recRightPaddle.height / 2) {
			recRightPaddle.y += 3.33f;
		}
		
		sprLeftPaddle.setPosition(recLeftPaddle.x, recLeftPaddle.y);
		sprRightPaddle.setPosition(recRightPaddle.x, recRightPaddle.y);
		sprBall.setPosition(recBall.x, recBall.y);
		
		spriteBatch.begin();
		String heading = "Pong";
		font.draw(spriteBatch, heading, Gdx.graphics.getWidth() / 2 - font.getBounds(heading).width / 2, Gdx.graphics.getHeight() - 10);
		String points = pointsLeft + " - " + pointsRight;
		font.draw(spriteBatch, points, Gdx.graphics.getWidth() / 2 - font.getBounds(points).width / 2, font.getBounds(points).height + 10);
		
		sprLeftPaddle.draw(spriteBatch);
		sprRightPaddle.draw(spriteBatch);
		sprBall.draw(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		AssetManager manager = new AssetManager();
		manager.load("data/msgothic.fnt", BitmapFont.class);
		manager.load("data/ball.png", Texture.class);
		manager.load("data/paddle.png", Texture.class);
		manager.finishLoading();
		font = manager.get("data/msgothic.fnt", BitmapFont.class);
		texBall = manager.get("data/ball.png", Texture.class);
		texPaddle = manager.get("data/paddle.png", Texture.class);
		
		spriteBatch = new SpriteBatch();
		
		sprLeftPaddle = new Sprite(texPaddle);
		recLeftPaddle = new Rectangle(0, Gdx.graphics.getHeight() / 2 - sprLeftPaddle.getHeight() / 2, texPaddle.getWidth(), texPaddle.getHeight());
		sprLeftPaddle.setPosition(recLeftPaddle.x, recLeftPaddle.y);
		
		sprRightPaddle = new Sprite(texPaddle);
		recRightPaddle = new Rectangle(Gdx.graphics.getWidth() - sprRightPaddle.getWidth(), 
				Gdx.graphics.getHeight() / 2 - sprRightPaddle.getHeight() / 2, 
				texPaddle.getWidth(), 
				texPaddle.getHeight());
		sprRightPaddle.setPosition(recRightPaddle.x, recRightPaddle.y);
		
		sprBall = new Sprite(texBall);
		recBall = new Rectangle(200, 200, texBall.getWidth(), texBall.getHeight());
		sprBall.setPosition(recBall.x, recBall.y);
		vecBallVelocity = new Vector2(3, 0.5f);
		
		pointsLeft = 0;
		pointsRight = 0;
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
