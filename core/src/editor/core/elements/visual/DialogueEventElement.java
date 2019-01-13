package editor.core.elements.visual;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import editor.core.elements.model.DialogueEventModel;
import editor.core.resource.ResourceManager;

public class DialogueEventElement implements Element{

    private final Vector2 eventProportions;
    private final Vector2 eventButtonProportions = new Vector2(40, 30);

    private final DialogueLineElement parent;
    private final DialogueEventModel model;

    private TextField eventTextField;

    private CheckBox buttonToTargetPlayer;
    private TextButton buttonToRemoveEvent;


    public DialogueEventElement(DialogueEventModel model, DialogueLineElement parent) {
        this.parent = parent;
        this.model = model;
        eventProportions = new Vector2(parent.getProportions().x - 85, 30);

        eventTextField = new TextField("Event ", ResourceManager.getSkin());
        eventTextField.setBounds(this.parent.getPosition().x + 5, this.parent.getPosition().y - 5,
                eventProportions.x, eventProportions.y);
        eventTextField.addListener(new TextFieldChangeListener());
        parent.addActorToStage(eventTextField);

        buttonToTargetPlayer = new CheckBox("T", ResourceManager.getSkin());
        buttonToTargetPlayer.setBounds(this.parent.getPosition().x + this.parent.getMessageFrameRectangle().width + 10, this.parent.getPosition().y - 5,
                eventButtonProportions.x, eventButtonProportions.y);
        parent.addActorToStage(buttonToTargetPlayer);

        buttonToRemoveEvent = new TextButton("-", ResourceManager.getSkin());
        buttonToRemoveEvent.setBounds(this.parent.getPosition().x + this.parent.getMessageFrameRectangle().width + buttonToTargetPlayer.getWidth() + 20, this.parent.getPosition().y - 5,
                eventButtonProportions.x, eventButtonProportions.y);
        buttonToRemoveEvent.addListener(new RemoveEventListener());
        parent.addActorToStage(buttonToRemoveEvent);
    }

    public void setPosition(Vector2 delta) {
        this.eventTextField.setPosition(delta.x, delta.y);
        this.buttonToTargetPlayer.setPosition(eventTextField.getX() + eventTextField.getWidth(), delta.y);
        this.buttonToRemoveEvent.setPosition(buttonToTargetPlayer.getX() + buttonToTargetPlayer.getWidth(), delta.y);
    }

    public Vector2 getProportions() {
        return new Vector2(eventTextField.getWidth() + buttonToTargetPlayer.getWidth() + buttonToRemoveEvent.getWidth() + 30, eventTextField.getHeight());
    }

    public DialogueEventModel getModel() {
        return model;
    }

    public void render(Batch batch) {
        eventTextField.act(Gdx.graphics.getDeltaTime());
        eventTextField.draw(batch, 5f);
        buttonToTargetPlayer.act(Gdx.graphics.getDeltaTime());
        buttonToTargetPlayer.draw(batch, 5f);
        buttonToRemoveEvent.act(Gdx.graphics.getDeltaTime());
        buttonToRemoveEvent.draw(batch, 5f);
    }

    @Override
    public void updateData() {
        getModel().setEventTitle(eventTextField.getMessageText());
    }

    class RemoveEventListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            DialogueEventElement.this.parent.removeEvent(DialogueEventElement.this);
            DialogueEventElement.this.eventTextField.remove();
            DialogueEventElement.this.buttonToTargetPlayer.remove();
            DialogueEventElement.this.buttonToRemoveEvent.remove();
            DialogueEventElement.this.parent.calculateElementsPositioning();
            return true;
        }
    }

    class TextFieldChangeListener extends ClickListener{
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            parent.getParentDialogue().addElementForUpdate(DialogueEventElement.this);
            return true;
        }
    }
}
