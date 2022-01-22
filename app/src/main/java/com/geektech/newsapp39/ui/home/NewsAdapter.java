package com.geektech.newsapp39.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.newsapp39.R;
import com.geektech.newsapp39.databinding.ItemNewsBinding;
import com.geektech.newsapp39.models.News;

import java.util.ArrayList;


@SuppressLint("NotifyDataSetChanged")
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

 private ArrayList<News> newsList = new ArrayList<>();
 private ItemNewsBinding binding;
 private onItemClickListener onItemClickListener;

    public void addNewsList(ArrayList<News> newsList) {
        this.newsList = newsList;
    }

    public void deleteNews(int pos){
        this.newsList.remove(pos);
        notifyItemRemoved(pos);
    }

    public News addNews(int pos){
        return newsList.get(pos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBind(newsList.get(position));
    }

    @Override
    public int getItemCount(){
        return newsList.size();
    }

    public void setOnItemClickListener(NewsAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                onItemClickListener.onItemClick(getAdapterPosition());
            });

            itemView.setOnLongClickListener(v -> {
                onItemClickListener.onLongItemClick(getAdapterPosition());
                return true;
            });
      }

        public void onBind(News news) {
            binding.titleTv.setText(news.getTitle());
            binding.dateTv.setText(String.valueOf(news.getCreatedAt()));

        }
    }

    public interface onItemClickListener{
        void onItemClick(int pos);
        void onLongItemClick(int pos);
    }
}

