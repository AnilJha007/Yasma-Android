package com.talview.assignment.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.talview.assignment.database.entity.PostUser;

import java.util.List;

public class PostViewModel extends ViewModel {

    private PostRepository postRepository;

    public PostViewModel(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public LiveData<List<PostUser>> getPosts() {
        return postRepository.getUserPosts();
    }
}
