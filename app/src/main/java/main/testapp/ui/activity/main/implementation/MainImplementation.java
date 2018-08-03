package main.testapp.ui.activity.main.implementation;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import main.testapp.core.api.ApiService;
import main.testapp.core.api.DeclarationApi;
import main.testapp.core.model.DeclarationModel;
import main.testapp.core.model.Item;
import main.testapp.ui.activity.base.implementation.BaseImplementation;
import main.testapp.ui.activity.favorite.Favorite;
import main.testapp.ui.activity.main.presenter.MainPresenter;
import main.testapp.ui.activity.main.view.MainView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public class MainImplementation extends BaseImplementation<MainView> implements MainPresenter {

    private ApiService api;
    private MainView view;

    public MainImplementation(MainView view){
        this.view = view;

        if (this.api==null){
            this.api = new DeclarationApi().getClient().create(ApiService.class);
        }
    }

    @Override
    public void getDeclarations(String searchName, final Context context){
        Call<DeclarationModel> callResults = api.getResults(searchName);
        callResults.enqueue(new Callback<DeclarationModel>() {
            @Override
            public void onResponse(@NonNull Call<DeclarationModel> call, @NonNull Response<DeclarationModel> response) {
                DeclarationModel data = response.body();
                if (data!=null && response.body()!= null){
                    if (data.getItems()!=null) {
                        List<Item> items = new ArrayList<>(data.getItems());
                            view.searchItemsIsReady(items);
                        } else {
                            Toast.makeText(context, "Невіроно ведений запит",Toast.LENGTH_LONG).show();}
                }
            }

            @Override
            public void onFailure(@NonNull Call<DeclarationModel> call, @NonNull Throwable t) {
            }
        });
    }

    @Override
    public void showFavorite() {
        Intent intent = new Intent(view.getContext(), Favorite.class);
        view.getContext().startActivity(intent);
    }

    public boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
