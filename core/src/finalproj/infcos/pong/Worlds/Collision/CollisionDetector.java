package finalproj.infcos.pong.Worlds.Collision;

import com.badlogic.gdx.math.Rectangle;


import finalproj.infcos.pong.AI.PongAI;
import finalproj.infcos.pong.Actors.GameBall;
import finalproj.infcos.pong.Actors.GamePaddle;

import static java.lang.Math.abs;

/**
 * Created by Master on 11/21/14.
 */
public class CollisionDetector {

    private static final CollisionDetector COLLISION_DETECTOR = new CollisionDetector();

    private int paddleHit;
    private float deltaY;

    Rectangle ballR;
    Rectangle paddleP1R;
    Rectangle paddleP2R;


    GameBall ball;
    GamePaddle paddleP1;
    GamePaddle paddleP2;

    private void setRectangles(String s){
        if(s.equals("ball")) {
            ballR = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
        }
        else if (s.equals("paddle 1")) {
            paddleP1R = new Rectangle(paddleP1.getX(), paddleP1.getY(), paddleP1.getWidth(), paddleP1.getHeight());
        }
        else if(s.equals("paddle 2")) {
            paddleP2R = new Rectangle(paddleP2.getX(), paddleP2.getY(), paddleP2.getWidth(), paddleP2.getHeight());
        }
    }
    public boolean checkOverlapping(){
        if(paddleP1R.overlaps(ballR)) {
            deltaY = ((ball.getY() - paddleP1.getY()) - (paddleP1.getHeight() / 2));
            paddleHit=1;
            return true;
        }
        else if(paddleP2R.overlaps(ballR)){
            deltaY = ((ball.getY() - paddleP2.getY()) - (paddleP2.getHeight() / 2));
            paddleHit = 2;
            return true;
        }
        else
        return false;
    }

    public void setBall(GameBall ball) {
        this.ball = ball;
        setRectangles("ball");
    }

    public void setPaddleP1(GamePaddle paddleP1) {
        this.paddleP1 = paddleP1;
        setRectangles("paddle 1");
    }

    public void setPaddleP2(GamePaddle paddleP2) {
        this.paddleP2 = paddleP2;
        setRectangles("paddle 2");
    }


    public float getDeltaY() {
        return deltaY;
    }

    public int getPaddleHit() {
        return paddleHit;
    }

    public float getPaddleHeight() {
        return paddleP1.getHeight();
    }
    public float getPaddlePosition(){
        return paddleP2.getY();
    }
    public float getBallX(){
        return this.ball.getX();
    }
    public float getBallY(){
        return this.ball.getY();
    }

    public static CollisionDetector getCollisionDetector(){
        return COLLISION_DETECTOR;
    }
}
