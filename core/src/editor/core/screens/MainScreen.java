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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;

import java.util.ArrayList;
import java.util.List;

import editor.core.control.DialogGestures;
import editor.core.control.DialogInputProccesser;
import editor.core.launcher.DialogueLine;
import editor.core.launcher.ScreenElement;

public class MainScreen implements Screen {

    private Game game;
    private SpriteBatch spriteBatch;

    private float worldWidth;
    private float worldHeight;
    private OrthographicCamera camera;
    private InputMultiplexer inputMultiplexer;
    private DialogInputProccesser input;
    private DialogGestures gestures;

    private DialogueLine dialogueLine1;
    private DialogueLine dialogueLine2;

    public static List<DialogueLine> screenElements = new ArrayList<DialogueLine>();

    public MainScreen(Game game, SpriteBatch batch, ShapeRenderer renderer, OrthographicCamera camera) {
        this.spriteBatch = batch;
        this.camera = camera;
    }

    @Override
    public void show() {
        worldWidth = Gdx.graphics.getWidth();
        worldHeight = Gdx.graphics.getHeight();


        camera = new OrthographicCamera(worldWidth, worldHeight);
        camera.position.set(worldWidth / 2, worldHeight / 2, 0);
        camera.update();
        inputMultiplexer = new InputMultiplexer();
        input = new DialogInputProccesser(camera);
        gestures = new DialogGestures(camera);

        inputMultiplexer.addProcessor(input);
        inputMultiplexer.addProcessor(new GestureDetector(gestures));

        Gdx.input.setInputProcessor(inputMultiplexer);

        dialogueLine1 = new DialogueLine(100, 100, 50, 200);
        dialogueLine2 = new DialogueLine(100, 100, 250, 200);

        screenElements.add(dialogueLine1);
        screenElements.add(dialogueLine2);

    }

    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
        dialogueLine1.render(spriteBatch);
        dialogueLine2.render(spriteBatch);
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
