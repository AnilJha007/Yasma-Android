package com.talview.assignment.ui.home;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class PostViewModelFactory implements ViewModelProvider.Factory {

    private PostRepository postRepository;

    @Inject
    public PostViewModelFactory(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (PostViewModel.class.isAssignableFrom(modelClass)) {
            return (T) new PostViewModel(postRepository);
        }
        return null;
    }
}
