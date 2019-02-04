package editor.core.screen.popup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import editor.core.elements.visual.DialogueLineElement;
import editor.core.elements.visual.DialogueReplyElement;
import editor.core.elements.visual.Element;
import editor.core.resource.ResourceManager;

public class EditDialogueReplyPopUp extends Dialog implements Element {

    private final DialogueLineElement parentLine;
    private final DialogueReplyElement parentReply;

    private Label messageTextField;
    private Label replyTextField;
    private Label nextMessageTextField;
    private CheckBox checkBox;
    private SelectBox selectBox;

    public EditDialogueReplyPopUp(String title, DialogueLineElement parent, DialogueReplyElement parentReply) {
        super(title, ResourceManager.getSkin());
        this.parentLine = parent;
        this.parentReply = parentReply;

        messageTextField = new Label(parent.getModel().getMessageText(), ResourceManager.getSkin());
        replyTextField = new Label(parentReply.getModel().getText(), ResourceManager.getSkin());
        nextMessageTextField = new Label(parentReply.getModel().getNextMessage(), ResourceManager.getSkin());
        selectBox = new SelectBox(ResourceManager.getSkin());
        selectBox.setItems(new TextField("Test", ResourceManager.getSkin()), new TextField("Le Test", ResourceManager.getSkin()));


        checkBox = new CheckBox("Any condition", ResourceManager.getSkin());
        checkBox.setChecked(parentReply.getModel().isAnyCondition());
        Table table = new Table(ResourceManager.getSkin());
        table.add(new Label("Message text:   ", ResourceManager.getSkin()));
        table.add(messageTextField);
        table.row();
        table.add(new Label("Reply text:   ", ResourceManager.getSkin()));
        table.add(replyTextField);
        table.row();
        table.add(new Label("Next Message text:   ", ResourceManager.getSkin()));
        table.add(nextMessageTextField);
        table.row();
        table.add(checkBox);
        table.row();
        table.add(selectBox).padTop(10);

        this.getContentTable().add(table);
        this.button("Done", "Done");
        this.setKeepWithinStage(false);

        getBackground().setMinWidth(Gdx.graphics.getWidth() - 100);
        getBackground().setMinHeight(Gdx.graphics.getHeight() - 100);


    }



    @Override
    protected void result(Object object) {
        if (object.equals("Done")) {
            hide();
        }
    }

    @Override
    public void update() {
        nextMessageTextField.setText(parentReply.getModel().getNextMessage());
        replyTextField.setText(parentReply.getModel().getText());
    }

    class CheckBoxListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (checkBox.isChecked()) {
                parentReply.getModel().setAnyCondition(true);
            } else {
                parentReply.getModel().setAnyCondition(false);
            }
            return true;
        }
    }
}
