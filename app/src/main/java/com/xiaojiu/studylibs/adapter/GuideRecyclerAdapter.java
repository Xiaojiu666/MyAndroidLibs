package com.xiaojiu.studylibs.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.myselfview.R;
import com.xiaojiu.studylibs.GuideItemBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideRecyclerAdapter extends RecyclerView.Adapter<GuideRecyclerAdapter.ViewHolder> {



    private Context mContext;
    private List<GuideItemBean> mList;
    private OnItemCliclkListener onItemClickListener;

    public GuideRecyclerAdapter(Context mContext, List<GuideItemBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_guide_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        GuideItemBean guideItemBean = mList.get(position);
        holder.mItemGuideTitleDesc.setText(guideItemBean.getTitleDesc());
        holder.mItemGuideTitleName.setText(guideItemBean.getTitleName());
        holder.mItemGuideTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_guide_title_name)
        TextView mItemGuideTitleName;
        @BindView(R.id.item_guide_title_desc)
        TextView mItemGuideTitleDesc;
        @BindView(R.id.item_guide_title)
        ConstraintLayout mItemGuideTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemCliclkListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemCliclkListener {
        void onClick(int position);
    }

}
