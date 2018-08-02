package main.testapp.ui.activity.favorite.view;

import io.realm.RealmResults;
import main.testapp.core.model.SaveModel;
import main.testapp.ui.activity.base.view.BaseView;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public interface FavView extends BaseView {
    void Back();
    void showListData(RealmResults<SaveModel> models);
}
