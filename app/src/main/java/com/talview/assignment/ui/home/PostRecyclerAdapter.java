package com.talview.assignment.ui.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.talview.assignment.R;
import com.talview.assignment.databinding.ItemPostListBinding;

class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.PostsViewHolder> {


    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ItemPostListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_post_list, viewGroup, false);
        return new PostsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder postsViewHolder, int position) {
        postsViewHolder.bindDataWithView(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder {

        private ItemPostListBinding binding;


        public PostsViewHolder(@NonNull ItemPostListBinding itemPostListBinding) {
            super(itemPostListBinding.getRoot());
            this.binding = itemPostListBinding;
        }

        private void bindDataWithView(int position) {

        }
    }
}
