package editor.core.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ResourceManager {

    private static Skin buttonSkin;

    public static void init(){
        buttonSkin = new Skin(Gdx.files.internal("clean-crispy-ui.json"));
    }

    public static Skin getButtonSkin() {
        if (buttonSkin == null) {
            init();
        }
        return buttonSkin;
    }
}
