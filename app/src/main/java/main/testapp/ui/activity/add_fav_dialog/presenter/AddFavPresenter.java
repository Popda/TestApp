package main.testapp.ui.activity.add_fav_dialog.presenter;

import main.testapp.ui.activity.add_fav_dialog.view.AddFavView;
import main.testapp.ui.activity.base.presenter.BasePresenter;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public interface AddFavPresenter extends BasePresenter<AddFavView> {
    void saveData();
}
