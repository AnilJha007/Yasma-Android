package com.talview.assignment.ui.home;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class AlbumViewModelFactory implements ViewModelProvider.Factory {

    private AlbumRepository albumRepository;

    @Inject
    public AlbumViewModelFactory(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (AlbumViewModel.class.isAssignableFrom(modelClass)) {
            return (T) new AlbumViewModel(albumRepository);
        }
        return null;
    }
}
