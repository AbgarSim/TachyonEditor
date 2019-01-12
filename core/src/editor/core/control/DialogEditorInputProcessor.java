package editor.core.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.HashMap;

import editor.core.elements.visual.DialogueLineElement;
import editor.core.screen.MainScreen;

public class DialogEditorInputProcessor implements InputProcessor {

    private OrthographicCamera camera;
    private Viewport viewport;
    private MainScreen screen;
    private Vector2 lastTouch = new Vector2();
    private boolean isDraggingCollidable = false;


    private HashMap<Integer, DialogueLineElement> currentDrags = new HashMap<>();


    public DialogEditorInputProcessor(OrthographicCamera camera, Viewport viewport, MainScreen screen) {
        this.camera = camera;
        this.screen = screen;
        this.viewport = viewport;
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
    public boolean touchDown(int x, int y, int pointer, int dy) {
        lastTouch.x = x;
        lastTouch.y = y;
        Vector2 coords = unprojectCoordinates(x, y);
        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int pointer, int i3) {
        this.screen.decrementZoomPointers();
        if (isDraggingCollidable) {
            DialogueLineElement currentDrag = currentDrags.get(pointer);
            if (currentDrag != null)
                currentDrags.remove(pointer);
            isDraggingCollidable = false;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if (this.screen.getZoomPointers() == 0) {
            Vector2 newTouch = new Vector2(x, y);
            if (!isDraggingCollidable) {
                Vector2 delta = newTouch.cpy().sub(lastTouch);
                Vector3 p = viewport.getCamera().position.cpy();
                viewport.getCamera().position.set((p.x - delta.x * camera.zoom), (p.y + delta.y * camera.zoom), 0);
                viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
            }
            lastTouch = newTouch;
            return true;
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


    public DialogueLineElement getCollisionIfExist(Vector2 coords) {
        for (DialogueLineElement line : screen.screenElements) {
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

