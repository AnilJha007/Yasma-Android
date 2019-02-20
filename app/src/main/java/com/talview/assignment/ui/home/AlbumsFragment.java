package com.talview.assignment.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.talview.assignment.R;
import com.talview.assignment.application.MyApp;
import com.talview.assignment.database.entity.AlbumUser;
import com.talview.assignment.database.entity.PostUser;
import com.talview.assignment.databinding.FragmentAlbumsBinding;
import com.talview.assignment.ui.home.di.DaggerAlbumComponent;

import java.util.List;

import javax.inject.Inject;

public class AlbumsFragment extends Fragment {

    @Inject
    AlbumViewModelFactory factory;

    private FragmentAlbumsBinding binding;
    private AlbumViewModel viewModel;
    private AlbumRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setUpDI();

        binding = FragmentAlbumsBinding.inflate(inflater, container, false);

        setUpRecyclerView();

        viewModel = ViewModelProviders.of(this, factory).get(AlbumViewModel.class);

        observeData();

        return binding.getRoot();
    }

    private void observeData() {

        // get albums data here
        viewModel.getAlbums().observe(getActivity(), new Observer<List<AlbumUser>>() {
            @Override
            public void onChanged(@Nullable List<AlbumUser> albumUsers) {
                adapter.setData(albumUsers);
            }
        });

        // get error data here
        viewModel.getError().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String errorMessage) {
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setUpDI() {
        DaggerAlbumComponent.builder().applicationComponent(MyApp.getApplicationComponent()).build().inject(this);
    }

    private void setUpRecyclerView() {

        adapter = new AlbumRecyclerAdapter();
        binding.recyclerAlbums.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerAlbums.setAdapter(adapter);
    }
}
