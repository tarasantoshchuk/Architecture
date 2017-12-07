package com.tarasantoshchuk.arch.core.view.impl;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.tarasantoshchuk.arch.core.core.ArchitectureDelegates;
import com.tarasantoshchuk.arch.core.core.ViewCallbacks;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.Routers;
import com.tarasantoshchuk.arch.core.view.RootView;
import com.tarasantoshchuk.arch.core.view.View;

import io.reactivex.Observable;

public abstract class BaseViewGroup<P> extends android.view.ViewGroup implements View<P> {
    private ViewCallbacks mViewCallbacks;

    public BaseViewGroup(Context context) {
        super(context);
    }

    public BaseViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initDelegate(Context context) {
        ArchitectureDelegates.onCreateView((RootView) context, this);
        ArchitectureDelegates.onStart((RootView) context, this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initDelegate(getContext());
    }

    @Override
    public RouterCallback provideRouterImplementation() {
        return Routers.fromAndroidView(this);
    }

    @Override
    public void setCallback(ViewCallbacks callbacks) {
        mViewCallbacks = callbacks;
    }

    protected final <T> Observable<T> stateObservable(Observable<T> observable) {
        return observable
                .doOnSubscribe(mViewCallbacks::unsubscribeOnDetach);
    }
}
