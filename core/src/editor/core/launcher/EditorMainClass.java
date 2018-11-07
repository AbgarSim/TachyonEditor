package editor.core.launcher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class EditorMainClass extends ApplicationAdapter {
	SpriteBatch batch;
	private Dialogue dialogue;
	private DialogueLine dialogueLine1;
	private DialogueLine dialogueLine;

	@Override
	public void create () {
		batch = new SpriteBatch();
		dialogueLine = new DialogueLine(300, 100);
		dialogueLine1 = new DialogueLine(200, 100);
		List<DialogueLine> lineList = new ArrayList<DialogueLine>();
		lineList.add(dialogueLine);
		lineList.add(dialogueLine1);
		dialogue = new Dialogue(lineList);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		dialogue.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
