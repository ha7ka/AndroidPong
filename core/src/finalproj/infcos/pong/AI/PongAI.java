package finalproj.infcos.pong.AI;

import com.badlogic.gdx.Gdx;

import finalproj.infcos.pong.Actors.GameBall;
import finalproj.infcos.pong.Actors.GamePaddle;
import finalproj.infcos.pong.Worlds.Collision.CollisionDetector;
import finalproj.infcos.pong.Worlds.Collision.GameWorldDefiner;

import static java.lang.Math.abs;

/**
 * Created by Master on 12/8/14.
 */
public class PongAI {

    private static final PongAI pongAI = new PongAI();
    private GameWorldDefiner gameWorldDefiner = GameWorldDefiner.getGameWorldDefiner();
    private CollisionDetector collisionDetector = CollisionDetector.getCollisionDetector();

    private float paddlePosition;
    private float ballX,ballY;
    private int directionY;
    private float velocityX,velocityY;

    private int paddleHit;

    private float screenW = Gdx.graphics.getWidth();
    private float screenH = Gdx.graphics.getHeight();

    private int aiState = 0;

    public void act(){
        switch (aiState){
            case 0:
                if(getBallX()<0)
                    aiState = 1;

                break;
            case 1:
                setActors();
                getCollisionDomain();
                aiState = 2;
                break;
            case 2:
                newPaddleY();
                aiState = 3;
                break;
            case 3:
                break;
            case 4:
                setPaddleHit();
                if(paddleHit == 2){
                    aiState = 5;

                }
                if(gameWorldDefiner.isReset()){
                    aiState = 0;
                    gameWorldDefiner.setReset(false);

                }

                break;
            case 5:
                setPaddleHit();
                if (paddleHit == 1){
                    aiState = 0;
                }
                break;
        }

    }
    private void newPaddleY(){
            paddlePosition = ballY;
    }
    private void getCollisionDomain(){
            while (checkEnd()){
                setNewBallX();
                setNewBallY();
                checkBounds();
            }
    }


    private boolean checkEnd(){
            if (ballX - velocityX > -screenW/2){
                return true;
            }
            else {
                setFinalY();
                return false;
            }
    }
    public void setFinalY(){
        float x1,x2,y1,y2;
        x1 = ballX -(-screenW/2);
        x2 = velocityX;
        y2 = velocityY;
        y1 =(x1*y2)/x2;
        velocityX = x1;
        velocityY = y1;
        setNewBallY();

    }
    private void setActors(){
        directionY = gameWorldDefiner.getBallDirectionY();
        velocityX = gameWorldDefiner.getVelocityX();
        velocityY = gameWorldDefiner.getVelocityY();
        ballX = collisionDetector.getBallX();
        ballY = collisionDetector.getBallY();
        paddlePosition = collisionDetector.getPaddlePosition();
    }

    public void setPaddleHit(){
        paddleHit = collisionDetector.getPaddleHit();
    }
    public float getPaddlePos(){
        return paddlePosition;
    }
    public int getAiState(){
        return aiState;
    }

    public void setAiState(int aiState) {
        this.aiState = aiState;
    }

    private void setNewBallX(){
             ballX = ballX - velocityX;
    }

    private void checkBounds(){
        if(ballY > screenH/2 ){
            velocityX += 30;
            directionY = 2;
        }
        if (ballY < - screenH/2){
            velocityX += 30;
            directionY = 1;
        }
    }

    private void setNewBallY(){
        switch (directionY){
            case 1:
                ballY= ballY + velocityY;
                break;
            case 2:
                ballY = ballY - velocityY;
        }
    }

    private float getBallX(){
        return collisionDetector.getBallX();
    }

    public static PongAI getPongAI(){
        return pongAI;
    }



}
