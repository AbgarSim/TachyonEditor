package editor.core.control;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import editor.core.screen.elements.DialogueLine;
import editor.core.screen.MainScreen;

public abstract class DialogInputProcessor implements InputProcessor {

    protected OrthographicCamera camera;

    public DialogInputProcessor(OrthographicCamera camera) {
        this.camera = camera;
    }

    protected Vector2 unprojectCoordinates(float x, float y) {
        Vector3 raw = new Vector3(x, y, 0);
        camera.unproject(raw);
        return new Vector2(raw.x, raw.y);
    }
}
