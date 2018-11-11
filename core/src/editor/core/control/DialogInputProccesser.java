package editor.core.control;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import editor.core.launcher.Dialogue;
import editor.core.launcher.DialogueLine;
import editor.core.screens.MainScreen;

public class DialogInputProccesser implements InputProcessor {

    private OrthographicCamera camera;

    private Vector3 lastTouch = new Vector3();

    private boolean isDraggingCollidable = false;
    private DialogueLine currentDrag;

    public DialogInputProccesser(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int i) {
        return false;
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
        lastTouch.set(i, i1, 0);
        currentDrag = getCollisionIfExist(new Vector3(i, i1, 0));
        if (currentDrag != null)
            isDraggingCollidable = true;
        System.out.println("x : " + i + " y: " + i1);
        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        if (isDraggingCollidable) {
            currentDrag = null;
            isDraggingCollidable = false;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {

        Vector3 newTouch = new Vector3(i, i1, 0);
        Vector3 delta = newTouch.cpy().sub(lastTouch);
        lastTouch = newTouch;
        if (isDraggingCollidable) {
            currentDrag.setPositions(currentDrag.getPosition().x + delta.x, currentDrag.getPosition().y - delta.y);
        } else {
            camera.position.set(camera.position.x - delta.x, camera.position.y + delta.y, 0);
            camera.update();
        }
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
        if (camera.zoom >= 0) {
            camera.update();
        }
        return true;
    }


    public DialogueLine getCollisionIfExist(Vector3 coords) {
        camera.unproject(coords);
        for (DialogueLine line : MainScreen.screenElements) {
            if ((coords.x >= line.getPosition().x &&
                    coords.x <= (line.getPosition().x + line.getProportions().x) &&
                    coords.y >= line.getPosition().y &&
                    coords.y <= (line.getPosition().y + line.getProportions().y))) {
                System.out.println("posx - " + coords.x + " posy - " + coords.y);
                System.out.println(" line.getPosition().x - " + line.getPosition().x);
                System.out.println(" line.getPosition().y - " + line.getPosition().y);
                System.out.println(" line.getPosition().x + line.getProportions().x - "
                        + (line.getPosition().x + line.getProportions().x));

                System.out.println(" line.getPosition().y + line.getProportions().y - "
                        + (line.getPosition().y + line.getProportions().y));
                return line;
            }
        }
        return null;
    }
}
