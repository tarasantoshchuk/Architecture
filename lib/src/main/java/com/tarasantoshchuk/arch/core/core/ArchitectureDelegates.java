package com.tarasantoshchuk.arch.core.core;


import com.tarasantoshchuk.arch.core.view.RootView;
import com.tarasantoshchuk.arch.core.view.View;

public class ArchitectureDelegates {
    public static void onCreateView(RootView root, View v) {
        dispatchOnCreate(root, v);
    }

    private static void dispatchOnCreate(RootView rootView, View v) {
        ArchitectureDelegateHolder holder = rootView.architectureHolder();

        RootArchitectureDelegate oldDelegate = holder.get();


        if (oldDelegate == null) {
            RootArchitectureDelegate newDelegate = new RootArchitectureDelegate(rootView.screenConfigurator());

            holder.set(newDelegate);
            onCreateDispatched(rootView, v, newDelegate);
        } else {
            replaceView(rootView, v, oldDelegate);
        }
    }

    private static void replaceView(RootView rootView, View v, RootArchitectureDelegate oldDelegate) {
        if (rootView == v) {
            oldDelegate.replaceView(rootView);
        } else {
            oldDelegate.replaceView(rootView, v);
        }
    }

    private static void onCreateDispatched(RootView rv, View v, RootArchitectureDelegate newDelegate) {
        if (rv == v) {
            newDelegate.onCreate();
        } else {
            newDelegate.onCreate(v);
        }
    }

    public static void onStart(RootView rootView, View v) {
        getRootDelegate(rootView).onStart(v);
    }

    public static void onStop(RootView rootView, View v) {
        getRootDelegate(rootView).onStop(v);
    }

    private static RootArchitectureDelegate getRootDelegate(RootView rootView) {
        return rootView.architectureHolder().get();
    }

    public static void onCreateView(RootView rootView) {
        onCreateView(rootView, rootView);
    }

    public static void onStart(RootView rootView) {
        getRootDelegate(rootView).onStart();
    }

    public static void onStop(RootView rootView) {
        getRootDelegate(rootView).onStop();
    }
}
