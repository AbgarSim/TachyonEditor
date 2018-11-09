package editor.core.launcher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import editor.core.control.MyGestureListener;
import editor.core.shape.Rectangle;

public class EditorMainClass extends ApplicationAdapter {

	private float worldWidth;
	private float worldHeight;

	private float rectangleWidth = 50;
	private float rectangleHeight = 100;

	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Rectangle rectangle1;
	private Rectangle rectangle2;

	MyGestureListener listener;
	InputMultiplexer multiplexer;

	Sprite sprite;

	@Override
	public void create () {
		batch = new SpriteBatch();
		sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
		worldWidth = Gdx.graphics.getWidth();
		worldHeight = Gdx.graphics.getHeight();


		camera = new OrthographicCamera(worldWidth, worldHeight);
		camera.position.set(worldWidth/2, worldHeight/2, 0);
		camera.update();


		rectangle1 = new Rectangle(290, 100, rectangleWidth, rectangleHeight, Color.BLACK);
		rectangle2 = new Rectangle(190, 100, rectangleWidth, rectangleHeight, Color.BLACK);


		Gdx.input.setInputProcessor(listener);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		sprite.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
