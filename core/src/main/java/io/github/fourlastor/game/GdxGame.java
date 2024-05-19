package io.github.fourlastor.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.github.tommyettinger.ds.ObjectList;
import io.github.fourlastor.game.di.GameComponent;
import io.github.fourlastor.game.intro.IntroComponent;
import io.github.fourlastor.harlequin.Harlequin;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class GdxGame extends Game {

    private final InputMultiplexer multiplexer;

    private final IntroComponent.Builder introScreenFactory;

    @Inject
    public GdxGame(
            InputMultiplexer multiplexer,
            IntroComponent.Builder introScreenFactory) {
        this.multiplexer = multiplexer;
        this.introScreenFactory = introScreenFactory;
        Harlequin.LIST_CREATOR = new Harlequin.ListCreator() {
            @Override
            public <T> List<T> newList() {
                return new ObjectList<>();
            }

            @Override
            public <T> List<T> newList(int size) {
                return new ObjectList<>(size);
            }
        };
    }

    @Override
    public void create() {
        Gdx.input.setInputProcessor(multiplexer);
        setScreen(introScreenFactory.build().screen());
    }

    public static GdxGame createGame() {
        return GameComponent.component().game();
    }
}
