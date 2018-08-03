package main.testapp.ui.activity.main.presenter;

import android.content.Context;

import main.testapp.ui.activity.base.presenter.BasePresenter;
import main.testapp.ui.activity.main.view.MainView;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public interface MainPresenter extends BasePresenter<MainView> {
    void showFavorite();
    void getDeclarations(String searchName, Context context);
    boolean isNetworkConnected(Context context);
}
