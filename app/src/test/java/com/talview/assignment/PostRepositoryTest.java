package com.talview.assignment;

import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.content.res.Resources;

import com.talview.assignment.database.DBManager;
import com.talview.assignment.database.dao.PostDao;
import com.talview.assignment.database.dao.UserDao;
import com.talview.assignment.database.entity.PostEntity;
import com.talview.assignment.database.entity.UserEntity;
import com.talview.assignment.network.ApiInterface;
import com.talview.assignment.ui.home.PostRepository;
import com.talview.assignment.utils.InternetUtil;
import com.talview.assignment.utils.schedulerProvider.BaseSchedulerProvider;
import com.talview.assignment.utils.schedulerProvider.TestingSchedulerProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Observable;

public class PostRepositoryTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    private DBManager dbManager;
    @Mock
    private Application context;
    @Mock
    private ApiInterface apiInterface;
    @Mock
    private InternetUtil internetUtil;
    @Mock
    private PostRepository postRepository;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        BaseSchedulerProvider schedulerProvider = new TestingSchedulerProvider();

        postRepository = Mockito.spy(new PostRepository(apiInterface, dbManager, context,
                internetUtil, schedulerProvider));

    }

    @Test
    public void getSuccessfulPostUserData() {

        Mockito.doReturn(true).when(internetUtil).isNetworkAvailable();

        ArrayList<PostEntity> mockListPost = Mockito.mock(ArrayList.class);
        ArrayList<UserEntity> mockListUser = Mockito.mock(ArrayList.class);

        PostDao postDao = Mockito.mock(PostDao.class);
        UserDao userDao = Mockito.mock(UserDao.class);

        Mockito.doReturn(postDao).when(dbManager).getPostDao();
        Mockito.doReturn(userDao).when(dbManager).getUserDao();

        Mockito.doReturn(Observable.just(mockListPost)).when(apiInterface).getPosts();
        Mockito.doReturn(Observable.just(mockListUser)).when(apiInterface).getUsers();

        postRepository.getUserPosts();

    }

    @Test
    public void getErrorDataForPostAndUserApi() {

        Mockito.doReturn(true).when(internetUtil).isNetworkAvailable();

        PostDao postDao = Mockito.mock(PostDao.class);
        Mockito.doReturn(postDao).when(dbManager).getPostDao();

        Mockito.doReturn(0).when(postDao).getRowsCount();

        Mockito.doReturn(Observable.error(new Exception())).when(apiInterface).getPosts();

        Mockito.doReturn(Observable.error(new Exception())).when(apiInterface).getUsers();

        Resources resources = Mockito.mock(Resources.class);

        Mockito.doReturn(resources).when(context).getResources();

        Mockito.doReturn("error").when(resources).getString(R.string.error_try_later);

        postRepository.getUserPosts();

    }

    @Test
    public void getInternetError() {

        Mockito.doReturn(false).when(internetUtil).isNetworkAvailable();

        Resources resources = Mockito.mock(Resources.class);

        Mockito.doReturn(resources).when(context).getResources();

        Mockito.doReturn("no internet error").when(resources).getString(R.string.internet_not_available);

    }
}
