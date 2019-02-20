package com.talview.assignment.ui.postdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.talview.assignment.database.entity.CommentEntity;
import com.talview.assignment.database.entity.PostUser;

import java.util.List;

public class PostDetailsViewModel extends ViewModel {

    private PostDetailsRepository postDetailsRepository;

    public PostDetailsViewModel(PostDetailsRepository postDetailsRepository) {
        this.postDetailsRepository = postDetailsRepository;
    }

    // get error message
    public LiveData<String> getError() {
        return postDetailsRepository.getErrorMsg();
    }

    // get post details
    public LiveData<PostUser> getPostDetails(int userId, int postId) {
        return postDetailsRepository.getPostDetails(userId, postId);
    }

    // get loading state
    public LiveData<Boolean> getIsLoading() {
        return postDetailsRepository.getIsLoading();
    }

    // get comments
    public LiveData<List<CommentEntity>> getComments(int postId) {
        return postDetailsRepository.getComments(postId);
    }
}
