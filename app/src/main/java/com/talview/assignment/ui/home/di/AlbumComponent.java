package com.talview.assignment.ui.home.di;

import com.talview.assignment.di.FragmentScope;
import com.talview.assignment.di.ApplicationComponent;
import com.talview.assignment.ui.home.AlbumsFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = ApplicationComponent.class)
public interface AlbumComponent {

    void inject(AlbumsFragment albumsFragment);
}
