package org.greenleaf.easyandroid.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * author: wangyonghua
 * version: V1.0
 * date: 2018/6/20
 * time: 17:19
 */

public class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private BaseAdapter<T> mBaseAdapter;

    public BaseViewHolder(BaseAdapter<T> baseAdapter, View itemView) {
        super(itemView);
        mBaseAdapter = baseAdapter;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        BaseAdapter.OnItemClickListener<T> itemClickListener = mBaseAdapter.getOnItemClickListener();
        if (itemClickListener != null) {
            T data = mBaseAdapter.getItem(getAdapterPosition());
            itemClickListener.onItemClick(v, getAdapterPosition(), data);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        BaseAdapter.OnItemClickListener<T> itemClickListener = mBaseAdapter.getOnItemClickListener();
        if (itemClickListener != null) {
            T data = mBaseAdapter.getItem(getAdapterPosition());
            return itemClickListener.onLongItemClick(v, getAdapterPosition(), data);
        }
        return false;
    }
}
