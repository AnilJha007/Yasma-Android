package com.talview.assignment.ui;

import com.talview.assignment.di.CustomScope;
import com.talview.assignment.di.ApplicationComponent;
import com.talview.assignment.ui.albumdetail.AlbumDetailsActivity;
import com.talview.assignment.ui.home.AlbumsFragment;
import com.talview.assignment.ui.home.PostsFragment;
import com.talview.assignment.ui.postdetails.PostDetailsActivity;

import dagger.Component;

@CustomScope
@Component(dependencies = ApplicationComponent.class)
public interface YasmaComponent {

    void inject(AlbumsFragment albumsFragment);

    void inject(PostsFragment postsFragment);

    void inject(AlbumDetailsActivity albumDetailsActivity);

    void inject(PostDetailsActivity postDetailsActivity);

}
