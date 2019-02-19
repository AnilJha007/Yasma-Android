package com.talview.assignment.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.talview.assignment.database.entity.PostEntity;

import java.util.List;

public class PostViewModel extends ViewModel {

    private PostRepository postRepository;

    public PostViewModel(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public LiveData<List<PostEntity>> getPosts() {
        return postRepository.getUserPosts();
    }
}
