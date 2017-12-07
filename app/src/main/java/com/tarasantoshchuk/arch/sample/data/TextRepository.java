package com.tarasantoshchuk.arch.sample.data;

import com.tarasantoshchuk.arch.data.Repository;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class TextRepository implements Repository<String> {
    private static final TextRepository ourInstance = new TextRepository();

    public static TextRepository getInstance() {
        return ourInstance;
    }

    private BehaviorSubject<String> mSubject = BehaviorSubject.createDefault("Text");

    private TextRepository() {
    }

    @Override
    public Observable<String> get() {
        return mSubject
                .delay(5, TimeUnit.SECONDS);
    }

    @Override
    public Completable set(String s) {
        mSubject.onNext(s);

        return Completable
                .complete()
                .delay(5, TimeUnit.SECONDS);
    }
}
