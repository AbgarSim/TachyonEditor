package editor.core.elements.model;

public class DialogueEventModel {

    private String id;
    private final DialogueLineModel parent;

    public DialogueEventModel(String id, DialogueLineModel parent) {
        this.id = id;
        this.parent = parent;
    }

    public String getId() {
        return id;
    }
}
