package forallstudio.mobilephone.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Mobile extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("rating")
    private double rating;
    
    @SerializedName("brand")
    private String brand;

    @SerializedName("price")
    private double price;

    @SerializedName("description")
    private String description;

    @SerializedName("thumbImageURL")
    private String thumbImageUrl;

    @SerializedName("name")
    private String name;

    private boolean isFavorite;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbImageUrl() {
        return thumbImageUrl;
    }

    public void setThumbImageUrl(String thumbImageUrl) {
        this.thumbImageUrl = thumbImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return "Mobile{" +
                "rating=" + rating +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", thumbImageUrl='" + thumbImageUrl + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", isFavorite='" + isFavorite + '\'' +
                '}';
    }

}