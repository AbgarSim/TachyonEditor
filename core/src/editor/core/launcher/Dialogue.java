package editor.core.launcher;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.List;

public class Dialogue implements ScreenElement{

    private List<DialogueLine> lines = new ArrayList<DialogueLine>();


    public Dialogue(List<DialogueLine> lines) {
        this.lines = lines;
    }


    @Override
    public void render(Batch batch) {
        for(DialogueLine line : lines)
            line.render(batch);
    }
}
