package editor.core.control;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import editor.core.screen.elements.DialogueLine;
import editor.core.screen.MainScreen;

public class DialogInputProccesser implements InputProcessor {

    private OrthographicCamera camera;

    private Vector2 lastTouch = new Vector2();

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
    public boolean touchDown(int x, int y, int dx, int dy) {
        lastTouch.x = x;
        lastTouch.y = y;
        Vector2 coords = unprojectCoordinates(x, y);
        currentDrag = getCollisionIfExist(coords);
        if (currentDrag != null) {
            isDraggingCollidable = true;
        }
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
    public boolean touchDragged(int x, int y, int pointer) {
        Vector2 newTouch = new Vector2(x, y);
        if (isDraggingCollidable) {
            Vector2 coordsOld = unprojectCoordinates(lastTouch.x, lastTouch.y);
            Vector2 coordsNew = unprojectCoordinates(newTouch.x, newTouch.y);
            Vector2 delta = coordsNew.sub(coordsOld);
            currentDrag.move(delta);
        } else {
            Vector2 delta = newTouch.cpy().sub(lastTouch);
            Vector3 p = camera.position.cpy();
            camera.position.set((p.x-delta.x*camera.zoom), (p.y+delta.y*camera.zoom), 0);
            camera.update();
        }
        lastTouch = newTouch;
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


    public DialogueLine getCollisionIfExist(Vector2 coords) {
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

    private Vector2 unprojectCoordinates(float x, float y) {
        Vector3 raw = new Vector3(x, y, 0);
        camera.unproject(raw);
        return new Vector2(raw.x, raw.y);
    }
}
