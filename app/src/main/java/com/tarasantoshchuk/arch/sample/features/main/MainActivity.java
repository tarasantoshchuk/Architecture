package com.tarasantoshchuk.arch.sample.features.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.tarasantoshchuk.arch.core.di.ScreenConfigurator;
import com.tarasantoshchuk.arch.core.view.impl.BaseActivity;
import com.tarasantoshchuk.arch.sample.R;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainPresenter;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainView;
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

    PublishSubject<Null> mEditClicks = PublishSubject.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_edit)
    void onEditClick() {
        mEditClicks.onNext(Null.INSTANCE);
    }

    @Override
    public Observable<Null> editClicks() {
        return mEditClicks;
    }

    @Override
    public void setText(String text) {
        mSavedText.setText(text);
    }

    @Override
    public void enableEdit(boolean isLocked) {
        mEdit.setEnabled(!isLocked);
    }

    @Override
    public ScreenConfigurator screenConfigurator() {
        return new MainScreenConfigurator(this);
    }
}
