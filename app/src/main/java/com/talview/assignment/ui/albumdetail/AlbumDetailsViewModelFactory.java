package com.talview.assignment.ui.albumdetail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.talview.assignment.ui.postdetails.PostDetailsRepository;
import com.talview.assignment.ui.postdetails.PostDetailsViewModel;

import javax.inject.Inject;

public class AlbumDetailsViewModelFactory implements ViewModelProvider.Factory {

    private AlbumDetailsRepository albumDetailsRepository;

    @Inject
    public AlbumDetailsViewModelFactory(AlbumDetailsRepository albumDetailsRepository) {
        this.albumDetailsRepository = albumDetailsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (AlbumDetailsViewModel.class.isAssignableFrom(modelClass)) {
            return (T) new AlbumDetailsViewModel(albumDetailsRepository);
        }
        return null;
    }
}
