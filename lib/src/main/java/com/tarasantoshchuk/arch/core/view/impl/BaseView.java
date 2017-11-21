package com.tarasantoshchuk.arch.core.view.impl;



import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.tarasantoshchuk.arch.core.core.ArchitectureDelegates;
import com.tarasantoshchuk.arch.core.core.ViewCallbacks;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.Routers;
import com.tarasantoshchuk.arch.core.view.RootView;
import com.tarasantoshchuk.arch.core.view.View;
import com.tarasantoshchuk.arch.util.Action;

import io.reactivex.Observable;
import io.reactivex.Observer;

public abstract class BaseView<P> extends android.view.View implements View<P> {
    ViewCallbacks<? extends Router> mViewCallbacks;

    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    public void setCallback(ViewCallbacks<? extends Router> callbacks) {
        mViewCallbacks = callbacks;
    }

    protected final <T> void observeState(Observable<T> observable, Action<T> observer) {
        observable
                .subscribe(
                        stateObserver(observer)
                );
    }

    private <T> Observer<T> stateObserver(Action<T> onNext) {
        return mViewCallbacks.stateObserver(onNext);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
