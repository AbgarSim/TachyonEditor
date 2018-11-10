package editor.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import editor.core.control.DialogGestures;
import editor.core.control.DialogInputProccesser;
import editor.core.shape.Rectangle;

public class MainScreen implements Screen {

    private Game game;
    private Batch batch;
    private Sprite sprite;
    private float worldWidth;
    private float worldHeight;
    private OrthographicCamera camera;
    private InputMultiplexer inputMultiplexer;
    private DialogInputProccesser input;
    private DialogGestures gestures;



    public MainScreen(Game game, Batch batch, OrthographicCamera camera) {
        this.batch = batch;
        this.camera = camera;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
        worldWidth = Gdx.graphics.getWidth();
        worldHeight = Gdx.graphics.getHeight();


        camera = new OrthographicCamera(worldWidth, worldHeight);
        camera.position.set(worldWidth / 2, worldHeight / 2, 0);
        camera.update();
        inputMultiplexer = new InputMultiplexer();
        input = new DialogInputProccesser(camera);
        gestures = new DialogGestures(camera);

        inputMultiplexer.addProcessor(input);
        Gdx.input.setInputProcessor(input);
        // rectangle1 = new Rectangle(290, 100, rectangleWidth, rectangleHeight, Color.BLACK);
        // rectangle2 = new Rectangle(190, 100, rectangleWidth, rectangleHeight, Color.BLACK);

    }

    @Override
    public void render(float v) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite.draw(batch);
        batch.end();
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
