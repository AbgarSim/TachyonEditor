package editor.core.screen.elements;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class DialogueEvent {

    private final Vector2 eventProportions = new Vector2(135, 30);
    private final Vector2 eventButtonProportions = new Vector2(50, 30);

    private final DialogueLine parent;
    private Rectangle eventRectangle = new Rectangle();
    private Rectangle eventButtonRectangle = new Rectangle();

    public DialogueEvent(DialogueLine parent) {
        this.parent = parent;

        eventRectangle.set(this.parent.getPosition().x + 5, this.parent.getPosition().y - 5,
                eventProportions.x, eventProportions.y);
        eventButtonRectangle.set(this.parent.getPosition().x + 150, this.parent.getPosition().y - 5,
                eventButtonProportions.x, eventButtonProportions.y);
    }

    public void setPosition(Vector2 delta) {
        this.eventRectangle.x = delta.x;
        this.eventRectangle.y = delta.y;
        this.eventButtonRectangle.x = eventRectangle.x + eventRectangle.width;
        this.eventButtonRectangle.y = delta.y;
    }

    public Vector2 getProportions() {
        return new Vector2(eventRectangle.width + eventButtonRectangle.width, eventRectangle.height);
    }

    public void render(ShapeRenderer renderer) {
        renderer.rect(eventRectangle.x, eventRectangle.y,
                eventRectangle.width, eventRectangle.height);
        renderer.rect(eventButtonRectangle.x, eventButtonRectangle.y,
                eventButtonRectangle.width, eventButtonRectangle.height);
    }
}
