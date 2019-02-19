package com.talview.assignment.ui.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.talview.assignment.R;
import com.talview.assignment.databinding.ItemAlbumListBinding;

class AlbumRecyclerAdapter extends RecyclerView.Adapter<AlbumRecyclerAdapter.AlbumViewHolder> {


    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ItemAlbumListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_album_list, viewGroup, false);
        return new AlbumViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder albumViewHolder, int position) {
        albumViewHolder.bindDataWithView(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {

        private ItemAlbumListBinding binding;

        public AlbumViewHolder(ItemAlbumListBinding itemAlbumListBinding) {
            super(itemAlbumListBinding.getRoot());
            this.binding = itemAlbumListBinding;
        }

        public void bindDataWithView(int position) {

        }
    }
}
