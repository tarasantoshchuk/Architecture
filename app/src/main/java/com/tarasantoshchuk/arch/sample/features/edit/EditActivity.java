package com.tarasantoshchuk.arch.sample.features.edit;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.tarasantoshchuk.arch.core.di.RootScreenConfigurator;
import com.tarasantoshchuk.arch.core.view.impl.BaseActivity;
import com.tarasantoshchuk.arch.sample.R;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditPresenter;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditView;
import com.tarasantoshchuk.arch.util.Null;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class EditActivity extends BaseActivity<EditPresenter> implements EditView {
    private PublishSubject<Null> mSaveClicks = PublishSubject.create();
    private PublishSubject<String> mTextChanges = PublishSubject.create();

    @BindView(R.id.edit_text)
    EditText mEditText;

    @BindView(R.id.save_button)
    View mSaveButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        ButterKnife.bind(this);
    }

    @Override
    public RootScreenConfigurator screenConfigurator() {
        return new EditScreenConfigurator(this);
    }

    @Override
    public void onAttachToPresenter(EditPresenter presenter) {
        super.onAttachToPresenter(presenter);

        observeState(
                presenter.textLoaded(),
                this::setText
        );

        observeState(
                presenter.uiEnabled(),
                this::enableUi
        );
    }

    @Override
    public Observable<Null> saveClicks() {
        return mSaveClicks;
    }

    @Override
    public Observable<String> textChanged() {
        return mTextChanges;
    }

    @OnClick(R.id.save_button)
    public void onSaveClick() {
        mSaveClicks.onNext(Null.INSTANCE);
    }

    @OnTextChanged(R.id.edit_text)
    public void onTextChanged(CharSequence text) {
        mTextChanges.onNext(text.toString());
    }

    private void setText(String text) {
        if (text.equals(mEditText.getText().toString())) {
            return;
        }

        mEditText.setText(text);
    }

    private void enableUi(Boolean isEnabled) {
        mEditText.setEnabled(isEnabled);
        mSaveButton.setEnabled(isEnabled);
    }
}
