package editor.core.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import editor.core.screen.MainScreen;

public class DialogGesturesDetector extends GestureDetector {

    public DialogGesturesDetector(OrthographicCamera camera, MainScreen screen) {
        super(new DialogueGestureListener(camera, screen));
    }
}
