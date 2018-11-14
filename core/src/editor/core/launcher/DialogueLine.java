package editor.core.launcher;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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
    private float width;
    private float height;
    private float posx;
    private float posy;
    private Rectangle frameRectangle;

    public DialogueLine(int width, int height, float posx, float posy) {
        this.width = width;
        this.height = height;
        this.posx = posx;
        this.posy = posy;
        frameRectangle = new Rectangle();
        frameRectangle.set(posx, posy, width, height);
    }

    @Override
    public void render(ShapeRenderer renderer) {
        renderer.setAutoShapeType(true);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        renderer.rect(frameRectangle.x, frameRectangle.y, frameRectangle.width, frameRectangle.height);
        renderer.end();
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
