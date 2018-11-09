package editor.core.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MyGestureListener extends InputListener implements GestureDetector.GestureListener {


    private float velX, velY;
    private boolean flinging = false;
    private float initialScale = 1;

    private OrthographicCamera camera;

    private Vector2 lastTouch = new Vector2();

    public MyGestureListener(OrthographicCamera camera) {
        this.camera = camera;
    }

    /*
         A user touches the screen.
         */
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        flinging = false;
        initialScale = camera.zoom;
        return true;
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

        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        flinging = true;
        velX = camera.zoom * velocityX * 0.5f;
        velY = camera.zoom * velocityY * 0.5f;
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean zoom(float originalDistance, float currentDistance) {
        float ratio = originalDistance / currentDistance;
        camera.zoom = initialScale * ratio;
        System.out.println(camera.zoom);
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer) {

        return false;
    }

    @Override
    public void pinchStop() {
    }

    public void update() {
        if (flinging) {
            velX *= 0.98f;
            velY *= 0.98f;
            camera.position.add(-velX * Gdx.graphics.getDeltaTime(), velY * Gdx.graphics.getDeltaTime(), 0);
            if (Math.abs(velX) < 0.01f) velX = 0;
            if (Math.abs(velY) < 0.01f) velY = 0;
        }
    }



    public boolean keyDown(int i) {
        if (i == Input.Keys.DOWN || i == Input.Keys.S)
            camera.position.set(camera.position.x, camera.position.y -= 5, 0);
        if (i == Input.Keys.UP || i == Input.Keys.W)
            camera.position.set(camera.position.x, camera.position.y += 5, 0);
        if (i == Input.Keys.LEFT || i == Input.Keys.A)
            camera.position.set(camera.position.x -= 5, camera.position.y, 0);
        if (i == Input.Keys.RIGHT || i == Input.Keys.D)
            camera.position.set(camera.position.x += 5, camera.position.y, 0);
        return false;
    }


    public boolean keyUp(int i) {
        return false;
    }


    public boolean keyTyped(char c) {
        return false;
    }


    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastTouch.set(screenX, screenY);
        return false;
    }


    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }


    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 newTouch = new Vector2(screenX, screenY);
        Vector2 delta = newTouch.cpy().sub(lastTouch);
        lastTouch = newTouch;
        camera.position.set(delta, 0);
        return false;
    }


    public boolean mouseMoved(int i, int i1) {
        return false;
    }


    public boolean scrolled(int i) {
        float zoomDirection = 2 * i;
        camera.zoom += zoomDirection;
        return false;
    }
}