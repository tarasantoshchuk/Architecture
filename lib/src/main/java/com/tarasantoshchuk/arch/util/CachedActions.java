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
     * @return true - instant execution
     */
    public boolean submit(Action<T> action) {
        if (mReceiver != null) {
            action.apply(mReceiver);
            return true;
        }

        mActions.add(action);
        return false;
    }

    private void applyPendingActions() {
        do {
            mActions.poll().apply(mReceiver);
        } while(!mActions.isEmpty() && mReceiver != null);
    }
}
