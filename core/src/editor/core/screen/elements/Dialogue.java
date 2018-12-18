package editor.core.screen.elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

public class Dialogue{

    private DialogueLine start;
    private List<DialogueLine> dialogueLines;
    private List<ArrowLine> arrows = new ArrayList<ArrowLine>();


    public Dialogue(DialogueLine start, List<DialogueLine> dialogueLines) {
        this.start = start;
        this.dialogueLines = dialogueLines;
    }


    public void render(ShapeRenderer renderer, SpriteBatch batch) {
        start.render(renderer, batch);
        for (DialogueLine line : dialogueLines){
            line.render(renderer, batch);
        }
    }
}
