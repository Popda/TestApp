package main.testapp.ui.activity.favorite.presenter;

import main.testapp.ui.activity.base.presenter.BasePresenter;
import main.testapp.ui.activity.favorite.view.FavView;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public interface FavPresenter extends BasePresenter<FavView> {
    void getAllFavs();
}
