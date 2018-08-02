package main.testapp.ui.activity.favorite.implementation;

import main.testapp.core.model.SaveModel;
import main.testapp.ui.activity.base.implementation.BaseImplementation;
import main.testapp.ui.activity.favorite.presenter.FavPresenter;
import main.testapp.ui.activity.favorite.view.FavView;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public class FavImplementation extends BaseImplementation<FavView> implements FavPresenter {
    @Override
    public void getAllFavs() {
        view.showListData(SaveModel.getAllNotes());
    }
}
