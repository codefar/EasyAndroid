package org.greenleaf.easyandroid.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.greenleaf.easyandroid.R;
import org.greenleaf.easyandroid.bean.Fuli;

import java.util.ArrayList;
import java.util.List;

public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallAdapter.ViewHolder> {

    private Context mContext;
    private List<Fuli> mList = new ArrayList<>();
    private List<Integer> mHeights;

    public WaterFallAdapter(Context context) {
        this.mContext = context;
    }

    public void getRandomHeight(List<Fuli> mList) {
        mHeights = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            //随机的获取一个范围为200-600直接的高度
            mHeights.add((int) (300 + Math.random() * 400));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.itemView.setLayoutParams(layoutParams);

        Fuli bean = mList.get(position);
        Picasso.with(mContext).load(bean.getUrl()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public ViewHolder(View view) {
            //需要设置super
            super(view);
            mImageView = view.findViewById(R.id.imageView);
        }
    }

    public List<Fuli> getList() {
        return mList;
    }
}
