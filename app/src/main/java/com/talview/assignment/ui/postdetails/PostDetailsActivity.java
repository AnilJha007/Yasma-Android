package com.talview.assignment.ui.postdetails;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.talview.assignment.R;
import com.talview.assignment.application.MyApp;
import com.talview.assignment.databinding.ActivityPostDetailsBinding;
import com.talview.assignment.ui.postdetails.di.DaggerPostDetailsComponent;
import com.talview.assignment.utils.ConstantUtil;

import javax.inject.Inject;

public class PostDetailsActivity extends AppCompatActivity {

    @Inject
    PostDetailsViewModelFactory factory;

    private ActivityPostDetailsBinding binding;
    private PostDetailsViewModel viewModel;
    private int postId, userId;
    private CommentsRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpDI();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_details);

        // get intent arguments from previous activity
        postId = getIntent().getIntExtra(ConstantUtil.POST_ID, 0);
        userId = getIntent().getIntExtra(ConstantUtil.USER_ID, 0);

        viewModel = ViewModelProviders.of(this, factory).get(PostDetailsViewModel.class);

        setUpToolbar();

        setUpCommentRecyclerView();

        observeData();

    }

    private void setUpCommentRecyclerView() {
        adapter = new CommentsRecyclerAdapter();
        binding.recyclerComments.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerComments.setAdapter(adapter);
    }

    private void setUpToolbar() {

        setSupportActionBar(binding.toolbar.toolbarCommon);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.post_details));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUpDI() {

        DaggerPostDetailsComponent.builder().applicationComponent(MyApp.getApplicationComponent()).build().inject(this);
    }

    private void observeData() {

        // get user details
        viewModel.getPostDetails(userId, postId).observe(this, postUser -> binding.setPostDetails(postUser));

        // get error message
        viewModel.getError().observe(this, errorMessage -> Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show());

        // get loading state
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading != null && isLoading)
                binding.tvLoading.setVisibility(View.VISIBLE);
            else
                binding.tvLoading.setVisibility(View.GONE);
        });

        // get comments data
        viewModel.getComments(postId).observe(this, comments -> adapter.setData(comments));

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
}
