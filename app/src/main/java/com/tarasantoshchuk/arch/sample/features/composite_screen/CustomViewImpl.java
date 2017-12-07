package com.tarasantoshchuk.arch.sample.features.composite_screen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.tarasantoshchuk.arch.core.di.ScreenConfigurator;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.router_impl.EmptyRouter;
import com.tarasantoshchuk.arch.core.view.impl.BaseView;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.CustomView;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.CustomView.CustomViewPresenter;
import com.tarasantoshchuk.arch.util.Null;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class CustomViewImpl extends BaseView<CustomViewPresenter> implements CustomView {
    private PublishSubject<Null> mClick = PublishSubject.create();
    private Paint mPaint = new Paint();

    public CustomViewImpl(Context context) {
        super(context);
    }

    public CustomViewImpl(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewImpl(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public ScreenConfigurator screenConfigurator() {
        return new ScreenConfigurator() {
            @Override
            public Presenter presenter() {
                return new CustomViewPresenterImpl();
            }

            @Override
            public Router router() {
                return new EmptyRouter();
            }

            @Override
            public com.tarasantoshchuk.arch.core.view.View view() {
                return CustomViewImpl.this;
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPaint(mPaint);
    }

    @Override
    public void onAttachToPresenter(CustomViewPresenter presenter) {
        super.onAttachToPresenter(presenter);

        stateObservable(presenter.switchChanged())
                .subscribe(this::changeColor);
    }

    private void changeColor(boolean v) {
        if (mPaint == null) {
            return;
        }

        mPaint.setColor(v ? Color.RED : Color.BLACK);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mClick.onNext(Null.INSTANCE);
        return super.onTouchEvent(event);
    }

    @Override
    public Observable<Null> touched() {
        return mClick;
    }
}
