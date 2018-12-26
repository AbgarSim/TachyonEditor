package editor.core.screen.elements;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

/*
 * A single dialogue line
 */
public class DialogueLine {

    public String id;
    public String messageText;

    public boolean randomEvent;
    public boolean remote;

    public HashMap<String, DialogueEvent> events = new HashMap<>();

    public HashMap<String, DialogueReply> replies = new HashMap<>();

    private BitmapFont messageFont;

    private Rectangle frameRectangle;
    private Rectangle messageFrameRectangle;

    public DialogueLine(float posx, float posy) {
        id = "1";
        messageText = "Testing 123456789987654321";
        frameRectangle = new Rectangle();
        frameRectangle.set(posx, posy, 195, calculateFrameHeight());

        messageFont = new BitmapFont();
        messageFont.setColor(Color.BLACK);
        messageFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        messageFrameRectangle = new Rectangle();
        messageFrameRectangle.set(frameRectangle.x + 5, frameRectangle.y + frameRectangle.height - 35, 185, 30);

        replies.put("1", new DialogueReply("Qw", "123", "BlaBla", this));
        replies.put("2", new DialogueReply("Qw1", "1234", "BlaBla", this));
        replies.put("3", new DialogueReply("Qw2", "123414", "QweRty", this));

        events.put("e1", new DialogueEvent(this));
        events.put("e2", new DialogueEvent(this));
        //events.put("e3", new DialogueEvent(this));

        int elementY = (int) (messageFrameRectangle.y);
        for (DialogueEvent event : events.values()){
            event.setPosition(new Vector2(frameRectangle.x + 5, elementY));
            elementY += event.getProportions().y;
        }
        elementY+=10;
        for (DialogueReply reply : replies.values()) {
            reply.setPosition(new Vector2(frameRectangle.x + 5, elementY));
            elementY += reply.getProportions().y;
        }
    }

    public void addReply(DialogueReply reply) {
        if (replies.containsKey(reply.id)) {
            System.out.println("Error! More than 1 reply with id " + reply.id + " in dialogue message id " + id + " with text " + messageText);
        }
        replies.put(reply.id, reply);
    }

    public DialogueReply getReply(String replyId) {
        return replies.get(replyId);
    }

    //TODO fix this
    //public void addEvent(String eventId, boolean affectsNPC) {
    //    events.add((affectsNPC ? "1" : "0") + eventId);
    //}

    //Get rectangle position
    public Vector2 getPosition() {
        return new Vector2(frameRectangle.x, frameRectangle.y);
    }

    //Set rectangles position
    public void setPositions(float x, float y) {
        frameRectangle.x = x;
        frameRectangle.y = y;
        messageFrameRectangle.x = x + 5;
        messageFrameRectangle.y = y + frameRectangle.height - messageFrameRectangle.height - 5;

    }

    private void calculateElementsPositioning() {
        int elementY = (int) (frameRectangle.y) + 15;
        for (DialogueEvent event : events.values()){
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
        if(!events.values().isEmpty()){
            frameHeight += 10;
            for(DialogueEvent event : events.values()){
                frameHeight += event.getProportions().y;
            }
        }

        if (!replies.values().isEmpty()) {
            for (DialogueReply reply : replies.values()) {
                frameHeight += reply.getProportions().y;
            }
        }

        return frameHeight;
    }

    public void move(Vector2 delta) {
        Vector2 pos = getPosition();
        setPositions(pos.x + delta.x, pos.y + delta.y);
        calculateElementsPositioning();
    }


    //Get rectangle width , height proportions
    public Vector2 getProportions() {
        return new Vector2(frameRectangle.getWidth(), frameRectangle.getHeight());
    }


    public void render(ShapeRenderer renderer, SpriteBatch batch) {
        renderer.setAutoShapeType(true);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);

        //Calculate frame height

        frameRectangle.height = calculateFrameHeight();

        //Render frame
        renderer.rect(frameRectangle.x, frameRectangle.y, frameRectangle.width, frameRectangle.height);

        //Render text
        renderer.rect(messageFrameRectangle.x, frameRectangle.y + frameRectangle.height - 35,
                messageFrameRectangle.width, messageFrameRectangle.height);

        for (DialogueReply reply : replies.values()) {
            reply.render(renderer);
        }
        for(DialogueEvent event : events.values()){
            event.render(renderer);
        }
        renderer.end();
        batch.begin();
        String messageToDraw;
        if (messageText.length() > 24) {
            messageToDraw = messageText.substring(0, 22) + " ...";
        } else {
            messageToDraw = messageText;
        }
        messageFont.draw(batch, messageToDraw, frameRectangle.x + 8, frameRectangle.y + frameRectangle.height - 8);
        batch.end();
    }


}
