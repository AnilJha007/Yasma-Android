package com.talview.assignment.ui.albumdetail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.talview.assignment.database.entity.AlbumsDetailsEntity;
import com.talview.assignment.database.entity.CommentEntity;
import com.talview.assignment.database.entity.PostUser;
import com.talview.assignment.ui.postdetails.PostDetailsRepository;

import java.util.List;

public class AlbumDetailsViewModel extends ViewModel {

    private AlbumDetailsRepository albumDetailsRepository;

    public AlbumDetailsViewModel(AlbumDetailsRepository albumDetailsRepository) {
        this.albumDetailsRepository = albumDetailsRepository;
    }

    // get error message
    public LiveData<String> getError() {
        return albumDetailsRepository.getErrorMsg();
    }

    // get loading state
    public LiveData<Boolean> getIsLoading() {
        return albumDetailsRepository.getIsLoading();
    }

    // get album details
    public LiveData<List<AlbumsDetailsEntity>> getAlbumDetails(int albumId) {
        return albumDetailsRepository.getAlbums(albumId);
    }
}
