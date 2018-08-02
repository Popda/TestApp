package main.testapp.ui.activity.main.presenter;

import main.testapp.ui.activity.base.presenter.BasePresenter;
import main.testapp.ui.activity.main.view.MainView;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public interface MainPresenter extends BasePresenter<MainView> {
    void showFavorite();
}
