package main.testapp.ui.activity.add_fav_dialog.implementation;

import main.testapp.core.model.SaveModel;
import main.testapp.ui.activity.add_fav_dialog.presenter.AddFavPresenter;
import main.testapp.ui.activity.add_fav_dialog.view.AddFavView;
import main.testapp.ui.activity.base.implementation.BaseImplementation;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public class AddFavImplementation extends BaseImplementation<AddFavView> implements AddFavPresenter {
    @Override
    public void saveData() {
        SaveModel noteModel = new SaveModel();
        noteModel.setText(view.getComment());
        noteModel.setId(view.getId());
        noteModel.setLinkPDF(view.getPDF());
        noteModel.setName(view.getName());
        noteModel.setPlaceOfWork(view.getPlaceOfWork());
        noteModel.setPosition(view.getPosition());
        noteModel.save();

        view.finish();
    }
}
