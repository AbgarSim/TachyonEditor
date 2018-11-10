package editor.core.control;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class DialogInputProccesser implements InputProcessor {

    private OrthographicCamera camera;

    private Vector2 lastTouch = new Vector2();

    public DialogInputProccesser(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int i) {
        if (i == Input.Keys.DOWN || i == Input.Keys.S)
            //camera.position.set(camera.position.x, camera.position.y + 2, 0);
            camera.translate(0, 2);
        if (i == Input.Keys.UP || i == Input.Keys.W)
            //camera.position.set(camera.position.x, camera.position.y - 2, 0);
            camera.translate(0, -2);
        if (i == Input.Keys.LEFT || i == Input.Keys.A)
            //camera.position.set(camera.position.x - 2, camera.position.y, 0);
            camera.translate(-2, 0);
        if (i == Input.Keys.RIGHT || i == Input.Keys.D)
            //camera.position.set(camera.position.x + 2, camera.position.y, 0);
            camera.translate(2, 0);
        camera.update();
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        lastTouch.set(i, i1);
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        Vector2 newTouch = new Vector2(i, i1);
        Vector2 delta = newTouch.cpy().sub(lastTouch);
        lastTouch = newTouch;
        camera.position.set(camera.position.x - delta.x, camera.position.y + delta.y, 0);
        camera.update();
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    // -1 or 1 depending on the direction of the wheel
    @Override
    public boolean scrolled(int i) {
        float zoomVar = 0.2f;
        camera.zoom += zoomVar * i;
        if (camera.zoom >= 0)
            camera.update();
        return true;
    }
}
