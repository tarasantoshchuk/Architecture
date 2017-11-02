package com.tarasantoshchuk.arch.sample;

import com.tarasantoshchuk.arch.core.routing.ScreensResolver;
import com.tarasantoshchuk.arch.sample.core.routing.Screens;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ScreensResolver.install(new ScreensResolver<Screens>() {
            @Override
            protected int toRequestCode(Screens screen) {
                return screen.toRequestCode();
            }

            @Override
            protected Screens fromRequestCode(int requestCode) {
                return Screens.fromCode(requestCode);
            }

            @Override
            protected Class<?> toScreenClass(Screens screen) {
                return screen.toClass();
            }
        });
    }
}
