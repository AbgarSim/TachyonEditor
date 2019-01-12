package editor.core.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.text.View;

import editor.core.screen.MainScreen;

//Nested class
public class DialogueGestureListener implements GestureDetector.GestureListener{

    private float initialScale = 1;
    private OrthographicCamera camera;
    private Viewport viewport;
    private MainScreen screen;


    public DialogueGestureListener(OrthographicCamera camera, Viewport viewport, MainScreen screen) {
        this.camera = camera;
        this.viewport = viewport;
        this.screen = screen;
    }

    /*
           A user touches the screen.
           */
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    /*
        A user touches the screen and lifts the finger again.
        The finger must not move outside a specified square area around the initial touch position for a tap to be registered.
        Multiple consecutive taps will be detected if the user performs taps within a specified time interval.
    */
    @Override
    public boolean tap(float x, float y, int count, int button) {

        return false;
    }

    /*
        A user touches the screen for some time.
    * */
    @Override
    public boolean longPress(float x, float y) {
        Vector2 coords = unprojectCoordinates(x, y);
        screen.addDialogueLine(coords);
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        //camera.translate(-deltaX, deltaY);
        //camera.setPosition();
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean zoom(float originalDistance, float currentDistance) {
        this.screen.setZoomPointers(2);
        float ratio = originalDistance / currentDistance;
        camera.zoom = initialScale * ratio;
        if(camera.zoom >= 0) {
            camera.update();
            viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer) {

        return false;
    }

    @Override
    public void pinchStop() {
    }

    private Vector2 unprojectCoordinates(float x, float y) {
        Vector3 raw = new Vector3(x, y, 0);
        camera.unproject(raw);
        return new Vector2(raw.x, raw.y);
    }
}
