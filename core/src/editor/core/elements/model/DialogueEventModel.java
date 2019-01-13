package editor.core.elements.model;

public class DialogueEventModel implements Model{

    private String id;
    private String eventTitle;
    private final DialogueLineModel parent;

    public DialogueEventModel(String id, DialogueLineModel parent) {
        this.id = id;
        this.parent = parent;
        this.eventTitle = "Def event";
    }

    public String getId() {
        return id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    @Override
    public void updateData() {

    }
}
