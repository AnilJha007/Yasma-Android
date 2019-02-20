package com.talview.assignment.ui.postdetails.di;

import com.talview.assignment.di.ActivityScope;
import com.talview.assignment.di.ApplicationComponent;
import com.talview.assignment.ui.postdetails.PostDetailsActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface PostDetailsComponent {

    void inject(PostDetailsActivity postDetailsActivity);

}
