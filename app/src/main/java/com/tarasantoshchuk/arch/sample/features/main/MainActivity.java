package com.tarasantoshchuk.arch.sample.features.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tarasantoshchuk.arch.core.di.RootScreenConfigurator;
import com.tarasantoshchuk.arch.core.view.impl.BaseActivity;
import com.tarasantoshchuk.arch.sample.R;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainView;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainView.MainPresenter;
import com.tarasantoshchuk.arch.util.log.Logger;
import com.tarasantoshchuk.arch.util.Null;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {
    @BindView(R.id.txt_saved_text)
    TextView mSavedText;

    @BindView(R.id.btn_edit)
    Button mEdit;

    @BindView(R.id.btn_screen_with_subview)
    View mSubviewScreen;

    PublishSubject<Null> mEditClicks = PublishSubject.create();
    PublishSubject<Null> mOpenOtherClicks = PublishSubject.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_edit)
    void onEditClick() {
        Logger.v(this, "onEditClick");

        mEditClicks.onNext(Null.INSTANCE);
    }

    @OnClick(R.id.btn_screen_with_subview)
    void onOpenOtherClick() {
        Logger.v(this, "onOpenOtherClick");

        mOpenOtherClicks.onNext(Null.INSTANCE);
    }

    @Override
    public Observable<Null> editClicks() {
        return mEditClicks;
    }

    @Override
    public Observable<Null> openOtherClicks() {
        return mOpenOtherClicks;
    }

    private void setText(String text) {
        Logger.v(this, "setText, text " + text);

        mSavedText.setText(text);
    }

    private void enableEdit(boolean isEnabled) {
        Logger.v(this, "enableEdit, isLocked " + isEnabled);

        mEdit.setEnabled(isEnabled);
    }

    @Override
    public RootScreenConfigurator screenConfigurator() {
        return new MainScreenConfigurator(this);
    }

    @Override
    public void onAttachToPresenter(MainPresenter presenter) {
        super.onAttachToPresenter(presenter);

        stateObservable(presenter.editEnabled())
                .subscribe(this::enableEdit);

        stateObservable(presenter.text())
                .subscribe(this::setText);
    }
}
