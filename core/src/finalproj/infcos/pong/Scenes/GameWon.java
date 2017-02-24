package finalproj.infcos.pong.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import finalproj.infcos.pong.Objects.Graphics;
import finalproj.infcos.pong.Worlds.BackGround.BackGroundRender;
import finalproj.infcos.pong.Objects.font;


/**
 * Created by Master on 12/1/14.
 */
public class GameWon implements Screen {

    private BackGroundRender backGroundRender = new BackGroundRender();
    private TextButton gameWonButton;
    private TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
    private OrthographicCamera orthographicCamera = new OrthographicCamera(Gdx.graphics.getHeight(),Gdx.graphics.getWidth());
    private BitmapFont font;
    private Stage stage = new Stage();

    public GameWon(String player){
        this.font = new font().getFontMedium();
        font.scale(2f);
        style.font = this.font;
        style.fontColor = Color.BLACK;
        gameWonButton =new TextButton(player,style);

    }

    @Override
    public void render(float delta) {
        backGroundRender.render();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        gameWonButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {

                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainScreen());
            }
        });
        orthographicCamera.setToOrtho(true);
        stage.getViewport().setCamera(orthographicCamera);
        stage.addActor(gameWonButton);
        gameWonButton.setOrigin(Align.center);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
