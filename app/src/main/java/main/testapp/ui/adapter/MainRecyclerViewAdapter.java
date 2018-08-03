package main.testapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import main.testapp.R;
import main.testapp.core.Strings;
import main.testapp.core.model.SaveModel;
import main.testapp.ui.activity.add_fav_dialog.AddFavDialog;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public class MainRecyclerViewAdapter extends RealmRecyclerViewAdapter<SaveModel, MainRecyclerViewAdapter.ViewHolder> {

    private Context context ;
    private OrderedRealmCollection<SaveModel> data;

    public MainRecyclerViewAdapter(OrderedRealmCollection<SaveModel> data, Context context) {
        super(data, true);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_adapter_item, parent, false);
        ViewHolder holder = new ViewHolder(itemView, new ViewHolder.MyClickListener() {
            @Override
            public void onDelete(int p) {
                Realm.getDefaultInstance().beginTransaction();
                data.get(p).deleteFromRealm();
                Realm.getDefaultInstance().commitTransaction();
            }

            @Override
            public void onPDFopen(int p) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(data.get(p).getLinkPDF()),"application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                context.startActivity(intent);
            }

            @Override
            public void onEdit(int p) {
                Intent intent = new Intent(parent.getContext(), AddFavDialog.class);
                intent.putExtra(Strings.NAME,data.get(p).getName());
                intent.putExtra(Strings.PLACEOFWORK, data.get(p).getPlaceOfWork());
                intent.putExtra(Strings.ID, data.get(p).getId());
                intent.putExtra(Strings.POS, data.get(p).getPosition());
                intent.putExtra(Strings.PDF, data.get(p).getLinkPDF());
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                Realm.getDefaultInstance().beginTransaction();
                data.get(p).deleteFromRealm();
                Realm.getDefaultInstance().commitTransaction();
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SaveModel noteModel = getItem(position);
        if (noteModel == null)
            return;

        holder.nameTextview.setText(noteModel.getName());
        holder.placeOfWorkTextview.setText(Strings.PLACE+noteModel.getPlaceOfWork());
        holder.positionTextview.setText(Strings.POSITION+noteModel.getPosition());
        holder.noteTextView.setText(noteModel.getText());
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.name_textview2)
        AppCompatTextView nameTextview;
        @BindView(R.id.position_textview2)
        AppCompatTextView positionTextview;
        @BindView(R.id.placeOfWork_textview2)
        AppCompatTextView placeOfWorkTextview;
        @BindView(R.id.delete_btn)
        AppCompatImageButton starBtn;
        @BindView(R.id.book2_btn)
        AppCompatImageButton bookBtn;
        @BindView(R.id.edit_comment_btn)
        AppCompatImageButton editComment;
        @BindView(R.id.note_textview)
        AppCompatTextView noteTextView;

        MyClickListener listener;

        ViewHolder(View view, MyClickListener listener) {
            super(view);
            this.listener=listener;
            ButterKnife.bind(this, view);
            starBtn.setOnClickListener(this);
            bookBtn.setOnClickListener(this);
            editComment.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.delete_btn:
                    listener.onDelete(this.getLayoutPosition());
                    break;
                case R.id.edit_comment_btn:
                    listener.onEdit(this.getLayoutPosition());
                    break;
                case R.id.book2_btn:
                    listener.onPDFopen(this.getLayoutPosition());
                    break;
            }
        }

        public interface MyClickListener {
            void onDelete(int p);
            void onPDFopen(int p);
            void onEdit(int p);
        }
    }
}
