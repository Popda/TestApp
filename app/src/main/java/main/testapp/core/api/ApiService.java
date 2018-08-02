package main.testapp.core.api;

import main.testapp.core.model.DeclarationModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public interface ApiService {
    @GET("declaration/")
    Call<DeclarationModel> getResults(@Query(value = "q", encoded = true) String searchName);

}
