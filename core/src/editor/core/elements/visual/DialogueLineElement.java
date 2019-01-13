package editor.core.elements.visual;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import editor.core.elements.model.DialogueEventModel;
import editor.core.elements.model.DialogueLineModel;
import editor.core.elements.model.DialogueReplyModel;
import editor.core.resource.ResourceManager;
import editor.core.screen.MainScreen;

/*
 * A single dialogue line
 */
public class DialogueLineElement implements Element{

    private static int counter;

    private final MainScreen parent;
    private final Dialogue parentDialogue;
    private final DialogueLineModel model;

    private HashMap<String, DialogueEventElement> events = new HashMap<>();
    private HashMap<String, DialogueReplyElement> replies = new HashMap<>();

    private Rectangle frameRectangle;
    private Rectangle messageFrameRectangle;

    private TextField dialogLineMessageText;
    private TextButton dragButton;
    private TextButton buttonToAddReply;
    private TextButton buttonToAddEvent;
    private TextButton connectNextLineButton;


    public DialogueLineElement(Vector2 pos, DialogueLineModel model, MainScreen screen, Dialogue parentDialogue) {
        this(pos.x, pos.y, model, screen, parentDialogue);
    }


    public DialogueLineElement(float posx, float posy, DialogueLineModel model, MainScreen screen, Dialogue parentDialogue) {
        this.parent = screen;
        this.model = model;
        this.parentDialogue = parentDialogue;
        frameRectangle = new Rectangle();
        frameRectangle.set(posx, posy, 225, calculateFrameHeight());

        messageFrameRectangle = new Rectangle();
        messageFrameRectangle.set(frameRectangle.x + 5, frameRectangle.y + frameRectangle.height - 35, frameRectangle.width - 45, 30);

        dialogLineMessageText = new TextField(model.getMessageText(), ResourceManager.getSkin());
        dialogLineMessageText.setBounds(frameRectangle.x + 5, frameRectangle.y + frameRectangle.height - 35, frameRectangle.width - 45, 30);
        dialogLineMessageText.addListener(new TextFieldChangeListener());


        //replies.put(String.valueOf(counter++), new DialogueReplyElement("Qw", "123", "BlaBla", this));
        //replies.put(String.valueOf(counter++), new DialogueReplyElement("Qw1", "1234", "BlaBla", this));
        //replies.put(String.valueOf(counter++), new DialogueReplyElement("Qw2", "123414", "QweRty", this));

        //events.put(String.valueOf(counter++), new DialogueEventElement(this));
        //events.put(String.valueOf(counter++), new DialogueEventElement(this));
        //events.put("e3", new DialogueEventElement(this));

        dragButton = new TextButton("Drag", ResourceManager.getSkin());
        buttonToAddReply = new TextButton("Add Reply", ResourceManager.getSkin());
        buttonToAddEvent = new TextButton("Add Event", ResourceManager.getSkin());
        connectNextLineButton = new TextButton("Connect", ResourceManager.getSkin());


        dragButton.addListener(this.new DragListener());
        buttonToAddReply.addListener(this.new AddReplyListener());
        buttonToAddEvent.addListener(this.new AddEventListener());
        connectNextLineButton.addListener(this.new ConnectNextLineListener());


        calculateElementsPositioning();
        addActorToStage(dialogLineMessageText);
        addActorToStage(dragButton);
        addActorToStage(buttonToAddReply);
        addActorToStage(buttonToAddEvent);
    }

    public DialogueLineModel getModel() {
        return model;
    }

    public Rectangle getMessageFrameRectangle() {
        return new Rectangle(messageFrameRectangle);
    }

    public MainScreen getParentScreen() {
        return parent;
    }

    public Dialogue getParentDialogue() {
        return parentDialogue;
    }

    //Get rectangle position
    public Vector2 getPosition() {
        return new Vector2(frameRectangle.x, frameRectangle.y);
    }

    public HashMap<String, DialogueEventElement> getEvents() {
        return new HashMap<>(events);
    }

