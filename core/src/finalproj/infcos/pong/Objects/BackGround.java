package finalproj.infcos.pong.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Master on 11/12/14.
 */
public class BackGround implements Graphics {

    private Vector2 linePt1, linePt2;


    public BackGround(){
        linePt1 = new Vector2(0 , screenH /2);
        linePt2 = new Vector2(0 ,-screenH /2 );
    }
    @Override
    public void make(float x,float y) {

        Gdx.gl.glClearColor(16/255f, 76/255f, 7/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.line(linePt1, linePt2);
        shapeRenderer.circle(0, 0, screenW /8);
        shapeRenderer.end();


    }
    @Override
    public void dispose() {
        this.shapeRenderer.dispose();
        this.spriteBatch.dispose();
    }
}
