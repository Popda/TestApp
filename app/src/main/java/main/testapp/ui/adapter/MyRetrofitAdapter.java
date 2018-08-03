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
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import main.testapp.R;
import main.testapp.core.Strings;
import main.testapp.core.model.Item;
import main.testapp.core.model.SaveModel;
import main.testapp.ui.activity.add_fav_dialog.AddFavDialog;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public class MyRetrofitAdapter extends RecyclerView.Adapter<MyRetrofitAdapter.ViewHolder> {
    private List<Item> items;
    private Context mContext ;

    public MyRetrofitAdapter(Context mContext, List<Item> items){
        this.mContext = mContext;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_adapter_item, parent, false);
        ViewHolder holder = new ViewHolder(itemView, new ViewHolder.MyClickListener() {
            @Override
            public void onAddToFav(int p) {
                if (Realm.getDefaultInstance().where(SaveModel.class).equalTo("id", items.get(p).getId()).findFirst()==null){
                    Intent intent = new Intent(parent.getContext(), AddFavDialog.class);
                    intent.putExtra(Strings.NAME,items.get(p).getLastname()+" "+items.get(p).getFirstname());
                    intent.putExtra(Strings.PLACEOFWORK, items.get(p).getPlaceOfWork());
                    intent.putExtra(Strings.ID, items.get(p).getId());
                    intent.putExtra(Strings.POS, items.get(p).getPosition());
                    intent.putExtra(Strings.PDF, items.get(p).getLinkPDF());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    mContext.startActivity(intent);
                } else {
                    Realm.getDefaultInstance().beginTransaction();
                    Realm.getDefaultInstance().where(SaveModel.class).equalTo("id", items.get(p).getId()).findFirst().deleteFromRealm();
                    Realm.getDefaultInstance().commitTransaction();
                    Toast.makeText(mContext,"Видалено",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPDFopen(int p) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(items.get(p).getLinkPDF()),"application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String fulName = items.get(position).getLastname() + " " + items.get(position).getFirstname();
        holder.nameTextview.setText(fulName);
        holder.placeOfWorkTextview.setText(Strings.PLACE+items.get(position).getPlaceOfWork());
        holder.positionTextview.setText(Strings.POSITION+items.get(position).getPosition());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.name_textview)
        AppCompatTextView nameTextview;
        @BindView(R.id.position_textview)
        AppCompatTextView positionTextview;
        @BindView(R.id.placeOfWork_textview)
        AppCompatTextView placeOfWorkTextview;
        @BindView(R.id.star_btn)
        AppCompatImageButton starBtn;
        @BindView(R.id.book_btn)
        AppCompatImageButton bookBtn;

        MyClickListener listener;

        ViewHolder(View view, MyClickListener listener) {
            super(view);
            this.listener=listener;
            ButterKnife.bind(this, view);
            starBtn.setOnClickListener(this);
            bookBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.star_btn:
                    listener.onAddToFav(this.getLayoutPosition());
                    break;
                case R.id.book_btn:
                    listener.onPDFopen(this.getLayoutPosition());
                    break;
            }
        }

        public interface MyClickListener {
            void onAddToFav(int p);
            void onPDFopen(int p);
        }
    }
}
