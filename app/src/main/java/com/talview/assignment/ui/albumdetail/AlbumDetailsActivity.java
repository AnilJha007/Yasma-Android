package com.talview.assignment.ui.albumdetail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.talview.assignment.R;
import com.talview.assignment.application.MyApp;
import com.talview.assignment.databinding.ActivityAlbumDetailsBinding;
import com.talview.assignment.ui.DaggerYasmaComponent;
import com.talview.assignment.ui.fullscreenimage.FullScreenImageActivity;
import com.talview.assignment.utils.ConstantUtil;

import javax.inject.Inject;

public class AlbumDetailsActivity extends AppCompatActivity implements OnAlbumClickListener {

    @Inject
    AlbumDetailsViewModelFactory factory;

    private ActivityAlbumDetailsBinding binding;
    private int albumId, userId;
    private AlbumDetailsViewModel viewModel;
    private AlbumDetailsRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpDI();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_details);

        // get intent arguments from previous activity
        albumId = getIntent().getIntExtra(ConstantUtil.ALBUM_ID, 0);
        userId = getIntent().getIntExtra(ConstantUtil.USER_ID, 0);

        viewModel = ViewModelProviders.of(this, factory).get(AlbumDetailsViewModel.class);

        setUpToolbar();

        observeData();

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {

        adapter = new AlbumDetailsRecyclerAdapter();
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        binding.recyclerAlbumsDetails.setLayoutManager(manager);
        binding.recyclerAlbumsDetails.setAdapter(adapter);
        adapter.setOnAlbumClickListener(this);
    }

    private void observeData() {

        // observe album details
        viewModel.getAlbumDetails(albumId).observe(this, albumDetails -> {
            adapter.setData(albumDetails);
        });

        // observe error
        viewModel.getError().observe(this, errorMessage -> Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show());

        // observe loading state
        viewModel.getIsLoading().observe(this, loadingState -> {
            if (loadingState)
                binding.tvLoading.setVisibility(View.VISIBLE);
            else
                binding.tvLoading.setVisibility(View.GONE);
        });
    }

    private void setUpToolbar() {

        setSupportActionBar(binding.toolbar.toolbarCommon);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.album_details));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUpDI() {
        DaggerYasmaComponent.builder().applicationComponent(MyApp.getApplicationComponent()).build().inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAlbumClicked(String imageUrl) {
        Intent intent = new Intent(this, FullScreenImageActivity.class);
        intent.putExtra(ConstantUtil.IMAGE_URL, imageUrl);
        startActivity(intent);
    }
}
