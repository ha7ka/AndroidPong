package finalproj.infcos.pong.Objects;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Master on 11/17/14.
 */
public class Ball implements Graphics{

    @Override
    public void make(float x,float y) {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(x,y, screenW /128);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
       this.shapeRenderer.dispose();
    }
}
