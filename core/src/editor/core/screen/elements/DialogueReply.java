package editor.core.screen.elements;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import editor.core.screen.elements.condition.Condition;

public class DialogueReply {

    public static final String ACTION_TAG = "[ACTION]";
    public static final String SKIP_TAG = "[SKIP]";
    private final Vector2 replyProportions = new Vector2(145, 30);
    private final Vector2 replyButtonProportions = new Vector2(40, 30);
    private final DialogueLine parent;

    public String id;
    public String nextMessage;
    public String text;
    public List<Condition> conditions = new ArrayList<>();
    public boolean anyCondition;


    private Rectangle messageRectangle = new Rectangle();
    private Rectangle messageButtonRectangle = new Rectangle();

    public DialogueReply(String replyId, String next, String replyText, DialogueLine parent) {
        id = replyId;
        nextMessage = next;
        text = replyText;
        this.parent = parent;
        messageRectangle.set(parent.getPosition().x + 5, parent.getPosition().y - 5,
                replyProportions.x, replyProportions.y);
        messageButtonRectangle.set(parent.getPosition().x + 150, parent.getPosition().y - 5,
                replyButtonProportions.x, replyButtonProportions.y);

    }

    public Vector2 getReplyProportions() {
        return new Vector2(messageRectangle.width + messageButtonRectangle.width, messageRectangle.height);
    }

    public void addCondition(Condition cond) {
        conditions.add(cond);
    }

    public void setPosition(Vector2 position) {
        this.messageRectangle.x = position.x;
        this.messageRectangle.y = position.y;
        this.messageButtonRectangle.x = messageRectangle.x + messageRectangle.width;
        this.messageButtonRectangle.y = position.y;
    }

    public void render(ShapeRenderer renderer) {
        renderer.rect(messageRectangle.x, messageRectangle.y,
                messageRectangle.width, messageRectangle.height);
    }

}
