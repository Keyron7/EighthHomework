package com.example.eighthhomework.Recyclerview;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eighthhomework.News;
import com.example.eighthhomework.R;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private List<News>mlist;
    private ItemClickListener itemClickListener;

    public RvAdapter (List<News>mlist){
        this.mlist=mlist;

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title;
        private TextView name;
        private TextView pub;

        private ItemClickListener itemClickListener;
        ViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            pub = itemView.findViewById(R.id.publishtime);
            title = itemView.findViewById(R.id.subtitle);

            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (itemClickListener!=null){
                itemClickListener.onItemClick(v,getPosition());
            }
        }
    }
    @NonNull
    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        ViewHolder holder = new ViewHolder(view,itemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder holder, int position) {
        News news = mlist.get(position);
        holder.title.setText(news.getTitle());
        holder.pub.setText(news.getPublishTime());
        holder.name.setText(news.getName());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    public interface ItemClickListener{
        void onItemClick(View view,int position);
    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
}
