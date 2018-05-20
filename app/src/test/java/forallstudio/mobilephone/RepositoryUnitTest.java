package forallstudio.mobilephone;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.MobileImage;
import forallstudio.mobilephone.data.source.IMobileRepository;
import io.reactivex.Completable;
import io.reactivex.Flowable;

import static junit.framework.Assert.assertEquals;

public class RepositoryUnitTest {

    @Mock
    private IMobileRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMobileImage_ifSuccess_returnMobileImageList() {
        List<MobileImage> mobiles = new ArrayList<>();
        MobileImage mobileImage = new MobileImage();
        mobileImage.setId(1);
        mobiles.add(mobileImage);

        Mockito.when(repository.getMobileImages(1))
                .thenReturn(Flowable.just(mobiles));

        List<MobileImage> mobileImages = repository.getMobileImages(1)
                .blockingSingle();

        assertEquals(1, mobileImages.size());
    }

    @Test
    public void saveOrUpdate_ifSuccess_returnCompletable() {
        Mobile mobile = new Mobile();
        Mockito.when(repository.saveOrUpdateMobile(mobile))
                .thenReturn(Completable.complete());

        Completable completable = repository.saveOrUpdateMobile(mobile);

        assertEquals(Completable.complete(), completable);
    }


    @Test
    public void deleteMobileFavorite_ifSuccess_returnCompletable() {
        Mockito.when(repository.deleteMobileFavorite(1))
                .thenReturn(Completable.complete());

        Completable completable = repository.deleteMobileFavorite(1);

        Assert.assertEquals(Completable.complete(), completable);
    }
}