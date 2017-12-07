package com.tarasantoshchuk.arch.util;


import android.support.annotation.NonNull;

import com.tarasantoshchuk.arch.util.log.Logger;

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
        Logger.v(this, ">> submit");
        
        if (mReceiver != null) {
            Logger.v(this, "submit, executing");
            action.apply(mReceiver);
        } else {
            Logger.v(this, "submit, cached");
            mActions.add(action);
        }

        Logger.v(this, "<< submit");
    }

    private void applyPendingActions() {
        Logger.v(this, ">> applyPendingActions, size " + mActions.size());

        while(!mActions.isEmpty() && mReceiver != null) {
            Logger.v(this, "applyPendingActions, executing");
            mActions.poll().apply(mReceiver);
        }

        Logger.v(this, "<< applyPendingActions, size " + mActions.size());
    }
}
