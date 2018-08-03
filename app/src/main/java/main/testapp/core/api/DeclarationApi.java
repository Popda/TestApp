package main.testapp.core.api;

import main.testapp.core.Strings;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public class DeclarationApi {
    private static Retrofit retrofit = null;

    public Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Strings.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
