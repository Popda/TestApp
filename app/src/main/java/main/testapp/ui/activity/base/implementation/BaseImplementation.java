package main.testapp.ui.activity.base.implementation;

import main.testapp.ui.activity.base.presenter.BasePresenter;
import main.testapp.ui.activity.base.view.BaseView;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public class BaseImplementation<V extends BaseView> implements BasePresenter<V> {
    protected V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void deatachView() {

    }
}
