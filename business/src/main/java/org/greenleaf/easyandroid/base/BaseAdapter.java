package org.greenleaf.easyandroid.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    public static final java.lang.String TAG = "BaseAdapter";

    private int mItemLayoutId;//列表item
    private List<T> mDataSource = new ArrayList<>();

    private OnItemClickListener<T> mOnItemClickListener = null;

    public BaseAdapter(@LayoutRes int itemLayoutId) {
        this.mItemLayoutId = itemLayoutId;
    }

    public BaseAdapter(@LayoutRes int itemLayoutId, @NonNull T[] arrays) {
        mDataSource.addAll(Arrays.asList(arrays));
        this.mItemLayoutId = itemLayoutId;
    }

    public BaseAdapter(@LayoutRes int itemLayoutId, @NonNull Collection<T> collection) {
        mDataSource.addAll(collection);
        this.mItemLayoutId = itemLayoutId;
    }

    public BaseAdapter(@LayoutRes int itemLayoutId, @NonNull List<T> list) {
        mDataSource.addAll(list);
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public final BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new BaseViewHolder<>(this, inflater.inflate(mItemLayoutId, parent, false));
    }

    @Override
    public final void onBindViewHolder(BaseViewHolder holder, int position) {
        bindView(holder, mDataSource.get(position), position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mDataSource == null ? 0 : mDataSource.size();
    }

    @Nullable
    public T getItem(int position) {
        return (position < 0 || position >= mDataSource.size()) ? null : mDataSource.get(position);
    }

    public List<T> getDataSource() {
        return mDataSource;
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mOnItemClickListener = listener;
    }

    public OnItemClickListener<T> getOnItemClickListener() {
        return mOnItemClickListener;
    }

    protected void setData(List<T> newDataList) {
        mDataSource.clear();
        mDataSource.addAll(newDataList);
    }

    protected abstract void bindView(BaseViewHolder baseViewHolder, T data, int position);

    public interface OnItemClickListener<T> {

        void onItemClick(View view, int position, T data);

        boolean onLongItemClick(View view, int position, T data);
    }
}
