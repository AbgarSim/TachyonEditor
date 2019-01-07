package editor.core.screen.elements;

import com.badlogic.gdx.math.Vector2;

import editor.core.screen.MainScreen;

public class DialogueLineFactory {
    private MainScreen parent;

    public DialogueLineFactory(MainScreen parent) {
        this.parent = parent;
    }

    public DialogueLine getDialogueLine(Vector2 positionToCreate) {
        DialogueLine lineToCreate = new DialogueLine(positionToCreate, parent);
        lineToCreate.setMessageText("DefaultMessage");
        return lineToCreate;
    }

    public DialogueLine getDialogueLine(String message, Vector2 positionToCreate) {
        DialogueLine lineToCreate = new DialogueLine(positionToCreate.x, positionToCreate.y, parent);
        lineToCreate.setMessageText(message);
        return lineToCreate;
    }

}
