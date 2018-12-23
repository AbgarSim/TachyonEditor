package editor.core.screen.elements;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

import editor.core.launcher.Event;

/*
 * A single dialogue line
 */
public class DialogueLine {

    public String id;
    public String messageText;

    public boolean randomEvent;
    public boolean remote;

    public HashMap<String, Event> eventList = new HashMap<>();

    public HashMap<String, DialogueReply> replies = new HashMap<>();

    private BitmapFont messageFont;

    private Rectangle frameRectangle;
    private Rectangle messageFrameRectangle;

    public DialogueLine(float posx, float posy) {
        id = "1";
        messageText = "Testing 123456789987654321";
        frameRectangle = new Rectangle();

        messageFont = new BitmapFont();
        messageFont.setColor(Color.BLACK);
        messageFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        messageFrameRectangle = new Rectangle();
        messageFrameRectangle.set(posx + 5, posy + 15, 185, 30);

        frameRectangle.set(posx, posy, 195, 40);


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
    //    eventList.add((affectsNPC ? "1" : "0") + eventId);
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
        messageFrameRectangle.y = y + 15;

    }

    public void move(Vector2 delta) {
        Vector2 pos = getPosition();
        setPositions(pos.x + delta.x, pos.y + delta.y);
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
        int frameHeight = 50;
        if(!replies.values().isEmpty()){
            for(DialogueReply reply : replies.values()){
                frameHeight += reply.getReplyProportions().y;
            }
        }
        frameRectangle.height = frameHeight;

        //Render frame
        renderer.rect(frameRectangle.x, frameRectangle.y, frameRectangle.width, frameRectangle.height);

        //Render text
        renderer.rect(messageFrameRectangle.x, messageFrameRectangle.y,
               messageFrameRectangle.width, messageFrameRectangle.height);

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
