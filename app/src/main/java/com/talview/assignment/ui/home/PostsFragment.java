package com.talview.assignment.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talview.assignment.R;
import com.talview.assignment.application.MyApp;
import com.talview.assignment.database.entity.PostEntity;
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setUpDi();

        binding = FragmentPostsBinding.inflate(inflater, container, false);

        setUpRecyclerView();

        viewModel = ViewModelProviders.of(this, factory).get(PostViewModel.class);

        viewModel.getPosts().observe(getActivity(), new Observer<List<PostUser>>() {
            @Override
            public void onChanged(@Nullable List<PostUser> postEntities) {

            }
        });

        return binding.getRoot();
    }

    private void setUpDi() {
        DaggerPostComponent.builder().applicationComponent(MyApp.getApplicationComponent()).build().inject(this);
    }

    private void setUpRecyclerView() {

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bd_recycler_divider));
        binding.recyclerPosts.addItemDecoration(dividerItemDecoration);
        binding.recyclerPosts.setAdapter(new PostRecyclerAdapter());

    }
}
