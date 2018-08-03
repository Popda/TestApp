package main.testapp.core.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public class Item {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("placeOfWork")
    @Expose
    private String placeOfWork;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("linkPDF")
    @Expose
    private String linkPDF;

    public Item(String id, String firstname, String lastname, String placeOfWork, String position, String linkPDF) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.placeOfWork = placeOfWork;
        this.position = position;
        this.linkPDF = linkPDF;
    }

    public String getId() {
        return id;
    }

    public String getLinkPDF() {
        return linkPDF;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public String getPosition() {
        return position;
    }
}
