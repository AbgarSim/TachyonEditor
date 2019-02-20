package editor.core.elements.visual;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


import editor.core.elements.model.DialogueReplyModel;
import editor.core.screen.popup.EditDialogueReplyPopUp;
import editor.core.resource.ResourceManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class DialogueReplyElement implements Element{

    public static final String ACTION_TAG = "[ACTION]";
    public static final String SKIP_TAG = "[SKIP]";

    private final Vector2 replyProportions; //225 - 85
    private final Vector2 replybuttonproportions = new Vector2(40, 30);

    private final DialogueLineElement parent;
    private DialogueReplyModel model;

    private TextField messageTextField;
    private TextButton buttonToEditCondition;
    private TextButton buttonToRemoveReply;
    private TextButton buttonToAddRemoveNextDialogueLine;


    public DialogueReplyElement(DialogueReplyModel model, DialogueLineElement parent) {
        this.model = model;
        this.parent = parent;
        replyProportions = new Vector2(parent.getProportions().x - 125, 30);

        messageTextField = new TextField(getModel().getText(), ResourceManager.getSkin());
        parent.addActorToStage(messageTextField);
        //messageRectangle.set(parent.getPosition().x + 5, parent.getPosition().y - 5,
        //        replyProportions.x, replyProportions.y);

        messageTextField.setBounds(parent.getPosition().x + 5, parent.getPosition().y - 5,
                replyProportions.x, replyProportions.y);

        buttonToEditCondition = new TextButton("Cond", ResourceManager.getSkin());
        buttonToEditCondition.setBounds(parent.getPosition().x + messageTextField.getWidth() + 20, parent.getPosition().y - 5,
                replybuttonproportions.x, replybuttonproportions.y);
        buttonToEditCondition.addListener(new EditConditionEventListener());
        parent.addActorToStage(buttonToEditCondition);

        buttonToRemoveReply = new TextButton("-", ResourceManager.getSkin());
        buttonToRemoveReply.setBounds(parent.getPosition().x + buttonToEditCondition.getX() + 10, parent.getPosition().y - 5,
                replybuttonproportions.x, replybuttonproportions.y);
        buttonToRemoveReply.addListener(new RemoveReplyListener());
        parent.addActorToStage(buttonToRemoveReply);

        buttonToAddRemoveNextDialogueLine = new TextButton(">", ResourceManager.getSkin());
        buttonToAddRemoveNextDialogueLine.setBounds(buttonToRemoveReply.getX() + buttonToRemoveReply.getWidth() + 10, buttonToRemoveReply.getY(),
                replybuttonproportions.x, replybuttonproportions.y);
        buttonToAddRemoveNextDialogueLine.addListener(new ArrowButtonListener());
        parent.addActorToStage(buttonToAddRemoveNextDialogueLine);
    }

    public DialogueReplyModel getModel() {
        return model;
    }

    public Vector2 getProportions() {
        return new Vector2(messageTextField.getWidth() + buttonToEditCondition.getWidth() + buttonToRemoveReply.getWidth() + 30, messageTextField.getHeight());
    }


    public void setPosition(float x, float y) {
        this.messageTextField.setPosition(x, y);
        this.buttonToEditCondition.setPosition(messageTextField.getX() + messageTextField.getWidth(), y);
        this.buttonToRemoveReply.setPosition(buttonToEditCondition.getX() + buttonToEditCondition.getWidth(), y);
        this.buttonToAddRemoveNextDialogueLine.setPosition(buttonToRemoveReply.getX() + buttonToRemoveReply.getWidth(), y);
    }

    public Vector2 getPosition() {
        return new Vector2(messageTextField.getX(), messageTextField.getY());
    }

    public DialogueLineElement getParent() {
        return parent;
    }

    public void render(Batch batch) {
        update();
        messageTextField.act(Gdx.graphics.getDeltaTime());
        messageTextField.draw(batch, 5f);
        buttonToEditCondition.act(Gdx.graphics.getDeltaTime());
        buttonToEditCondition.draw(batch, 5f);
        buttonToRemoveReply.act(Gdx.graphics.getDeltaTime());
        buttonToRemoveReply.draw(batch, 5f);
        buttonToAddRemoveNextDialogueLine.act(Gdx.graphics.getDeltaTime());
        buttonToAddRemoveNextDialogueLine.draw(batch, 5f);
    }

    @Override
    public void update() {
        getModel().setText(messageTextField.getText());
    }


    class EditConditionEventListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

            editor.core.screen.popup.EditDialogueReplyPopUp dialogueReplyPopUp = new EditDialogueReplyPopUp("Edit condition", DialogueReplyElement.this.parent, DialogueReplyElement.this);
            dialogueReplyPopUp.update();
            dialogueReplyPopUp.setPosition(Gdx.graphics.getWidth() / 2 - (Gdx.graphics.getWidth() - 100)/2, Gdx.graphics.getHeight() / 2 - (Gdx.graphics.getHeight() - 100)/2);
            dialogueReplyPopUp.show(parent.getParentScreen().getStage(), sequence(Actions.alpha(0), Actions.fadeIn(0.4f, Interpolation.fade)));
            parent.getParentScreen().addInputMultiplexer(dialogueReplyPopUp.getStage());
            return true;
        }
    }

    class RemoveReplyListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            DialogueReplyElement.this.parent.removeReply(DialogueReplyElement.this);
            DialogueReplyElement.this.messageTextField.remove();
            DialogueReplyElement.this.buttonToEditCondition.remove();
            DialogueReplyElement.this.buttonToRemoveReply.remove();
            DialogueReplyElement.this.buttonToAddRemoveNextDialogueLine.remove();
            return true;
        }
    }

    class ArrowButtonListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (DialogueReplyElement.this.getModel().getNextLine() == null) {
                DialogueReplyElement.this.parent.getParentScreen().setConnectArrowMode(true, DialogueReplyElement.this);
                DialogueReplyElement.this.parent.getParentDialogue().addConnectButtons();
            } else {
                DialogueReplyElement.this.getModel().setNextLine(null);
            }
            return true;
        }
    }



}
