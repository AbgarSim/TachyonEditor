package editor.core.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ResourceManager {

    private static Skin buttonSkin;
    private static BitmapFont messageFont;
    private static BitmapFont editDialogPopUpFont;
    public static Skin getSkin() {
        if (buttonSkin == null) {
            buttonSkin = new Skin(Gdx.files.internal("metal-ui.json"));
        }
        return buttonSkin;
    }

    public static BitmapFont getBitmapFont() {
        if (messageFont == null) {
            messageFont = new BitmapFont();
            messageFont.setColor(Color.BLACK);
            messageFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        return messageFont;
    }

    public static BitmapFont getEditDialogPopUpFont() {
        if (editDialogPopUpFont == null) {
            editDialogPopUpFont = new BitmapFont();
            editDialogPopUpFont.setColor(Color.BLACK);
            editDialogPopUpFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        return editDialogPopUpFont;
    }

}
