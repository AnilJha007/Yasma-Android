package com.talview.assignment.ui.postdetails;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.talview.assignment.R;
import com.talview.assignment.database.entity.AlbumUser;
import com.talview.assignment.database.entity.CommentEntity;
import com.talview.assignment.databinding.ItemAlbumListBinding;
import com.talview.assignment.databinding.ItemCommentListBinding;
import com.talview.assignment.databinding.ItemPostListBinding;

import java.util.ArrayList;
import java.util.List;

class CommentsRecyclerAdapter extends RecyclerView.Adapter<CommentsRecyclerAdapter.CommentsViewHolder> {

    private ArrayList<CommentEntity> comments = new ArrayList<>();

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ItemCommentListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_comment_list, viewGroup, false);
        return new CommentsViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder commentsViewHolder, int position) {
        commentsViewHolder.bindDataWithView(position);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {

        private ItemCommentListBinding binding;

        public CommentsViewHolder(ItemCommentListBinding itemCommentListBinding) {
            super(itemCommentListBinding.getRoot());
            this.binding = itemCommentListBinding;
        }

        public void bindDataWithView(int position) {
            CommentEntity commentEntity = comments.get(position);
            binding.setComments(commentEntity);
        }
    }

    public void setData(List<CommentEntity> commentEntities) {

        if (comments == null)
            comments = new ArrayList<>();
        else
            comments.clear();

        comments.addAll(commentEntities);
        notifyDataSetChanged();

    }
}
