package editor.core.elements.factory;

import com.badlogic.gdx.math.Vector2;

import editor.core.elements.model.DialogueLineModel;
import editor.core.elements.visual.DialogueLineElement;
import editor.core.screen.MainScreen;

public class DialogueLineFactory {
    private MainScreen parent;
    private static int counter;

    public DialogueLineFactory(MainScreen parent) {
        this.parent = parent;
    }

    public DialogueLineElement getDialogueLine(Vector2 positionToCreate) {
        DialogueLineElement lineToCreate = new DialogueLineElement(positionToCreate, new DialogueLineModel(String.valueOf(counter++), "DefaultMessage"), parent);
        return lineToCreate;
    }

    public DialogueLineElement getDialogueLine(String message, Vector2 positionToCreate) {
        DialogueLineElement lineToCreate = new DialogueLineElement(positionToCreate.x, positionToCreate.y, new DialogueLineModel(String.valueOf(counter++), message), parent);
        return lineToCreate;
    }

}
