package forallstudio.mobilephone.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class MobileImage extends RealmObject {

    @SerializedName("url")
    private String url;
    @SerializedName("id")
    private int id;
    @SerializedName("mobile_id")
    private int mobileId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMobileId() {
        return mobileId;
    }

    public void setMobileId(int mobileId) {
        this.mobileId = mobileId;
    }

    @Override
    public String toString() {
        return "MobileImage{" +
                "url='" + url + '\'' +
                ", id=" + id +
                ", mobileId=" + mobileId +
                '}';
    }

}