package com.tarasantoshchuk.arch.sample.features.composite_screen;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.tarasantoshchuk.arch.core.di.BaseRootScreenConfigurator;
import com.tarasantoshchuk.arch.core.di.RootScreenConfigurator;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.router_impl.EmptyRouter;
import com.tarasantoshchuk.arch.core.view.impl.BaseActivity;
import com.tarasantoshchuk.arch.sample.R;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.HostView;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.HostView.HostViewPresenter;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.Interactor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class CompositeScreenActivity extends BaseActivity<HostViewPresenter> implements HostView {
    private static final String TAG = CompositeScreenActivity.class.getSimpleName();

    @BindView(R.id.fragment_container)
    ViewGroup mFragmentContainer;

    @BindView(R.id.btn_enable_ui)
    Switch mUiEnabledSwitch;

    @BindView(R.id.text_view)
    TextView mText;

    private PublishSubject<Boolean> mSwitch = PublishSubject.create();

    @Override
    public RootScreenConfigurator screenConfigurator() {
        return new BaseRootScreenConfigurator<CompositeScreenActivity, HostViewPresenter, Interactor, Router>(this) {
            @Override
            public Interactor interactor() {
                return new InteractorImpl();
            }

            @Override
            public HostViewPresenter presenter() {
                return new HostViewPresenterImpl();
            }

            @Override
            public Router router() {
                return new EmptyRouter();
            }
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.composite_screen_activity);
        ButterKnife.bind(this);

        if (getFragmentManager().findFragmentByTag(TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new FragmentImpl(), TAG).commit();
        }
    }

    @OnCheckedChanged(R.id.btn_enable_ui)
    void checkedChanged() {
        mSwitch.onNext(mUiEnabledSwitch.isChecked());
    }

    @Override
    public Observable<Boolean> switched() {
        return mSwitch;
    }

    @Override
    public void onAttachToPresenter(HostViewPresenter presenter) {
        super.onAttachToPresenter(presenter);

        stateObservable(presenter.textChanged())
                .subscribe(this::setText);

        stateObservable(presenter.click())
                .subscribe(__ -> changeVisibility());
    }

    private void setText(String text) {
        mText.setText(text);
    }

    private void changeVisibility() {
        if (mText.getVisibility() == View.VISIBLE) {
            mText.setVisibility(View.INVISIBLE);
        } else {
            mText.setVisibility(View.VISIBLE);
        }
    }
}
