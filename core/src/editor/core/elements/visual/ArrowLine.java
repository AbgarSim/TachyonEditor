package editor.core.elements.visual;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import editor.core.resource.ResourceManager;

public class ArrowLine {

    private DialogueReplyElement fromElement;
    private DialogueLineElement toElement;


    private boolean isFromLeftConnectPoint = false;
    private boolean isFromRightConnectPoint = false;

    private boolean isUpperConnectPoint = false;
    private boolean isLowerConnectPoint = false;
    private boolean isLeftConnectPoint = false;
    private boolean isRightConnectPoint = false;

    private float middlePointFrom;
    private Vector2 from;
    private Vector2 to;

    public ArrowLine(DialogueReplyElement fromElement, DialogueLineElement toElement) {
        this.fromElement = fromElement;
        this.toElement = toElement;
    }


    private void calculateFromPoint() {
        resetFromPoints();
        //Left connect point
        Vector2 point1 = new Vector2(fromElement.getPosition().x,
                fromElement.getPosition().y + (fromElement.getProportions().y / 2));
        //Right connect point
        Vector2 point2 = new Vector2(fromElement.getPosition().x + fromElement.getProportions().x,
                fromElement.getPosition().y + (fromElement.getProportions().y / 2));

        middlePointFrom = fromElement.getPosition().x + (fromElement.getProportions().x / 2);
        float middlePointTo = toElement.getPosition().x + (toElement.getProportions().x / 2);
        if (middlePointFrom >= middlePointTo) {
            isFromLeftConnectPoint = true;
            from = point1;
        } else {
            isFromRightConnectPoint = true;
            from = point2;
        }
    }

    private void calculateToPoint() {

        Vector2 upperConnectPoint = new Vector2(toElement.getPosition().x + (toElement.getProportions().x / 2), toElement.getPosition().y);
        Vector2 leftMiddleConnectPoint = new Vector2(toElement.getPosition().x, toElement.getPosition().y + (toElement.getProportions().y / 2));
        Vector2 rightMiddleConnectPoint = new Vector2(toElement.getPosition().x + toElement.getProportions().x, toElement.getPosition().y + (toElement.getProportions().y / 2));
        Vector2 lowerConnectPoint = new Vector2(toElement.getPosition().x + (toElement.getProportions().x / 2), toElement.getPosition().y + toElement.getProportions().y);


        float distToUpperPoint = (from.x - upperConnectPoint.x) * (from.x - upperConnectPoint.x) + (upperConnectPoint.y - from.y) * (upperConnectPoint.y - from.y);
        float distToLowerPoint = (from.x - lowerConnectPoint.x) * (from.x - lowerConnectPoint.x) + (lowerConnectPoint.y - from.y) * (lowerConnectPoint.y - from.y);
        float distToLeftPoint = (from.x - leftMiddleConnectPoint.x) * (from.x - leftMiddleConnectPoint.x) + (leftMiddleConnectPoint.y - from.y) * (leftMiddleConnectPoint.y - from.y);
        float distToRightPoint = (from.x - rightMiddleConnectPoint.x) * (from.x - rightMiddleConnectPoint.x) + (rightMiddleConnectPoint.y - from.y) * (rightMiddleConnectPoint.y - from.y);

        float result = Math.min(distToUpperPoint, Math.min(distToLowerPoint, Math.min(distToLeftPoint, distToRightPoint)));

        resetConnectPoint();
        if (result == distToUpperPoint || result == distToLowerPoint) {

            if (isFromLeftConnectPoint && (from.x <= upperConnectPoint.x)) {
                to = leftMiddleConnectPoint;
                isLeftConnectPoint = true;

            } else if (isFromRightConnectPoint && (from.x >= upperConnectPoint.x)) {
                to = rightMiddleConnectPoint;
                isRightConnectPoint = true;
            } else {
                if (result == distToUpperPoint) {
                    to = upperConnectPoint;
                    isUpperConnectPoint = true;
                } else {
                    to = lowerConnectPoint;
                    isLowerConnectPoint = true;
                }
            }


        } else if (result == distToLeftPoint) {
            if (isFromRightConnectPoint && (from.x >= leftMiddleConnectPoint.x - 20)) {
                if (distToUpperPoint <= distToLowerPoint) {
                    to = upperConnectPoint;
                    isUpperConnectPoint = true;
                } else {
                    to = lowerConnectPoint;
                    isLowerConnectPoint = true;
                }
            } else {
                to = leftMiddleConnectPoint;
                isLeftConnectPoint = true;
            }
        } else {
            if (isFromLeftConnectPoint && (from.x <= rightMiddleConnectPoint.x + 20)) {
                if (distToUpperPoint <= distToLowerPoint) {
                    to = upperConnectPoint;
                    isUpperConnectPoint = true;
                } else {
                    to = lowerConnectPoint;
                    isLowerConnectPoint = true;
                }
            } else {
                to = rightMiddleConnectPoint;
                isRightConnectPoint = true;
            }
        }
    }

