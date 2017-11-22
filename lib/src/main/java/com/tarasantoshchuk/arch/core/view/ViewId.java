package com.tarasantoshchuk.arch.core.view;


public final class ViewId {
    private final String mViewKey;

    ViewId(String viewKey) {
        mViewKey = viewKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewId viewId = (ViewId) o;

        return mViewKey != null ? mViewKey.equals(viewId.mViewKey) : viewId.mViewKey == null;
    }

    @Override
    public int hashCode() {
        return mViewKey != null ? mViewKey.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ViewId{" +
                "mViewKey='" + mViewKey + '\'' +
                '}';
    }
}
