package editor.core.launcher;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import editor.core.screens.MainScreen;

public class EditorMainClass extends Game {

	private Game game;
	private OrthographicCamera camera;
	private Batch batch;

	public EditorMainClass() {
		this.game = this;
	}

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.zoom += 5f;
		camera.update();

		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);


		this.setScreen(new MainScreen(this, batch, camera));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}

}
