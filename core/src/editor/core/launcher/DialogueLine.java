package editor.core.launcher;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/*
 * A single dialogue line
 */
public class DialogueLine implements ScreenElement {

    private String id;
    private String messageText;
    private BitmapFont messageFont;
    private Rectangle frameRectangle;
    private Rectangle messageFrameRectangle;


    public DialogueLine(float posx, float posy) {
        frameRectangle = new Rectangle();
        frameRectangle.set(posx, posy, 175, 150);
        messageFont = new BitmapFont();
        messageFont.setColor(Color.BLACK);
        messageFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        messageFrameRectangle = new Rectangle();
        messageFrameRectangle.set(posx + 5, posy + 5, 165, 30);

    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch batch) {
        renderer.setAutoShapeType(true);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        renderer.rect(frameRectangle.x, frameRectangle.y, frameRectangle.width, frameRectangle.height);
        renderer.rect(frameRectangle.x + 5, frameRectangle.y + frameRectangle.height - messageFrameRectangle.height - 5, messageFrameRectangle.width, messageFrameRectangle.height);
        renderer.end();
        batch.begin();
        messageFont.draw(batch, "This is a random text...", frameRectangle.x  + 8, frameRectangle.y + frameRectangle.height - 8);
        batch.end();
    }


    public Vector2 getPosition() {
        return new Vector2(frameRectangle.x, frameRectangle.y);
    }

    public Vector2 getProportions() {
        return new Vector2(frameRectangle.getWidth(), frameRectangle.getHeight());
    }

    public void setPositions(float x, float y) {
        frameRectangle.x = x;
        frameRectangle.y = y;
    }

    public void move(Vector2 delta) {
        Vector2 pos = getPosition();
        setPositions(pos.x + delta.x, pos.y + delta.y);
    }

}
