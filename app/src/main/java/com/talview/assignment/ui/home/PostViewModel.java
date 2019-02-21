package com.talview.assignment.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.talview.assignment.database.entity.PostUser;

import java.util.List;

public class PostViewModel extends ViewModel {

    private PostRepository postRepository;
    private LiveData<List<PostUser>> postUserLiveData;

    public PostViewModel(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // get users posts
    public LiveData<List<PostUser>> getPosts() {
        if (postUserLiveData == null) {
            postUserLiveData = postRepository.getUserPosts();
        }
        return postUserLiveData;
    }

    // get error message
    public MutableLiveData<String> getError() {
        return postRepository.getError();
    }
}