    public HashMap<String, DialogueReplyElement> getReplies() {
        return new HashMap<>(replies);
    }

    public void addReply() {
        replies.put(String.valueOf(counter), new DialogueReplyElement(new DialogueReplyModel(String.valueOf(counter++), "Default", this.model), this));
    }

    public void removeReply(DialogueReplyElement reply) {
        replies.values().remove(reply);
    }

    public void addEvent() {
        events.put(String.valueOf(counter), new DialogueEventElement(new DialogueEventModel(String.valueOf(counter++), this.model), this));
    }

    public void removeEvent(DialogueEventElement event) {
        events.values().remove(event);
    }


    public void addActorToStage(Actor actor) {
        parent.addActorToStage(actor);
    }

    /**
     * Get rectangle width , height proportion
     */
    public Vector2 getProportions() {
        return new Vector2(frameRectangle.getWidth(), frameRectangle.getHeight());
    }

    /**
     * Set rectangles position
     */
    private void moveFrameAndMessageRectangles(float x, float y) {
        frameRectangle.x = x;
        frameRectangle.y = y;
        messageFrameRectangle.x = x + 5;
        messageFrameRectangle.y = frameRectangle.y + frameRectangle.height - 35;
    }

    public Camera getCamera() {
        return parent.getMovingCamera();
    }

    public void calculateElementsPositioning() {
        int elementY = (int) (frameRectangle.y) + 10;


        buttonToAddReply.setBounds(frameRectangle.x + 5, elementY, frameRectangle.width - 135, 40);
        buttonToAddEvent.setBounds(frameRectangle.x + buttonToAddReply.getWidth() + 10, elementY, frameRectangle.width - 125, 40);
        elementY += buttonToAddReply.getHeight();

        List<DialogueEventElement> eventElements = new ArrayList<>(events.values());

        for (int i = eventElements.size() - 1; i >= 0; i--) {
            eventElements.get(i).setPosition(new Vector2(frameRectangle.x + 5, elementY));
            elementY += eventElements.get(i).getProportions().y;
        }

        elementY += 10;


        List<DialogueReplyElement> replyElements = new ArrayList<>(replies.values());
        for (int i = replyElements.size() - 1; i >= 0; i--) {
            replyElements.get(i).setPosition(frameRectangle.x + 5, elementY);
            elementY += replyElements.get(i).getProportions().y;
        }
        dialogLineMessageText.setPosition(frameRectangle.x + 5, frameRectangle.y + frameRectangle.height - 35);
        dragButton.setBounds(messageFrameRectangle.x + messageFrameRectangle.width + 5, frameRectangle.y + frameRectangle.height - 35,
                40, 30);

        connectNextLineButton.setPosition(frameRectangle.x + (frameRectangle.width / 2) - (connectNextLineButton.getWidth() / 2), elementY + connectNextLineButton.getHeight() + 2);
    }

    private int calculateFrameHeight() {
        int frameHeight = 50;
        if (!events.values().isEmpty()) {
            frameHeight += 10;
            for (DialogueEventElement event : events.values()) {
                frameHeight += event.getProportions().y;
            }
        } else {
            frameHeight += 10;
        }

        if (!replies.values().isEmpty()) {
            for (DialogueReplyElement reply : replies.values()) {
                frameHeight += reply.getProportions().y;
            }
        }

        frameHeight += 40;

        return frameHeight;
    }

    public void addConnectButtonToStage(){
        parent.addActorToStage(connectNextLineButton);
    }

    public void removeConnectButtonFromStage(){
        connectNextLineButton.remove();
    }

