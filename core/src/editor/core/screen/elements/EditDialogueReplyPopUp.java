package editor.core.screen.elements;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import editor.core.resource.ResourceManager;

public class EditDialogueReplyPopUp extends Dialog {

    public EditDialogueReplyPopUp(String title) {
        super(title, ResourceManager.getSkin());
    }

    public EditDialogueReplyPopUp(String title, String windowStyleName) {
        super(title, ResourceManager.getSkin(), windowStyleName);
    }

    {
        CheckBox checkBox = new CheckBox("Any condition", ResourceManager.getSkin());
        Table table = new Table(ResourceManager.getSkin());
        table.add(new Label("Message text", ResourceManager.getSkin()));
        table.row();
        table.add(new Label("Reply text", ResourceManager.getSkin()));
        table.row();
        table.add(new Label("Next Message text", ResourceManager.getSkin()));
        table.row();
        table.add(checkBox);
        table.row();

        this.getContentTable().add(table);
        this.button("Done", "Done");
        this.setKeepWithinStage(true);

    }

    public EditDialogueReplyPopUp(String title, WindowStyle windowStyle) {
        super(title, windowStyle);
    }


    @Override
    protected void result(Object object) {
        if (object.equals("Done")) {
            hide();
        }
    }
}
