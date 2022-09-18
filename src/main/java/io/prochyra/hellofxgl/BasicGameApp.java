package io.prochyra.hellofxgl;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Map;

public class BasicGameApp extends GameApplication {

    public static final String PIXELS_MOVED = "pixelsMoved";
    private Entity player;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Basic Game App");
        gameSettings.setVersion("0.1");
    }

    @Override
    protected void initGame() {
        player = FXGL.entityBuilder()
                .at(300, 300)
                .view(new Rectangle(25, 25, Color.BLUE))
                .buildAndAttach();
    }

    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.D, () -> {
            player.translateX(5);
            FXGL.inc(PIXELS_MOVED, +5);
        });
        FXGL.onKey(KeyCode.A, () -> {
            player.translateX(-5);
            FXGL.inc(PIXELS_MOVED, +5);
        });
        FXGL.onKey(KeyCode.W, () -> {
            player.translateY(-5);
            FXGL.inc(PIXELS_MOVED, +5);
        });
        FXGL.onKey(KeyCode.S, () -> {
            player.translateY(5);
            FXGL.inc(PIXELS_MOVED, +5);
        });
    }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.setTranslateX(50);
        textPixels.setTranslateY(100);

        textPixels.textProperty()
                .bind(FXGL.getWorldProperties()
                        .intProperty(PIXELS_MOVED)
                        .asString());

        FXGL.getGameScene().addUINode(textPixels);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put(PIXELS_MOVED, 0);
    }
}
