package editor.core.screen.elements;

import java.util.ArrayList;
import java.util.List;

import editor.core.screen.elements.condition.Condition;

public class DialogueReply {

    public static final String ACTION_TAG = "[ACTION]";
    public static final String SKIP_TAG = "[SKIP]";

    public String id;
    public String nextMessage;
    public String text;
    public List<Condition> conditions = new ArrayList<>();
    public boolean anyCondition;

    public DialogueReply(String replyId, String next, String replyText) {
        id = replyId;
        nextMessage = next;
        text = replyText;
    }

    public void addCondition(Condition cond) {
        conditions.add(cond);
    }

}
