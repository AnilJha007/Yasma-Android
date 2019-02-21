package com.talview.assignment.ui.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.talview.assignment.R;
import com.talview.assignment.database.entity.PostUser;
import com.talview.assignment.databinding.ItemPostListBinding;

import java.util.ArrayList;
import java.util.List;


class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.PostsViewHolder> {


    private ArrayList<PostUser> postUsers = new ArrayList<>();
    private ClickListener clickListener;

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
        return postUsers.size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder {

        private ItemPostListBinding binding;

        public PostsViewHolder(@NonNull ItemPostListBinding itemPostListBinding) {
            super(itemPostListBinding.getRoot());
            binding = itemPostListBinding;

            // send clicked position back to associated fragment
            binding.cardParent.setOnClickListener(v -> clickListener.clickedPosition(getAdapterPosition(), postUsers.get(getAdapterPosition()).getUser_id(), postUsers.get(getAdapterPosition()).getId()));

        }

        private void bindDataWithView(int position) {
            PostUser postUser = postUsers.get(position);
            binding.setPostUser(postUser);
        }

    }

    void setData(List<PostUser> posts) {

        if (postUsers == null)
            postUsers = new ArrayList<>();
        else
            postUsers.clear();

        postUsers.addAll(posts);
        notifyDataSetChanged();

    }

    void setOnClickListener(ClickListener onClickListener) {
        clickListener = onClickListener;
    }
}
