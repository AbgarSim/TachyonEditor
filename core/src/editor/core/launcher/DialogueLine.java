package editor.core.launcher;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    private Texture frame;
    private Sprite frameSprite;

    public DialogueLine(int width, int height, float posx, float posy) {
        this.width = width;
        this.height = height;
        this.posx = posx;
        this.posy = posy;
        frame = getRectPixmap(width, height);
        frameSprite = new Sprite(frame);
        frameSprite.setPosition(posx, posy);
        frame.dispose();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        frameSprite.setPosition(posx, posy);
        frameSprite.draw(spriteBatch);
        spriteBatch.end();
    }

    private Texture getRectPixmap(int width, int height){
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fillRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(1, 1, pixmap.getWidth()-1, pixmap.getHeight()-1);
        Texture rectTexture = new Texture(pixmap);
        pixmap.dispose();
        return rectTexture;

    }

    public Vector2 getPosition(){
        return new Vector2(frameSprite.getX(), frameSprite.getY());
    }

    public Vector2 getProportions(){
        return new Vector2(frameSprite.getWidth(), frameSprite.getHeight());
    }

    public void setPositions(float x, float y){
        posx = x;
        posy = y;
    }

}
