package editor.core.elements.visual;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

import editor.core.elements.model.DialogueReplyModel;

public class Dialogue {

    private List<DialogueLineElement> dialogueLines;
    private List<ArrowLine> arrows;


    public Dialogue(List<DialogueLineElement> dialogueLines) {
        this.dialogueLines = dialogueLines;
        this.arrows = new ArrayList<>();
    }

    public Dialogue() {
        this.dialogueLines = new ArrayList<>();
        this.arrows = new ArrayList<>();
    }

    public void addDialogueLine(DialogueLineElement element) {
        dialogueLines.add(element);
    }

    public List<DialogueLineElement> getDialogueLines() {
        return new ArrayList<>(dialogueLines);
    }

    private void calculateArrows() {
        for (DialogueLineElement line : dialogueLines) {
            if (!line.getReplies().isEmpty()) {
                for (DialogueReplyElement replyElement : line.getReplies().values()) {
                    if (replyElement.getModel().getNextLine() != null) {
                        DialogueLineElement element = getDialogueLineElementById(replyElement.getModel().getNextLine().getId());
                        if (element != null)
                            arrows.add(new ArrowLine(replyElement, element));
                    }
                }
            }
        }
    }

    private DialogueLineElement getDialogueLineElementById(String id) {
        for (DialogueLineElement element : dialogueLines) {
            if (element.getModel().getId().equals(id))
                return element;
        }
        return null;
    }

    public void render(ShapeRenderer renderer, SpriteBatch batch) {
        calculateArrows();

        for (DialogueLineElement line : dialogueLines) {
            line.render(renderer, batch);
        }
        for (ArrowLine arrowLine : arrows) {
            arrowLine.render(batch, renderer);
        }
    }
}
