package editor.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;

import java.util.ArrayList;
import java.util.List;

import editor.core.control.DialogEditorInputProcessor;
import editor.core.control.DialogGestures;
import editor.core.control.DialogInputProcessor;
import editor.core.screen.elements.DialogueLine;

public class MainScreen implements Screen {

    private SpriteBatch spriteBatch;
    private ShapeRenderer renderer;


    private float worldWidth;
    private float worldHeight;
    private OrthographicCamera camera;
    private InputMultiplexer inputMultiplexer;
    private DialogInputProcessor input;
    private DialogGestures gestures;

    public static List<DialogueLine> screenElements = new ArrayList<>();

    public MainScreen(SpriteBatch batch, ShapeRenderer renderer, OrthographicCamera camera) {
        this.spriteBatch = batch;
        this.camera = camera;
        this.renderer = renderer;
    }

    @Override
    public void show() {
        worldWidth = Gdx.graphics.getWidth();
        worldHeight = Gdx.graphics.getHeight();


        camera = new OrthographicCamera(worldWidth, worldHeight);
        camera.position.set(worldWidth / 2, worldHeight / 2, 0);
        camera.update();
        inputMultiplexer = new InputMultiplexer();
        input = new DialogEditorInputProcessor(camera, this);
        gestures = new DialogGestures(camera);

        inputMultiplexer.addProcessor(input);
        inputMultiplexer.addProcessor(new GestureDetector(gestures));

        Gdx.input.setInputProcessor(inputMultiplexer);

        screenElements.add(new DialogueLine( 50, 200));
        screenElements.add(new DialogueLine(550, 200));

    }

    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setProjectionMatrix(camera.combined);
        spriteBatch.setProjectionMatrix(camera.combined);
        for (DialogueLine dLine: screenElements) {
            dLine.render(renderer, spriteBatch);
        }
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
