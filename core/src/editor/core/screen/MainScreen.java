package editor.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

import editor.core.control.DialogGesturesDetector;
import editor.core.control.DialogEditorInputProcessor;
import editor.core.elements.visual.Dialogue;
import editor.core.elements.visual.DialogueLineElement;
import editor.core.elements.factory.DialogueLineFactory;
import editor.core.elements.visual.DialogueReplyElement;
import editor.core.elements.visual.popup.AreYouSurePopUp;
import editor.core.resource.ResourceManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class MainScreen implements Screen {

    private SpriteBatch spriteBatch;
    private ShapeRenderer renderer;


    private float worldWidth;
    private float worldHeight;

    private OrthographicCamera movingCamera;
    private OrthographicCamera staticCamera;
    private Viewport viewport;

    private Stage stage;
    private Stage staticStage;

    private TextButton chooseWorkingDirectory;
    private TextButton saveButton;


    private InputMultiplexer inputMultiplexer;
    private DialogEditorInputProcessor input;
    private DialogGesturesDetector gestures;

    private Dialogue dialogue;
    private DialogueLineFactory dialogueLineFactory;
    public List<DialogueLineElement> screenElements = new ArrayList<DialogueLineElement>();

    /*
    * Used to stop touchDragged after  zoom if both fingers aren't lifted up
    * If != 0 then touchDragged won't do anything
    * */
    private int zoomPointers = 0;

    private boolean connectArrowMode = false;
    private DialogueReplyElement connectFrom;

    public MainScreen(SpriteBatch batch, ShapeRenderer renderer, OrthographicCamera camera) {
        this.spriteBatch = batch;
        this.movingCamera = camera;
        this.renderer = renderer;
    }

    public Stage getStage() {
        return stage;
    }

    public Stage getStaticStage(){ return staticStage; }

    public OrthographicCamera getMovingCamera() {
        return movingCamera;
    }

    public Dialogue getDialogue() {
        return dialogue;
    }

    public int getZoomPointers() {
        return zoomPointers;
    }

    public void setZoomPointers(int zoomPointers) {
        this.zoomPointers = zoomPointers;
    }

    public void decrementZoomPointers(){
        if (zoomPointers > 0)
            zoomPointers--;
    }

    public boolean isConnectArrowMode() {
        return connectArrowMode;
    }

    public DialogueReplyElement getConnectFromElement(){
        return connectFrom;
    }

    public void setConnectArrowMode(boolean connectArrow, DialogueReplyElement from) {
        this.connectArrowMode = connectArrow;
        this.connectFrom = from;
    }

    public void addDialogueLine(Vector2 pos) {
        dialogue.addDialogueLine(dialogueLineFactory.getDialogueLine(pos));
    }

    public void addActorToStage(Actor actor) {
        stage.addActor(actor);
    }

    public void addActorToStaticStage(Actor actor){
        staticStage.addActor(actor);
    }


    @Override
    public void show() {
        worldWidth = Gdx.graphics.getWidth();
        worldHeight = Gdx.graphics.getHeight();


        movingCamera = new OrthographicCamera();
        viewport = new StretchViewport(worldWidth, worldHeight, movingCamera);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
        movingCamera.position.set(worldWidth / 2, worldHeight / 2, 0);
        movingCamera.update();

        staticCamera = new OrthographicCamera();
        staticCamera.position.set(worldWidth / 2, worldHeight / 2, 0);
        staticCamera.update();


        stage = new Stage(viewport, spriteBatch);
        staticStage = new Stage();

        inputMultiplexer = new InputMultiplexer();
        gestures = new DialogGesturesDetector(movingCamera, viewport, this);
        input = new DialogEditorInputProcessor(movingCamera, viewport, this);


        inputMultiplexer.addProcessor(staticStage);
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(gestures);
        inputMultiplexer.addProcessor(input);

        Gdx.input.setInputProcessor(inputMultiplexer);

        dialogueLineFactory = new DialogueLineFactory(this);

        dialogue = new Dialogue();
        dialogue.addDialogueLine(dialogueLineFactory.getDialogueLine("I dunno", new Vector2(50, 200)));
        dialogue.addDialogueLine(dialogueLineFactory.getDialogueLine("I dunno x2", new Vector2(550, 200)));

        saveButton = new TextButton("Save changes",ResourceManager.getSkin());
        saveButton.setBounds(0,Gdx.graphics.getHeight() - 50, 150, 50);
        saveButton.addListener(new SaveButtonListener());
        addActorToStaticStage(saveButton);

    }


    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setProjectionMatrix(movingCamera.combined);
        spriteBatch.setProjectionMatrix(movingCamera.combined);
        dialogue.render(renderer, spriteBatch);

        stage.act();
        stage.draw();

        spriteBatch.setProjectionMatrix(staticCamera.combined);
        staticStage.act();
        staticStage.draw();

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    class SaveButtonListener extends ClickListener{
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            AreYouSurePopUp areYouSurePopUp = new AreYouSurePopUp("Confirm", "Are you sure you want to save current changes?");
            areYouSurePopUp.setPosition(Gdx.graphics.getWidth()/2 - areYouSurePopUp.getWidth()/2,Gdx.graphics.getHeight()/2 - areYouSurePopUp.getHeight()/2);
            areYouSurePopUp.show(getStaticStage(), sequence(Actions.alpha(0), Actions.fadeIn(0.4f, Interpolation.fade)));
            return true;
        }
    }
}
