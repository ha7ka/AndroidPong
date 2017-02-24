package finalproj.infcos.pong.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;



import finalproj.infcos.pong.Actors.GameBall;
import finalproj.infcos.pong.Actors.GamePaddle;
import finalproj.infcos.pong.Worlds.BackGround.BackGroundRender;
import finalproj.infcos.pong.Worlds.Collision.GameWorldDefiner;
import finalproj.infcos.pong.Objects.font;

import static java.lang.Math.round;

/**
 * Created by Master on 11/17/14.
 *
 */
public class PongGameScreen implements Screen {

    private GameWorldDefiner gameWorldDefiner = GameWorldDefiner.getGameWorldDefiner();
    private Stage stage = new Stage();
    private BackGroundRender backGroundRender = new BackGroundRender();
    private OrthographicCamera orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

    private Table scoreChart = new Table();
    private TextButton player1Score;
    private TextButton player2Score;

    private BitmapFont font= new font().getFontMedium();
    private TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();

    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;

    private GamePaddle gamePaddleP1;
    private GamePaddle gamePaddleP2;
    private GameBall ball;

    private boolean singlePlayer;



    PongGameScreen(boolean singlePlayer){
        gameWorldDefiner.reset((int)round(Math.random())+1);
        this.font.scale(1f);
        style.font = this.font;
        style.fontColor= Color.BLACK;
        player1Score = new TextButton(Integer.toString(scorePlayer1),style);
        player2Score = new TextButton(Integer.toString(scorePlayer2),style);
        stage.getViewport().setCamera(orthographicCamera);
        ball = new GameBall(stage.getViewport());
        gamePaddleP1 = new GamePaddle(stage.getViewport(),1);
        if(!singlePlayer)
            gamePaddleP2 = new GamePaddle(stage.getViewport(),2);
        else
            gamePaddleP2 = new GamePaddle(stage.getViewport(),singlePlayer);
        Gdx.input.setInputProcessor(stage);
        this.singlePlayer = singlePlayer;
    }

    @Override
    public void render(float delta) {
        backGroundRender.render();
        stage.act(Gdx.graphics.getRawDeltaTime());
        stage.draw();
        gameWorldDefiner.boundHit(this.ball);
        gameWorldDefiner.paddleHit();
        checkScored();
    }


    @Override
    public void show() {
        gameWorldDefiner.setStage(stage);
        stage.addActor(gamePaddleP1);
        stage.addActor(gamePaddleP2);
        stage.addActor(ball);
        stage.addActor(scoreChart);
        gamePaddleP1.setTouchable(Touchable.enabled);
        gamePaddleP2.setTouchable(Touchable.enabled);

        gamePaddleP1.addListener(new DragListener(){
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                Actor target = event.getTarget();
                target.setY(target.getY() + y -(target.getHeight()/2));
            }
        });
        if(!singlePlayer) {
            Gdx.app.log("Game is 2", "Player");
            gamePaddleP2.addListener(new DragListener() {

                @Override
                public void touchDragged(InputEvent event, float x, float y, int pointer) {
                    Actor target = event.getTarget();
                    target.setY(target.getY() + y - (target.getHeight() / 2));
                }
            });
        }
        else
            Gdx.app.log("Game is 1", "AI");
        scoreChart.setY(Gdx.graphics.getHeight() / 2 - 30f);
        scoreChart.add(player2Score);
        scoreChart.add(player1Score);
    }
    private void checkScored(){
        if(gameWorldDefiner.playerScored(this.ball).equals("")){
            return;
        }
        if(gameWorldDefiner.playerScored(this.ball).equals("Player 1")){
            scorePlayer2++;
            player2Score.setText(Integer.toString(scorePlayer2));
            if(!gameWon(scorePlayer2)){
              ball.setX(0f);
              ball.setY(0f);
              gameWorldDefiner.reset(2);
           }
           else {
                Gdx.app.log("Player 2 won","");
               ((Game) Gdx.app.getApplicationListener()).setScreen(new GameWon("Player 1 Won"));
           }
        }
        else if(gameWorldDefiner.playerScored(this.ball).equals("Player 2")){
            scorePlayer1++;
            player1Score.setText(Integer.toString(scorePlayer1));
            if(!gameWon(scorePlayer1)){
                ball.setX(0f);
                ball.setY(0f);
                gameWorldDefiner.reset(1);
            }
            else {
                Gdx.app.log("Player 1 won","");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameWon("Player 2 Won"));
            }
        }

    }
    private boolean gameWon(int score){
        if (score >= 5){
            Gdx.app.log("player", "Won");
            return true;
        }
        else {
            Gdx.app.log("player", "Lost");
            return false;
        }
    }

    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void hide() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void pause() {
    }
    @Override
    public void dispose() {
        this.stage.dispose();
    }
}
