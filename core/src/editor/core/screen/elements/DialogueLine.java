package editor.core.screen.elements;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import editor.core.launcher.Event;


/*
 * A single dialogue line
 */
public class DialogueLine {

    private String id;
    private String messageText;
    private BitmapFont messageFont;

    private Rectangle frameRectangle;
    private Rectangle messageFrameRectangle;
    private Rectangle eventRectangle;
    private Rectangle eventCheckboxRectangle; //temporary
    private Rectangle replyTextRectangle;
    private Rectangle replyTextEditRectangle; //temporary

    private List<Event> eventList = new ArrayList<Event>();
    private HashMap<Rectangle, Event> eventMap = new HashMap<Rectangle, Event>();

    private HashMap<String, DialogueLine> replyList = new HashMap<String, DialogueLine>();
    private HashMap<Rectangle, DialogueLine> replyMap = new HashMap<Rectangle, DialogueLine>();

    public DialogueLine(float posx, float posy) {
        frameRectangle = new Rectangle();
        frameRectangle.set(posx, posy, 195, 150);

        messageFont = new BitmapFont();
        messageFont.setColor(Color.BLACK);
        messageFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        messageFrameRectangle = new Rectangle();
        messageFrameRectangle.set(posx + 5, posy - 5, 185, 30);
        messageText = "Testing 123456789987654321";

        eventRectangle = new Rectangle();
        eventRectangle.set(posx + 5, posy - 35, 145, 30);


        eventCheckboxRectangle = new Rectangle();
        eventCheckboxRectangle.set(posx + 150, posy - 35, 40, 30);


        replyTextRectangle = new Rectangle();
        replyTextRectangle.set(posx + 5, posy - 65, 145, 30);


        replyTextEditRectangle = new Rectangle();
        replyTextEditRectangle.set(posx + 150, posy - 65, 40, 30);
    }


    public void move(Vector2 delta) {
        Vector2 pos = getPosition();
        setPositions(pos.x + delta.x, pos.y + delta.y);
    }




    //Get rectangle position
    public Vector2 getPosition() {
        return new Vector2(frameRectangle.x, frameRectangle.y);
    }

    //Set rectangle position
    public void setPositions(float x, float y) {
        frameRectangle.x = x;
        frameRectangle.y = y;
    }

    //Get rectangle width , height proportions
    public Vector2 getProportions() {
        return new Vector2(frameRectangle.getWidth(), frameRectangle.getHeight());
    }

    private void drawDialogLineRectangleElement(Rectangle rectangle, ShapeRenderer renderer){
        renderer.rect(rectangle.x, rectangle.y + frameRectangle.height - rectangle.height, rectangle.width, rectangle.height);
    }

    public void render(ShapeRenderer renderer, SpriteBatch batch) {
        renderer.setAutoShapeType(true);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        renderer.rect(frameRectangle.x, frameRectangle.y, frameRectangle.width, frameRectangle.height);
        drawDialogLineRectangleElement(messageFrameRectangle, renderer);
        drawDialogLineRectangleElement(replyTextRectangle, renderer);
        drawDialogLineRectangleElement(replyTextEditRectangle, renderer);
        drawDialogLineRectangleElement(eventRectangle, renderer);
        drawDialogLineRectangleElement(eventCheckboxRectangle, renderer);
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
