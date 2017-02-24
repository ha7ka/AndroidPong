package finalproj.infcos.pong.Scenes;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import finalproj.infcos.pong.Worlds.BackGround.BackGroundRender;
import finalproj.infcos.pong.Objects.font;

/**
 * Created by Master on 11/13/14.
 */
public class MainScreen implements Screen {

    private BackGroundRender backGroundRender;
    private TextButton singlePlayerButton;
    private TextButton multiPlayerButton;
    private TextButton.TextButtonStyle style;
    private OrthographicCamera orthographicCamera;
    private BitmapFont font;


    private Stage stage;
    private Table table;

    public MainScreen(){
        orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        font = new font().getFontMedium();
        this.font.scale(5f);
        style = new TextButton.TextButtonStyle();
        style.font = this.font;
        style.fontColor = Color.BLACK;
        singlePlayerButton = new TextButton("1 Player",style);
        multiPlayerButton = new TextButton("2 Players", style);
        backGroundRender = new BackGroundRender();
        stage = new Stage();
        table = new Table();

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

        singlePlayerButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {

                ((Game)Gdx.app.getApplicationListener()).setScreen(new PongGameScreen(true));
            }
        });

        multiPlayerButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                ((Game)Gdx.app.getApplicationListener()).setScreen(new PongGameScreen(false));
            }
        });


        stage.addActor(table);
        table.align(Align.center);
        stage.getViewport().setCamera(orthographicCamera);
        table.add(singlePlayerButton);
        table.row();
        table.add(multiPlayerButton);
        table.row();
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
        backGroundRender.dispose();
        stage.dispose();
        font.dispose();
    }

}
