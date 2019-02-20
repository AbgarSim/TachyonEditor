package editor.core.elements.model;

import java.util.ArrayList;
import java.util.List;

import editor.core.elements.model.condition.Condition;

public class DialogueReplyModel implements Model {

    private final DialogueLineModel parent;
    private DialogueLineModel nextLine;

    private String id;
    private String text;
    private List<Condition> conditions = new ArrayList<>();
    private boolean anyCondition;

    public DialogueReplyModel(String id, String text, DialogueLineModel parent) {
        this(id, text, null, parent);
    }

    public DialogueReplyModel(String id, String text, DialogueLineModel nextLine, DialogueLineModel parent) {
        this.id = id;
        this.text = text;
        this.nextLine = nextLine;
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }

    public boolean isAnyCondition() {
        return anyCondition;
    }

    public void setAnyCondition(boolean anyCondition) {
        this.anyCondition = anyCondition;
    }

    public DialogueLineModel getNextLine() {
        return nextLine;
    }

    public void setNextLine(DialogueLineModel nextLine) {
        this.nextLine = nextLine;
    }

    public void addCondition(Condition condition){
        if(!conditions.contains(condition)){
            conditions.add(condition);
        }
    }

    public void removeCondition(Condition condition){
        conditions.remove(condition);
    }

    public List<Condition> getConditions(){
        return conditions;
    }

    public String getNextMessage() {
        if (nextLine != null)
            return nextLine.getMessageText();
        else
            return "-";
    }


    @Override
    public void updateData() {

    }
}
