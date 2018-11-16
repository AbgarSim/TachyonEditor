package editor.core.launcher;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

public class Dialogue implements ScreenElement{

    private List<DialogueLine> lines = new ArrayList<DialogueLine>();


    public Dialogue(List<DialogueLine> lines) {
        this.lines = lines;
    }


    @Override
    public void render(ShapeRenderer renderer, SpriteBatch batch) {
        //for(DialogueLine line : lines)
            //line.render(spriteBatch);
    }
}
