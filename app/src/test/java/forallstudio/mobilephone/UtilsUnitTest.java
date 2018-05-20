package forallstudio.mobilephone;

import org.junit.Test;

import forallstudio.mobilephone.utils.Utils;

import static org.junit.Assert.assertEquals;

public class UtilsUnitTest {

    @Test
    public void isNullOrEmpty_stringNotNullOrNotEmpty_returnFalse() {
        String str = "string";
        boolean nullOrEmpty = Utils.isNullOrEmpty(str);
        assertEquals(false, nullOrEmpty);
    }

    @Test
    public void isNullOrEmpty_stringIsNull_returnTrue() {
        String str = null;
        boolean nullOrEmpty = Utils.isNullOrEmpty(str);
        assertEquals(true, nullOrEmpty);
    }

    @Test
    public void isNullOrEmpty_stringIsEmpty_returnTrue() {
        String str = "";
        boolean nullOrEmpty = Utils.isNullOrEmpty(str);
        assertEquals(true, nullOrEmpty);
    }

    @Test
    public void convertUrlIfMissingHttpProtocol_whenUrlMissHttpOrHttps_returnCorrectHttp() {
        String imageUrl = "www.91-img.com/pictures/nokia-6-mobile-phone-hres-3.jpg";
        String urlWithHttp = Utils.convertUrlIfMissingHttpProtocol(imageUrl);
        String expected = "http://" + imageUrl;
        assertEquals(expected, urlWithHttp);
    }

    @Test
    public void convertUrlIfMissingHttpProtocol_whenUrlIsHttps_returnSameUrl() {
        String imageUrl = "https://www.91-img.com/pictures/nokia-6-mobile-phone-hres-3.jpg";
        String urlWithHttp = Utils.convertUrlIfMissingHttpProtocol(imageUrl);
        assertEquals(imageUrl, urlWithHttp);
    }

    @Test
    public void convertUrlIfMissingHttpProtocol_whenUrlIsHttp_returnSameUrl() {
        String imageUrl = "http://www.91-img.com/pictures/nokia-6-mobile-phone-hres-3.jpg";
        String urlWithHttp = Utils.convertUrlIfMissingHttpProtocol(imageUrl);
        assertEquals(imageUrl, urlWithHttp);
    }

}