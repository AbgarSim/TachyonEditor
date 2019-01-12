package editor.core.elements.model;

import java.util.ArrayList;
import java.util.List;

import editor.core.elements.model.condition.Condition;

public class DialogueReplyModel {

    private final DialogueLineModel parent;
    private DialogueLineModel nextLine;

    private String id;
    private String nextMessage;
    private String text;
    private List<Condition> conditions = new ArrayList<>();
    private boolean anyCondition;

    public DialogueReplyModel(String id, String nextMessage, String text, DialogueLineModel parent) {
        this(id, nextMessage, text, null, parent);
    }

    public DialogueReplyModel(String id, String nextMessage, String text, DialogueLineModel nextLine, DialogueLineModel parent) {
        this.id = id;
        this.nextMessage = nextMessage;
        this.text = text;
        this.nextLine = nextLine;
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public DialogueLineModel getNextLine() {
        return nextLine;
    }

    public void setNextLine(DialogueLineModel nextLine) {
        this.nextLine = nextLine;
    }

    public String getNextMessage() {
        return nextMessage;
    }

    public String getText() {
        return text;
    }


}
