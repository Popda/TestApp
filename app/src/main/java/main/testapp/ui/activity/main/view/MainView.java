package main.testapp.ui.activity.main.view;

import java.util.List;

import main.testapp.core.model.Item;
import main.testapp.ui.activity.base.view.BaseView;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public interface MainView extends BaseView {
    void searchNewItems();
    void showFavorite();
    void searchItemsIsReady(List<Item> items);
}
