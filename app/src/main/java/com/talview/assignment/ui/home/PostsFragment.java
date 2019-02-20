package com.talview.assignment.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.talview.assignment.application.MyApp;
import com.talview.assignment.database.entity.PostUser;
import com.talview.assignment.databinding.FragmentPostsBinding;
import com.talview.assignment.ui.home.di.DaggerPostComponent;

import java.util.List;

import javax.inject.Inject;

public class PostsFragment extends Fragment {

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
        viewModel.getPosts().observe(getActivity(), postEntities -> {
            adapter.setData(postEntities);
        });

        // observe error data here
        viewModel.getError().observe(getActivity(), errorMessage -> {
            if (errorMessage != null)
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
        });
    }

    private void setUpDi() {
        DaggerPostComponent.builder().applicationComponent(MyApp.getApplicationComponent()).build().inject(this);
    }

    private void setUpRecyclerView() {

        adapter = new PostRecyclerAdapter();
        binding.recyclerPosts.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerPosts.setAdapter(adapter);

    }
}
