package com.xiaojiu.studylibs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.myselfview.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public  class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.MyViewHolder> {

    private List mlist;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;


    @Override
    public int getItemViewType(int position) {
        //KLog.e(position);
        return super.getItemViewType(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
   /*     switch (viewType) {
            case TYPE_FOOTER:
                break;
            case TYPE_ITEM:
            default:
                getItemViewHolder(parent);
                break;
        }*/
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_base, parent,
                false);
        return new MyViewHolder(inflate);
    }

   // public abstract RecyclerView.ViewHolder getItemViewHolder(ViewGroup parent);

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mPartTvStatues.setText(mlist.get(position) + "");
    }

    public void setList(List mlist) {
        this.mlist = mlist;
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView2)
        TextView mPartTvStatues;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
