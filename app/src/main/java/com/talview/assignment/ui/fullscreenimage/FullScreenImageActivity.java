package com.talview.assignment.ui.fullscreenimage;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.squareup.picasso.Picasso;
import com.talview.assignment.R;
import com.talview.assignment.databinding.ActivityFullScreenImageBinding;
import com.talview.assignment.utils.ConstantUtil;

public class FullScreenImageActivity extends AppCompatActivity {

    private ActivityFullScreenImageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_full_screen_image);

        String imageUrl = getIntent().getStringExtra(ConstantUtil.IMAGE_URL);

        Picasso.get().load(imageUrl).into(binding.imageView);

    }

}
