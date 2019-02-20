package editor.core.screen.popup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.List;
import java.util.Set;

import editor.core.elements.model.condition.Condition;
import editor.core.elements.model.condition.ConditionsService;
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
    private TextButton conditionsTextField;
    private CheckBox checkBox;
    private boolean selectActionEvent = true;
    private SelectBox selectBox;
    private ScrollPane scrollPane;

    public EditDialogueReplyPopUp(String title, DialogueLineElement parent, DialogueReplyElement parentReply) {
        super(title, ResourceManager.getSkin());
        this.parentLine = parent;
        this.parentReply = parentReply;

        messageTextField = new Label(parent.getModel().getMessageText(), ResourceManager.getSkin());
        replyTextField = new Label(parentReply.getModel().getText(), ResourceManager.getSkin());
        nextMessageTextField = new Label(parentReply.getModel().getNextMessage(), ResourceManager.getSkin());
        conditionsTextField = new TextButton("Conditions", ResourceManager.getSkin());
        conditionsTextField.addListener(new AddConditionListener());


        selectBox = new SelectBox(ResourceManager.getSkin());
        //sflectableItems());
        selectBox.setVisible(false);
        selectBox.addListener(new ChooseConditionFromSelectBoxListener());


        checkBox = new CheckBox("Any condition", ResourceManager.getSkin());
        checkBox.setChecked(parentReply.getModel().isAnyCondition());

        buildContentTable();

        getBackground().setMinWidth(Gdx.graphics.getWidth() - 100);
        getBackground().setMinHeight(Gdx.graphics.getHeight() - 100);

        button("Done", "Done");
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

    private void buildContentTable() {
        this.getContentTable().clear();
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

        for (Condition condition : parentReply.getModel().getConditions()) {
            table.add(new Label("---------------------------------------------------------------------------", ResourceManager.getSkin()));
            table.row();
            table.add(new Label("Condition " + condition.getType(), ResourceManager.getSkin()));
            table.row();
            if (condition.isAbstract) {
                table.add(new Label("isAbstract:", ResourceManager.getSkin()));
                table.add(new TextField("true", ResourceManager.getSkin()));
                table.row();
            }
            for (String paramName : condition.getParameters().keySet()) {
                table.add(new Label(paramName, ResourceManager.getSkin()));
                String paramInput;
                if (condition.getParameters().get(paramName).equals("String")) {
                    TextField inputField = new TextField("String text", ResourceManager.getSkin());
                    table.add(inputField);
                    table.row();
                } else if (condition.getParameters().get(paramName).equals("boolean")) {
                    SelectBox inputField = new SelectBox(ResourceManager.getSkin());
                    inputField.setItems(new String[]{"true", "false"});
                    table.add(inputField);
                    table.row();
                } else if (condition.getParameters().get(paramName).equals("int")) {
                    TextField inputField = new TextField("0", ResourceManager.getSkin());
                    table.add(inputField);
                    table.row();
                } else if (condition.getParameters().get(paramName).contains("List")) {
                    Label label = new Label("List not implemented yet :(", ResourceManager.getSkin());
                    table.add(label);
                    table.row();
                } else {
                    Label label = new Label(condition.getParameters().get(paramName) + " not supported yet", ResourceManager.getSkin());
                    table.add(label);
                    table.row();
                }
            }
        }

        table.add(conditionsTextField);
        table.row();
        table.add(selectBox).padTop(10);

        ScrollPane scrollPane = new ScrollPane(table, ResourceManager.getSkin());
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setOverscroll(false, false);

        this.getContentTable().add(scrollPane).fill().expand();
        this.setKeepWithinStage(false);
    }

    private String[] getSelectableItems() {
        Set<String> selectableTypes = ConditionsService.getSelectableConditionTypes();
        List<Condition> conditions = parentReply.getModel().getConditions();

        String[] typeArray = new String[selectableTypes.size() - conditions.size()];

        int i = 0;
        for (String type : selectableTypes) {
            if (!conditions.contains(ConditionsService.getConditionMetadataByType(type)))
                typeArray[i++] = type;
        }
        return typeArray;
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

    class AddConditionListener extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (selectBox.isVisible()) {
                selectBox.setVisible(false);
            } else {
                selectActionEvent = false;
                selectBox.setItems(getSelectableItems());
                selectActionEvent = true;
                selectBox.setVisible(true);
            }
            return true;
        }
    }

    class ChooseConditionFromSelectBoxListener extends ChangeListener {


        @Override
        public void changed(ChangeEvent event, Actor actor) {
            if (selectActionEvent) {
                selectActionEvent = false;
                Condition condition = ConditionsService.getConditionMetadataByType((String) selectBox.getSelected());
                parentReply.getModel().addCondition(condition);
                selectBox.setVisible(false);
                selectActionEvent = true;
                buildContentTable();
            }
        }
    }
}
