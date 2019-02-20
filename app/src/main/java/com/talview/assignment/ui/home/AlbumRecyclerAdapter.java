package com.talview.assignment.ui.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.talview.assignment.R;
import com.talview.assignment.database.entity.AlbumUser;
import com.talview.assignment.database.entity.PostUser;
import com.talview.assignment.databinding.ItemAlbumListBinding;

import java.util.ArrayList;
import java.util.List;

class AlbumRecyclerAdapter extends RecyclerView.Adapter<AlbumRecyclerAdapter.AlbumViewHolder> {

    private ArrayList<AlbumUser> albumUsers = new ArrayList<>();

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
        return albumUsers.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {

        private ItemAlbumListBinding binding;

        public AlbumViewHolder(ItemAlbumListBinding itemAlbumListBinding) {
            super(itemAlbumListBinding.getRoot());
            this.binding = itemAlbumListBinding;
        }

        public void bindDataWithView(int position) {
            AlbumUser albumUser = albumUsers.get(position);
            binding.setAlbumUser(albumUser);
        }
    }

    public void setData(List<AlbumUser> albums) {

        if (albumUsers == null)
            albumUsers = new ArrayList<>();
        else
            albumUsers.clear();

        albumUsers.addAll(albums);
        notifyDataSetChanged();

    }
}
