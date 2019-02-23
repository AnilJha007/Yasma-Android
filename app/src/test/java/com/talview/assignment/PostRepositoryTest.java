package com.talview.assignment;

import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.content.res.Resources;

import com.talview.assignment.database.DBManager;
import com.talview.assignment.database.dao.PostDao;
import com.talview.assignment.database.dao.UserDao;
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
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        BaseSchedulerProvider schedulerProvider = new TestingSchedulerProvider();

        postRepository = Mockito.spy(new PostRepository(apiInterface, dbManager, context,
                internetUtil, schedulerProvider));

    }

    @Test
    public void getSuccessfulPostData() {

        Mockito.doReturn(true).when(internetUtil).isNetworkAvailable();


    }

    @Test
    public void getErrorData() {

        Mockito.doReturn(true).when(internetUtil).isNetworkAvailable();

    }

    @Test
    public void getInternetError() {

        Mockito.doReturn(false).when(internetUtil).isNetworkAvailable();

        Resources resources = Mockito.mock(Resources.class);

        Mockito.doReturn(resources).when(context).getResources();

        Mockito.doReturn("no internet error").when(resources).getString(R.string.internet_not_available);

    }
}
