package main.testapp.ui.activity.add_fav_dialog.view;

import main.testapp.ui.activity.base.view.BaseView;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public interface AddFavView extends BaseView{
    void saveData();
    String getComment();
    String getName();
    String getId();
    String getPDF();
    String getPosition();
    String getPlaceOfWork();



}
