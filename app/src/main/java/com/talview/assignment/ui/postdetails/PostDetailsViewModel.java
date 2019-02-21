package com.talview.assignment.ui.postdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.talview.assignment.database.entity.CommentEntity;
import com.talview.assignment.database.entity.PostUser;

import java.util.List;

public class PostDetailsViewModel extends ViewModel {

    private PostDetailsRepository postDetailsRepository;
    private LiveData<PostUser> postUserLiveData;
    LiveData<List<CommentEntity>> commentsLiveData;

    public PostDetailsViewModel(PostDetailsRepository postDetailsRepository) {
        this.postDetailsRepository = postDetailsRepository;
    }

    // get error message
    public MutableLiveData<String> getError() {
        return postDetailsRepository.getErrorMsg();
    }

    // get post details
    public LiveData<PostUser> getPostDetails(int userId, int postId) {
        if (postUserLiveData == null) {
            postUserLiveData = postDetailsRepository.getPostDetails(userId, postId);
        }
        return postUserLiveData;
    }

    // get loading state
    public MutableLiveData<Boolean> getIsLoading() {
        return postDetailsRepository.getIsLoading();
    }

    // get comments
    public LiveData<List<CommentEntity>> getComments(int postId) {
        if (commentsLiveData == null) {
            commentsLiveData = postDetailsRepository.getComments(postId);
        }
        return commentsLiveData;
    }
}
