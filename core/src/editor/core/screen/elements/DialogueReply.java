package editor.core.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.Text;

import editor.core.resource.ResourceManager;
import editor.core.screen.elements.condition.Condition;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

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


   // private Rectangle messageRectangle = new Rectangle();
   // private Rectangle messageButtonRectangle = new Rectangle();

    private TextField messageTextField;

    private TextButton buttonToEditCondition;
    private TextButton buttonToRemoveReply;

    public DialogueReply(String replyId, String next, String replyText, DialogueLine parent) {
        id = replyId;
        nextMessage = next;
        text = replyText;
        this.parent = parent;

        messageTextField = new TextField("Default for now", ResourceManager.getSkin());
        //messageRectangle.set(parent.getPosition().x + 5, parent.getPosition().y - 5,
        //        replyProportions.x, replyProportions.y);

        messageTextField.setBounds(parent.getPosition().x + 5, parent.getPosition().y - 5,
                replyProportions.x, replyProportions.y);
        ;

        buttonToEditCondition = new TextButton("Cond", ResourceManager.getSkin());
        buttonToEditCondition.setBounds(parent.getPosition().x + messageTextField.getWidth() + 20, parent.getPosition().y - 5,
                replyButtonProportions.x, replyButtonProportions.y);
        buttonToEditCondition.addListener(new EditConditionEventListener());
        parent.addActorToStage(buttonToEditCondition);

        buttonToRemoveReply = new TextButton("-", ResourceManager.getSkin());
        buttonToRemoveReply.setBounds(parent.getPosition().x + buttonToEditCondition.getX() + 10, parent.getPosition().y - 5,
                replyButtonProportions.x, replyButtonProportions.y);
        buttonToRemoveReply.addListener(new RemoveReplyListener());
        parent.addActorToStage(buttonToRemoveReply);
    }

    public Vector2 getProportions() {
        return new Vector2(messageTextField.getWidth() + buttonToEditCondition.getWidth() + buttonToRemoveReply.getWidth() + 30, messageTextField.getHeight());
    }

    public void addCondition(Condition cond) {
        conditions.add(cond);
    }

    public void setPosition(float x, float y) {
        this.messageTextField.setPosition(x, y);
        this.buttonToEditCondition.setPosition(messageTextField.getX() + messageTextField.getWidth(), y);
        this.buttonToRemoveReply.setPosition(buttonToEditCondition.getX() + buttonToEditCondition.getWidth(), y);
    }

    public Vector2 getPosition() {
        return new Vector2(messageTextField.getX(), messageTextField.getY());
    }


    //public void renderShapes(ShapeRenderer renderer) {
    //    renderer.rect(messageRectangle.x, messageRectangle.y,
    //            messageRectangle.width, messageRectangle.height);
    //}

    public void render(Batch batch) {
        messageTextField.act(Gdx.graphics.getDeltaTime());
        messageTextField.draw(batch, 5f);
        buttonToEditCondition.act(Gdx.graphics.getDeltaTime());
        buttonToEditCondition.draw(batch, 5f);
        buttonToRemoveReply.act(Gdx.graphics.getDeltaTime());
        buttonToRemoveReply.draw(batch, 5f);
    }

    class EditConditionEventListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            EditDialogueReplyPopUp dialogueReplyPopUp = new EditDialogueReplyPopUp("Edit condition");

            dialogueReplyPopUp.setPosition(Gdx.graphics.getWidth() / 2 - dialogueReplyPopUp.getWidth() / 2,
                    Gdx.graphics.getHeight() / 2 - dialogueReplyPopUp.getHeight() / 2);
            dialogueReplyPopUp.show(parent.getParentScreen().getStage(), sequence(Actions.alpha(0), Actions.fadeIn(0.4f, Interpolation.fade)));
            return true;
        }
    }

    class RemoveReplyListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            DialogueReply.this.parent.removeReply(DialogueReply.this);
            DialogueReply.this.buttonToEditCondition.remove();
            DialogueReply.this.buttonToRemoveReply.remove();
            return true;
        }
    }
}
