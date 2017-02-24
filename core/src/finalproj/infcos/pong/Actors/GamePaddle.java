package finalproj.infcos.pong.Actors;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;

import finalproj.infcos.pong.AI.PongAI;
import finalproj.infcos.pong.Objects.Paddle;
import finalproj.infcos.pong.Worlds.Collision.CollisionDetector;
import finalproj.infcos.pong.Worlds.Collision.GameWorldDefiner;


/**
 * Created by Master on 11/17/14.
 */
public class GamePaddle extends Actor {

    private CollisionDetector collisionDetector = CollisionDetector.getCollisionDetector();
    private GameWorldDefiner gameWorldDefiner = GameWorldDefiner.getGameWorldDefiner();
    private PongAI pongAI = PongAI.getPongAI();
    boolean isActor = false;

    private Paddle paddle;
    private float width;
    private float height;
    private int playerNumber;


    public GamePaddle(Viewport viewport,int playerNumber) {
        paddle = new Paddle();
        width = viewport.getScreenWidth();
        height = viewport.getScreenHeight();
        this.playerNumber = playerNumber;
        setPaddlePos(width,height,playerNumber);
        setActorProprieties();
        setCollisions();
    }
    public GamePaddle(Viewport viewport,boolean singlePlayer) {
        paddle = new Paddle();
        width = viewport.getScreenWidth();
        height = viewport.getScreenHeight();
        this.playerNumber = 2;
        setPaddlePos(width,height,playerNumber);
        this.setActor(singlePlayer);
        setActorProprieties();
        setCollisions();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        paddle.make(getX(), getY());
        batch.begin();
    }

    @Override
    public void act(float delta) {
        setCollisions();
        if(isActor) {
            pongAI.act();
            if (pongAI.getAiState() == 3){
                setY(pongAI.getPaddlePos());
                Gdx.app.log("PADDLE POSITION",Float.toString(getY()));
                pongAI.setAiState(4);
            }
            if(gameWorldDefiner.isReset()){
                setPaddlePos(width,height,playerNumber);
            }
        }

    }

    private void setPaddlePos(float width,float height,int playerNumber){
        switch (playerNumber){
            case 1:
                setX((width/2)-(this.width/64)-30);
                setY(-(height/5)/2);
                break;
            case 2:
                setX(-(width/2)+(this.width/64));
                setY(-(height/5)/2);
                break;
        }

    }
    private void setCollisions(){
        switch (playerNumber){
            case 1:
                collisionDetector.setPaddleP1(this);
                break;
            case 2:
                collisionDetector.setPaddleP2(this);
                break;
        }
    }
    private void setPaddleY(){
        float yPos = pongAI.getPaddlePos();

    }

    private void setActorProprieties(){
        setHeight(height/5);
        setWidth((width/32));
        setOrigin(getHeight()/2,getWidth()/2);
    }

    public void setActor(boolean a){
        this.isActor =a;
    }




}


