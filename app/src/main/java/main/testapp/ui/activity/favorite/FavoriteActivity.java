package main.testapp.ui.activity.favorite;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;
import main.testapp.R;
import main.testapp.core.model.SaveModel;
import main.testapp.ui.activity.base.BaseActivity;
import main.testapp.ui.activity.favorite.implementation.FavImplementation;
import main.testapp.ui.activity.favorite.presenter.FavPresenter;
import main.testapp.ui.activity.favorite.view.FavView;
import main.testapp.ui.adapter.MainRecyclerViewAdapter;

public class Favorite extends BaseActivity implements FavView {

    @BindView(R.id.list2)
    protected RecyclerView list;

    private FavPresenter favPresenter = new FavImplementation();

    @Override
    protected int getContentView() {
        return R.layout.activity_favirite;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favirite);
        ButterKnife.bind(this);
        favPresenter.attachView(this);
        list.setLayoutManager(new LinearLayoutManager(this));
        favPresenter.getAllFavs();
    }

    @Override
    public void showListData(RealmResults<SaveModel> models) {
        if (list.getAdapter() == null) {
            list.setAdapter(new MainRecyclerViewAdapter(models, this));
        }
    }

    @Override
    @OnClick(R.id.backBtn)
    public void Back() {
        finish();
    }

    @Override
    protected void onDestroy() {
        favPresenter.deatachView();
        super.onDestroy();
    }
}



