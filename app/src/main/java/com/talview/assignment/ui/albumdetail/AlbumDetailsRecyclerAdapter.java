package com.talview.assignment.ui.albumdetail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.talview.assignment.R;
import com.talview.assignment.database.entity.AlbumsDetailsEntity;
import com.talview.assignment.databinding.ItemAlbumDetailsListBinding;

import java.util.ArrayList;
import java.util.List;

interface OnAlbumClickListener {
    void onAlbumClicked(String imageUrl);
}

class AlbumDetailsRecyclerAdapter extends RecyclerView.Adapter<AlbumDetailsRecyclerAdapter.AlbumDetailsViewHolder> {

    private ArrayList<AlbumsDetailsEntity> albumDetails = new ArrayList<>();
    private OnAlbumClickListener onAlbumClickListener;

    @NonNull
    @Override
    public AlbumDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ItemAlbumDetailsListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_album_details_list, viewGroup, false);
        return new AlbumDetailsViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull AlbumDetailsViewHolder albumDetailsViewHolder, int position) {
        albumDetailsViewHolder.bindDataWithView(position);
    }

    @Override
    public int getItemCount() {
        return albumDetails.size();
    }

    public class AlbumDetailsViewHolder extends RecyclerView.ViewHolder {

        private ItemAlbumDetailsListBinding binding;

        public AlbumDetailsViewHolder(ItemAlbumDetailsListBinding itemAlbumDetailsListBinding) {
            super(itemAlbumDetailsListBinding.getRoot());
            binding = itemAlbumDetailsListBinding;

            // send clicked position back to associated fragment
            binding.cardParent.setOnClickListener(v -> onAlbumClickListener.onAlbumClicked(albumDetails.get(getAdapterPosition()).getUrl()));
        }

        public void bindDataWithView(int position) {
            AlbumsDetailsEntity albumsDetailsEntity = albumDetails.get(position);
            binding.setAlbumDetails(albumsDetailsEntity);
        }

    }

    public void setData(List<AlbumsDetailsEntity> albumsDetailsEntities) {

        if (albumDetails == null)
            albumDetails = new ArrayList<>();
        else
            albumDetails.clear();

        albumDetails.addAll(albumsDetailsEntities);
        notifyDataSetChanged();

    }

    public void setOnAlbumClickListener(OnAlbumClickListener onAlbumClickListener) {
        this.onAlbumClickListener = onAlbumClickListener;
    }
}
