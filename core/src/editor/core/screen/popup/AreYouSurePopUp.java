package editor.core.screen.popup;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import editor.core.resource.ResourceManager;

public class AreYouSurePopUp extends Dialog {

    private Label areYouSureTextField;


    public AreYouSurePopUp(String title, String message) {
        super(title, ResourceManager.getSkin());
        areYouSureTextField = new Label(message, ResourceManager.getSkin());

        getContentTable().add(areYouSureTextField);
        button("Yes", "Yes");
        button("No", "No");

    }


    @Override
    protected void result(Object object) {
        if (object.equals("Yes")) {
            hide();
        }
    }
}
