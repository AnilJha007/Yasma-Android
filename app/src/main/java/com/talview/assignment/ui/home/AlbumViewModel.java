package com.talview.assignment.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.talview.assignment.database.entity.AlbumUser;

import java.util.List;

public class AlbumViewModel extends ViewModel {

    private AlbumRepository albumRepository;

    public AlbumViewModel(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public LiveData<List<AlbumUser>> getAlbums() {
        return albumRepository.getUserAlbums();
    }
}
