package main.testapp.core.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public class DeclarationModel {

    @SerializedName("items")
    @Expose
    private List<Item> items;

    public DeclarationModel(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}
