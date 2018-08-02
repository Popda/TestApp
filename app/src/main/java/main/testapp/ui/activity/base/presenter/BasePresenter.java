package main.testapp.ui.activity.base.presenter;

import main.testapp.ui.activity.base.view.BaseView;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public interface BasePresenter<V extends BaseView> {
    void attachView(V view);

    void deatachView();
}
