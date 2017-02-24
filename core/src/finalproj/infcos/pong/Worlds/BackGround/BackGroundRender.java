package finalproj.infcos.pong.Worlds.BackGround;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import finalproj.infcos.pong.Objects.BackGround;

/**
 * Created by Master on 11/13/14.
 */
public class BackGroundRender {

    private OrthographicCamera orthographicCamera;
    private BackGround backGround;


    public BackGroundRender(){

        backGround = new BackGround();
        orthographicCamera= new OrthographicCamera();
        orthographicCamera.update();

    }
    public void render() {
        backGround.make(0f,0f);
    }
    public void dispose(){
        backGround.dispose();
    }
}

