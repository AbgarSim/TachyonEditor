package editor.core.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import editor.core.resource.ResourceManager;

public class DialogueEvent {

    private final Vector2 eventProportions = new Vector2(135, 30);
    private final Vector2 eventButtonProportions = new Vector2(40, 30);

    private final DialogueLine parent;
    //private Rectangle eventRectangle = new Rectangle();
    //private Rectangle eventButtonRectangle = new Rectangle();

    private TextField eventTextField;

    private CheckBox buttonToTargetPlayer;
    private TextButton buttonToRemoveEvent;


    public DialogueEvent(DialogueLine parent) {
        this.parent = parent;

        eventTextField = new TextField("Event ", ResourceManager.getSkin());
        eventTextField.setBounds(this.parent.getPosition().x + 5, this.parent.getPosition().y - 5,
                eventProportions.x, eventProportions.y);

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


    public void render(Batch batch) {
        eventTextField.act(Gdx.graphics.getDeltaTime());
        eventTextField.draw(batch, 5f);
        buttonToTargetPlayer.act(Gdx.graphics.getDeltaTime());
        buttonToTargetPlayer.draw(batch, 5f);
        buttonToRemoveEvent.act(Gdx.graphics.getDeltaTime());
        buttonToRemoveEvent.draw(batch, 5f);
    }

    class RemoveEventListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            DialogueEvent.this.parent.removeEvent(DialogueEvent.this);
            DialogueEvent.this.buttonToTargetPlayer.remove();
            DialogueEvent.this.buttonToRemoveEvent.remove();
            DialogueEvent.this.parent.calculateElementsPositioning();
            return true;
        }
    }
}
