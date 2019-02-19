package com.talview.assignment.ui.home;

import android.arch.lifecycle.ViewModel;

public class AlbumViewModel extends ViewModel {

    private AlbumRepository albumRepository;

    public AlbumViewModel(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }
}
