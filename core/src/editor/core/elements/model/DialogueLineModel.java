package editor.core.elements.model;

import java.util.HashMap;

public class DialogueLineModel {


    private String id;
    private String messageText;

    private boolean randomEvent;
    private boolean remote;

    private HashMap<String, DialogueReplyModel> replies = new HashMap<>();
    private HashMap<String, DialogueEventModel> events = new HashMap<>();

    public DialogueLineModel(String id, String messageText) {
        this.id = id;
        this.messageText = messageText;
    }

    public String getId() {
        return id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public HashMap<String, DialogueReplyModel> getReplies() {
        return replies;
    }

    public HashMap<String, DialogueEventModel> getEvents() {
        return events;
    }

    public void addReply(DialogueReplyModel replyModel){
        replies.put(replyModel.getId(), replyModel);
    }

    public void removeReply(DialogueReplyModel replyModel){
        for (DialogueReplyModel reply : replies.values()){
            if (reply.equals(replyModel)){
                replies.remove(reply);
            }
        }
    }

    public void addEvent(DialogueEventModel eventModel){
        events.put(eventModel.getId(), eventModel);
    }

    public void removeEvent(DialogueEventModel eventModel){
        for (DialogueEventModel event : events.values()){
            if(event.equals(eventModel)){
                events.remove(eventModel);
            }
        }
    }
}
