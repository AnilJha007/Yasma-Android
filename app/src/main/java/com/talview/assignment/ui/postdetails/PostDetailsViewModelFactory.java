package com.talview.assignment.ui.postdetails;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class PostDetailsViewModelFactory implements ViewModelProvider.Factory {

    private PostDetailsRepository postDetailsRepository;

    @Inject
    public PostDetailsViewModelFactory(PostDetailsRepository postDetailsRepository) {
        this.postDetailsRepository = postDetailsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (PostDetailsViewModel.class.isAssignableFrom(modelClass)) {
            return (T) new PostDetailsViewModel(postDetailsRepository);
        }
        return null;
    }
}
