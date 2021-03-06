package main.testapp.ui.activity.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import main.testapp.R;
import main.testapp.core.model.Item;
import main.testapp.ui.activity.base.BaseActivity;
import main.testapp.ui.activity.main.implementation.MainImplementation;
import main.testapp.ui.activity.main.presenter.MainPresenter;
import main.testapp.ui.activity.main.view.MainView;
import main.testapp.ui.adapter.MainRecyclerViewAdapter;
import main.testapp.ui.adapter.RetrofitAdapter;

public class MainActivity extends BaseActivity implements MainView{

    @BindView(R.id.list)
    protected RecyclerView list;
    @BindView(R.id.searchBtn)
    protected Button searchBtn;
    @BindView(R.id.searchEditText)
    protected EditText searchName;
    @BindView(R.id.favBtn)
    protected Button favBtn;
    RetrofitAdapter adapter;
    List<Item> items;

    private MainPresenter mainPresenter = new MainImplementation(this);

    @Override
    protected int getContentView() {
        return R.layout.main_activity_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter.attachView(this);
        list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    @OnClick(R.id.searchBtn)
    public void searchNewItems() {
        if (mainPresenter.isNetworkConnected(this)){
            mainPresenter.getDeclarations(searchName.getText().toString(),this);
        } else {Toast.makeText(this,"Немає з'єднання з мережею Internet",Toast.LENGTH_LONG).show();}
    }

    @Override
    @OnClick(R.id.favBtn)
    public void showFavorite() {
        mainPresenter.showFavorite();
    }

    @Override
    public void searchItemsIsReady(List<Item> items) {
            list.setAdapter(new RetrofitAdapter(this, items));
    }

    @Override
    protected void onDestroy() {
        mainPresenter.deatachView();
        super.onDestroy();
    }
}
