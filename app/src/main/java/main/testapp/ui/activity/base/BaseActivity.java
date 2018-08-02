package main.testapp.ui.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import main.testapp.ui.activity.base.view.BaseView;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView{
    protected abstract int getContentView();

    private Unbinder unbinder;

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
