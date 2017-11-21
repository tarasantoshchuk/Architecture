package com.tarasantoshchuk.arch.sample.features.composite_screen;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tarasantoshchuk.arch.core.di.ScreenConfigurator;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.router_impl.EmptyRouter;
import com.tarasantoshchuk.arch.core.view.impl.BaseFragment;
import com.tarasantoshchuk.arch.sample.R;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.FragmentView;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.FragmentView.FragmentViewPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class FragmentImpl extends BaseFragment<FragmentViewPresenter> implements FragmentView {
    private PublishSubject<String> mTextChanges = PublishSubject.create();

    @BindView(R.id.text_edit)
    EditText mEditText;

    @Override
    public ScreenConfigurator screenConfigurator() {
        return new ScreenConfigurator<FragmentImpl, Contract.FragmentView.FragmentViewPresenter, Router>() {
            @Override
            public FragmentViewPresenter presenter() {
                return new FragmentPresenterImpl();
            }

            @Override
            public Router router() {
                return new EmptyRouter();
            }

            @Override
            public FragmentImpl view() {
                return FragmentImpl.this;
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onAttachToPresenter(FragmentViewPresenter presenter) {
        super.onAttachToPresenter(presenter);

        observeState(presenter.switchChanged(),
                this::changeColor);
    }

    private void changeColor(boolean value) {
        View view = getView();

        if (view != null) {
            view.setBackgroundColor(value ? Color.BLUE : Color.YELLOW);
        }
    }

    @OnTextChanged(R.id.text_edit)
    public void onTextChanged(CharSequence text) {
        mTextChanges.onNext(text.toString());
    }

    @Override
    public Observable<String> textEdited() {
        return mTextChanges;
    }
}
