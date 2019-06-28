package xyz.aakashrivastava.onelabsdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageModel implements Parcelable{

    private int id;
    private String url;
    private String type;

    public ImageModel() {

    }

    public ImageModel(int id, String url, String type) {
        this.id = id;
        this.url = url;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(url);
        dest.writeString(type);
    }

    //constructor used for parcel
    public ImageModel(Parcel parcel){
        id = parcel.readInt();
        url = parcel.readString();
        type = parcel.readString();
    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Parcelable.Creator<ImageModel> CREATOR = new Parcelable.Creator<ImageModel>(){

        @Override
        public ImageModel createFromParcel(Parcel parcel) {
            return new ImageModel(parcel);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
}
