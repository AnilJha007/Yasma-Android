package com.talview.assignment.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.talview.assignment.database.entity.AlbumUser;

import java.util.List;

public class AlbumViewModel extends ViewModel {

    private AlbumRepository albumRepository;
    private LiveData<List<AlbumUser>> albumUserLiveData;

    public AlbumViewModel(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    // get albums data here
    public LiveData<List<AlbumUser>> getAlbums() {
        if (albumUserLiveData == null) {
            albumUserLiveData = albumRepository.getUserAlbums();
        }
        return albumUserLiveData;
    }

    // get error data here
    public MutableLiveData<String> getError() {
        return albumRepository.getErrorMsg();
    }
}
