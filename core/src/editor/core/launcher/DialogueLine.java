package editor.core.launcher;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/*
 * A single dialogue line
 */
public class DialogueLine implements ScreenElement {

    private String messageText;
    ShapeRenderer renderer = new ShapeRenderer();
    private float width;
    private float height;
    private float posx;
    private float posy;

    public DialogueLine(float width, float height, float posx, float posy) {
        this.width = width;
        this.height = height;
        this.posx = posx;
        this.posy = posy;
    }

    @Override
    public void render(Batch batch) {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        renderer.rect(posx, posy ,width, height);
        renderer.end();
    }
}
