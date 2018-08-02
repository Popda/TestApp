package main.testapp.ui.activity.main.implementation;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import main.testapp.ui.activity.base.implementation.BaseImplementation;
import main.testapp.ui.activity.favorite.Favorite;
import main.testapp.ui.activity.main.presenter.MainPresenter;
import main.testapp.ui.activity.main.view.MainView;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public class MainImplementation extends BaseImplementation<MainView> implements MainPresenter {

    @Override
    public void showFavorite() {
        Intent intent = new Intent(view.getContext(), Favorite.class);
        view.getContext().startActivity(intent);
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
