package editor.core.launcher.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import editor.core.launcher.EditorMainClass;

public class DesktopLauncher {

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;

	public static void main (String[] arg) {
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Tachyon editor";
		config.width=WIDTH;
		config.height=HEIGHT;
		new LwjglApplication(new EditorMainClass(), config);
	}
}
