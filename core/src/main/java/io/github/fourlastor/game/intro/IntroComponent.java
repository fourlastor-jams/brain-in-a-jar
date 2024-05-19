package io.github.fourlastor.game.intro;

import dagger.Subcomponent;
import io.github.fourlastor.game.di.ScreenScoped;

@ScreenScoped
@Subcomponent
public interface IntroComponent {

    @ScreenScoped
    IntroScreen screen();

    @Subcomponent.Builder
    interface Builder {

        IntroComponent build();
    }
}
