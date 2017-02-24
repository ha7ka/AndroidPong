package finalproj.infcos.pong.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import finalproj.infcos.pong.SmartFont.SmartFontGenerator;

/**
 * Created by Master on 11/15/14.
 */
public class font {
    SmartFontGenerator smartFontGenerator = new SmartFontGenerator();
    FileHandle fontFile = Gdx.files.internal("desfont.ttf");
    BitmapFont fontMedium = smartFontGenerator.createFont(fontFile, "broken-medium", 38);

    public BitmapFont getFontMedium() {
        return fontMedium;
    }
}