    private void resetConnectPoint() {
        isUpperConnectPoint = false;
        isLowerConnectPoint = false;
        isLeftConnectPoint = false;
        isRightConnectPoint = false;
    }

    private void resetFromPoints() {
        isFromLeftConnectPoint = false;
        isFromRightConnectPoint = false;
    }


    public void render(SpriteBatch batch, ShapeRenderer renderer) {
        renderer.setAutoShapeType(true);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);

        calculateFromPoint();
        calculateToPoint();

        renderArrowPath(renderer);
        renderArrowHead(renderer);
        renderer.end();
    }

    private void renderArrowPath(ShapeRenderer renderer) {


        if (isUpperConnectPoint || isLowerConnectPoint) {
            Vector2 betweenPoint = new Vector2(to.x, from.y);
            renderer.line(from, betweenPoint);
            renderer.line(betweenPoint, to);
        } else {
            Vector2 distance;
            if (from.y >= to.y)
                distance = new Vector2(from.x - to.x, from.y - to.y);
            else
                distance = new Vector2(from.x - to.x, to.y - from.y);

            Vector2 betweenPoint1 = new Vector2(from.x - (distance.x / 2), from.y);
            Vector2 betweenPoint2 = new Vector2(betweenPoint1.x, to.y);
            if (collisionExist(betweenPoint2)) {
                if (isFromLeftConnectPoint) {
                    betweenPoint1 = new Vector2(toElement.getPosition().x - 50, from.y);
                    betweenPoint2 = new Vector2(betweenPoint1.x, to.y);
                } else {
                    betweenPoint1 = new Vector2(toElement.getPosition().x + toElement.getProportions().x + 50, from.y);
                    betweenPoint2 = new Vector2(betweenPoint1.x, to.y);
                }
            }
            renderer.line(from, betweenPoint1);
            renderer.line(betweenPoint1, betweenPoint2);
            renderer.line(betweenPoint2, to);
        }
    }

    private void renderArrowHead(ShapeRenderer renderer) {
        Vector2 arrowHead = new Vector2(to);
        Vector2 internalPoint;
        Vector2 trianglePoint1;
        Vector2 trianglePoint2;
        if (isLowerConnectPoint) {
            internalPoint = new Vector2(arrowHead.x, arrowHead.y + 20);
            trianglePoint1 = new Vector2(internalPoint.x - 10, internalPoint.y);
            trianglePoint2 = new Vector2(internalPoint.x + 10, internalPoint.y);
        } else if (isUpperConnectPoint) {
            internalPoint = new Vector2(arrowHead.x, arrowHead.y - 20);
            trianglePoint1 = new Vector2(internalPoint.x - 10, internalPoint.y);
            trianglePoint2 = new Vector2(internalPoint.x + 10, internalPoint.y);
        } else if (isLeftConnectPoint) {
            internalPoint = new Vector2(arrowHead.x - 20, arrowHead.y);
            trianglePoint1 = new Vector2(internalPoint.x, internalPoint.y - 10);
            trianglePoint2 = new Vector2(internalPoint.x, internalPoint.y + 10);
        } else {
            internalPoint = new Vector2(arrowHead.x + 20, arrowHead.y);
            trianglePoint1 = new Vector2(internalPoint.x, internalPoint.y - 10);
            trianglePoint2 = new Vector2(internalPoint.x, internalPoint.y + 10);
        }

        renderer.triangle(arrowHead.x, arrowHead.y, trianglePoint1.x, trianglePoint1.y, trianglePoint2.x, trianglePoint2.y);
    }

    private boolean collisionExist(Vector2 coords) {
        for (DialogueLineElement line : this.toElement.getParentScreen().getDialogue().getDialogueLines()) {
            if ((coords.x >= line.getPosition().x &&
                    coords.x <= (line.getPosition().x + line.getProportions().x) &&
                    coords.y >= line.getPosition().y &&
                    coords.y <= (line.getPosition().y + line.getProportions().y))) {
                return true;
            }
        }
        return false;
    }

}