    public void render(ShapeRenderer renderer, SpriteBatch batch) {
        renderer.setAutoShapeType(true);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        calculateElementsPositioning();

        //Calculate frame height
        frameRectangle.height = calculateFrameHeight();

        //Render frame
        renderer.rect(frameRectangle.x, frameRectangle.y, frameRectangle.width, frameRectangle.height);

        dialogLineMessageText.act(Gdx.graphics.getDeltaTime());
        dragButton.act(Gdx.graphics.getDeltaTime());
        buttonToAddReply.act(Gdx.graphics.getDeltaTime());
        buttonToAddEvent.act(Gdx.graphics.getDeltaTime());

        if (this.getParentScreen().isConnectArrowMode())
            connectNextLineButton.act(Gdx.graphics.getDeltaTime());


        renderer.end();
        batch.begin();
        String messageToDraw;
        if (model.getMessageText().length() > 24) {
            messageToDraw = model.getMessageText().substring(0, 22) + " ...";
        } else {
            messageToDraw = model.getMessageText();
        }
        ResourceManager.getBitmapFont().draw(batch, messageToDraw, frameRectangle.x + 8, frameRectangle.y + frameRectangle.height - 8);

        for (DialogueReplyElement reply : replies.values()) {
            reply.render(batch);
        }
        for (DialogueEventElement event : events.values()) {
            event.render(batch);
        }
        dialogLineMessageText.draw(batch, 5f);
        dragButton.draw(batch, 5f);
        buttonToAddReply.draw(batch, 5f);
        buttonToAddEvent.draw(batch, 5f);
        if (this.getParentScreen().isConnectArrowMode()) {
            connectNextLineButton.draw(batch, 5f);
        }
        batch.end();
    }

    @Override
    public void updateData() {
        getModel().setMessageText(dialogLineMessageText.getMessageText());
    }


    class DragListener extends ClickListener {

        private Vector2 lastTouch = new Vector2();

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            Vector2 coords = unprojectCoordinates(x, y);
            lastTouch = coords;
            return true;
        }

        @Override
        public void touchDragged(InputEvent event, float x, float y, int pointer) {
            Vector2 oldButtonCoords = new Vector2(dragButton.getX(), dragButton.getY());
            dragButton.moveBy(x - dragButton.getWidth() / 2, y - dragButton.getHeight() / 2);

            Vector2 oldCoords = DialogueLineElement.this.getPosition();

            DialogueLineElement.this.moveFrameAndMessageRectangles(oldCoords.x + (dragButton.getX() - oldButtonCoords.x), oldCoords.y + (dragButton.getY() - oldButtonCoords.y));

        }

        private Vector2 unprojectCoordinates(float x, float y) {
            Vector3 raw = new Vector3(x, y, 0);
            DialogueLineElement.this.getCamera().unproject(raw);
            return new Vector2(raw.x, raw.y);
        }
    }

    class AddReplyListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            DialogueLineElement.this.addReply();
            return true;
        }
    }

    class AddEventListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            DialogueLineElement.this.addEvent();
            return true;
        }
    }

    class ConnectNextLineListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (DialogueLineElement.this.getParentScreen().isConnectArrowMode()) {
                DialogueReplyElement connectFrom = DialogueLineElement.this.getParentScreen().getConnectFromElement();
                connectFrom.getModel().setNextLine(DialogueLineElement.this.getModel());
                DialogueLineElement.this.getParentScreen().setConnectArrowMode(false, null);
                DialogueLineElement.this.getParentDialogue().removeConnectButtons();
                return true;
            }
            return false;
        }
    }

    class TextFieldChangeListener extends ClickListener{
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            getParentDialogue().addElementForUpdate(DialogueLineElement.this);
            return true;
        }
    }

}

//public void addReply(DialogueReplyElement reply) {
//    if (replies.containsKey(reply.id)) {
//        System.out.println("Error! More than 1 reply with id " + reply.id + " in dialogue message id " + id + " with text " + messageText);
//    }
//    replies.put(reply.id, reply);
//}

//public DialogueReplyElement getReply(String replyId) {
//    return replies.get(replyId);
//}

//TODO fix this
//public void addEvent(String eventId, boolean affectsNPC) {
//    events.add((affectsNPC ? "1" : "0") + eventId);
//}