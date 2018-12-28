package editor.core.screen.elements;

import com.badlogic.gdx.math.Vector2;

public class DialogueLineFactory {


    public DialogueLine getDialogueLine(Vector2 positionToCreate) {
        DialogueLine lineToCreate = new DialogueLine(positionToCreate);
        lineToCreate.setMessageText("DefaultMessage");
        return lineToCreate;
    }

    public DialogueLine getDialogueLine(String message, Vector2 positionToCreate) {
        DialogueLine lineToCreate = new DialogueLine(positionToCreate.x, positionToCreate.y);
        lineToCreate.setMessageText(message);
        return lineToCreate;
    }

}
