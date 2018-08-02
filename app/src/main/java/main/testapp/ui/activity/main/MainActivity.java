package main.testapp.ui.activity.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import main.testapp.R;
import main.testapp.core.api.ApiService;
import main.testapp.core.api.DeclarationApi;
import main.testapp.core.model.DeclarationModel;
import main.testapp.core.model.Item;
import main.testapp.ui.activity.base.BaseActivity;
import main.testapp.ui.activity.main.implementation.MainImplementation;
import main.testapp.ui.activity.main.presenter.MainPresenter;
import main.testapp.ui.activity.main.view.MainView;
import main.testapp.ui.adapter.MyRetrofitAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements MainView{

    @BindView(R.id.list)
    protected RecyclerView list;
    @BindView(R.id.searchBtn)
    protected Button searchBtn;
    @BindView(R.id.searchEditText)
    protected EditText searchName;
    @BindView(R.id.favBtn)
    protected Button favBtn;
    MyRetrofitAdapter adapter;
    List<Item> items;

    private MainPresenter mainPresenter = new MainImplementation();

    @Override
    protected int getContentView() {
        return R.layout.main_activity_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        ButterKnife.bind(this);
        mainPresenter.attachView(this);
        items = new ArrayList<>();
    }

    @Override
    @OnClick(R.id.searchBtn)
    public void searchNewItems() {
        if (MainImplementation.isNetworkConnected(this)){
            ApiService api = DeclarationApi.getClient().create(ApiService.class);
            Call<DeclarationModel> callResults = api.getResults(searchName.getText().toString());
            callResults.enqueue(new Callback<DeclarationModel>() {
                @Override
                public void onResponse(Call<DeclarationModel> call, Response<DeclarationModel> response) {
                    DeclarationModel data = response.body();
                    if (data!=null){
                        try {
                            items = new ArrayList<>();
                            items.addAll(data.getItems());
                        } catch (Exception e){
                            Toast.makeText(MainActivity.this, "Не правильний запит", Toast.LENGTH_SHORT).show();
                        }
                    }
                    list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    adapter = new MyRetrofitAdapter(MainActivity.this, items);
                    list.setAdapter(adapter);
                    list.getAdapter().notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<DeclarationModel> call, Throwable t) {
                }
            });
        } else {Toast.makeText(this,"Немає з'єднання з мережею Internet",Toast.LENGTH_LONG);}
    }

    @Override
    @OnClick(R.id.favBtn)
    public void showFavorite() {
        mainPresenter.showFavorite();
    }

    @Override
    protected void onDestroy() {
        mainPresenter.deatachView();
        super.onDestroy();
    }
}
