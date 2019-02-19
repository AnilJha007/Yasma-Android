package com.talview.assignment.ui.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talview.assignment.R;
import com.talview.assignment.application.MyApp;
import com.talview.assignment.databinding.FragmentAlbumsBinding;
import com.talview.assignment.ui.home.di.DaggerAlbumComponent;

import javax.inject.Inject;

public class AlbumsFragment extends Fragment {

    @Inject
    AlbumViewModelFactory factory;

    private FragmentAlbumsBinding binding;
    private AlbumViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setUpDI();

        binding = FragmentAlbumsBinding.inflate(inflater, container, false);

        setUpRecyclerView();

        viewModel = ViewModelProviders.of(this, factory).get(AlbumViewModel.class);

        return binding.getRoot();
    }

    private void setUpDI() {
        DaggerAlbumComponent.builder().applicationComponent(MyApp.getApplicationComponent()).build().inject(this);
    }

    private void setUpRecyclerView() {

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bd_recycler_divider));
        binding.recyclerAlbums.addItemDecoration(dividerItemDecoration);
        binding.recyclerAlbums.setAdapter(new AlbumRecyclerAdapter());
    }
}
