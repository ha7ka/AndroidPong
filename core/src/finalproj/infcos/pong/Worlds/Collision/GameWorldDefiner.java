package finalproj.infcos.pong.Worlds.Collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import finalproj.infcos.pong.AI.PongAI;
import finalproj.infcos.pong.Actors.GameBall;
import finalproj.infcos.pong.Actors.GamePaddle;
import finalproj.infcos.pong.Objects.Paddle;

import static java.lang.Math.abs;
import static java.lang.Math.round;
import static java.lang.Math.tan;

/**
 * Created by Master on 11/28/14.
 */
public class GameWorldDefiner {

    private static final GameWorldDefiner GAME_WORLD_DEFINER = new GameWorldDefiner();
    private CollisionDetector collisionDetector = CollisionDetector.getCollisionDetector();

    private int ballDirectionX,ballDirectionY;
    private float velocityY = 0f,velocityX;

    private Stage stage;

    boolean reset;


    private GameWorldDefiner(){
        ballDirectionX = (int)round(Math.random())+1;
        ballDirectionY = 1;
        velocityX = 200f;
    }
    private void setVelocityY(){
       float deltaY = collisionDetector.getDeltaY();
                if(deltaY>0 ){
                    velocityY =yVelocityCalc(yAngleCalc(abs(deltaY)));
                    ballDirectionY =1;
                }
                if(deltaY == 0){
                    velocityY =0;
                }
                if (deltaY < 0){
                    velocityY =yVelocityCalc(yAngleCalc(abs(deltaY)));
                    yAngleCalc(deltaY);
                    ballDirectionY = 2;
                }
    }
    private float yAngleCalc(float deltaY){
        float angle = 0;
        while (deltaY > 0){
             angle +=5;
            deltaY = deltaY - (collisionDetector.getPaddleHeight()/30);
        }
        return  angle;
    }
    private float yVelocityCalc(float angle){
        float y1, y2, yIntercept;
        float slope = (float)tan(angle);
        yIntercept =(collisionDetector.getBallY() - (slope*collisionDetector.getBallX()));
        y1 = collisionDetector.getBallY();
        y2 = (slope * (collisionDetector.getBallX()+velocityX))+yIntercept;
        float returnVal = abs(y2-y1);
        if(returnVal > 500f){
            returnVal = 500f;
        }
        return returnVal;
    }
    public void reset(int a){
        ballDirectionX = a;
        ballDirectionY = 1;
        velocityX = 200f;
        velocityY = 0f;
        reset =true;

    }

    public void paddleHit(){
            if (collisionDetector.checkOverlapping()) {
                switch (collisionDetector.getPaddleHit()) {
                    case 1:
                        setVelocityY();
                        ballDirectionX = 2;
                        velocityX += 30;
                        break;
                    case 2:
                        setVelocityY();
                        ballDirectionX = 1;
                        velocityX += 30;
                        break;
                }
            }
    }
    public void boundHit(GameBall ball){
        if(ball.getY() > stage.getViewport().getScreenHeight()/2 ){
            velocityX += 30;
            ballDirectionY =2;
        }
        if (ball.getY() < - stage.getViewport().getScreenHeight()/2){
            velocityX += 30;
            ballDirectionY = 1;
        }
    }
    public String playerScored(GameBall ball){
        String player = "";
        if(ball.getX() >stage.getViewport().getScreenWidth()/2){
            player = "Player 1";
        }
        if(ball.getX() < - stage.getViewport().getScreenWidth()/2){
            player = "Player 2";
        }
        return player;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public int getBallDirectionX() {
        return ballDirectionX;
    }

    public int getBallDirectionY() {
        return ballDirectionY;
    }

    public float getVelocityX() {
        return velocityX;
    }
    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }
    public float getVelocityY() {
        return velocityY;
    }

    public static GameWorldDefiner getGameWorldDefiner(){
        return GAME_WORLD_DEFINER;
    }
}
