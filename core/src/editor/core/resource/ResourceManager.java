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
    private static Sprite triangle;

    public static Skin getSkin() {
        if (buttonSkin == null) {
            buttonSkin = new Skin(Gdx.files.internal("clean-crispy-ui.json"));
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

    public static Sprite getTriangleSprite() {
        if (triangle == null) {
            triangle = new Sprite(new TextureRegion(new Texture(Gdx.files.internal("triangle.png"))));
        }
        return triangle;
    }
}
