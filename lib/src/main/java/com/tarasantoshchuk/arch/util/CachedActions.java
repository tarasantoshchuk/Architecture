package com.tarasantoshchuk.arch.util;


import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.Queue;

public class CachedActions<T> {
    private T mReceiver;
    private Queue<Action<T>> mActions = new LinkedList<>();

    public void setReceiver(@NonNull T t) {
        mReceiver = t;

        applyPendingActions();
    }

    public void removeReceiver() {
        mReceiver = null;
    }

    /**
     * applies action on receiver, either now or when receiver arrives
     * @param action - action that will be applied on receiver
     */
    public void submit(Action<T> action) {
        if (mReceiver != null) {
            action.apply(mReceiver);
        }

        mActions.add(action);
    }

    private void applyPendingActions() {
        while(!mActions.isEmpty() && mReceiver != null) {
            mActions.poll().apply(mReceiver);
        }
    }
}
