package editor.core.screen.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class ArrowLine{

    private Vector2 from;
    private Vector2 to;
    private Vector2 toAdd = new Vector2(0.5f, 0.5f);

    public ArrowLine(Vector2 from, Vector2 to) {
        this.from = from;
        this.to = to;
    }


    public void update(Vector2 from, Vector2 to){
        this.from = from;
        this.to = to;
    }

    public void render(ShapeRenderer renderer) {
        renderer.setAutoShapeType(true);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        renderer.line(from, to);
        Vector2 arrowHead = new Vector2(to);
        Vector2 internalPoint = new Vector2();
        internalPoint.x = arrowHead.x - 3;
        internalPoint.y = arrowHead.y -3;
        Vector2 sidePoint1 = new Vector2(internalPoint.x + toAdd.x, internalPoint.y + toAdd.y);
        Vector2 sidePoint2 = new Vector2(internalPoint.x - toAdd.x, internalPoint.y - toAdd.y);
        renderer.triangle(arrowHead.x, arrowHead.y, sidePoint1.x,sidePoint1.y, sidePoint2.x, sidePoint2.y);
        renderer.end();
    }
}
