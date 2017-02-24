package finalproj.infcos.pong.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Master on 11/16/14.
 */
public interface Graphics    {

    public float screenW = Gdx.graphics.getWidth();
    public float screenH =Gdx.graphics.getHeight();
    public SpriteBatch spriteBatch= new SpriteBatch();
    public ShapeRenderer shapeRenderer = new ShapeRenderer();
    public OrthographicCamera orthographicCamera = new OrthographicCamera(screenW, screenH);

    public void make(float x, float y);
    public void dispose();
}
