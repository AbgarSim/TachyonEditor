package editor.core.screen.elements;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.HashMap;

import editor.core.resource.ResourceManager;
import editor.core.screen.MainScreen;

/*
 * A single dialogue line
 */
public class DialogueLine {

    private static int counter;
    private MainScreen parent;

    private String id;
    private String messageText;

    public boolean randomEvent;
    public boolean remote;

    private HashMap<String, DialogueEvent> events = new HashMap<>();

    private HashMap<String, DialogueReply> replies = new HashMap<>();

    private Rectangle frameRectangle;
    private Rectangle messageFrameRectangle;


    private TextButton buttonToAddReply;
    private TextButton buttonToAddEvent;




    public DialogueLine(Vector2 pos, MainScreen screen) {
        this(pos.x, pos.y, screen);
    }

    public DialogueLine(float posx, float posy, MainScreen screen) {
        this.parent = screen;
        id = "1";
        messageText = "Testing 123456789987654321";
        frameRectangle = new Rectangle();
        frameRectangle.set(posx, posy, 235, calculateFrameHeight());

        messageFrameRectangle = new Rectangle();
        messageFrameRectangle.set(frameRectangle.x + 5, frameRectangle.y + frameRectangle.height - 35, frameRectangle.width - 45, 30);

        //replies.put(String.valueOf(counter++), new DialogueReply("Qw", "123", "BlaBla", this));
        //replies.put(String.valueOf(counter++), new DialogueReply("Qw1", "1234", "BlaBla", this));
        //replies.put(String.valueOf(counter++), new DialogueReply("Qw2", "123414", "QweRty", this));

        //events.put(String.valueOf(counter++), new DialogueEvent(this));
        //events.put(String.valueOf(counter++), new DialogueEvent(this));
        //events.put("e3", new DialogueEvent(this));

        buttonToAddReply = new TextButton("Add Reply", ResourceManager.getSkin());
        buttonToAddEvent = new TextButton("Add Event", ResourceManager.getSkin());


        buttonToAddReply.addListener(this.new AddReplyListener());
        buttonToAddEvent.addListener(this.new AddEventListener());

        calculateElementsPositioning();
        addActorToStage(buttonToAddReply);
        addActorToStage(buttonToAddEvent);
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Rectangle getMessageFrameRectangle(){
        return new Rectangle(messageFrameRectangle);
    }

    public MainScreen getParentScreen() {
        return parent;
    }

    //Get rectangle position
    public Vector2 getPosition() {
        return new Vector2(frameRectangle.x, frameRectangle.y);
    }

    public void addReply() {
        replies.put(String.valueOf(counter++), new DialogueReply("Ex", "1234", "Lel", this));
    }

    public void removeReply(DialogueReply reply){
        replies.values().remove(reply);
    }

    public void addEvent() {
        events.put(String.valueOf(counter++), new DialogueEvent(this));
    }

    public void removeEvent(DialogueEvent event){
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
        messageFrameRectangle.y = y + frameRectangle.height - messageFrameRectangle.height - 5;
    }

    public void calculateElementsPositioning() {
        int elementY = (int) (frameRectangle.y) + 10;

        buttonToAddReply.setBounds(frameRectangle.x + 5, elementY, 90, 40);
        buttonToAddEvent.setBounds(frameRectangle.x + buttonToAddReply.getWidth() + 10, elementY, 90, 40);
        elementY += buttonToAddReply.getHeight();

        for (DialogueEvent event : events.values()) {
            event.setPosition(new Vector2(frameRectangle.x + 5, elementY));
            elementY += event.getProportions().y;
        }
        elementY += 10;
        for (DialogueReply reply : replies.values()) {
            reply.setPosition(new Vector2(frameRectangle.x + 5, elementY));
            elementY += reply.getProportions().y;
        }
    }

    private int calculateFrameHeight() {
        int frameHeight = 50;
        if (!events.values().isEmpty()) {
            frameHeight += 10;
            for (DialogueEvent event : events.values()) {
                frameHeight += event.getProportions().y;
            }
        }else{
            frameHeight += 10;
        }

        if (!replies.values().isEmpty()) {
            for (DialogueReply reply : replies.values()) {
                frameHeight += reply.getProportions().y;
            }
        }

        frameHeight += 40;

        return frameHeight;
    }

    public void move(Vector2 delta) {
        Vector2 pos = getPosition();
        moveFrameAndMessageRectangles(pos.x + delta.x, pos.y + delta.y);
        calculateElementsPositioning();
    }


    public void render(ShapeRenderer renderer, SpriteBatch batch) {
        renderer.setAutoShapeType(true);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);

        //Calculate frame height
        frameRectangle.height = calculateFrameHeight();

        //Render frame
        renderer.rect(frameRectangle.x, frameRectangle.y, frameRectangle.width, frameRectangle.height);

        buttonToAddReply.act(Gdx.graphics.getDeltaTime());
        buttonToAddEvent.act(Gdx.graphics.getDeltaTime());


        //Render text
        renderer.rect(messageFrameRectangle.x, frameRectangle.y + frameRectangle.height - 35,
                messageFrameRectangle.width, messageFrameRectangle.height);

        for (DialogueReply reply : replies.values()) {
            reply.renderShapes(renderer);
        }
        for (DialogueEvent event : events.values()) {
            event.renderShapes(renderer);
        }
        renderer.end();
        batch.begin();
        String messageToDraw;
        if (messageText.length() > 24) {
            messageToDraw = messageText.substring(0, 22) + " ...";
        } else {
            messageToDraw = messageText;
        }
        ResourceManager.getBitmapFont().draw(batch, messageToDraw, frameRectangle.x + 8, frameRectangle.y + frameRectangle.height - 8);

        for (DialogueReply reply : replies.values()) {
            reply.renderButtons(batch);
        }
        for (DialogueEvent event : events.values()) {
            event.renderButtons(batch);
        }
        buttonToAddReply.draw(batch, 5f);
        buttonToAddEvent.draw(batch, 5f);
        batch.end();
    }

    class AddReplyListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            DialogueLine.this.addReply();
            DialogueLine.this.calculateElementsPositioning();
            return true;
        }
    }

    class AddEventListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            DialogueLine.this.addEvent();
            DialogueLine.this.calculateElementsPositioning();
            return true;
        }
    }


}

//public void addReply(DialogueReply reply) {
//    if (replies.containsKey(reply.id)) {
//        System.out.println("Error! More than 1 reply with id " + reply.id + " in dialogue message id " + id + " with text " + messageText);
//    }
//    replies.put(reply.id, reply);
//}

//public DialogueReply getReply(String replyId) {
//    return replies.get(replyId);
//}

//TODO fix this
//public void addEvent(String eventId, boolean affectsNPC) {
//    events.add((affectsNPC ? "1" : "0") + eventId);
//}