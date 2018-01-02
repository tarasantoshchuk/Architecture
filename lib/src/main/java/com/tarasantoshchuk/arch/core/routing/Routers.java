package com.tarasantoshchuk.arch.core.routing;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.tarasantoshchuk.arch.core.routing.callback_impl.ActivityRouterCallback;
import com.tarasantoshchuk.arch.core.routing.callback_impl.AndroidViewRouterCallback;
import com.tarasantoshchuk.arch.core.routing.callback_impl.FragmentRouterCallback;
import com.tarasantoshchuk.arch.core.routing.callback_impl.SupportFragmentRouterCallback;

public abstract class Routers {
    public static RouterCallback fromActivity(Activity activity) {
        return new ActivityRouterCallback(activity);
    }

    public static RouterCallback fromFragment(Fragment fragment) {
        return new FragmentRouterCallback(fragment);
    }

    public static RouterCallback fromSupportFragment(android.support.v4.app.Fragment fragment) {
        return new SupportFragmentRouterCallback(fragment);
    }

    public static RouterCallback fromAndroidView(View view) {
        return new AndroidViewRouterCallback(view);
    }
}
