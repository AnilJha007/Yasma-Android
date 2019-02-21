package com.talview.assignment.ui.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.talview.assignment.application.MyApp;
import com.talview.assignment.databinding.FragmentPostsBinding;
import com.talview.assignment.ui.home.di.DaggerPostComponent;
import com.talview.assignment.ui.postdetails.PostDetailsActivity;
import com.talview.assignment.utils.ConstantUtil;

import javax.inject.Inject;

public class PostsFragment extends Fragment implements ClickListener {

    @Inject
    PostViewModelFactory factory;

    private FragmentPostsBinding binding;
    private PostViewModel viewModel;
    private PostRecyclerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setUpDi();

        binding = FragmentPostsBinding.inflate(inflater, container, false);

        setUpRecyclerView();

        viewModel = ViewModelProviders.of(this, factory).get(PostViewModel.class);

        observeData();

        return binding.getRoot();
    }

    private void observeData() {

        // observe posts data here
        viewModel.getPosts().observe(getActivity(), postEntities ->
                adapter.setData(postEntities));

        // observe error data here
        viewModel.getError().observe(getActivity(), errorMessage ->
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show());
    }

    private void setUpDi() {
        DaggerPostComponent.builder().applicationComponent(MyApp.getApplicationComponent()).build().inject(this);
    }

    private void setUpRecyclerView() {

        adapter = new PostRecyclerAdapter();
        binding.recyclerPosts.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerPosts.setAdapter(adapter);
        adapter.setOnClickListener(this);

    }

    @Override
    public void clickedPosition(int clickedPosition, int userId, int postOrAlbumId) {
        Intent intentToPostDetails = new Intent(getActivity(), PostDetailsActivity.class);
        intentToPostDetails.putExtra(ConstantUtil.USER_ID, userId);
        intentToPostDetails.putExtra(ConstantUtil.POST_ID, postOrAlbumId);
        startActivity(intentToPostDetails);
    }
}
