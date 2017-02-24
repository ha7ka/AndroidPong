package finalproj.infcos.pong.Objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Master on 11/17/14.
 */
public class Paddle implements Graphics {

    @Override
    public void make(float x, float y) {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(x, y, screenW / 64, screenH / 5);
        shapeRenderer.end();
    }

    public float getWidth() {
        return this.screenW / 64;
    }

    public float getHeight() {
        return this.screenH / 5;
    }

    @Override
    public void dispose() {
        this.shapeRenderer.dispose();
    }
}
