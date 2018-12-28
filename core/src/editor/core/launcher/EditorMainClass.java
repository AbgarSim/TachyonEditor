package editor.core.launcher;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import editor.core.screen.MainScreen;
import editor.core.screen.elements.condition.ConditionsService;

public class EditorMainClass extends Game {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private ShapeRenderer renderer;


	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.zoom += 5f;
		camera.update();

		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		renderer = new ShapeRenderer();
		renderer.setProjectionMatrix(camera.combined);

		this.setScreen(new MainScreen(batch, renderer, camera));
		ConditionsService.loadConditions();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}

}
