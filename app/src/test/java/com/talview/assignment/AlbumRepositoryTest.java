package com.talview.assignment;

import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.content.res.Resources;

import com.talview.assignment.database.DBManager;
import com.talview.assignment.database.dao.AlbumDao;
import com.talview.assignment.database.entity.AlbumEntity;
import com.talview.assignment.network.ApiInterface;
import com.talview.assignment.ui.home.AlbumRepository;
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

public class AlbumRepositoryTest {

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
    private AlbumRepository albumRepository;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        BaseSchedulerProvider schedulerProvider = new TestingSchedulerProvider();

        albumRepository = Mockito.spy(new AlbumRepository(apiInterface, dbManager, context,
                internetUtil, schedulerProvider));

    }

    @Test
    public void getSuccessfulAlbumData() {

        Mockito.doReturn(true).when(internetUtil).isNetworkAvailable();

        ArrayList<AlbumEntity> mockListAlbum = Mockito.mock(ArrayList.class);

        AlbumDao albumDao = Mockito.mock(AlbumDao.class);

        Mockito.doReturn(albumDao).when(dbManager).getAlbumDao();

        Mockito.doReturn(Observable.just(mockListAlbum)).when(apiInterface).getAlbums();

        albumRepository.getUserAlbums();

    }

    @Test
    public void getErrorDataForAlbumApi() {

        Mockito.doReturn(true).when(internetUtil).isNetworkAvailable();

        AlbumDao albumDao = Mockito.mock(AlbumDao.class);
        Mockito.doReturn(albumDao).when(dbManager).getAlbumDao();

        Mockito.doReturn(0).when(albumDao).getRowsCount();

        Mockito.doReturn(Observable.error(new Exception())).when(apiInterface).getAlbums();

        Resources resources = Mockito.mock(Resources.class);

        Mockito.doReturn(resources).when(context).getResources();

        Mockito.doReturn("error").when(resources).getString(R.string.error_try_later);

        albumRepository.getUserAlbums();

    }

    @Test
    public void getInternetError() {

        Mockito.doReturn(false).when(internetUtil).isNetworkAvailable();

        Resources resources = Mockito.mock(Resources.class);

        Mockito.doReturn(resources).when(context).getResources();

        Mockito.doReturn("no internet error").when(resources).getString(R.string.internet_not_available);

    }
}
