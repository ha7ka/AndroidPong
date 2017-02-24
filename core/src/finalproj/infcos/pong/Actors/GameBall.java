package finalproj.infcos.pong.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;


import finalproj.infcos.pong.AI.PongAI;
import finalproj.infcos.pong.Objects.Ball;
import finalproj.infcos.pong.Worlds.Collision.CollisionDetector;
import finalproj.infcos.pong.Worlds.Collision.GameWorldDefiner;

/**
 * Created by Master on 11/17/14.
 */
public class GameBall extends Actor{

    private GameWorldDefiner gameWorldDefiner = GameWorldDefiner.getGameWorldDefiner();
    private CollisionDetector collisionDetector = CollisionDetector.getCollisionDetector();
    private Ball ball;
    private float width;


    public GameBall(Viewport viewport){
        ball=new Ball();
        width=viewport.getScreenWidth();
        setDefaultCords();
        collisionDetector.setBall(this);
    }
    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.end();
        ball.make(this.getX(), this.getY());
        batch.begin();

    }
    @Override
    public void act(float delta){
        switch (gameWorldDefiner.getBallDirectionX()){
            case 1:
                setX(getX() + (gameWorldDefiner.getVelocityX() * delta));
                setYCords(delta);
                break;

            case 2:
                setX(getX()-(gameWorldDefiner.getVelocityX() * delta));
                setYCords(delta);
                break;
        }
        updateBounds();
        collisionDetector.setBall(this);

    }


    private void setYCords(float delta){
        switch (gameWorldDefiner.getBallDirectionY()){
            case 1:
                setY(getY()+(gameWorldDefiner.getVelocityY()*delta));
                break;
            case 2:
                setY(getY()-(gameWorldDefiner.getVelocityY()*delta));
                break;
        }

    }

    private void setDefaultCords(){
        setX(0f);
        setY(0f);
        setHeight((this.width/128)*2);
        setWidth((this.width / 128) * 2);
    }
    private void updateBounds(){
        setBounds(getX(),getY(),getWidth(),getHeight());
    }

}
