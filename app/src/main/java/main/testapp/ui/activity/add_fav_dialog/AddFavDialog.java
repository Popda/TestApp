package main.testapp.ui.activity.add_fav_dialog;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import main.testapp.R;
import main.testapp.core.Strings;
import main.testapp.ui.activity.add_fav_dialog.implementation.AddFavImplementation;
import main.testapp.ui.activity.add_fav_dialog.presenter.AddFavPresenter;
import main.testapp.ui.activity.add_fav_dialog.view.AddFavView;
import main.testapp.ui.activity.base.BaseActivity;

public class AddFavDialog extends BaseActivity implements AddFavView {
    @BindView(R.id.note_edit_text)
    protected AppCompatEditText noteEditText;
    @BindView(R.id.note_ok_btn)
    protected AppCompatButton okBtn;

    private AddFavPresenter presenter = new AddFavImplementation();

    @Override
    protected int getContentView() {
        return R.layout.add_note_dialog_fragment_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFinishOnTouchOutside(true);
        presenter.attachView(this);
    }

    @Override
    @OnClick(R.id.note_ok_btn)
    public void saveData() {
        presenter.saveData();
        Toast toast = Toast.makeText(getApplicationContext(), "Додано", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public String getComment() {
        return noteEditText.getText().toString();
    }

    @Override
    public String getName() {
        return getIntent().getStringExtra(Strings.NAME);
    }

    @Override
    public String getId() {
        return getIntent().getStringExtra(Strings.ID);
    }

    @Override
    public String getPDF() {
        return getIntent().getStringExtra(Strings.PDF);
    }

    @Override
    public String getPosition() {
        return getIntent().getStringExtra(Strings.POS);
    }

    @Override
    public String getPlaceOfWork() {
        return getIntent().getStringExtra(Strings.PLACEOFWORK);
    }

    @Override
    public void onBackPressed() {
        saveData();
        super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        presenter.deatachView();
        super.onDestroy();
    }
}
