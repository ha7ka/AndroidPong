package finalproj.infcos.pong;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;


import finalproj.infcos.pong.Objects.Ball;
import finalproj.infcos.pong.Scenes.*;


public class PongActivity extends Game {

        Screen mainScreen;

        @Override
        public void create(){
            mainScreen =new MainScreen();
            setScreen(mainScreen);
        }

}
